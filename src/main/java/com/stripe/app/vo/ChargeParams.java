package com.stripe.app.vo;

import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

/**
 * @ClassName ChargeParams
 * @Description TODO
 * @Author weng_jun_ji_
 * @Date 2019/8/1 22:07
 * @Vervsion 1.0
 */
public class ChargeParams {
    private BigDecimal amount;
    private String currency;
    private String customer;

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }
}
