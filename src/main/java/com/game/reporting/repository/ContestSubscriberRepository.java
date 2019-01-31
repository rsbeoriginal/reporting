package com.game.reporting.repository;

import com.game.reporting.dto.ContestSubscriberCountsDTO;
import com.game.reporting.entity.ContestSubscriber;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContestSubscriberRepository extends CrudRepository<ContestSubscriber, String> {
    @Query("SELECT new com.game.reporting.dto.ContestSubscriberCountsDTO(contestId, MIN(contestName), MIN(type), COUNT(*) as subscriberCount) FROM ContestSubscriber GROUP BY contestId ORDER BY subscriberCount")
    List<ContestSubscriberCountsDTO> getSummary();

//    @Query("SELECT NEW com.game.reporting.dto.ContestSubscriberCountsDTO(x.contestId, y.contestName, y.type, x.subscriberCount) " +
//            "FROM (SELECT x.contestId, COUNT(*) AS x.subscriberCount x FROM ContestSubscriber GROUP BY contestId) " +
//            "INNER JOIN ContestSubscriber y ON x.contestId = y.contestId")
//    List<ContestSubscriberCountsDTO> getSummary();
}
