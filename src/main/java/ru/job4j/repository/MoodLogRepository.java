package ru.job4j.repository;

import java.util.List;
import java.util.stream.Stream;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ru.job4j.model.MoodLog;
import ru.job4j.model.User;

@Repository
public interface MoodLogRepository extends CrudRepository<MoodLog, Long> {
	List<MoodLog> findAll();
	
    List<MoodLog> findByUserId(Long userId);

    Stream<MoodLog> findByUserIdOrderByCreatedAtDesc(Long userId);
    
    List<User> findUsersWhoDidNotVoteToday(long startOfDay, long endOfDay);
    
    List<MoodLog> findMoodLogsForWeek(Long userId, long weekStart);
 
    List<MoodLog> findMoodLogsForMonth(Long userId, long monthStart);
}
