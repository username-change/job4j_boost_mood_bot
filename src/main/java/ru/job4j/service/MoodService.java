package ru.job4j.service;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.BeanNameAware;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import ru.job4j.content.Content;
import ru.job4j.content.RecommendationEngine;
import ru.job4j.model.MoodLog;
import ru.job4j.model.User;
import ru.job4j.repository.AchievementRepository;
import ru.job4j.repository.MoodLogRepository;
import ru.job4j.repository.UserRepository;

@Component
public class MoodService {
	private final MoodLogRepository moodLogRepository;
	private final RecommendationEngine recommendationEngine;
	private final UserRepository userRepository;
	private final AchievementRepository achievementRepository;
	private final DateTimeFormatter formatter = DateTimeFormatter
			.ofPattern("dd-MM-yyyy HH:mm")
			.withZone(ZoneId.systemDefault());

	public MoodService(MoodLogRepository moodLogRepository,
			RecommendationEngine recommendationEngine,
			UserRepository userRepository,
			AchievementRepository achievementRepository) {
		this.moodLogRepository = moodLogRepository;
		this.recommendationEngine = recommendationEngine;
		this.userRepository = userRepository;
		this.achievementRepository = achievementRepository;
	}

	public Content chooseMood(User user, Long moodId) {
		return recommendationEngine.recommendFor(user.getChatId(), moodId);
	}

	public Optional<Content> weekMoodLogCommand(long chatId, Long clientId) {
		var content = new Content(chatId);
		return Optional.of(content);
	}

	public Optional<Content> monthMoodLogCommand(long chatId, Long clientId) {
		var content = new Content(chatId);
		return Optional.of(content);
	}

	private String formatMoodLogs(List<MoodLog> logs, String title) {
		if (logs.isEmpty()) {
			return title + ":\nNo mood logs found.";
		}
		var sb = new StringBuilder(title + ":\n");
		logs.forEach(log -> {
			String formattedDate = formatter.format(Instant.ofEpochSecond(log.getCreatedAt()));
			sb.append(formattedDate).append(": ").append(log.getMood().getText()).append("\n");
		});
		return sb.toString();
	}

	public Optional<Content> awards(long chatId, Long clientId) {
		var content = new Content(chatId);
		return Optional.of(content);
	}
}
