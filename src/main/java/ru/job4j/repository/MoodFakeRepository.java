package ru.job4j.repository;

import java.util.ArrayList;
import java.util.List;
import org.springframework.test.fake.CrudRepositoryFake;

import ru.job4j.model.Mood;

public class MoodFakeRepository 
		extends CrudRepositoryFake<Mood, Long>
		implements MoodRepository {

	public List<Mood> findAll() {
		return new ArrayList<>(memory.values());
	}
}
