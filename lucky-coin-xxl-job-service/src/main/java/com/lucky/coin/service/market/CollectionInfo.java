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
     void getEveryCoinScore();


     MarketInfoBean getInitMarketInfoBean(CoinInfoBean coinInfoBean, Long day);
     public Map<String,Long> getHistoryScore(List<MarketInfoBean> markets, List<String> baseCoin,Long day);

      Map<String, Long> getCoinRank(List<MarketInfoBean> markets, List<String> baseCoin, Boolean isStrictCheck);


}
