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
class HobbyServiceTest {

    @Autowired
    private HobbyService service;

    @Autowired
    private TestUtils testUtils;

    @MockBean
    private HobbyDao hobbyDao;

    @Test
    void contextLoads() {
        assertThat(this.service, is(notNullValue()));
    }

    @Test
    void shouldGetHobbies() {
        Hobby hobby = testUtils.getHobby();
        when(this.hobbyDao.getHobbies(eq(1))).thenReturn(List.of(hobby));

        List<Hobby> hobbies = this.service.getHobbies(1);
        assertThat(hobbies, is(List.of(hobby)));
    }

    @Test
    void shouldDeleteHobby() {
        HobbyDto hobbyDto = testUtils.getHobbyDto();
        this.service.deleteHobby(hobbyDto);
        verify(this.hobbyDao, times(1)).deleteHobby(eq(hobbyDto));
    }
}
