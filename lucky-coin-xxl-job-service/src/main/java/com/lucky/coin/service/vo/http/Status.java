package com.lucky.coin.service.vo.http;

import lombok.Data;

@Data
public class Status {
    private String timestamp;
    private int error_code;
    private String error_message;
    private int elapsed;
    private int credit_count;
    private String notice;
}
