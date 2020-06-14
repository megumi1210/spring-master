package com.example.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


//自定义异常映射， code 是映射码，reason 代表异常原因
@ResponseStatus(code = HttpStatus.NOT_FOUND ,reason = "找不到信息异常！！")
public class CustomException extends RuntimeException {
}