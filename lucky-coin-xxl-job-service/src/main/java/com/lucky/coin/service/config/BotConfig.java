package com.lucky.coin.service.config;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BotConfig {
    String botID;

    String apiBase;

    String appID;
    String appSecret;

}
