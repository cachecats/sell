package com.solo.sell.utils;

import com.solo.sell.VO.ResultVO;

/**
 * 返回结果封装工具类
 */
public class ResultVOUtils {

    public static ResultVO success(Object object){
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(0);
        resultVO.setMsg("success");
        resultVO.setData(object);
        return resultVO;
    }

    public static ResultVO success(){
        return success();
    }

    public static ResultVO error(Integer code, String msg){
        ResultVO resultVO =  new ResultVO();
        resultVO.setCode(code);
        resultVO.setMsg(msg);
        return resultVO;
    }
}
