package com.app.datingapp.user;

import com.app.datingapp.TestUtils;
import com.app.datingapp.exceptions.InvalidUserException;
import com.app.datingapp.registration.RegistrationController;
import com.app.datingapp.registration.RegistrationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.InputStreamSource;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
public class UserControllerTest {

    @Autowired
    private TestUtils testUtils;

    @Autowired
    private UserController userController;

    @MockBean
    private UserService userService;

    @Test
    void contextLoads() {
        assertThat(this.userController, is(notNullValue()));
    }

    @Test
    void shouldGetUser() throws IOException {
        UserDto userDto = testUtils.getUserDto();
        when(this.userService.getUserByEmail(eq("test@test.com"))).thenReturn(userDto);

        UserDto registeredUser = this.userController.getUser("test@test.com");
        assertThat(registeredUser, is(userDto));

    }

    @Test
    void shouldThrowIOExceptionWheGettingUser() throws IOException {
        when(this.userService.getUserByEmail(eq("test@test.com"))).thenThrow(new IOException());

        assertThrows(IOException.class, () -> {
            this.userController.getUser("test@test.com");
        });
    }

    @Test
    void shouldUpdateUser() throws IOException, InvalidUserException {
        UserDto userDto = this.testUtils.getUserDto();
        User user = this.testUtils.getUser();
        when(this.userService.updateUser(eq(userDto))).thenReturn(user);

        User registeredUser = this.userController.updateUser(userDto);
        assertThat(registeredUser, is(user));
    }

    @Test
    void shouldCreateFile() throws InvalidUserException {
        this.userController.createFile(null, "test");
        verify(this.userService, times(1)).createFile(eq(null), eq("test"));
    }

    @Test
    void shouldCreateFileResource()  {
        FileSystemResource fileSystemResource = new FileSystemResource("");
        when(this.userService.createFile()).thenReturn(fileSystemResource);

        Resource file = this.userController.createFile();
        assertThat(file, is(fileSystemResource));
    }

}
