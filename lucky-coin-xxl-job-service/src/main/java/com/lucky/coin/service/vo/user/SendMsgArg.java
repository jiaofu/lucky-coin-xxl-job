package com.lucky.coin.service.vo.user;

import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
@Builder
public class SendMsgArg {
    /**
     * 防钓鱼码
     */
    private String antiPhishingCode;

    /**
     * 抄送
     */
    private List<String> bcc;


    /**
     * 模板参数数据
     */
    private Map<String, String> data;
    /**
     *
     */
    private String ip;

    /**
     * 是否是业务邮件，如果不是业务邮件，则false，会用非业务通道发送邮件
     */
    private Boolean isBusines;


    /**
     * 指定语言,优先拿这个语言。其次是ApiRequest，默认en
     */
    private String language;

    /**
     * 模板内容
     */
    private String mailtext;

    /**
     * 发送语音短信需要传
     */
    private String msgType;

    /**
     * mobileCode(发送手机验证码国外必传)
     *
     * @return
     */
    private String mobileCode;

    /**
     * 是否需要返回内容
     */

    private List<Boolean> needContent;


    /**
     * 是否需要ip检查，默认需要
     */
    private Boolean needIpCheck;
    /**
     * 是否需要发送次数检查，默认需要
     */
    private Boolean needSendTimesCheck;

    /**
     * 是否需要走队列发送，测试邮件如果不想走sqs，不想被dev上的队列消费，可以传true,
     */
    private Boolean noTrySQS;


    /**
     * 接收者
     */
    private String recipient;


    /**
     * 是否是注册邮件
     */
    private Boolean register;

    /**
     * 是否是resend
     */
    private Boolean resend;


    /**
     * AppId/Function
     */
    private String sourceFunction;


    /**
     * 请求发送标签，是运营推送，还是用户端触发，CUSTOMER/SYSTEM
     */
    private String sourceTag;


    /**
     * title
     */
    private String subject;


    /**
     * 模板code
     */
    private String tplCode;


    /**
     * 可以手动指定发送渠道。
     */
    private String userChannel;


    /**
     * userId
     */
    private String userId;

    /**
     * 发送邮件/短信的唯一标识
     */
    private String uuid;


    /**
     * pnk里面verifyCode表的id
     */
    private String verifyCodeId;
}
