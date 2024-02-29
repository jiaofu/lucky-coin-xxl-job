package com.lucky.coin.service.vo.http;


import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

@Data
public class GetMemPoolInfoVo {

    private boolean loaded;
    private Integer size;
    private int bytes;
    private int usage;
    @JSONField(name = "total_fee")
    private double totalFee;
    private int maxmempool;
    private double mempoolminfee;
    private double minrelaytxfee;
    private int unbroadcastcount;

}
