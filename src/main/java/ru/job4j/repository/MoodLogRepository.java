package ru.job4j.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ru.job4j.model.MoodLog;

@Repository
public interface MoodLogRepository extends CrudRepository<MoodLog, Long> {
	List<MoodLog> findAll();
}
