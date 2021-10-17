package com.app.datingapp.hobby;

import com.app.datingapp.TestUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;

@SpringBootTest
class HobbyDaoTest {

    @Autowired
    TestUtils testUtils;

    @Autowired
    private HobbyDao hobbyDao;

    @Test
    void contextLoads() {
        assertThat(this.hobbyDao, is(notNullValue()));
    }

    @Test
    void shouldGetHobbies() {
        List<Hobby> hobbies = this.hobbyDao.getHobbies(1);
        assertThat(hobbies, is(List.of()));
    }
}
