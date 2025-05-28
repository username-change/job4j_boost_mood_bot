package ru.job4j.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ru.job4j.model.Award;

@Repository
public interface AwardRepository extends CrudRepository<Award, Long> {
	List<Award> findAll();
}
