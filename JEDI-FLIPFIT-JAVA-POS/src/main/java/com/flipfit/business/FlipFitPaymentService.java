package com.flipfit.business;

import com.flipfit.bean.FlipFitTransaction;
import com.flipfit.dao.FlipFitPaymentDAO;
import com.flipfit.exception.PaymentFailedException;

public interface FlipFitPaymentService {
    FlipFitTransaction processPayment(FlipFitTransaction transaction) throws PaymentFailedException;
}
