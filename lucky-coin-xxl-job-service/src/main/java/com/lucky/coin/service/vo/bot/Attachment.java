package com.lucky.coin.service.vo.bot;

import lombok.Data;

@Data
public class Attachment {
    String contentType;
    String authorizeId;
    String key;
    Integer size;
    String digest;
    int flags;
    String fileName;
    int width;
    int height;
    String preview;
    String caption;

    String url; // 密文下载链接；回调callback URL推送时才可能非空。
}
