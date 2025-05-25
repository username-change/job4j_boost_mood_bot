package ru.job4j.bot;

import ru.job4j.content.Content;

public class TelegramBotService {
	private final BotCommandHandler handler;

	public TelegramBotService(BotCommandHandler handler) {
		this.handler = handler;
	}

	public void receive(Content content) {
		handler.receive(content);
	}
}
