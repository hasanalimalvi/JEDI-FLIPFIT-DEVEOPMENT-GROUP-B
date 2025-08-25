package com.flipfit.business;

import com.flipfit.bean.FlipFitTransaction;
import com.flipfit.dao.FlipFitPaymentDAO;
import com.flipfit.dao.FlipFitPaymentDAOImpl;
import com.flipfit.exception.EntityNotFoundException;
import com.flipfit.exception.PaymentFailedException;

/**
 *@author : "Bhavya"
 *@parameters: "FlipFitCustomerDAOImpl, FlipFitGymOwnerDAOImpl, FlipFitAdminDAOImpl, FlipFitPaymentDAOImpl"
 *@exceptions: "EntityNotFoundException, SQLException"
 *@exceptions : "This interface provides data access object (DAO) methods for managing admin-related operations in the FlipFit application."
 */

public class FlipFitPaymentServiceImpl implements FlipFitPaymentService{

    FlipFitPaymentDAO flipFitPaymentDAO = new FlipFitPaymentDAOImpl();

    @Override
    public FlipFitTransaction processPayment(FlipFitTransaction transaction) throws PaymentFailedException, EntityNotFoundException {
        return flipFitPaymentDAO.processPayment(transaction);
    }
}