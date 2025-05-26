package ru.job4j.content;

import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

@Service
public class RecommendationEngine {
	private final List<ContentProvider> contents;
	private static final Random RND = new Random(System.currentTimeMillis());

	public RecommendationEngine(List<ContentProvider> contents) {
		this.contents = contents;
	}

	public Content recommendFor(Long chatId, Long moodId) {
		var index = RND.nextInt(0, contents.size());
		return contents.get(index).byMood(chatId, moodId);
	}

	@PostConstruct
	public void init() {
		System.out.println("Bean is going through init.");
	}

	@PreDestroy
	public void destroy() {
		System.out.println("Bean will be destroyed now.");
	}

}
