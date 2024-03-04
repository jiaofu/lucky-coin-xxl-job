package com.lucky.coin.service.market.impl;

import com.alibaba.fastjson.JSON;
import com.lucky.coin.dao.bean.CoinInfoBean;
import com.lucky.coin.dao.bean.MarketInfoBean;
import com.lucky.coin.dao.pool.CoinInfoDao;
import com.lucky.coin.dao.pool.MarketInfoDao;
import com.lucky.coin.service.http.HttpFromMarketInfo;
import com.lucky.coin.service.market.CollectionInfo;
import com.lucky.coin.service.util.DateUtil;
import com.lucky.coin.service.vo.http.Coin;
import com.lucky.coin.service.vo.http.CoinFullyDilutedMarketCapVo;
import com.lucky.coin.service.vo.http.CoinValueVo;
import com.lucky.coin.service.vo.http.CoinmarketcapCoinInfoVo;
import com.lucky.coin.service.vo.http.Currency;
import com.lucky.coin.service.vo.market.CoinRankVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;


@Service
@Slf4j
public class CollectionInfoImpl implements CollectionInfo {


    @Resource
    HttpFromMarketInfo httpFromMarketInfo;

    @Resource
    CoinInfoDao coinInfoDao;

    @Resource
    MarketInfoDao marketInfoDao;

    @Override
    public void checkSymbol() {

    }

    @Override
    public void initSymbol(String yBase) {
        Long day = DateUtil.getMinerDayBefore(0);
        List<String> baseCoin = Arrays.stream(yBase.split(",")).collect(Collectors.toList());
        Set<String> setCoin = baseCoin.stream().collect(Collectors.toSet());
        if(setCoin.size() - baseCoin.size() !=0){
            log.error(" getCoinValue 币种有错误 yBase  symbol 有重复 ");
            return ;
        }
        List<CoinFullyDilutedMarketCapVo> marketCapVos = getFullyMarketCap(baseCoin);
        initMarketRank(marketCapVos,day);
    }

    @Override
    public void getEveryCoinScore() {
        Long day = DateUtil.getMinerDayBefore(0);
        List<MarketInfoBean> marketInfoBeans = marketInfoDao.getAll(day);
        if(marketInfoBeans.stream().filter(q->q.getDay()-day==0).count()>0){
            log.error("  getEveryCoinScore 今日的数据已经获取到了  day : {}",day);
            writeSymbol(marketInfoBeans,day);
            return;
        }
        List<CoinInfoBean> coinInfoBeans  = coinInfoDao.getCoinInfo();

        List<String> getSymbols =  coinInfoBeans.stream().map(q->q.getSymbol()).collect(Collectors.toList());
        List<CoinFullyDilutedMarketCapVo> marketCapVos = getFullyMarketCap(getSymbols);
        initMarketRank(marketCapVos,day);
        handleCoinScoreByDay(marketCapVos,day);
        List<MarketInfoBean> dayMarketInfoBeans = marketInfoDao.getAll(day);
        writeSymbol(dayMarketInfoBeans,day);
    }

    /**
     * 显示排序的币种
     * @param marketInfoBeans
     * @param day
     */
    private void writeSymbol( List<MarketInfoBean> marketInfoBeans,Long day){
        List<CoinRankVo> coinRankVos =  marketInfoBeans.stream().filter(q->q.getDay()-day ==0)
                .map(q->{
                    CoinRankVo coinRankVo = CoinRankVo.builder()
                            .coinScore(q.getCoinScore())
                            .symbol(q.getSymbol())
                            .build();
                    return coinRankVo;
                }).sorted((o1,o2)->(o1.getCoinScore().compareTo(o2.getCoinScore()))).collect(Collectors.toList());


        StringBuilder sb = new StringBuilder();

        for( CoinRankVo vo :    coinRankVos){
            sb .append( JSON.toJSON(vo) + "\n\r ");
        }

        log.info("  writeSymbol : \n\r {}  day :{}   排序总币种数量:{}   ", sb.toString(),day,coinRankVos.size());

    }


