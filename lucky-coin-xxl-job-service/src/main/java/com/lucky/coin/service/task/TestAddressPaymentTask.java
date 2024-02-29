package com.lucky.coin.service.task;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.lucky.coin.service.config.SysConfig;

import com.lucky.coin.service.vo.http.BachSignArg;
import com.lucky.coin.service.vo.http.FundAccountVo;
import com.lucky.coin.service.vo.http.ReceiveAccountVo;
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



    public static void main(String[] args) {


        BachSignArg arg = new BachSignArg();
        FundAccountVo fundAccountVo = new FundAccountVo();
        fundAccountVo.setAddress("address");
        arg.setPub("pub");
        arg.setFund_account(fundAccountVo);

        List<ReceiveAccountVo> voList = new LinkedList<>();
        ReceiveAccountVo vo = new ReceiveAccountVo();
        ReceiveAccountVo vo2 = new ReceiveAccountVo();

        // wenhui 的地址
        vo.setAddress("bc1q827nqr3ncw2dhufkgzp4e2pa7jsk6qvlht03d0");
        vo.setAmount("3000");

//            vo2.setAddress("bc1q3493surp0du6xhnvkk54ucxwu2kczdu6v9dsk3");
//            vo2.setAmount("40000");

        voList.add(vo);
        voList.add(vo2);
        arg.setReceive_account(voList);
        arg.setByte_fee(null);
        String str = JSONObject.toJSONString(arg, SerializerFeature.WriteMapNullValue);
        System.out.println(str);

    }


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
