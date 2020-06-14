package com.example.validator;

import com.example.domain.Transaction;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 *  通过springframework 提供的验证器自定义验证规则
 */
public class TransactionValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        //判断验证是否为 Transaction ，如果是则进行验证
        return Transaction.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
                  Transaction transaction = (Transaction) target;
                  //求交易金额和价格 * 数量的差额
                 double dis =  transaction.getAmount() -
                         (transaction.getPrice()*transaction.getQuantity());
                 //如果差额大于 0.01 ，则任务业务错误
                 if(Math.abs(dis) >0.01){
                     //加入错误信息
                     errors.rejectValue("amount",null,
                             "交易金额和购买数量与价格不匹配");
                 }

    }
}
