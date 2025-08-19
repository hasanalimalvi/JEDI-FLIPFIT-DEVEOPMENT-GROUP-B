package com.flipfit.business;

import com.flipfit.bean.DirectCustomer;

public interface AuthenticationService {
    <T> boolean authenticate(T user);


}
