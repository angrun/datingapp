package com.app.datingapp.image;

import com.app.datingapp.TestUtils;
import com.app.datingapp.hobby.Hobby;
import com.app.datingapp.hobby.HobbyDao;
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
class ImageDaoTest {

    @Autowired
    TestUtils testUtils;

    @Autowired
    private ImageDao imageDao;

    @Test
    void contextLoads() {
        assertThat(this.imageDao, is(notNullValue()));
    }

    @Test
    void shouldThrowFileNotFoundException() throws IOException {
        assertThrows(FileNotFoundException.class, () -> this.imageDao.getUserImages(1L));
    }
}
