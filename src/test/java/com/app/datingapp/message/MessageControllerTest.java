package com.app.datingapp.message;

import com.app.datingapp.TestUtils;
import com.app.datingapp.exceptions.InvalidUserException;
import com.app.datingapp.hobby.Hobby;
import com.app.datingapp.hobby.HobbyController;
import com.app.datingapp.hobby.HobbyDto;
import com.app.datingapp.hobby.HobbyService;
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
class MessageControllerTest {

    @Autowired
    private MessageController messageController;

    @Autowired
    private TestUtils testUtils;

    @MockBean
    private MessageService service;

    @Test
    void contextLoads() {
        assertThat(this.messageController, is(notNullValue()));
    }

    @Test
    void shouldSendMessage() throws InvalidUserException {
        MessageDto messageDto = this.testUtils.getMessageDto();
        this.messageController.sendMessage(messageDto);
        verify(this.service, times(1)).sendMessage(eq(messageDto));
    }

    @Test
    void shouldGetHobbies() {
        Message message = this.testUtils.getMessage();
        when(this.service.getMessages(eq("test"), eq(1))).thenReturn(List.of(message));

        List<Message> messages = this.service.getMessages("test", 1);
        assertThat(messages, is(List.of(message)));
    }

    @Test
    void shouldUpdateMessagesToBeSeen() {
        HobbyDto hobbyDto = testUtils.getHobbyDto();
        this.messageController.updateMessagesToBeSeen("test", 1);
        verify(this.service, times(1)).updateMessagesToBeSeen(eq("test"), eq(1));
    }
}
