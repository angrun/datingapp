package com.app.datingapp.user;


import com.app.datingapp.hobby.Hobby;
import com.app.datingapp.image.Image;
import com.app.datingapp.message.Message;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    Long id;

    @NotNull(message = "Name is compulsory")
    @NotEmpty(message = "Name is compulsory")
    String name;

    @NotNull(message = "Surname is compulsory")
    @NotEmpty(message = "Surname is compulsory")
    String surname;

    @NotNull(message = "Email is compulsory")
    @NotEmpty(message = "Email is compulsory")
    @Email(message = "Email format is invalid")
    String email;

    @NotNull(message = "City is compulsory")
    @NotEmpty(message = "City is compulsory")
    String city;

    @NotNull(message = "Country is compulsory")
    @NotEmpty(message = "Country is compulsory")
    String country;

    @NotNull(message = "Gender is compulsory")
    @NotEmpty(message = "Gender is compulsory")
    String gender;

    Integer age;

    Integer likes;

    String bio;

    LocalDate registerDate;

    List<Image> image;

    List<Hobby> hobbies;

    Boolean seen;

    Message lastMessage;

    @Override
    public String toString() {
        return "UserDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                ", gender='" + gender + '\'' +
                ", age=" + age +
                ", likes=" + likes +
                ", bio='" + bio + '\'' +
                ", registerDate=" + registerDate +
                ", image=" + image +
                ", hobbies=" + hobbies +
                ", seen=" + seen +
                '}';
    }
}




