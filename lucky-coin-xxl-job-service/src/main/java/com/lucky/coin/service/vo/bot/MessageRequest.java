package com.lucky.coin.service.vo.bot;


import com.alibaba.fastjson.JSONObject;
import lombok.Data;

import java.util.List;

@Data
public class MessageRequest {
    int version = 1;
    String src;
    Destination dest;
    String type;
    long timestamp;
    Msg msg;

    Receipt receipt;

    JSONObject notify;

    @Data
    public static class Destination {
        List<String> wuid;
        String groupID;
        String type;
    }

    @Data
    public static class Msg {
        String body;
        String[] atPersons;
        Attachment attachment;
        long serverTimestamp;
        long sortID;

        public Msg(long serverTimestamp) {
            this.serverTimestamp = serverTimestamp; //= System.currentTimeMillis();
            sortID = 1;
        }
    }

    @Data
    public static class Receipt {
        String type;
        List<Long> timestamp;

        public Receipt(String type, List<Long> timestamp) {
            this.type = type;
            this.timestamp = timestamp;
        }
    }
}
