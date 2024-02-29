package com.lucky.coin.service.vo.bot;

import lombok.Data;

import java.util.List;

@Data
public class BotResponseMsg<T> {
    private int ver;
    private int status;
    private String reason;
    private List<T> data;
}