    /**
     * 处理每日的线上数据
     * @param marketCapVos
     * @param day
     */
    public void  handleCoinScoreByDay( List<CoinFullyDilutedMarketCapVo> marketCapVos,Long day){

        List<CoinInfoBean> coinInfoBeans  = coinInfoDao.getCoinInfo();
        List<MarketInfoBean> marketInfoBeans = marketInfoDao.getAll(day);

        /**
         * 从7d 行情大到小的排序
         */
        List<CoinFullyDilutedMarketCapVo> sore =  marketCapVos.stream().sorted((o1,o2) ->o2.getPercentChange7d() .compareTo(o1.getPercentChange7d())).collect(Collectors.toList());

        List<String> lastSymbols = sore.stream().map(q->q.getSymbol()).collect(Collectors.toList());

        log.info(" handleCoinScoreByDay @@@@@@@@@@@@@@  7d 行情大到小的排序  : {}",JSON.toJSONString(lastSymbols));
        List<String> baseSymbols = coinInfoBeans.stream().map(q->q.getSymbol()).collect(Collectors.toList());

        List<String> duplicatesList = lastSymbols.stream()
                .collect(Collectors.groupingBy(s -> s))
                .entrySet()
                .stream()
                .filter(e -> e.getValue().size() > 1)
                .map(e -> e.getKey())
                .collect(Collectors.toList());

        if(duplicatesList != null && duplicatesList.size()>0){
            log.error(" handleCoinScoreByDay 币种有错误   symbol  大于基础   lastSize  :{}  基础币种:{} 重复:{} ",lastSymbols.size(),baseSymbols.size(), JSON.toJSONString(duplicatesList));
            return;
        }

        /**
         * 获取排名，用于，如果在没有获得排名。根据这些获取初始化排名
         */
       Map<String,Long>   rankMarketMap =  coinInfoBeans.stream().collect(Collectors.toMap(q->q.getSymbol(),t->t.getCoinRanking()));

        Map<String,Long>   rankMap =  getCoinRank(lastSymbols,baseSymbols);

        Map<String,Long> scoreMap = getHistoryScore(marketInfoBeans,baseSymbols);
        if(rankMap == null || scoreMap == null){
            log.error(" handleCoinScoreByDay 排名有错误 symbol:{} ",lastSymbols);
            return ;
        }

        List<MarketInfoBean> list = new ArrayList<>();
        for( CoinFullyDilutedMarketCapVo capVo :    sore){

            CoinInfoBean coinInfoBean = coinInfoBeans.stream().filter(q->q.getSymbol().equalsIgnoreCase(capVo.getSymbol())).findFirst().orElse(null);

            MarketInfoBean marketInfoBean = getInitMarketInfoBean(coinInfoBean,day);

            marketInfoBean.setPrice(capVo.getPrice());
            marketInfoBean.setPercentChange7d(capVo.getPercentChange7d());
            marketInfoBean.setPercentChange24h(capVo.getPercentChange24h());
            marketInfoBean.setPercentChange30d(capVo.getPercentChange30d());
            marketInfoBean.setFullyDilutedMarketCap(capVo.getFullyDilutedMarketCap());
            marketInfoBean.setLastUpdated(capVo.getLastUpdated());

            Long rank = rankMap.get(capVo.getSymbol());
            Long score = scoreMap.get(capVo.getSymbol());
            Long marketRank = rankMarketMap.get(capVo.getSymbol());
            if(marketInfoBeans.size()==0){
                score = 0L;
            }

            Long sum = rank +score+marketRank;
            marketInfoBean.setCoinRanking( new Long(rank));
            marketInfoBean.setCoinScore(sum);
            list.add(marketInfoBean);
        }


        Integer batchNum = marketInfoDao.batchInsert(list);
        log.info("  handleCoinScoreByDay   day :{}  batchNum : {} ",day,batchNum);
    }

    /**
     * 初始化市场行请数据
     * @param marketCapVos
     * @param day
     */
    public void initMarketRank(  List<CoinFullyDilutedMarketCapVo> marketCapVos, Long day){
        List<CoinFullyDilutedMarketCapVo> sore =  marketCapVos.stream().sorted((o1,o2) ->o2.getFullyDilutedMarketCap().compareTo(o1.getFullyDilutedMarketCap())).collect(Collectors.toList());
        List<String>  initCoins = sore.stream().map(q->q.getSymbol()).collect(Collectors.toList());
        log.info(" initMarketRank @@@@ 市值 排序   {} ",initCoins.stream().collect(Collectors.joining(",")));

        List<CoinInfoBean> list = new ArrayList<>();
        Integer rankCount = sore.size() ;
        for(CoinFullyDilutedMarketCapVo vo : sore){
            CoinInfoBean coinInfoBean = CoinInfoBean.builder()
                    .day(day)
                    .symbol(vo.getSymbol())
                    .name(vo.getName())
                    .slug(vo.getSlug())
                    .marketId(vo.getMarketId())
                    .maxSupply(vo.getMaxSupply())
                    .totalSupply(vo.getTotalSupply())
                    .fullyDilutedMarketCap(vo.getFullyDilutedMarketCap())
                    .coinRanking(new Long( rankCount))
                    .build();

            rankCount = rankCount -1;
            list.add(coinInfoBean);
        }

        Integer count = coinInfoDao.batchInsert(list);
        log.info(" initMarketRank coinInfo 插入的数据 num : {} ",count);
    }



