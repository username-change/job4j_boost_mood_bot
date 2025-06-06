package ru.job4j.service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import ru.job4j.content.Content;
import ru.job4j.content.RecommendationEngine;
import ru.job4j.model.Award;
import ru.job4j.model.Mood;
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
    	Mood mood = new Mood();
    	mood.setId(moodId);
        long currentTimeFormat = LocalDateTime.now().toEpochSecond(ZoneOffset.UTC);
        
        MoodLog moodLog = new MoodLog();
        moodLog.setUser(user);
        moodLog.setMood(mood);
        moodLog.setCreatedAt(currentTimeFormat);
        moodLogRepository.save(moodLog);
        return recommendationEngine.recommendFor(user.getChatId(), moodId);
    }

	public Optional<Content> weekMoodLogCommand(long chatId, Long clientId) {
	    long weekAgo = LocalDateTime.now().minusWeeks(1).toEpochSecond(ZoneOffset.UTC);
	    
	    List<MoodLog> moodLogs = moodLogRepository.findAll().stream()
                .filter(moodLog -> moodLog.getUser().getClientId() == clientId)
	            .filter(moodLog -> moodLog.getCreatedAt() >= weekAgo)
	            .toList();

	    var content = new Content(chatId);
	    content.setText(formatMoodLogs(moodLogs, "Ваши настроения за последнюю неделю"));
	    return Optional.of(content);
	}

	public Optional<Content> monthMoodLogCommand(long chatId, Long clientId) {
		LocalDateTime currentTime = LocalDateTime.now();
	    LocalDateTime mounthAgo = currentTime.minusMonths(1);
	    long monthAgoFormat = mounthAgo.toEpochSecond(ZoneOffset.UTC);
	    
	    List<MoodLog> moodLogs = moodLogRepository.findAll().stream()
                .filter(moodLog -> moodLog.getUser().getClientId() == clientId)
                .filter(moodLog -> moodLog.getCreatedAt() >= monthAgoFormat)
                .toList();

        var content = new Content(chatId);
        content.setText(formatMoodLogs(moodLogs, "Ваши настроения за последний месяц"));
        return Optional.of(content);
	}

	private String formatMoodLogs(List<MoodLog> logs, String title) {
		if (logs.isEmpty()) {
			return title + ":\nНет записей о настроении";
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
		List<Award> awards = achievementRepository.findAll().stream()
				.filter(achievement -> achievement.getUser().getClientId() == clientId)
				.map(achievement -> achievement.getAward())
				.toList();
		
	    if (awards.isEmpty()) {
	        content.setText("Ваши достижения:\nНет записей о достижениях");
	    } else {
	        var sb = new StringBuilder("Ваши достижения:\n");
	        awards.forEach(award -> sb.append(award.getTitle()).append(": ").append(award.getDescription()).append("\n"));	        
	        content.setText(sb.toString());
	    }
	    return Optional.of(content);
	}
}
