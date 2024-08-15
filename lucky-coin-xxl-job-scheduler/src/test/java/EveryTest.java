
import com.lucky.coin.LuckyCoinXxlJobApplication;
import com.lucky.coin.service.http.HttpFromMarketInfo;
import com.lucky.coin.service.market.CollectionInfo;
import com.lucky.coin.service.market.InitCoinScore;
import com.lucky.coin.service.util.DateUtil;
import com.lucky.coin.service.vo.http.CoinmarketcapCoinInfoVo;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TimeZone;
import java.util.stream.Collectors;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = LuckyCoinXxlJobApplication.class)
@Slf4j
public class EveryTest {


    @Resource
    CollectionInfo collectionInfo;

    @Resource
    InitCoinScore initCoinScore;

    @Resource
    HttpFromMarketInfo httpFromMarketInfo;


    @Test
    public void testDuCoin(){
        List<String> coinNameList = new ArrayList<>();
        coinNameList.add("MAPO");
        CoinmarketcapCoinInfoVo coinInfoVo = httpFromMarketInfo.getCoinInfoByList(coinNameList);
    }
    @Test
    public void baseCoin(){
        String yBaseStr ="btc,eth,sol,bnb,avax,arb,tia,sui,op,okb,tao,sei,pyth,imx,stx,alt,zeta,kas,olas,xai,inj,ldo,grt,ron,rune,wemix,bigtime,akt,fet,ar,sats,metis,ilv,shrap,iotx,nos,api3,magic,flux,lpt,mask,naka,mapo,cqt,gns,trias,wifi,equ,dnx,zkf,ton";
        List<String> baseCoins =  Arrays.stream(yBaseStr.split(",")).collect(Collectors.toList());

        baseCoins.add("pepe");
        baseCoins.add("sys");
        baseCoins.add("crv");
        baseCoins.add("gala");
        baseCoins.add("hnt");
        baseCoins.add("ace");
        baseCoins.add("near");
        baseCoins.add("nfp");
        baseCoins.add("deso");
        baseCoins.add("rio");
        baseCoins.add("shdw");
        baseCoins.add("zrx");
        baseCoins.add("axl");
        baseCoins.add("ngl");
        baseCoins.add("vanry");
        baseCoins.add("arkm");
        baseCoins.add("cyber");
        baseCoins.add("ssv");
        baseCoins.add("gal");
        baseCoins.add("ftm");
        baseCoins.add("ckb");
        baseCoins.add("gfi");
        baseCoins.add("ondo");
        baseCoins.add("apx");
        baseCoins.add("rss3");
        baseCoins.add("alph");
        baseCoins.add("aevo");
        baseCoins.add("zkj");
        baseCoins.add("core");
        baseCoins.add("theta");
        baseCoins.add("ena");
        baseCoins.add("ethfi");
        baseCoins.add("BGB");
        baseCoins.add("MSN");
        baseCoins.add("ENS");
        baseCoins.add("YGG");
        baseCoins.add("ID");
        baseCoins.add("ZENT");
        baseCoins.add("CHZ");
     //   baseCoins.add("TURBO");
        baseCoins.add("NEXT");
        baseCoins.add("UNI");
        baseCoins.add("PSTAKE");
        baseCoins.add("GPT");
        baseCoins.add("BICO");
        baseCoins.add("ALGO");
        baseCoins.add("ATH");
        baseCoins.add("IO");
        baseCoins.add("BAKE");
        baseCoins.add("ZK");
        baseCoins.add("MKR");
        baseCoins.add("TWT");
        baseCoins.add("AAVE");
        baseCoins.add("CFX");
        baseCoins.add("ZRO");

        baseCoins.add("RENDER"); // rndr 升级

        String yBase = baseCoins.stream().collect(Collectors.joining(","));
        initCoinScore.initSymbol(yBase);
    }

    @Test
    public void everyDay(){
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
        collectionInfo.getEveryCoinScore(64);
        log.info("  everyDay 今日计算数据 day : {} ", DateUtil.getMinerDayBefore(0));
    }
}
