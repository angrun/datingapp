package com.app.datingapp.match;

import com.app.datingapp.TestUtils;
import com.app.datingapp.image.ImageDao;
import com.app.datingapp.user.UserDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class MatchingDaoTest {

    @Autowired
    TestUtils testUtils;

    @Autowired
    private MatchingDao matchingDao;

    @Test
    void contextLoads() {
        assertThat(this.matchingDao, is(notNullValue()));
    }

    @Test
    void shouldMatches() throws IOException {
        List<UserDto> matches = this.matchingDao.getMatches("test@test.com");
        assertThat(matches, is(List.of()));
    }
}
