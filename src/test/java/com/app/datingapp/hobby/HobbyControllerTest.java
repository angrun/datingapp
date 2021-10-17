package com.app.datingapp.hobby;


import com.app.datingapp.TestUtils;
import com.app.datingapp.exceptions.InvalidUserException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;


import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class HobbyControllerTest {

    @Autowired
    private HobbyController hobbyController;

    @Autowired
    private TestUtils testUtils;

    @MockBean
    private HobbyService service;

    @Test
    void contextLoads() {
        assertThat(this.hobbyController, is(notNullValue()));
    }

    @Test
    void shouldAddHobby() throws InvalidUserException {
        HobbyDto hobbyDto = testUtils.getHobbyDto();
        this.hobbyController.addHobby(hobbyDto);
        verify(this.service, times(1)).addHobby(eq(hobbyDto));
    }

    @Test
    void shouldGetHobbies() {
        Hobby hobby = testUtils.getHobby();
        when(this.service.getHobbies(eq(1))).thenReturn(List.of(hobby));

        List<Hobby> hobbies = this.hobbyController.getHobbies(1);
        assertThat(hobbies, is(List.of(hobby)));
    }

    @Test
    void shouldDeleteHobby() throws InvalidUserException {
        HobbyDto hobbyDto = testUtils.getHobbyDto();
        this.hobbyController.deleteHobby(hobbyDto);
        verify(this.service, times(1)).deleteHobby(eq(hobbyDto));
    }
}
