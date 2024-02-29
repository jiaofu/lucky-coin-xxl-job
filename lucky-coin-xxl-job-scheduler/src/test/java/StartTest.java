import com.alibaba.fastjson.JSON;
import com.lucky.coin.LuckyCoinXxlJobApplication;
import com.lucky.coin.dao.bean.PoolCoinBean;

import com.lucky.coin.dao.pool.CoinDao;
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
    CoinDao coinDao;

    @Test
    public void readDb(){
        PoolCoinBean poolCoinBean = coinDao.getCoinHashInfoBean(1);
        log.info(" PoolCoinHashInfoBean : {} ", JSON.toJSONString(poolCoinBean));
    }
}
