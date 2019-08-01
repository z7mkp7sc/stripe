package com.stripe.app.controller;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import com.stripe.model.Customer;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Value;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName TestStripeCustomer
 * @Description TODO
 * @Author weng_jun_ji_
 * @Date 2019/8/1 14:42
 * @Vervsion 1.0
 */
public class TestStripeCustomer {


    @Test
    public void testCustomer() throws StripeException {
        // Set your secret key: remember to change this to your live secret key in production
        // See your keys here: https://dashboard.stripe.com/account/apikeys
        Stripe.apiKey = "sk_test_hiHWTLSoxwDWmW50JoRdrRR50061iPRYRk";
        String token = "tok_mastercard";
//        Stripe.apiKey = apiKey_;
        // Create a Customer:
        Map<String, Object> customerParams = new HashMap<>();
        customerParams.put("source", token);
        customerParams.put("email", "18814121387@163.com");
        Customer customer = Customer.create(customerParams);

        {
            // Charge the Customer instead of the card:
            Map<String, Object> chargeParams = new HashMap<>();
            chargeParams.put("amount", 1000);
            chargeParams.put("currency", "usd");
            chargeParams.put("customer", customer.getId());
            Charge charge = Charge.create(chargeParams);
            System.out.println(charge.toString());
        }


        // YOUR CODE: Save the customer ID and other info in a database for later.

        // When it's time to charge the customer again, retrieve the customer ID.
        Map<String, Object> params = new HashMap<>();
        params.put("amount", 1500); // $15.00 this time
        params.put("currency", "usd");
        params.put("customer", customer.getId()); // Previously stored, then retrieved
        Charge charge = Charge.create(params);
        System.out.println(charge);
    }
}
