package com.app.datingapp.message;

import com.app.datingapp.TestUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest
class MessageServiceTest {

    @Autowired
    private TestUtils testUtils;

    @Autowired
    private MessageService messageService;

    @MockBean
    private MessageDao messageDao;

    @Test
    void contextLoads() {
        assertThat(this.messageService, is(notNullValue()));
    }

    @Test
    void shouldSendMessage() {
        MessageDto messageDto = testUtils.getMessageDto();
        this.messageService.sendMessage(messageDto);
        verify(this.messageDao, times(1)).sendMessage(eq(messageDto));
    }
}