    /**
     * 组装http的数据
     * @param baseCoin
     * @return
     */
    private List<CoinFullyDilutedMarketCapVo> getFullyMarketCap( List<String> baseCoin){
        try {

            List<CoinFullyDilutedMarketCapVo> list = new ArrayList<>();
            CoinmarketcapCoinInfoVo coinInfoVo =   httpFromMarketInfo.getCoinInfoByList(baseCoin);
            for( String str :  baseCoin){

                if(coinInfoVo == null){
                    log.info(" getFullyMarketCap symbol :{}  null",str);
                }
                Coin coin = coinInfoVo.getData().get(str.toUpperCase());
                Currency currency = coin.getQuote().getUSD();
                CoinFullyDilutedMarketCapVo vo = new CoinFullyDilutedMarketCapVo();
                vo.setFullyDilutedMarketCap(currency.getFully_diluted_market_cap());

                vo.setMarketId(coin.getId());
                vo.setName(coin.getName());
                vo.setSymbol(coin.getSymbol());
                vo.setSlug(coin.getSlug());
                vo.setMaxSupply(coin.getTotal_supply());
                vo.setTotalSupply(coin.getMax_supply());

                //
                vo.setPrice(currency.getPrice());
                vo.setPercentChange7d(currency.getPercent_change_7d());
                vo.setPercentChange30d(currency.getPercent_change_30d());
                vo.setPercentChange24h(currency.getPercent_change_24h());

               Date lastUpdate =  DateUtil.getISO8601TimeDate(currency.getLast_updated());
                vo.setLastUpdated(lastUpdate);
                list.add(vo);
            }
            return list;

        }catch (Exception ex){
            log.error(" getFullyMarketCap  异常 ",ex);
        }
        return null;

    }



    @Override
    public void initMarketHistory(String symbols, Long day) {

        symbols = symbols.toUpperCase();

        List<CoinInfoBean> coinInfoBeans  = coinInfoDao.getCoinInfo();
        List<MarketInfoBean> marketInfoBeans = marketInfoDao.getAll(day);
        List<String> baseSymbols =  coinInfoBeans.stream().map(q->q.getSymbol()).collect(Collectors.toList());
        List<String> lastSymbols = Arrays.stream(symbols.split(",")).collect(Collectors.toList());
        for( String str :     lastSymbols){
            if(!baseSymbols.contains(str.toUpperCase())){
                log.error(" initMarketHistory 币种有错误   symbol 在 baseCoin 不存在 : {} ",str);
                return;
            };
        }




        List<String> duplicatesList = lastSymbols.stream()
                .collect(Collectors.groupingBy(s -> s))
                .entrySet()
                .stream()
                .filter(e -> e.getValue().size() > 1)
                .map(e -> e.getKey())
                .collect(Collectors.toList());

        if(duplicatesList != null && duplicatesList.size()>0){
            log.error(" initMarketHistory 币种有错误   symbol  大于基础   lastSize  :{}  基础币种:{} 重复:{} ",lastSymbols.size(),baseSymbols.size(), JSON.toJSONString(duplicatesList));
            return;
        }

        Map<String,Long>   rankMarketMap =  coinInfoBeans.stream().collect(Collectors.toMap(q->q.getSymbol(),t->t.getCoinRanking()));

        Map<String,Long>   rankMap =  getCoinRank(lastSymbols,baseSymbols);

        Map<String,Long> scoreMap = getHistoryScore(marketInfoBeans,baseSymbols);
        if(rankMap == null || scoreMap == null){
            log.error(" initMarketHistory 排名有错误 symbol:{} ",symbols);
            return ;
        }

        List<MarketInfoBean> list = new ArrayList<>();
        for( String symbol :    baseSymbols){
            CoinInfoBean coinInfoBean = coinInfoBeans.stream().filter(q->q.getSymbol().equalsIgnoreCase(symbol)).findFirst().orElse(null);

            MarketInfoBean marketInfoBean = getInitMarketInfoBean(coinInfoBean,day);

            Long rank = rankMap.get(symbol);
            Long score = scoreMap.get(symbol);
            Long marketRank = rankMarketMap.get(symbol);
            if(marketInfoBeans.size()==0){
                score = 0L;
            }

            Long sum = rank +score+marketRank;
            marketInfoBean.setCoinRanking( new Long(rank));
            marketInfoBean.setCoinScore(sum);
            list.add(marketInfoBean);
        }


        Integer batchNum = marketInfoDao.batchInsert(list);
        log.info("  initMarketHistory   day :{}  batchNum : {} ",day,batchNum);
    }

