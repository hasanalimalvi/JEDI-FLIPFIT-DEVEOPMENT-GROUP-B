package com.flipfit.dao;

import com.flipfit.bean.FlipFitTransaction;

public interface FlipFitPaymentDAO {
    FlipFitTransaction processPayment(FlipFitTransaction transaction);
}
