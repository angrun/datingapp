package com.app.datingapp.registration;

import com.app.datingapp.TestUtils;
import com.app.datingapp.exceptions.InvalidUserException;
import com.app.datingapp.match.MatchingController;
import com.app.datingapp.match.MatchingService;
import com.app.datingapp.user.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@SpringBootTest
class RegistrationControllerTest {

    @Autowired
    private TestUtils testUtils;

    @Autowired
    private RegistrationController registrationController;

    @MockBean
    private RegistrationService registrationService;

    @Test
    void contextLoads() {
        assertThat(this.registrationController, is(notNullValue()));
    }

    @Test
    void shouldRegisterUser() throws InvalidUserException {
        User user = testUtils.getUser();
        when(this.registrationService.registerUser(eq(user))).thenReturn(user);

        User registeredUser = this.registrationController.registerUser(user);
        assertThat(registeredUser, is(user));

    }

    @Test
    void shouldThrowInvalidUserExceptionWhenRegisteringUser() throws InvalidUserException {
        User user = testUtils.getUser();
        when(this.registrationService.registerUser(eq(user))).thenThrow(new InvalidUserException("test", 200));

        assertThrows(InvalidUserException.class, () -> {
            this.registrationController.registerUser(user);
        });
    }

}