    @Override
    public void initMarketHistory(Long day) {
        if(day < 20240301L){
            log.error(" initMarketHistory 初始化程序 错误 ");
        }
        List<MarketInfoBean> marketInfoBeans = marketInfoDao.getByDay(day);
        String  markCoin =  marketInfoBeans.stream().sorted((o1,o2) ->o1.getCoinRanking().compareTo(o2.getCoinRanking())).map(q->q.getSymbol()).collect(Collectors.joining(","));
        log.info(" initMarketHistory 获取的数据 ： {}  ",markCoin);
        initMarketHistory(markCoin,day);
    }

    private MarketInfoBean getInitMarketInfoBean(CoinInfoBean coinInfoBean,Long day){
        MarketInfoBean marketInfoBean = MarketInfoBean.builder()
                .coinId(coinInfoBean.getId())
                .day(day)
                .lastUpdated(new Date())
                .fullyDilutedMarketCap(BigDecimal.ZERO)
                .percentChange7d(BigDecimal.ZERO)
                .percentChange24h(BigDecimal.ZERO)
                .percentChange30d(BigDecimal.ZERO)
                .symbol(coinInfoBean.getSymbol())
                .price(BigDecimal.ZERO)
                .build();
        return marketInfoBean;
    }

    /**
     * coin排名与得分
     * @param yList
     * @param
     */
    private Map<String,Long>   getCoinRank(List<String> yList, List<String> baseCoin){

        Map<String,Long> map = new LinkedHashMap<>();

        for(Long i=0L;i<yList.size(); i++    ){


            String name = yList.get(Integer.parseInt(String.valueOf(i))).toUpperCase();
            if(!baseCoin.contains(name)){
                log.error(" getCoinValue 币种没有包含 : {} ",name);
                return null;
            }

            map.put(name, i+1);
        }
        // 如果币种没有的话，那就按照btc的分数
        for(int i=0;i<baseCoin.size(); i++    ){

            String baseName = baseCoin.get(i).toUpperCase();

            Long rank =   map.get(baseName.toUpperCase());
            if(rank == null){
                 rank   = map.get("BTC");
            }
            map.put(baseName, rank);
        }
        return map;

    }

    /**
     * 获取历史得分
     * @param baseCoin
     * @param markets
     * @return
     */
    private Map<String,Long> getHistoryScore( List<MarketInfoBean> markets,List<String> baseCoin){

        Map<String,Long> map = new LinkedHashMap<>();
        Map<Long,List<MarketInfoBean>> marketMap =  markets.stream().collect(Collectors.groupingBy(q->q.getDay()));
        for( Map.Entry<Long, List<MarketInfoBean>>  entry :    marketMap.entrySet()){

          Map<String,Long> entryMap =  entry.getValue().stream().collect(Collectors.toMap(q->q.getSymbol(),t->t.getCoinRanking(),(o1,o2)->o1));

            for(int i=0;i<baseCoin.size(); i++    ){

                String baseName = baseCoin.get(i).toUpperCase();

                Long rank =   entryMap.get(baseName);
                if(rank == null){
                    // 获取当天btc的数据
                    rank   = entryMap.get("BTC");
                }

                Long value = map.get(baseName);
                if(value == null){
                    value = 0L;
                }
               Long coinScore = value + rank;

                map.put(baseName, coinScore);
            }

        }
        return map;
    }

    /**
     * 币种计算分数
     * @param yList
     * @param map
     * @param baseCoin
     * @return
     */
    private Boolean  getCoinValue(List<String> yList , Map<String,Integer> map, List<String> baseCoin){

        Map<String,Integer> yMap = new LinkedHashMap<>();

       for(int i=0;i<yList.size(); i++    ){


           String name = yList.get(i);
            if(!baseCoin.contains(name)){
                log.error(" getCoinValue 币种没有包含 : {} ",name);
                return false;
            }
            yMap.put(name, (i+1));
        }

        // 如果币种没有的话，那就按照btc的分数
        for(int i=0;i<baseCoin.size(); i++    ){

            String baseName = baseCoin.get(i);

            Integer value =   yMap.get(baseName);
            if(value == null){
                value = yMap.get("BTC");
            }

            Integer baseValue = map.get(baseName);

            Integer nowValue =  baseValue + value;

            map.put(baseName,nowValue);
        }
        return true;
    }
}
