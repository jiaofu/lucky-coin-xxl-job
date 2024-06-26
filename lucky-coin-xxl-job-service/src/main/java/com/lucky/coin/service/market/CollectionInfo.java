package com.lucky.coin.service.market;

import com.lucky.coin.dao.bean.CoinInfoBean;
import com.lucky.coin.dao.bean.MarketInfoBean;
import com.lucky.coin.service.vo.http.CoinFullyDilutedMarketCapVo;

import java.util.List;
import java.util.Map;

public interface CollectionInfo {


     public void initMarketRank(List<CoinFullyDilutedMarketCapVo> marketCapVos, Long day);

     public List<CoinFullyDilutedMarketCapVo> getFullyMarketCap( List<String> baseCoin);
     /**
      * 检查币种
      */
     void  checkSymbol();



     /**
      * 获取币种排名
      */
     void getEveryCoinScore(Integer beforeDay);


     MarketInfoBean getInitMarketInfoBean(CoinInfoBean coinInfoBean, Long day);

     /**
      * 这里是根据 rank 进行排名汇总成score
      * @param markets
      * @param baseCoin
      * @param start
      * @param end
      * @return
      */
     Map<String,Long> getHistorySumRank(List<MarketInfoBean> markets, List<String> baseCoin,Long start,Long end);

      Map<String, Long> getCoinRank(List<MarketInfoBean> markets, List<String> baseCoin, Boolean isStrictCheck);


}
