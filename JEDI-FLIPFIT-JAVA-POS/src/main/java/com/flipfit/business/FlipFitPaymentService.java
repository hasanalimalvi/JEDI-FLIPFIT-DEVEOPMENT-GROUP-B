package com.flipfit.business;

import com.flipfit.bean.FlipFitTransaction;

public interface FlipFitPaymentService {
    void processPayment(FlipFitTransaction transaction);
}
