package com.example.domain;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.util.Date;

/**
 *   注解验证 Bean 的内容
 */
public class Transaction {

    //产品编号
    @NotNull
    private Long  productId;

    //用户编号
    @NotNull //不能为空
    private Long  userId;

    @Future  //只能是将来的日期
    @DateTimeFormat(pattern = "yyyy-MM-dd") // 日期格式转换
    @NotNull
    private Date date;

    //价格
    @NotNull
    @DecimalMin(value = "0.1") //最小值 0.1元
    private  Double price;


    //数量
    @NotNull
    @Min(1) //最小值为1
    @Max(100) //最大值为 100
    private Integer quantity;

    //交易金额
    @NotNull
    @DecimalMax("500000.00") //最大交易金额为 5 万元
    @DecimalMin("1.00") //最小交易金额为 1 元
    private Double amount;


    //正则表达式验证邮件
    @Pattern(regexp = "^([a-zA-Z0-9]*[-_]?[a-zA-Z0-9]+)*@" +
                "([a-zA-Z0-9]*[-_]?[a-zA-z0-9]+)+[\\.][A-Za-z]{2,3}([\\.][A-Za-z]{2})?$",
                  message = "不符合邮件格式")
    private String  email;

    //备注
    @Size(min= 0 ,max = 256) //0 到 255 个字符
    private String note;

    /***************getter and setter*************/


    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
