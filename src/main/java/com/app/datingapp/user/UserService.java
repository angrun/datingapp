package com.app.datingapp.user;

import com.app.datingapp.exceptions.InvalidUserException;
import com.app.datingapp.hobby.Hobby;
import com.app.datingapp.hobby.HobbyDao;
import com.app.datingapp.image.Image;
import com.app.datingapp.image.ImageDao;
import com.app.datingapp.security.JwtDecoder;
import com.app.datingapp.utils.Utils;
import com.app.datingapp.validation.Validation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;


@Service
public class UserService {

    @Autowired
    UserDao usersDao;

    @Autowired
    ImageDao imageDao;

    @Autowired
    HobbyDao hobbyDao;

    @Autowired
    Validation validation;

    private JwtDecoder decoder = new JwtDecoder();

    public Long getUserIdByEmail(String authorization) {
        return usersDao.getUserByEmail(decoder.getEmailFromToken(authorization)).getId();
    }

    UserDto getUserByEmail(String authorization) throws IOException {
        User user = usersDao.getUserByEmail(decoder.getEmailFromToken(authorization));
        int age = Utils.getUserAge(user.getBirth(), LocalDate.now());

        List<Image> userImages = imageDao.getUserImages(user.getId());
        List<Hobby> hobbies = hobbyDao.getHobbies(Math.toIntExact(user.getId()));

        return new UserDto(
                user.getId(),
                user.getName(),
                user.getSurname(),
                user.getEmail(),
                user.getCity(),
                user.getCountry(),
                user.getGender(),
                age,
                user.getLikes(),
                user.getBio(),
                user.getRegisterDate(),
                userImages,
                hobbies,
                false,
                null);

    }

    User updateUser(UserDto userDto) throws InvalidUserException {
        validation.updateEmailExists(userDto.getEmail(), userDto.getId());
        return usersDao.update(userDto);
    }

    void createFile(MultipartFile file,
                    String authorization) throws InvalidUserException {
        try {
            imageDao.createImage(file, decoder.getEmailFromToken(authorization));
        } catch (IOException e) {
            System.out.println("FAIL");
        }
    }

    Resource createFile() {
        return imageDao.findOneImage("test");
    }

}