package ru.job4j.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

import ru.job4j.repository.MoodFakeRepository;
import ru.job4j.repository.MoodRepository;

@SpringBootTest
@ContextConfiguration(classes = { TgUI.class, MoodFakeRepository.class })
class TgUITest {
	@Autowired
	@Qualifier("moodFakeRepository")
	private MoodRepository moodRepository;

	@Autowired
	private TgUI tgUI;

	@Test
	public void whenBtnGood() {
		assertThat(moodRepository).isNotNull();
	}

	@Test
	void keyboardSizeMatchesMoods() {
	    int expectedRows = moodRepository.findAll().size();
		InlineKeyboardMarkup markup = tgUI.buildButtons();
		assertThat(markup.getKeyboard()).hasSize(expectedRows);
	}
}
