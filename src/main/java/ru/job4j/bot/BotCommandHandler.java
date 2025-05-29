package ru.job4j.bot;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;

import ru.job4j.content.Content;
import ru.job4j.model.User;
import ru.job4j.repository.UserRepository;
import ru.job4j.service.MoodService;
import ru.job4j.service.TgUI;

@Service
public class BotCommandHandler {
	private final UserRepository userRepository;
	private final MoodService moodService;
	private final TgUI tgUI;

	public BotCommandHandler(UserRepository userRepository, MoodService moodService, TgUI tgUI) {
		this.userRepository = userRepository;
		this.moodService = moodService;
		this.tgUI = tgUI;
	}

	Optional<Content> commands(Message message) {
		if ("/start".equals(message.getText())) {
			return handleStartCommand(message.getChatId(), message.getFrom().getId());
		} else if ("/week_mood_log".equals(message.getText())) {
			return moodService.weekMoodLogCommand(message.getChatId(), message.getFrom().getId());
		} else if ("/month_mood_log".equals(message.getText())) {
			return moodService.monthMoodLogCommand(message.getChatId(), message.getFrom().getId());
		} else if ("/award".equals(message.getText())) {
			return moodService.awards(message.getChatId(), message.getFrom().getId());
		}
		return Optional.empty();
	}

	Optional<Content> handleCallback(CallbackQuery callback) {
		var moodId = Long.valueOf(callback.getData());
		var user = userRepository.findById(callback.getFrom().getId());
		return user.map(value -> moodService.chooseMood(value, moodId));
	}

	private Optional<Content> handleStartCommand(long chatId, Long clientId) {
		var user = new User();
		user.setClientId(clientId);
		user.setChatId(chatId);
		userRepository.save(user);
		var content = new Content(user.getChatId());
		content.setText("Как настроение?");
		content.setMarkup(tgUI.buildButtons());
		return Optional.of(content);
	}
}
