package ru.job4j.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ru.job4j.model.Mood;

public interface MoodLogRepository extends CrudRepository<Mood, Long> {
	List<Mood> findAll();
}
