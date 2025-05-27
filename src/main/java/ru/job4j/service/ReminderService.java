package ru.job4j.service;

import org.springframework.beans.factory.BeanNameAware;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import ru.job4j.repository.UserRepository;

@Service
public class ReminderService implements BeanNameAware {
	private final TgRemoteService tgRemoteService;
	private final UserRepository userRepository;

	public ReminderService(TgRemoteService tgRemoteService, UserRepository userRepository) {
		this.tgRemoteService = tgRemoteService;
		this.userRepository = userRepository;
	}

	@Scheduled(fixedRateString = "${remind.period}")
	public void ping() {
		for (var user : userRepository.findAll()) {
			var message = new SendMessage();
			message.setChatId(user.getChatId());
			message.setText("Ping");
			tgRemoteService.send(message);
		}
	}

	@Override
	public void setBeanName(String name) {
		System.out.println("Bean name: " + name);
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
