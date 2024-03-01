package com.lucky.coin.service.market;

public interface CollectionInfo {

     /**
      * 检查币种
      */
     void  checkSymbol();

     /**
      * 初始化币种
      */
     void  initSymbol(String yBase);

     /**
      * 获取币种排名
      */
     void getEveryCoinScore();


     /**
      * 初始化 market的历史数据
      * @param symbols
      * @param day
      */
     void initMarketHistory(String symbols,Long day);
}
