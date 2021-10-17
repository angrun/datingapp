package com.app.datingapp.browse;


import com.app.datingapp.TestUtils;
import com.app.datingapp.filter.FilterDto;
import com.app.datingapp.match.Matching;
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
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class BrowseServiceTest {

    @Autowired
    private TestUtils testUtils;

    @Autowired
    private BrowseService browseService;

    @MockBean
    BrowseDao browseDao;

    @Test
    void contextLoads() {
        assertThat(this.browseService, is(notNullValue()));
    }

    @Test
    void shouldGetAllUsers() throws IOException {
        FilterDto filterDto = testUtils.getFilterDto();
        List<UserDto> users = List.of(this.testUtils.getUserDto());

        when(this.browseDao.getAllUsers(eq(filterDto))).thenReturn(users);
        var response = this.browseService.getAllUsers(filterDto);
        assertThat(response, is(users));
    }

    @Test
    void shouldThrowIOException() throws IOException {
        FilterDto filterDto = this.testUtils.getFilterDto();

        when(this.browseDao.getAllUsers(eq(filterDto))).thenThrow(new IOException());
        assertThrows(IOException.class, () -> {
            this.browseService.getAllUsers(filterDto);
        });
    }

    @Test
    void shouldLikeUser() {
        Matching matching = this.testUtils.getMatching();
        this.browseService.gradeUser(matching);
        verify(this.browseDao, times(1)).gradeUser(eq(matching));
    }
}
