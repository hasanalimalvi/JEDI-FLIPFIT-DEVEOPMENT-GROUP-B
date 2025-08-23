package com.flipfit.dao;

import com.flipfit.bean.FlipFitTransaction;
import com.flipfit.exception.EntityNotFoundException;
import com.flipfit.exception.PaymentFailedException;

/**
 *@Author : "Chanukya"
 *@Parameters: "FlipFitCustomerDAOImpl, FlipFitGymOwnerDAOImpl, FlipFitAdminDAOImpl, FlipFitPaymentDAOImpl"
 *@Exceptions: "PaymentFailedException, EntityNotFoundException"
 *@Description : "This interface provides data access object (DAO) methods for managing payment-related operations in the FlipFit application."
 */
public interface FlipFitPaymentDAO {
    FlipFitTransaction processPayment(FlipFitTransaction transaction) throws PaymentFailedException, EntityNotFoundException;
}