package com.lucky.coin.service.vo.bot;


import lombok.Data;

import java.util.List;

@Data
public class BotResponseData<T> {
    private List<T> groups;
}
