
package com.flipfit.business;

import com.flipfit.bean.FlipFitTransaction;
import com.flipfit.exception.EntityNotFoundException;
import com.flipfit.exception.PaymentFailedException;

/**
 *@author : "Harshil"
 *@parameters: "FlipFitCustomerDAOImpl, FlipFitGymOwnerDAOImpl, FlipFitAdminDAOImpl, FlipFitPaymentDAOImpl"
 *@exceptions: "EntityNotFoundException, SQLException"
 *@description : "This interface provides data access object (DAO) methods for managing admin-related operations in the FlipFit application."
 */

public interface FlipFitPaymentService {
    FlipFitTransaction processPayment(FlipFitTransaction transaction) throws PaymentFailedException, EntityNotFoundException;
}