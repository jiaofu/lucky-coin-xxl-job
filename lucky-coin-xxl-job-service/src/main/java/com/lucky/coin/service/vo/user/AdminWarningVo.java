package com.lucky.coin.service.vo.user;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class AdminWarningVo {
    private int sendCount;

    private long lastSendTime;
}
