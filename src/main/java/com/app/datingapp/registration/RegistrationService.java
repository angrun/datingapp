package com.app.datingapp.registration;


import com.app.datingapp.exceptions.InvalidUserException;
import com.app.datingapp.user.User;
import com.app.datingapp.validation.Validation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegistrationService {

    @Autowired
    UserAuthorizationDao userAuthorizationDao;

    @Autowired
    Validation validation;

    User registerUser(User user) throws InvalidUserException {

        validation.validateUserRegistration(user);
        return userAuthorizationDao.register(user);
    }
}
