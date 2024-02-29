package com.lucky.coin.service.vo.http;


import lombok.Data;

@Data
public class MemPoolFeesVo {

    /**
     * <pre>
     * limits
     * </pre>
     */
    private MemPoolFeesLimitsVo limits;

    /**
     * <pre>
     *
     * </pre>
     */
    private Long regular;

    /**
     * <pre>
     *
     * </pre>
     */
    private Long priority;


}
