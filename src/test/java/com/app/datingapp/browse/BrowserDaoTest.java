package com.app.datingapp.browse;

import com.app.datingapp.TestUtils;
import com.app.datingapp.filter.FilterDto;
import com.app.datingapp.user.UserDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;

@SpringBootTest
class BrowserDaoTest {

    @Autowired
    TestUtils testUtils;

    @Autowired
    private BrowseDao browseDao;

    @Test
    void contextLoads() {
        assertThat(this.browseDao, is(notNullValue()));
    }

    @Test
    void shouldGetAllUsers() throws IOException {
        FilterDto filterDto = this.testUtils.getFilterDto();
        List<UserDto> allUsers = this.browseDao.getAllUsers(filterDto);
        assertThat(allUsers, is(List.of()));
    }
}
