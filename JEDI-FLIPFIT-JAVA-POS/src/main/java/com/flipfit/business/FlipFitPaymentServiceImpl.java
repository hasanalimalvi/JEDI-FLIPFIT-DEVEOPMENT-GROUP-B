package com.flipfit.business;

import com.flipfit.bean.FlipFitTransaction;
import com.flipfit.dao.FlipFitPaymentDAO;
import com.flipfit.dao.FlipFitPaymentDAOImpl;

public class FlipFitPaymentServiceImpl implements FlipFitPaymentService{

    FlipFitPaymentDAO flipFitPaymentDAO = new FlipFitPaymentDAOImpl();

    @Override
    public FlipFitTransaction processPayment(FlipFitTransaction transaction) {
      return flipFitPaymentDAO.processPayment(transaction);
    }
}
