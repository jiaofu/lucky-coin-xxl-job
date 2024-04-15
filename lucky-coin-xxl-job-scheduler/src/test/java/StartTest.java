import com.lucky.coin.LuckyCoinXxlJobApplication;
import com.lucky.coin.service.market.CollectionInfo;
import com.lucky.coin.service.util.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = LuckyCoinXxlJobApplication.class)
@Slf4j
public class StartTest {



    @Resource
    CollectionInfo collectionInfo;



//    @Test
//    public void readDb(){
//        PoolCoinBean poolCoinBean = coinDao.getCoinHashInfoBean(1);
//        log.info(" PoolCoinHashInfoBean : {} ", JSON.toJSONString(poolCoinBean));
//    }

    @Test
    public void initDb(){
        String yBase ="btc,eth,sol,bnb,avax,arb,tia,sui,op,okb,tao,sei,pyth,imx,stx,alt,zeta,kas,rndr,olas,xai,inj,ldo,grt,ron,rune,wemix,bigtime,akt,fet,ar,sats,metis,ilv,shrap,iotx,nos,api3,magic,flux,lpt,mask,naka,mapo,cqt,gns,trias,ator,wifi,equ,dnx,zkf";

        collectionInfo.initSymbol(yBase);

    }


    @Test
    public void initMarketDb(){



        String y20231219 = "nos,sei,inj,trias,imx,iotx,fet,bnb,sui,grt,ron,sol,sats,tao,btc,op,wemix,arb,rndr,okb,magic,olas,kas,ldo,avax,ilv,shrap,pyth,rune,bigtime,naka,dnx";
        String y20240104 = "trias,inj,iotx,sei,nos,ron,arb,ar,ilv,magic,fet,imx,sui,avax,bnb,sol,op,btc,ldo,shrap,grt,wemix,rune,wifi,tao,okb,kas,rndr,naka,dnx,olas,pyth,bigtime,sats";
        String y20240110 = "ldo,nos,equ,inj,btc,arb,trias,okb,bnb,sei,kas,wemix,fet,sol,op,tao,ron,sui,ilv,naka,dnx,shrap,avax,rndr,olas,ar,rune,magic,imx,bigtime,grt,pyth,iotx,sats,wifi";
        String y20240111 = "arb,naka,ar,trias,magic,iotx,avax,grt,sei,fet,imx,ilv,pyth,sats,op,sui,ron,shrap,rndr,bigtime,wemix,rune,kas,ldo,inj,sol,btc,okb,bnb,dnx,tao,olas,nos,cqt,equ,wifi";
        String y20240118 = "sui,pyth,wifi,tia,tao,sei,ar,nos,ron,bigtime,zkf,magic,bnb,okb,sol,rndr,iotx,inj,eth,sats,shrap,kas,imx,avax,grt,ilv,cqt,btc,fet,wemix,dnx,op,arb,rune,ldo,naka,olas,trias,equ";
        String Y20240121 ="pyth,ron,dnx,tao,bnb,nos,rndr,okb,magic,wemix,btc,grt,iotx,eth,sol,tia,ar,ilv,sei,inj,imx,avax,cqt,rune,bigtime,fet,kas,sui,olas,naka,ldo,gns,shrap,arb,op,trias,wifi,sats,equ,zkf";
        String Y20240122 = "ron,pyth,tao,nos,dnx,rndr,bnb,iotx,okb,btc,eth,wemix,sol,grt,magic,ilv,imx,kas,ar,cqt,naka,rune,wifi,avax,inj,sei,op,gns,fet,shrap,ldo,tia,trias,arb,sats,sui,bigtime,olas,equ,zkf";
        String Y20240123 = "nos,ron,pyth,tao,dnx,rndr,bnb,okb,iotx,btc,eth,wemix,naka,cqt,grt,ilv,kas,sol,rune,ar,imx,op,magic,wifi,avax,shrap,ldo,fet,inj,trias,gns,tia,arb,sui,sats,bigtime,olas,sei,equ,zkf";
        String Y20240124 ="nos,ron,pyth,tao,dnx,bnb,btc,rndr,naka,wemix,sui,kas,ilv,iotx,tia,imx,eth,grt,okb,ar,sol,avax,fet,rune,trias,sei,gns,shrap,inj,cqt,op,bigtime,arb,ldo,olas,wifi,magic,sats,zkf,equ";
        String Y20240126 = "nos,tao,pyth,olas,ron,cqt,sui,shrap,naka,iotx,rune,dnx,ldo,trias,grt,kas,btc,imx,okb,fet,bnb,op,arb,sol,tia,avax,wemix,ar,magic,eth,gns,rndr,bigtime,ilv,inj,zkf,sei,wifi,equ,sats";
        String Y20240128 ="nos,tao,sui,trias,ron,naka,avax,ldo,kas,rndr,olas,shrap,rune,magic,sol,stx,cqt,mapo,fet,btc,iotx,imx,tia,inj,arb,sei,bigtime,grt,okb,op,gns,bnb,pyth,dnx,sats,ar,wemix,eth,zkf,wifi,ilv,equ";
        String Y20240130 = "tao,sui,trias,nos,avax,sol,olas,mapo,sei,stx,tia,rndr,magic,ron,rune,sats,naka,inj,arb,dnx,imx,ldo,op,fet,pyth,grt,bigtime,cqt,iotx,kas,okb,gns,btc,ar,wemix,bnb,ilv,eth,zkf,shrap,wifi,equ";
        String Y20240201 = "tao,sats,magic,ron,trias,rndr,sui,rune,dnx,mapo,avax,imx,sol,inj,btc,tia,pyth,olas,op,bnb,grt,eth,gns,wifi,arb,stx,sei,kas,naka,okb,bigtime,wemix,ilv,iotx,ldo,ar,fet,cqt,nos,shrap,zkf,equ";
        String Y20240217 = "lpt,nos,zeta,equ,stx,ar,wifi,api3,sei,olas,shrap,tao,kas,bigtime,naka,trias,gns,fet,iotx,sats,imx,alt,grt,eth,rndr,bnb,ron,mapo,btc,ilv,ldo,rune,magic,pyth,op,okb,avax,wemix,inj,sui,arb,sol,dnx,cqt,zkf,tia";

        String Y20240225 = "xai,nos,fet,cqt,rndr,grt,dnx,metis,alt,ator,akt,mask,ar,olas,eth,bnb,ldo,kas,ilv,stx,mapo,gns,op,rune,btc,magic,wemix,okb,ron,wifi,api3,imx,sol,arb,shrap,bigtime,trias,naka,pyth,sui,avax,inj,sei,equ,tia,tao,iotx,zeta,sats,zkf,lpt";

        String Y20240227 = "nos,olas,pyth,alt,flux,akt,rndr,metis,xai,grt,fet,wifi,bnb,stx,zeta,mapo,ldo,ilv,ator,mask,eth,rune,ar,btc,iotx,dnx,imx,naka,op,avax,sol,sei,cqt,okb,sats,wemix,shrap,equ,sui,tao,trias,arb,api3,bigtime,gns,inj,kas,magic,ron,tia,lpt,zkf";

        String Y20240228 ="nos,ar,flux,metis,ilv,akt,pyth,grt,rndr,olas,rune,inj,bnb,mapo,fet,btc,bigtime,eth,dnx,zeta,shrap,wifi,ldo,iotx,stx,sats,avax,naka,cqt,wemix,sol,api3,equ,okb,tao,ator,xai,op,imx,sei,alt,arb,zkf,mask,gns,sui,tia,kas,lpt,trias,magic,ron";
      //  collectionInfo.initMarketHistory(Y20240201,20240201L);

        collectionInfo.initMarketHistory(y20231219,20231219L);
        collectionInfo.initMarketHistory(y20240104,20240104L);
        collectionInfo.initMarketHistory(y20240110,20240110L);
        collectionInfo.initMarketHistory(y20240111,20240111L);
        collectionInfo.initMarketHistory(y20240118,20240118L);
        collectionInfo.initMarketHistory(Y20240121,20240121L);
        collectionInfo.initMarketHistory(Y20240122,20240122L);
        collectionInfo.initMarketHistory(Y20240123,20240123L);
        collectionInfo.initMarketHistory(Y20240124,20240124L);
        collectionInfo.initMarketHistory(Y20240126,20240126L);
        collectionInfo.initMarketHistory(Y20240128,20240128L);
        collectionInfo.initMarketHistory(Y20240130,20240130L);
        collectionInfo.initMarketHistory(Y20240201,20240201L);
        collectionInfo.initMarketHistory(Y20240217,20240217L);
        collectionInfo.initMarketHistory(Y20240225,20240225L);
        collectionInfo.initMarketHistory(Y20240227,20240227L);
        collectionInfo.initMarketHistory(Y20240228,20240228L);
        log.info(" 完成");


    }

    @Test
    public void initMarketDbTwo(){

        Long start = 20240301L;
        Long end  = DateUtil.getMinerDayBefore(1);
        while (start <=end){
            log.info("  initMarketDbTwo start : {}  ",start);
            collectionInfo.initMarketHistory(start);
            start = DateUtil.getAfterDay(start,1);

        }
        log.info(" 完成");

    }

}
