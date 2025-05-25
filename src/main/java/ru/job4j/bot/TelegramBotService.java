package ru.job4j.bot;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import ru.job4j.content.Content;

public class TelegramBotService {
	private final BotCommandHandler handler;

	public TelegramBotService(BotCommandHandler handler) {
		this.handler = handler;
	}

	public void receive(Content content) {
		handler.receive(content);
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
