package com.app.datingapp.match;

import com.app.datingapp.TestUtils;
import com.app.datingapp.user.UserDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.io.IOException;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@SpringBootTest
class MatchingControllerTest {

    @Autowired
    private TestUtils testUtils;

    @Autowired
    private MatchingController matchingController;

    @MockBean
    private MatchingService matchingService;

    @Test
    void contextLoads() {
        assertThat(this.matchingController, is(notNullValue()));
    }

    @Test
    void shouldGetAllUsers() throws IOException {
        UserDto userDto = this.testUtils.getUserDto();
        when(this.matchingService.getMatches("test")).thenReturn(List.of(userDto));

        List<UserDto> userDtos = this.matchingController.getAllUsers("test");
        assertThat(userDtos, is(List.of(userDto)));
    }

    @Test
    void shouldThrowIOExceptionWhenGettingAllUsers() throws IOException {
        when(this.matchingService.getMatches("test")).thenThrow(new IOException());
        assertThrows(IOException.class, () -> {
            this.matchingController.getAllUsers("test");
        });

    }
}
