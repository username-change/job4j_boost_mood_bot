package ru.job4j.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import ru.job4j.model.User;
import ru.job4j.repository.UserRepository;

@Service
public class TgRemoteService extends TelegramLongPollingBot {

	private final String botName;
	private final String botToken;
    private final UserRepository userRepository;

	public TgRemoteService(@Value("${telegram.bot.name}") String botName,
							@Value("${telegram.bot.token}") String botToken,
							UserRepository userRepository) {
		this.botName = botName;
		this.botToken = botToken;
		this.userRepository = userRepository;
	}

	@Override
	public String getBotToken() {
		return botToken;
	}

	@Override
	public String getBotUsername() {
		return botName;
	}

	@Override
	public void onUpdateReceived(Update update) {
	    if (update.hasMessage() && update.getMessage().hasText()) {
	        var message = update.getMessage();
	        if ("/start".equals(message.getText())) {
	            long chatId = message.getChatId();
	            var user = new User();
	            user.setClientId(message.getFrom().getId());
	            user.setChatId(chatId);
	            userRepository.save(user);
	            send(sendButtons(chatId));
	        }
	    }
	}

	public void send(SendMessage message) {
		try {
			execute(message);
		} catch (TelegramApiException e) {
			e.printStackTrace();
		}
	}

	public SendMessage sendButtons(long chatId) {
		SendMessage message = new SendMessage();
		message.setChatId(chatId);
		return message;
	}
}
