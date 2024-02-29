package com.lucky.coin.service.vo.bot;

import lombok.Data;

@Data
public class BotResponseGroup {
    private String gid;
    private String name;
    private int messageExpiry;
    private String avatar;
    private int invitationRule;
    private String members;
    private int version;

}
