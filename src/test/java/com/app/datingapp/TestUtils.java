package com.app.datingapp;

import com.app.datingapp.filter.FilterDto;
import com.app.datingapp.hobby.Hobby;
import com.app.datingapp.hobby.HobbyDto;
import com.app.datingapp.match.Matching;
import com.app.datingapp.message.Message;
import com.app.datingapp.message.MessageDto;
import com.app.datingapp.user.User;
import com.app.datingapp.user.UserDto;
import org.springframework.stereotype.Component;

import javax.xml.crypto.Data;
import java.time.LocalDate;
import java.util.List;

import static java.time.LocalDate.now;

@Component
public class TestUtils {

    public UserDto getUserDto() {
        return new UserDto().setName("Monica")
                .setSurname("Belucci")
                .setEmail("test@test.com")
                .setCity("Tallinn")
                .setCountry("Estonia")
                .setGender("woman")
                .setAge(20)
                .setLikes(10)
                .setBio("Bio")
                .setRegisterDate(now())
                .setImage(List.of())
                .setHobbies(List.of(new Hobby().setName("sport").setUserId(1L).setId(1L)))
                .setSeen(true)
                .setLastMessage(new Message().setMessage("Hello").setMessageSeen(true));
    }

    public User getUser() {
        return new User().setName("Monica")
                .setBirth(LocalDate.of(1997, 10, 23))
                .setSurname("Belucci")
                .setPassword("test")
                .setPassword2("test")
                .setEmail("test@test.com")
                .setCity("Tallinn")
                .setCountry("Estonia")
                .setGender("woman")
                .setLikes(10)
                .setBio("Bio")
                .setRegisterDate(now());
    }

    public FilterDto getFilterDto() {
        return new FilterDto()
                .setCity("Tallinn")
                .setCountry("Estonia")
                .setGender("woman")
                .setHobby("sport")
                .setId(1);
    }

    public Matching getMatching() {
        return new Matching().setFromUserId(1)
                .setId(1L)
                .setLikeValue(10)
                .setSeen(true)
                .setToUserId(2);
    }

    public HobbyDto getHobbyDto() {
        return new HobbyDto().setHobby("sport")
                .setUserId(1L);
    }

    public Hobby getHobby() {
        return new Hobby().setName("sport")
                .setUserId(1L)
                .setId(1L);
    }

    public Message getMessage() {
        return new Message().setMessage("hello")
                .setMessageSeen(true)
                .setFromUserId(1L)
                .setToUserId(2L)
                .setId(1L);
    }

    public MessageDto getMessageDto() {
        return new MessageDto().setMessage("hello")
                .setFromUserId(1L)
                .setToUserId(2L);
    }
}
