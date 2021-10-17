package com.app.datingapp.hobby;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "hobby", schema = "public")
public class Hobby {


    @Id
    @GeneratedValue
    Long id;

    @Column(name = "user_id")
    Long userId;

    @Column
    String name;

    @Override
    public String toString() {
        return "Hobby{" +
                "id=" + id +
                ", userId=" + userId +
                ", name='" + name + '\'' +
                '}';
    }
}
