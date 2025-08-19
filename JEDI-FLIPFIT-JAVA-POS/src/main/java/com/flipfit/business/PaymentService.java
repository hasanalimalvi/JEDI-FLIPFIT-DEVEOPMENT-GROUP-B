package com.flipfit.business;

import com.flipfit.bean.Transaction;

public interface PaymentService {
    void processPayment(Transaction transaction);
}
