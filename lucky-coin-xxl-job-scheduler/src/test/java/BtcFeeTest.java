import com.lucky.coin.LuckyCoinXxlJobApplication;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.StopWatch;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = LuckyCoinXxlJobApplication.class)
@Slf4j
public class BtcFeeTest {


    @Test
    public void getGetMemPoolInfo() {
        try {
            StopWatch stopWatch = new StopWatch();
            stopWatch.start("ces");

            stopWatch.stop();

            log.info(" 结束 ");
        } catch (Exception ex) {
            log.error(" 异常", ex);
        }

    }


}
