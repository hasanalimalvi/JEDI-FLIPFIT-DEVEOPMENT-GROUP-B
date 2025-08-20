package com.flipfit.business;

import com.flipfit.bean.FlipFitTransaction;

public interface PaymentService {
    void processPayment(FlipFitTransaction transaction);
}
