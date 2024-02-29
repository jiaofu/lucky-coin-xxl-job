package com.lucky.coin.service.vo.bot;

import lombok.Data;

@Data
public class BotResponse<T> {
    private int ver;
    private int status;
    private String reason;
    private BotResponseData<T> data;
}
