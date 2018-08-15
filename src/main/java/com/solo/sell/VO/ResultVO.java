package com.solo.sell.VO;

import lombok.Data;

/**
 * 返回的统一结果格式
 * @param <T>
 */
@Data
public class ResultVO<T> {

    /** 状态码 */
    private Integer code;

    /** 返回信息 */
    private String msg;

    /** 返回数据 */
    private T data;

    public ResultVO() {

    }
}
