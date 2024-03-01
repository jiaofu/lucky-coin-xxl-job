package com.lucky.coin.service.task;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.lucky.coin.service.config.SysConfig;

import com.xxl.job.core.context.XxlJobHelper;
import com.xxl.job.core.handler.annotation.XxlJob;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;


@Component
@Slf4j
public class TestAddressPaymentTask {


    @Resource
    SysConfig sysConfig;





    @XxlJob("helloWorldJobHandler")
    public void helloWorld() throws Exception {
        try {

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String msg = sdf.format(new Date());
            log.info("helloWorldJobHandler info :{}" + msg);
            Thread.sleep(60000);
            log.info("helloWorldJobHandler 沉睡成功 :{}" + msg);
            //XxlJobHelper.handleFail("helloWorldJobHandler XxlJobHelper handleFail  log "+msg);
        } catch (Exception ex) {
            XxlJobHelper.handleFail(ex.getMessage());
            log.error("  helloWorldJobHandler 异常  ");
        }


    }

}
