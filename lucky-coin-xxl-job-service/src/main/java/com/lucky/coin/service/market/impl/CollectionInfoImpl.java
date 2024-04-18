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
import com.lucky.coin.service.vo.http.CoinmarketcapCoinInfoVo;
import com.lucky.coin.service.vo.http.Currency;
import com.lucky.coin.service.vo.market.CoinAllInfoVo;
import com.lucky.coin.service.vo.market.CoinRankFeeVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

import static com.lucky.coin.service.util.Constants.defaultFactor;


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
    public void getEveryCoinScore() {
        Long day = DateUtil.getMinerDayBefore(0);
        List<MarketInfoBean> marketInfoBeans = marketInfoDao.getLessThanOrEqualTo(day);
        List<CoinInfoBean> coinInfoBeans  = coinInfoDao.getCoinInfo();
        if(marketInfoBeans.stream().filter(q->q.getDay()-day==0).count()>0){
            log.error("  getEveryCoinScore 今日的数据已经获取到了  day : {}",day);
            writeSymbol(day,marketInfoBeans,coinInfoBeans);
            return;
        }


        List<String> getSymbols =  coinInfoBeans.stream().map(q->q.getSymbol()).collect(Collectors.toList());
        List<CoinFullyDilutedMarketCapVo> marketCapVos = getFullyMarketCap(getSymbols);
        initMarketRank(marketCapVos,day);
        Boolean saveMarket = handleCoinScoreByDay(marketCapVos,day);
        if(!saveMarket){
            log.error(" getEveryCoinScore  没有报错，请检查  ");
            return;
        }
        marketInfoBeans = marketInfoDao.getLessThanOrEqualTo(day);


        writeSymbol(day,marketInfoBeans,coinInfoBeans);
    }

    /**
     * 数据写的方法
     * @param day
     * @param marketInfoBeans
     * @param coinInfoBeans
     */
    private void writeSymbol(  Long day, List<MarketInfoBean> marketInfoBeans,  List<CoinInfoBean> coinInfoBeans){
      //  writeSymbolScore(marketInfoBeans,day);
        List<CoinRankFeeVo> rankFeeVos = writeSymbolRankFee(marketInfoBeans,coinInfoBeans,8);
        writeSymbolAll(rankFeeVos,marketInfoBeans,day);
    }



    /**
     * 获取币种的进步速度(这7天每天的进步)
     * @param marketInfoBeans
     * @param coinInfoBeans
     * @param
     */
    private  List<CoinRankFeeVo>  writeSymbolRankFee( List<MarketInfoBean> marketInfoBeans,  List<CoinInfoBean> coinInfoBeans,Integer takeNum ){
       Map<Long,List<MarketInfoBean>>  map = marketInfoBeans.stream().collect(Collectors.groupingBy(q->q.getDay()));
       // 8条数据， 第一条数据用户初始化，其他的7条数据用户计算速率
       List<Long> days =  map.entrySet().stream().map(q->q.getKey()).sorted(((o1, o2) -> o1.compareTo(o2))).skip(map.size()-takeNum).collect(Collectors.toList());

       Map<String,List<Long>> mapRank = new HashMap<>();

       List<CoinRankFeeVo> result = new ArrayList<>();
       for( Long day :   days){
           List<MarketInfoBean> marketInfoBeanList = map.get(day);

           Map<String,MarketInfoBean> marketRankByDay = marketInfoBeanList.stream().collect(Collectors.toMap(q->q.getSymbol(),t->t));

           // 初始化 第一版确实的数据。是获取 btc 的数据，但是99% 的币种都比不上btc。 造成新币种的优势太大了，后期尽管大跌，也可以不被列为垃圾币
           // 再次是获取 中间的币种，虽然不知道好坏。但是用中间的就是不确定性
           // MarketInfoBean initBean =  marketRankByDay.get("BTC");


           MarketInfoBean defaultMarket = getDefaultMarketInfo(marketInfoBeanList);
            for(CoinInfoBean coinInfoBean :  coinInfoBeans){
                MarketInfoBean bean =   marketRankByDay.get(coinInfoBean.getSymbol());

                if(bean == null){

                    bean = defaultMarket;
                }
                List<Long> listRank =  mapRank.get(coinInfoBean.getSymbol());
                if(listRank == null){
                    listRank = new ArrayList<>();
                }
                listRank.add(bean.getCoinRanking());
                mapRank.put(coinInfoBean.getSymbol(),listRank);

            }
       }
        // 获取速率
        for(   Map.Entry<String,List<Long>>  entity :   mapRank.entrySet()){
            String symbol = entity.getKey();
            List<Long> ranks = entity.getValue();
            Long lastRank = null;
            Long fee = 0L;
            for( Long ra :   ranks){
                if(lastRank == null){
                    lastRank = ra;
                    continue;
                }
                // 排名越小越好，所以是这次减去上次 ，然后取负值
                Long feeByDay = -(ra - lastRank);
                fee = feeByDay +fee;

                // 把上次数据给变量，用户计算
                lastRank = ra;
            }
            CoinRankFeeVo vo = CoinRankFeeVo.builder()
                    .fee(fee)
                    .symbol(symbol)
                    .build();
            result.add(vo);
        }

        // 进步越大越好
        List<CoinRankFeeVo> soreResult =    result.stream().sorted((o1,o2)->o2.getFee().compareTo(o1.getFee())).collect(Collectors.toList());


        StringBuilder sb = new StringBuilder();

        for( CoinRankFeeVo vo :    soreResult){
            sb .append( JSON.toJSONString(vo) + "\n\r ");
        }
      //  log.info("  feeSoreSymbol : \n\r {}  day :{}  币种数量:{}   \n\r  ", sb.toString(),dayNow,soreResult.size());

        return soreResult;
    }



    /**
     * 数据最后写出
     * @param rankFeeVos
     * @param marketInfoBeans
     * @param day
     */
    private void  writeSymbolAll( List<CoinRankFeeVo> rankFeeVos, List<MarketInfoBean> marketInfoBeans,Long day  ){

        List<MarketInfoBean> marketInfoBeanByDay = marketInfoBeans.stream().filter(q -> q.getDay() - day == 0).collect(Collectors.toList());
        List<CoinAllInfoVo> coinAllInfoVos = marketInfoBeanByDay.stream()
                .map(q->{
                    CoinAllInfoVo coinRankVo = new CoinAllInfoVo();
                    coinRankVo.setSymbol(q.getSymbol());
                    coinRankVo.setWeekUpFee(0L);
                    coinRankVo.setCoinScore(q.getCoinScore());
                    return coinRankVo;
                }).sorted((o1,o2)->(o1.getCoinScore().compareTo(o2.getCoinScore()))).collect(Collectors.toList());


        Double factor = getFactor(marketInfoBeanByDay);
        Map<String,Long> rankMap =  rankFeeVos.stream().collect(Collectors.toMap(q->q.getSymbol(),t->t.getFee()));

        Long perScore = (  coinAllInfoVos.get(coinAllInfoVos.size()-1).getCoinScore() -coinAllInfoVos.get(0).getCoinScore()) /
                (coinAllInfoVos.size());
        for(CoinAllInfoVo vo :    coinAllInfoVos){
            Long fee = rankMap.get(vo.getSymbol());
            if(fee ==null){
                continue;
            }
            vo.setWeekUpFee(fee);

            Long coinAllInfoVo =  new Double( vo.getCoinScore()- (fee*perScore*factor) ).longValue();
            vo.setFeeAddScore(coinAllInfoVo);
        }
        List<CoinAllInfoVo> feeAddScoreList =  coinAllInfoVos.stream().
                sorted((o1,o2)->(o1.getFeeAddScore().compareTo(o2.getFeeAddScore()))).collect(Collectors.toList());


        // 之前是取10分之9了，但是大部分币种都不跑赢btc。 98% 都是垃圾币
        // 所有应该是动态的过程,btc 跑赢大部分币种的时候，那么垃圾币种应该少
        // btc 跑输大部分币种的时候，那么垃圾币种应该多
        // 那么垃圾币一半中大部分，取factor 都是要垃圾

        /**
         *    //第一次买入池，第二区是奋进区，第三区是观察区。 第四区是准备抛售区，第五区是垃圾区, 没有获取到的币种，默认是第三区。
         * @param marketInfoBeans
         * @return
         */

        Double classFactor = factor;
        if (factor > defaultFactor) {
            classFactor = defaultFactor;
        }
        Integer baseSize = new Double(Math.ceil(coinAllInfoVos.size() * classFactor)+1).intValue();
        //Integer buy = new Double( Math.ceil( coinAllInfoVos.size()*classFactor)).intValue();
        Integer chasing = baseSize;
        Integer observe = baseSize * 2;
        Integer selling = baseSize * 3;
        Integer garbage = baseSize * 4;

        StringBuilder sb = new StringBuilder();

        sb.append("当前时间： "+ DateUtil.getMinerDayBefore(0) +" 日\n\r");

        sb.append("开始时间：20231219 日 \n\r");
        sb.append("算力因子： " + factor + " \n\r");
        sb.append("分类因子： " + classFactor + " \n\r");


//        Integer countScore = 0;
//        sb.append(" score+fee \n\r \n\r");
//        for( CoinAllInfoVo vo :    coinAllInfoVos){
//            countScore++;
//
//            if(countScore==topCoinList){
//                sb.append(" 垃圾币种 \n\r \n\r");
//            }
//            sb .append( JSON.toJSONString(vo) + "\n\r ");
//        }
//        sb.append(" score+fee 排序  ");
//        sb.append(" day  "+day);
//        sb.append("   \n\r");


        sb.append("-----------买入区币种----------- \n\r");
        Integer count = 0;
        for( CoinAllInfoVo vo :    feeAddScoreList){
            count++;


            if (count == (chasing.intValue()+1)) {
                sb.append("-----------奋进区币种----------- \n\r");
            }
            if (count == (observe.intValue()+1)) {
                sb.append("-----------观察区币种----------- \n\r");
            }

            if (count == (selling.intValue()+1)) {
                sb.append("-----------预抛售区币种----------- \n\r");
            }
            if (count == (garbage.intValue()+1)) {
                sb.append("-----------垃圾币种(立马抛售，不能犹豫)----------- \n\r");
            }

            sb .append( JSON.toJSONString(vo) + "\n\r ");

            if(vo.getSymbol().equalsIgnoreCase("BTC")){
                sb.append(" -----------btc---------- \n\r ");
            }
        }
        log.info("  writeSymbol : \n\r{}  day :{}   排序总币种数量:{}      \n\r  ", sb, day, coinAllInfoVos.size());


    }

    public static void main(String[] args) {
        int[] nums = {1, 6, 10, 27, 34, 58, 54, 5};
        int totalDifference = 0;

        for (int i = 0; i < nums.length - 1; i++) {
            int difference = nums[i] - nums[i + 1];
            totalDifference += difference;
        }
        System.out.println("The total difference is " + totalDifference);
    }
    /**
     * 处理每日的线上数据
     * @param marketCapVos
     * @param day
     */
    public Boolean  handleCoinScoreByDay( List<CoinFullyDilutedMarketCapVo> marketCapVos,Long day){

        List<CoinInfoBean> coinInfoBeans  = coinInfoDao.getCoinInfo();
        List<MarketInfoBean> marketInfoBeans = marketInfoDao.getLessThanOrEqualTo(day);

        /**
         * 从7d 行情大到小的排序
         */
        List<CoinFullyDilutedMarketCapVo> sore =  marketCapVos.stream().sorted((o1,o2) ->o2.getPercentChange7d() .compareTo(o1.getPercentChange7d())).collect(Collectors.toList());

        List<String> lastSymbolStr = sore.stream().map(q->q.getSymbol()).collect(Collectors.toList());

        log.info(" handleCoinScoreByDay @@@@@@@@@@@@@@  7d 行情大到小的排序  : {}",JSON.toJSONString(lastSymbolStr));
        List<String> baseSymbols = coinInfoBeans.stream().map(q->q.getSymbol()).collect(Collectors.toList());

        List<String> duplicatesList = lastSymbolStr.stream()
                .collect(Collectors.groupingBy(s -> s))
                .entrySet()
                .stream()
                .filter(e -> e.getValue().size() > 1)
                .map(e -> e.getKey())
                .collect(Collectors.toList());

        if(duplicatesList != null && duplicatesList.size()>0){
            log.error(" handleCoinScoreByDay 币种有错误   symbol  大于基础   lastSize  :{}  基础币种:{} 重复:{} ",lastSymbolStr.size(),baseSymbols.size(), JSON.toJSONString(duplicatesList));
            return false;
        }




        Map<String,Long> scoreMap = getHistoryScore(marketInfoBeans,baseSymbols,day);
        if( scoreMap == null){
            log.error(" handleCoinScoreByDay 排名有错误 symbol:{} ",lastSymbolStr);
            return false;
        }

        List<MarketInfoBean> list = new ArrayList<>();
        Integer rank =1;
        for( CoinFullyDilutedMarketCapVo capVo :    sore){

            CoinInfoBean coinInfoBean = coinInfoBeans.stream().filter(q->q.getSymbol().equalsIgnoreCase(capVo.getSymbol())).findFirst().orElse(null);

            MarketInfoBean marketInfoBean = getInitMarketInfoBean(coinInfoBean,day);

            marketInfoBean.setPrice(capVo.getPrice());
            marketInfoBean.setPercentChange7d(capVo.getPercentChange7d());
            marketInfoBean.setPercentChange24h(capVo.getPercentChange24h());
            marketInfoBean.setPercentChange30d(capVo.getPercentChange30d());
            marketInfoBean.setFullyDilutedMarketCap(capVo.getFullyDilutedMarketCap());
            marketInfoBean.setLastUpdated(capVo.getLastUpdated());


            Long score = scoreMap.get(capVo.getSymbol());

            Long sum = rank +score;
            marketInfoBean.setCoinRanking( new Long(rank));
            marketInfoBean.setCoinScore(sum);
            list.add(marketInfoBean);
            rank++;
        }


        Integer batchNum = marketInfoDao.batchInsert(list);
        log.info("  handleCoinScoreByDay   day :{}  batchNum : {} ",day,batchNum);
        if(batchNum>0){
            return true;
        }
        return false;
    }

    /**
     * 初始化市场行请数据
     * @param marketCapVos
     * @param day
     */
    @Override
    public void initMarketRank(  List<CoinFullyDilutedMarketCapVo> marketCapVos, Long day){
        List<CoinFullyDilutedMarketCapVo> sore =  marketCapVos.stream().sorted((o1,o2) ->o2.getFullyDilutedMarketCap().compareTo(o1.getFullyDilutedMarketCap())).collect(Collectors.toList());
        List<String>  initCoins = sore.stream().map(q->q.getSymbol()).collect(Collectors.toList());
        log.info(" initMarketRank @@@@ 市值 排序   {} ",initCoins.stream().collect(Collectors.joining(",")));

        List<CoinInfoBean> list = new ArrayList<>();

        Long marketCapRank = 1L;
        for(CoinFullyDilutedMarketCapVo vo : sore){
            Long rankCount = new Long(sore.size()) -marketCapRank;
            CoinInfoBean coinInfoBean = CoinInfoBean.builder()
                    .day(day)
                    .symbol(vo.getSymbol())
                    .name(vo.getName())
                    .slug(vo.getSlug())
                    .marketId(vo.getMarketId())
                    .maxSupply(vo.getMaxSupply())

                    .totalSupply(vo.getTotalSupply())
                    .fullyDilutedMarketCap(vo.getFullyDilutedMarketCap())
                    .coinRanking( rankCount)
                    .marketCapRank(marketCapRank)
                    .build();

            marketCapRank ++ ;

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
    public List<CoinFullyDilutedMarketCapVo> getFullyMarketCap( List<String> baseCoin){
        try {

            List<CoinFullyDilutedMarketCapVo> list = new ArrayList<>();
            CoinmarketcapCoinInfoVo coinInfoVo =   httpFromMarketInfo.getCoinInfoByList(baseCoin);
            for( String str :  baseCoin){

                if(coinInfoVo == null){
                    log.info(" getFullyMarketCap  获取错误",str);
                    return list;
                }
                Coin coin = coinInfoVo.getData().get(str.toUpperCase());
                if(coin == null ){
                    log.error(" getFullyMarketCap symbol :{}  null",str);
                }
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
    public MarketInfoBean getInitMarketInfoBean(CoinInfoBean coinInfoBean,Long day){
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
     * @param
     * @param
     */
    @Override
    public Map<String, Long> getCoinRank(List<MarketInfoBean> markets, List<String> baseCoin, Boolean isStrictCheck) {



        Map<String,Long> map = markets.stream().collect(Collectors.toMap(q->q.getSymbol(),o1->o1.getCoinRanking()));

        Long defaulRank = getRankByStr(markets);
        //每个币种必须需要排序
        for(int i=0;i<baseCoin.size(); i++    ){

            String baseName = baseCoin.get(i).toUpperCase();

            Long rank =   map.get(baseName.toUpperCase());
            if(rank == null){
                if (isStrictCheck) {
                    log.error(" getCoinValue 币种没有包含 : {} rank ", baseName.toUpperCase());
                    return null;
                } else {

                    rank = defaulRank;

                }

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
    @Override
    public Map<String,Long> getHistoryScore( List<MarketInfoBean> markets,List<String> baseCoin,Long day){

        Map<String,Long> map = new LinkedHashMap<>();
        Map<Long,List<MarketInfoBean>> marketMap =  markets.stream().filter(q->q.getDay()<day).collect(Collectors.groupingBy(q->q.getDay()));
        for( Map.Entry<Long, List<MarketInfoBean>>  entry :    marketMap.entrySet()){

          Map<String,Long> entryMap =  entry.getValue().stream().collect(Collectors.toMap(q->q.getSymbol(),t->t.getCoinRanking(),(o1,o2)->o1));

            // 初始化 第一版确实的数据。是获取 btc 的数据，但是99% 的币种都比不上btc。 造成新币种的优势太大了，后期尽管大跌，也可以不被列为垃圾币
            // 再次是获取 中间的币种，虽然不知道好坏。但是用中间的就是不确定性
            // MarketInfoBean initBean =  marketRankByDay.get("BTC");

            // 再次优化了。根据股票上二八分。20%挣钱。80%亏欠
            // 那么优化为拿到算力取4次
            //第一次买入池，第二区是奋进区，第三区是观察区。 第四区是抛售区，第五区是垃圾区, 没有获取到的币种，默认是第三区。


            MarketInfoBean defaultMarket = getDefaultMarketInfo(entry.getValue());

            for(int i=0;i<baseCoin.size(); i++    ){

                String baseName = baseCoin.get(i).toUpperCase();

                Long rank =   entryMap.get(baseName);
                if(rank == null){
                    // 获取当天的数据
                    rank = defaultMarket.getCoinRanking();
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
     * 获取 速率
     *
     * @param marketInfoBeans
     * @return
     */
    private Double getFactor(List<MarketInfoBean> marketInfoBeans) {

        List<MarketInfoBean> marketInfos = marketInfoBeans.stream().sorted((o1, o2) -> o1.getCoinScore().compareTo(o2.getCoinScore())).collect(Collectors.toList());

        int i = 0;
        for (MarketInfoBean vo : marketInfos) {
            i++;
            if (vo.getSymbol().equalsIgnoreCase("BTC")) {
                break;
            }


        }


        double factor = new Double(i) / marketInfoBeans.size();
        return factor;
    }

    /**
     * //第一次买入池，第二区是奋进区，第三区是观察区。 第四区是抛售区，第五区是垃圾区, 没有获取到的币种，默认是第三区。
     *
     * @param marketInfoBeans
     * @return
     */
    private MarketInfoBean getDefaultMarketInfo(List<MarketInfoBean> marketInfoBeans) {
        List<MarketInfoBean> marketInfos = marketInfoBeans.stream().sorted((o1, o2) -> o1.getCoinScore().compareTo(o2.getCoinScore())).collect(Collectors.toList());
        Double factor = getFactor(marketInfos);

        if (factor > defaultFactor) {
            factor = defaultFactor;
        }
        Integer index = new Double(marketInfos.size() * (factor * 2)).intValue();
        MarketInfoBean marketInfoBean = marketInfoBeans.get(index);
        return marketInfoBean;
    }

    /**
     * 根据list 获取
     *
     * @param
     * @return
     */
    private Long getRankByStr(List<MarketInfoBean> markets) {

        Integer i = 0;

        for (MarketInfoBean bean : markets) {
            i++;
            if (bean.getSymbol().equalsIgnoreCase("BTC")) {

                break;
            }

        }

        double factor = new Double(i) / markets.size();

        if (factor > defaultFactor) {
            factor = defaultFactor;
        }
        Long index = new Double(markets.size() * (factor * 2)).longValue();
        return index;
    }


}
