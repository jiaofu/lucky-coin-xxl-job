package com.lucky.coin.service.vo.http;

import lombok.Data;

import java.util.List;

@Data
public class Transactions {
    private String data;
    private String txid;
    private String hash;
    private List<String> depends;
    private Long fee;
    private int sigops;
    private int weight;
}
