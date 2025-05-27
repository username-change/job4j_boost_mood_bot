package ru.job4j.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ru.job4j.model.Achievement;

@Repository
public interface AchievementRepository extends CrudRepository<Achievement, Long> {
	List<Achievement> findAll();
}
