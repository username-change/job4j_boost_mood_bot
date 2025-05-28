package ru.job4j.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import ru.job4j.repository.MoodRepository;

@Component
public class TgUI {
	private final MoodRepository moodRepository;

	public TgUI(MoodRepository moodRepository) {
		this.moodRepository = moodRepository;
	}

    public InlineKeyboardMarkup buildButtons() {
        var inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();
        for (var mood : moodRepository.findAll()) {
            keyboard.add(List.of(createBtn(mood.getText(), mood.getId())));
        }
        inlineKeyboardMarkup.setKeyboard(keyboard);
        return inlineKeyboardMarkup;
    }

	public InlineKeyboardButton createBtn(String name, Long moodId) {
		var inline = new InlineKeyboardButton();
		inline.setText(name);
		inline.setCallbackData(String.valueOf(moodId));
		return inline;
	}
}
