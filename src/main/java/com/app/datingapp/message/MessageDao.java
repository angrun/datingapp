package com.app.datingapp.message;


import com.app.datingapp.user.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Query;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.time.LocalDateTime;
import java.util.List;

@Repository
@Transactional
public class MessageDao {

    @PersistenceContext
    public EntityManager em;


    @Transactional
    void sendMessage(MessageDto messageDto) {

        Message message = new Message();

        message.setFromUserId(messageDto.getFromUserId());
        message.setToUserId(messageDto.getToUserId());
        message.setMessage(messageDto.getMessage());
        message.setDateSent(LocalDateTime.now());
        message.setMessageSeen(false);

        em.persist(message);
    }

    @Transactional
    List<Message> getMessages(String email, Integer friendId) {

        TypedQuery<User> query1 = em.createQuery("SELECT u FROM User u WHERE u.email = :email", User.class);
        query1.setParameter("email", email);
        User client = query1.getResultList().get(0);
        int userId = client.getId().intValue();


        TypedQuery<Message> query = em.createQuery("SELECT m FROM Message m WHERE (m.fromUserId = :friendId " +
                "AND m.toUserId = :userId) OR (m.fromUserId = :userId  AND m.toUserId = :friendId) ORDER BY m.dateSent", Message.class);
        query.setParameter("friendId", Long.valueOf(friendId));
        query.setParameter("userId", (long) userId);

        Query query2 = em.createQuery("UPDATE Matching m SET m.seen = TRUE WHERE m.fromUserId = :userId and m.toUserId = :friendId");
        query2.setParameter("friendId", friendId);
        query2.setParameter("userId", userId);
        query2.executeUpdate();


        return query.getResultList();

    }

    @Transactional
    void updateMessagesToBeSeen(String email, Integer friendId) {


        TypedQuery<User> query1 = em.createQuery("SELECT u FROM User u WHERE u.email = :email", User.class);
        query1.setParameter("email", email);
        User client = query1.getResultList().get(0);
        int userId = client.getId().intValue();

        Query query3 = em.createQuery("UPDATE Message m SET m.messageSeen = TRUE WHERE m.fromUserId = :friendId and m.toUserId = :userId");
        query3.setParameter("friendId", (long) friendId);
        query3.setParameter("userId", (long) userId);
        query3.executeUpdate();
    }
}
