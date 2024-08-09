package com.example.repository;

import com.example.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long>{

    @Query("FROM Message WHERE messageId = :messageid")
    Message findMessageById(@Param("messageid") int messageId);

    @Query("FROM Message WHERE postedBy = :postedby")
    List<Message> findMessageByPoster(@Param("postedby") int postedBy);

    
}
