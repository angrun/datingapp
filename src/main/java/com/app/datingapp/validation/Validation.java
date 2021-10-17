package com.app.datingapp.validation;


import com.app.datingapp.exceptions.InvalidUserException;
import com.app.datingapp.hobby.Hobby;
import com.app.datingapp.hobby.HobbyDto;
import com.app.datingapp.user.User;
import com.app.datingapp.utils.Utils;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


import static com.app.datingapp.utils.Utils.BAD_REQUEST;

@Component
public class Validation {

    @PersistenceContext
    public EntityManager em;

    private static final String IMAGE_PATTERN =
            "([^\\s]+(\\.(?i)(jpg|png|jpeg))$)";

    public void validateUserRegistration(User user) throws InvalidUserException {

        regEmailExists(user.getEmail());


        if (!user.getPassword().equals(user.getPassword2())) {
            throw new InvalidUserException("Passwords do not match", BAD_REQUEST);
        }

        int userAge = Utils.getUserAge(user.getBirth(), LocalDate.now());

        if (userAge >= 150 || userAge <= 8) {
            throw new InvalidUserException("Not supported age for user", BAD_REQUEST);
        }
    }

    private void regEmailExists(String email) throws InvalidUserException {
        TypedQuery<User> query1 = em.createQuery("SELECT u FROM User u WHERE u.email = :email", User.class);
        query1.setParameter("email", email);

        if (!query1.getResultList().isEmpty()) {
            throw new InvalidUserException("Person with such email already exists", BAD_REQUEST);
        }
    }

    public void updateEmailExists(String email, Long id) throws InvalidUserException {
        TypedQuery<User> query1 = em.createQuery("SELECT u FROM User u WHERE u.email = :email AND u.id <> :id", User.class);
        query1.setParameter("email", email);
        query1.setParameter("id", id);

        if (!query1.getResultList().isEmpty()) {
            throw new InvalidUserException("Person with such email already exists", BAD_REQUEST);
        }
    }


    public void validateImage(String image) throws InvalidUserException {

        Pattern pattern = Pattern.compile(IMAGE_PATTERN);
        Matcher matcher = pattern.matcher(image);
        if (!matcher.matches()) {
            throw new InvalidUserException("Allowed images formats: .jpg .png .jpeg", BAD_REQUEST);
        }
    }

    public void validateHobby(HobbyDto hobbyDto) throws InvalidUserException {


        TypedQuery<Hobby> query1 = em.createQuery("SELECT h FROM Hobby h WHERE h.userId = :userId AND h.name = :name", Hobby.class);
        query1.setParameter("userId", hobbyDto.getUserId());
        query1.setParameter("name", hobbyDto.getHobby());


        if (!query1.getResultList().isEmpty()) {
            throw new InvalidUserException("You have already declared such hobby", BAD_REQUEST);
        }
    }
}


