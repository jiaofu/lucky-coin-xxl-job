package com.lucky.coin.service.market.impl;

import com.alibaba.fastjson.JSON;
import com.lucky.coin.dao.bean.CoinInfoBean;
import com.lucky.coin.dao.bean.MarketInfoBean;
import com.lucky.coin.dao.pool.CoinInfoDao;
import com.lucky.coin.dao.pool.MarketInfoDao;
import com.lucky.coin.service.market.CollectionInfo;
import com.lucky.coin.service.market.InitCoinScore;
import com.lucky.coin.service.util.DateUtil;
import com.lucky.coin.service.vo.http.CoinFullyDilutedMarketCapVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

import static com.lucky.coin.service.util.Constants.startDay;


@Service
@Slf4j
public class InitCoinScoreImpl implements InitCoinScore {
    @Resource
    CoinInfoDao coinInfoDao;

    @Resource
    MarketInfoDao marketInfoDao;

    @Resource
    CollectionInfo  collectionInfo;



    @Override
    public void initSymbol(String yBase) {
        Long day = DateUtil.getMinerDayBefore(0);
        List<String> baseCoin = Arrays.stream(yBase.split(",")).collect(Collectors.toList());
        Set<String> setCoin = baseCoin.stream().collect(Collectors.toSet());
        if(setCoin.size() - baseCoin.size() !=0){
            log.error(" getCoinValue 币种有错误 yBase  symbol 有重复 ");
            return ;
        }
        List<CoinFullyDilutedMarketCapVo> marketCapVos = collectionInfo.getFullyMarketCap(baseCoin);
        collectionInfo.initMarketRank(marketCapVos,day);
    }

    public void initMarketByThanDay(Long dayData){
        List<MarketInfoBean> list = new ArrayList<>();
        List<CoinInfoBean> coinInfoBeans  = coinInfoDao.getCoinInfo();
        List<String> baseSymbols =  coinInfoBeans.stream().map(q->q.getSymbol()).collect(Collectors.toList());
        List<MarketInfoBean> marketInfoBeans = marketInfoDao.getGreaterThanOrEqualTo(dayData);
        Map<Long,List<MarketInfoBean>>  map = marketInfoBeans.stream().collect(Collectors.groupingBy(q->q.getDay()));
       List<Long> allDays  =  map.keySet().stream().sorted((o1,o2)->o1.compareTo(o2)).collect(Collectors.toList());
        for( Long day  : allDays){
            List<MarketInfoBean> marketInfoBeanByDay =  map.get(day);
           List<String> lastSymbolsStr =  marketInfoBeanByDay.stream().map(q->q.getSymbol()).collect(Collectors.toList());
            for( String str :     lastSymbolsStr){
                if(!baseSymbols.contains(str.toUpperCase())){
                    log.error(" initMarketHistory 币种有错误   symbol 在 baseCoin 不存在 : {} day:{} ",str,day);
                    return;
                };
            }

            List<String> duplicatesList = lastSymbolsStr.stream()
                    .collect(Collectors.groupingBy(s -> s))
                    .entrySet()
                    .stream()
                    .filter(e -> e.getValue().size() > 1)
                    .map(e -> e.getKey())
                    .collect(Collectors.toList());

            if(duplicatesList != null && duplicatesList.size()>0){
                log.error(" initMarketHistory 币种有错误   symbol  大于基础   lastSize  :{} day:{}  基础币种:{} 重复:{} ",marketInfoBeanByDay.size(),day, baseSymbols.size(), JSON.toJSONString(duplicatesList));
                return;
            }


            /**
             * 市值得分
             */
            Map<String, Long> rankFullyDilutedMarketCapMap = coinInfoBeans.stream().collect(Collectors.toMap(q -> q.getSymbol(), t -> t.getCoinRanking()));

            /**
             * 本次得分
             */
            Map<String, Long> rankMap = collectionInfo.getCoinRank(marketInfoBeanByDay, baseSymbols, false);


            /**
             * 历史得分
             */
            Map<String,Long> scoreMap = collectionInfo.getHistorySumRank(marketInfoBeans,baseSymbols,startDay,day);

            if ((rankMap == null || scoreMap == null) && (day - startDay > 0)) {
                log.error(" initMarketHistory 排名有错误 day:{} ",day);
                return ;
            }


            for( CoinInfoBean coinInfoBean :    coinInfoBeans){


                String symbol = coinInfoBean.getSymbol();
                MarketInfoBean marketInfoBean = marketInfoBeans.stream().filter(q->q.getDay() - day==0 && q.getSymbol().equalsIgnoreCase(symbol)).findFirst().orElse(null);

                if(marketInfoBean == null){

                    log.error(" initMarketHistory 出现问题 symbol : {} day : {}",symbol,day);

                    continue;
                }
                /**
                 * 本次排名得分
                 */
                Long rank = rankMap.get(symbol);
                // 历史的分
                Long score = scoreMap.get(symbol);

                if ( (day - startDay == 0)) {
                    // 本次市值排名
                    score = rankFullyDilutedMarketCapMap.get(symbol);
                }

                Long sum = rank + score;
                marketInfoBean.setCoinRanking( new Long(rank));
                marketInfoBean.setSumCoinRanking(sum);
                marketInfoBean.setLastUpdated(new Date());
                list.add(marketInfoBean);

            }




        }
        Integer batchNum = marketInfoDao.batchInsert(list);
        log.info("  initMarketHistory  list:size :{}   batchNum : {} ",list.size(),batchNum);
    }


}
