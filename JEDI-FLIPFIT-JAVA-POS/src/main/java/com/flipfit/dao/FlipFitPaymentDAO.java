package com.flipfit.dao;

import com.flipfit.bean.FlipFitTransaction;

public interface FlipFitPaymentDAO {
    void processPayment(FlipFitTransaction transaction);
}
