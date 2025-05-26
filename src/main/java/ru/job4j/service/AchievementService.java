package ru.job4j.service;

import org.springframework.beans.factory.BeanNameAware;
import org.springframework.stereotype.Component;

@Component
public class AchievementService implements BeanNameAware {
	@Override
	public void setBeanName(String name) {
		System.out.println("Bean name: " + name);
	}
}
