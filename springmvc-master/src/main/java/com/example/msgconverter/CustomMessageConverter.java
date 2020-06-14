package com.example.msgconverter;

import com.example.domain.DemoObj;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.util.StreamUtils;


import java.io.IOException;

import java.nio.charset.StandardCharsets;

/**
 *  自定义消息转换器，用来处理 request 和response中的数据
 */
public class CustomMessageConverter extends AbstractHttpMessageConverter<DemoObj> {

    //自定义媒体类型
    public CustomMessageConverter() {
        super(new MediaType("application","x-wisely", StandardCharsets.UTF_8));
    }

    //给定类型是否是DemoObj类或者子类
    @Override
    protected boolean supports(Class<?> clazz) {
        return DemoObj.class.isAssignableFrom(clazz);
    }

    //按照一定格式读数据
    @Override
    protected DemoObj readInternal(Class<? extends DemoObj> clazz, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
        String temp = StreamUtils.copyToString(inputMessage.getBody(),StandardCharsets.UTF_8);
        String[]  tempArr = temp.split("-");
        return  new DemoObj(new Long(tempArr[0]),tempArr[1]);

    }

    //内部先往消息体中按照一定格式写数据
    @Override
    protected void writeInternal(DemoObj demoObj, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
            String out = "hello:" + demoObj.getId() +"-" + demoObj.getName();
            outputMessage.getBody().write(out.getBytes());
    }
}
