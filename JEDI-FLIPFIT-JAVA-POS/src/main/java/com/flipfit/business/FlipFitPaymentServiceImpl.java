package com.flipfit.business;

import com.flipfit.bean.FlipFitTransaction;
import com.flipfit.dao.FlipFitPaymentDAO;
import com.flipfit.dao.FlipFitPaymentDAOImpl;
import com.flipfit.exception.PaymentFailedException;

public class FlipFitPaymentServiceImpl implements FlipFitPaymentService{

    FlipFitPaymentDAO flipFitPaymentDAO = new FlipFitPaymentDAOImpl();

    @Override
    public FlipFitTransaction processPayment(FlipFitTransaction transaction) throws PaymentFailedException {
      return flipFitPaymentDAO.processPayment(transaction);
    }
}
