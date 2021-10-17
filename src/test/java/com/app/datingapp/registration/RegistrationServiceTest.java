package com.app.datingapp.registration;

import com.app.datingapp.TestUtils;
import com.app.datingapp.exceptions.InvalidUserException;
import com.app.datingapp.user.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@SpringBootTest
class RegistrationServiceTest {

    @Autowired
    private TestUtils testUtils;

    @Autowired
    private RegistrationService registrationService;

    @MockBean
    private UserAuthorizationDao userAuthorizationDao;

    @Test
    void shouldRegisterUser() throws InvalidUserException {
        User user = testUtils.getUser();
        when(this.userAuthorizationDao.register(eq(user))).thenReturn(user);

        User registeredUser = this.registrationService.registerUser(user);
        assertThat(registeredUser, is(user));
    }
}
