package com.app.datingapp.browse;

import com.app.datingapp.filter.FilterDto;
import com.app.datingapp.match.Matching;
import com.app.datingapp.user.UserDto;
import com.app.datingapp.user.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/browse")
public class BrowseController {


    @Autowired
    BrowseService browseService;

    @Autowired
    UserService userServiceImpl;

    @GetMapping("/all")
    @ApiOperation("Gets all users")
    public List<UserDto> getAllUsers(FilterDto filterDto) throws IOException {
        return browseService.getAllUsers(filterDto);
    }

    @PostMapping("/grade")
    @ApiOperation("Matching certain user")
    public void likeUser(@RequestBody Matching matching) {
        browseService.gradeUser(matching);
    }

    @GetMapping("/id")
    @ApiOperation("Get id of active user")
    public Long getId(@RequestHeader(value="Authorization") String authorization) {
        return userServiceImpl.getUserIdByEmail(authorization);
    }
}
