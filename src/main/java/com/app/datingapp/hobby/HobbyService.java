package com.app.datingapp.hobby;


import com.app.datingapp.exceptions.InvalidUserException;
import com.app.datingapp.validation.Validation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class HobbyService {

    @Autowired
    HobbyDao hobbyDao;

    @Autowired
    Validation validation;

    void addHobby(HobbyDto hobbyDto) throws InvalidUserException {
        validation.validateHobby(hobbyDto);
        hobbyDao.addHobby(hobbyDto);
    }

    List<Hobby> getHobbies(Integer userId) {
        return hobbyDao.getHobbies(userId);
    }

    @Transactional
    void deleteHobby(HobbyDto hobbyDto) {
        hobbyDao.deleteHobby(hobbyDto);
    }
}
