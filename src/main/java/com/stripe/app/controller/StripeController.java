package com.stripe.app.controller;

import com.alibaba.druid.support.json.JSONUtils;
import com.stripe.Stripe;
import com.stripe.app.vo.ChargeParams;
import com.stripe.app.vo.Params;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import com.stripe.model.Customer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.xml.transform.Result;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName StripeController
 * @Description TODO
 * @Author weng_jun_ji_
 * @Date 2019/8/1 16:08
 * @Vervsion 1.0
 */
@RestController
@RequestMapping("/stripe")
public class StripeController {

    @Value("${stripe.apiKey}")
    private String stripe_apiKey;

    /**
     * @Author weng_jun_ji_
     * @Description 根据卡号创建的token和相应信息, 创建客户
     * @Date 2019/8/1 17:18
     * @Param [
     * request,
     * email 客户邮箱,
     * description 客户名称
     * ]
     **/
    @PostMapping("/createCustomer")
    public String createCustomer(@RequestHeader("token") String token,
                                 @RequestBody Params params) {
        Stripe.apiKey = "sk_test_hiHWTLSoxwDWmW50JoRdrRR50061iPRYRk";
        //token由js代码 stripe.createToken(card) 创建
        if (StringUtils.isEmpty(token)) throw new RuntimeException("token doesn't exist");

        Map<String, Object> customerParams = new HashMap<>();
        customerParams.put("source", token);
        customerParams.put("email", params.getEmail());
        customerParams.put("description", params.getDescription());
        System.out.println(JSONUtils.toJSONString(customerParams));
        try {
            Customer customer = Customer.create(customerParams);
            System.out.println("customer message: ");
            System.out.println(customer.toString());
            return "success";
        } catch (StripeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * @Author weng_jun_ji_
     * @Description 根据客户id进行消费
     * @Date 2019/8/1 17:24
     * @Param [
     * amount 收费金额,
     * currency 币种,
     * customer 客户的id(customer.getId())
     * ]
     **/
    @PostMapping("/charge")
    public String charge(@RequestBody ChargeParams p) {
        Stripe.apiKey = "sk_test_hiHWTLSoxwDWmW50JoRdrRR50061iPRYRk";

        Map<String, Object> params = new HashMap<>();
        params.put("amount", p.getAmount());
        params.put("currency", p.getCurrency());
        params.put("customer", p.getCustomer());
        try {
            Charge charge = Charge.create(params);
            System.out.println(charge.toString());
            return "charge success";
        } catch (StripeException e) {
            throw new RuntimeException("charge failed, " + e.getMessage());
        }
    }
}
