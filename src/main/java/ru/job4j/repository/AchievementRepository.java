package ru.job4j.repository;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

public class AchievementRepository {
	
    @PostConstruct
    public void init() {
        System.out.println("Bean is going through init.");
    }

    @PreDestroy
    public void destroy() {
        System.out.println("Bean will be destroyed now.");
    }
}
