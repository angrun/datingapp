package com.app.datingapp.match;

import com.app.datingapp.TestUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;

@SpringBootTest
class MatchingServiceTest {

    @Autowired
    private TestUtils testUtils;

    @Autowired
    private MatchingService matchingService;

    @MockBean
    private MatchingDao matchingDao;

    @Test
    void contextLoads() {
        assertThat(this.matchingService, is(notNullValue()));
    }
}
