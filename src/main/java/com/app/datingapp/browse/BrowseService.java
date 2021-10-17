package com.app.datingapp.browse;

import com.app.datingapp.filter.FilterDto;
import com.app.datingapp.match.Matching;
import com.app.datingapp.user.UserDao;
import com.app.datingapp.user.UserDto;
import org.springframework.stereotype.Service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;


@Service
public class BrowseService {

    @Autowired
    BrowseDao browseDao;

    @Autowired
    UserDao usersDao;

    List<UserDto> getAllUsers(FilterDto filterDto) throws IOException {
        return browseDao.getAllUsers(filterDto);
    }

    void gradeUser(Matching matching) {
        browseDao.gradeUser(matching);
    }

}