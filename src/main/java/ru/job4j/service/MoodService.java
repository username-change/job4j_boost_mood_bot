package ru.job4j.service;

import org.springframework.beans.factory.BeanNameAware;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

@Component
public class MoodService implements BeanNameAware {
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
