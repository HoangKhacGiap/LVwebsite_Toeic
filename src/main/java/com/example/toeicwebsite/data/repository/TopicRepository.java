package com.example.toeicwebsite.data.repository;

import com.example.toeicwebsite.data.entity.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface TopicRepository extends JpaRepository<Topic, Long>{
//    @Query("SELECT t FROM Topic t " +
//            "JOIN Level l ON t.level.id = l.id " +
//            "JOIN Part p ON t.part.id = p.id " +
//            "WHERE " +
//            "       (l.name LIKE %:levelName%) " +
//            "AND    (p.name LIKE %:partName%) " +
//            "ORDER BY t.id DESC")
//    Page<Topic> filterTopic(@Param("levelName") String levelname,
//                            @Param("partName") String partname,
//                            @Param("numberOfTopic") int numbertopic);
    @Query("SELECT t FROM Topic t WHERE t.part.id = :partId AND t.level.name = :levelOfTopic")
    List<Topic> findAllByLevelNameAndPartId(@Param("partId") Long partId,
                                        @Param("levelOfTopic") String levelOfTopic);
}
