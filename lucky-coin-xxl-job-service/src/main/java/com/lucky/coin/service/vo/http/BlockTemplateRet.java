package com.lucky.coin.service.vo.http;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.util.List;

@Data
public class BlockTemplateRet {
    private List<String> capabilities;
    private int version;
    private List<String> rules;

    private int vbrequired;
    private String previousblockhash;
    private List<Transactions> transactions;

    private int coinbasevalue;
    private String longpollid;
    private String target;
    private int mintime;
    private List<String> mutable;
    private String noncerange;
    private int sigoplimit;
    private int sizelimit;
    private int weightlimit;
    private int curtime;
    private String bits;
    private int height;
    @JSONField(name = "default_witness_commitment")
    private String defaultWitnessCommitment;


}
