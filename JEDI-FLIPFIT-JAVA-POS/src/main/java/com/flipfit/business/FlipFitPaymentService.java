
package com.flipfit.business;

import com.flipfit.bean.FlipFitTransaction;
import com.flipfit.dao.FlipFitPaymentDAO;
import com.flipfit.exception.EntityNotFoundException;
import com.flipfit.exception.PaymentFailedException;

/**
 *@Author : "Harshil"
 *@Parameters: "FlipFitCustomerDAOImpl, FlipFitGymOwnerDAOImpl, FlipFitAdminDAOImpl, FlipFitPaymentDAOImpl"
 *@Exceptions: "EntityNotFoundException, SQLException"
 *@Description : "This interface provides data access object (DAO) methods for managing admin-related operations in the FlipFit application."
 */

public interface FlipFitPaymentService {
    FlipFitTransaction processPayment(FlipFitTransaction transaction) throws PaymentFailedException, EntityNotFoundException;
}