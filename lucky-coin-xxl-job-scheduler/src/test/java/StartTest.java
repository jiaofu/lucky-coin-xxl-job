import com.lucky.coin.LuckyCoinXxlJobApplication;
import com.lucky.coin.service.market.CollectionInfo;
import com.lucky.coin.service.market.InitCoinScore;
import com.lucky.coin.service.util.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = LuckyCoinXxlJobApplication.class)
@Slf4j
public class StartTest {



    @Resource
    CollectionInfo collectionInfo;

    @Resource
    InitCoinScore initCoinScore;

//    @Test
//    public void readDb(){
//        PoolCoinBean poolCoinBean = coinDao.getCoinHashInfoBean(1);
//        log.info(" PoolCoinHashInfoBean : {} ", JSON.toJSONString(poolCoinBean));
//    }

    @Test
    public void initDb(){
        String yBase ="btc,eth,sol,bnb,avax,arb,tia,sui,op,okb,tao,sei,pyth,imx,stx,alt,zeta,kas,rndr,olas,xai,inj,ldo,grt,ron,rune,wemix,bigtime,akt,fet,ar,sats,metis,ilv,shrap,iotx,nos,api3,magic,flux,lpt,mask,naka,mapo,cqt,gns,trias,ator,wifi,equ,dnx,zkf";

        initCoinScore.initSymbol(yBase);

    }




    @Test
    public void initMarketDbTwo(){

        Long start = 20230301L;
        initCoinScore.initMarketByThanDay(start);

        log.info(" 完成");

    }

}
