package com.flipfit.business;

import com.flipfit.bean.FlipFitTransaction;
import com.flipfit.dao.FlipFitPaymentDAO;

public interface FlipFitPaymentService {
    FlipFitTransaction processPayment(FlipFitTransaction transaction);
}
