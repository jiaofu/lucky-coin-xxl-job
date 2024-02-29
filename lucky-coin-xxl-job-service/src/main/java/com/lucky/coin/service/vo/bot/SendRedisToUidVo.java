package com.lucky.coin.service.vo.bot;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class SendRedisToUidVo {
    private String uidList;
    private String msg;
}
