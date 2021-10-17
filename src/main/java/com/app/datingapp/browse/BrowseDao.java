package com.app.datingapp.browse;


import com.app.datingapp.filter.FilterDto;
import com.app.datingapp.hobby.Hobby;
import com.app.datingapp.image.Image;
import com.app.datingapp.image.ImageDao;
import com.app.datingapp.match.Matching;
import com.app.datingapp.user.User;
import com.app.datingapp.user.UserDto;
import com.app.datingapp.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.io.IOException;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

@Repository
public class BrowseDao {

    @PersistenceContext
    public EntityManager em;

    @Autowired
    Environment environment;

    @Autowired
    ImageDao imageDao;


    List<UserDto> getAllUsers(FilterDto filterDto) throws IOException {

        Integer userId = filterDto.getId();
        String city = filterDto.getCity();
        String country = filterDto.getCountry();
        String gender = filterDto.getGender();
        String hobby = filterDto.getHobby();

        TypedQuery<User> usersQuery;

        if (hobby == null || hobby.equals("")) {
            usersQuery = em.createQuery("SELECT u FROM User u WHERE u.id NOT IN (SELECT m.toUserId from Matching m  WHERE m.fromUserId = :userId) " +
                    "AND u.id <> :userId AND u.city = :city AND u.country = :country AND u.gender = :gender", User.class);
            usersQuery.setParameter("userId", userId);
            usersQuery.setParameter("city", city);
            usersQuery.setParameter("country", country);
            usersQuery.setParameter("gender", gender);


        } else {
            usersQuery = em.createQuery("SELECT u FROM User u WHERE u.id NOT IN (SELECT m.toUserId from Matching m  WHERE m.fromUserId = :userId) " +
                    "AND u.id <> :userId AND u.city = :city AND u.country = :country AND u.gender = :gender " +
                    "AND u.id IN (SELECT h.userId FROM Hobby h WHERE h.name = :hobby)", User.class);
            usersQuery.setParameter("userId", userId);
            usersQuery.setParameter("city", city);
            usersQuery.setParameter("country", country);
            usersQuery.setParameter("gender", gender);
            usersQuery.setParameter("hobby", hobby);

        }

        List<User> users = usersQuery.getResultList();

        List<UserDto> userDto = new LinkedList<>();

        for (User user : users) {

            //IMAGES
            List<Image> userImages = imageDao.getUserImages(user.getId());

            //HOBBIES
            TypedQuery<Hobby> query = em.createQuery("SELECT h FROM Hobby h WHERE h.userId = :userId", Hobby.class);
            query.setParameter("userId", user.getId());
            List<Hobby> hobbies = query.getResultList();

            //AGE
            int age = Utils.getUserAge(user.getBirth(), LocalDate.now());

            userDto.add(new UserDto(
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
                    null));
        }
        return userDto;
    }

    @Transactional
    public void gradeUser(Matching matching) {
        em.persist(matching);
    }
}

