import com.binance.pool.util.DateUtils;
import com.lucky.coin.LuckyCoinXxlJobApplication;
import com.lucky.coin.service.market.CollectionInfo;
import com.lucky.coin.service.util.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = LuckyCoinXxlJobApplication.class)
@Slf4j
public class EveryTest {


    @Resource
    CollectionInfo collectionInfo;

    @Test
    public void baseCoin(){
        String yBaseStr ="btc,eth,sol,bnb,avax,arb,tia,sui,op,okb,tao,sei,pyth,imx,stx,alt,zeta,kas,rndr,olas,xai,inj,ldo,grt,ron,rune,wemix,bigtime,akt,fet,ar,sats,metis,ilv,shrap,iotx,nos,api3,magic,flux,lpt,mask,naka,map,cqt,gns,trias,ator,wifi,equ,dnx,zkf,ton";
        List<String> baseCoins =  Arrays.stream(yBaseStr.split(",")).collect(Collectors.toList());

        baseCoins.add("pepe");
        baseCoins.add("sys");
        baseCoins.add("crv");


        String yBase = baseCoins.stream().collect(Collectors.joining(","));
        collectionInfo.initSymbol(yBase);
    }

    @Test
    public void everyDay(){
        collectionInfo.getEveryCoinScore();
        log.info("  everyDay 今日计算数据 day : {} ", DateUtils.getMinerDayBefore(0));
    }
}
