package com.flipfit.dao;

import com.flipfit.bean.FlipFitTransaction;
import com.flipfit.exception.EntityNotFoundException;
import com.flipfit.exception.PaymentFailedException;

public interface FlipFitPaymentDAO {
    FlipFitTransaction processPayment(FlipFitTransaction transaction) throws PaymentFailedException, EntityNotFoundException;
}
