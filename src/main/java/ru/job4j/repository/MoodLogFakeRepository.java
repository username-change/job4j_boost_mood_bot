package ru.job4j.repository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.stereotype.Repository;
import org.springframework.test.fake.CrudRepositoryFake;

import ru.job4j.model.MoodLog;
import ru.job4j.model.User;

@Repository
public class MoodLogFakeRepository 
		extends CrudRepositoryFake<MoodLog, Long> 
		implements MoodLogRepository {
	
	public List<MoodLog> findAll() {
        return new ArrayList<>(memory.values());
    }

    @Override
    public List<MoodLog> findByUserId(Long userId) {
        return memory.values().stream()
                .filter(moodLog -> moodLog.getUser().getId().equals(userId))
                .collect(Collectors.toList());
    }

    @Override
    public Stream<MoodLog> findByUserIdOrderByCreatedAtDesc(Long userId) {
        return memory.values().stream()
                .filter(moodLog -> moodLog.getUser().getId().equals(userId))
                .sorted(Comparator.comparing(MoodLog::getCreatedAt).reversed());
    }

    @Override
    public List<User> findUsersWhoDidNotVoteToday(long startOfDay, long endOfDay) {
        return memory.values().stream()
                .filter(moodLog -> moodLog.getCreatedAt() < startOfDay)
                .map(MoodLog::getUser)
                .distinct()
                .collect(Collectors.toList());
    }

    @Override
    public List<MoodLog> findMoodLogsForWeek(Long userId, long weekStart) {
        return memory.values().stream()
                .filter(moodLog -> moodLog.getUser().getId().equals(userId))
                .filter(moodLog -> moodLog.getCreatedAt() >= weekStart)
                .collect(Collectors.toList());
    }

    @Override
    public List<MoodLog> findMoodLogsForMonth(Long userId, long monthStart) {
        return memory.values().stream()
                .filter(moodLog -> moodLog.getUser().getId().equals(userId))
                .filter(moodLog -> moodLog.getCreatedAt() >= monthStart)
                .collect(Collectors.toList());
    }
}
