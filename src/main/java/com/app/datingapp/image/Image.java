package com.app.datingapp.image;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "image", schema = "public")
public class Image {

    @Id
    @GeneratedValue
    Long id;

    @Column
    String name;

    @Column(name = "user_id")
    Long userId;

    @Column(name = "date_created")
    LocalDateTime dateCreated;

    public Image(String name, Long userId, LocalDateTime dateCreated) {
        this.name = name;
        this.userId = userId;
        this.dateCreated = dateCreated;

    }

    @Override
    public String toString() {
        return "Image{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", userId=" + userId +
                ", dateCreated=" + dateCreated +
                '}';
    }
}
