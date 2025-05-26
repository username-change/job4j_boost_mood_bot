package ru.job4j.content;

import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

public class Content {
	private final Long chatId;
	private String text;
	private InputFile photo;
	private InlineKeyboardMarkup markup;
	private InputFile audio;

	public Content(Long chatId) {
		this.chatId = chatId;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public InputFile getPhoto() {
		return photo;
	}

	public void setPhoto(InputFile photo) {
		this.photo = photo;
	}

	public InlineKeyboardMarkup getMarkup() {
		return markup;
	}

	public void setMarkup(InlineKeyboardMarkup markup) {
		this.markup = markup;
	}

	public InputFile getAudio() {
		return audio;
	}

	public void setAudio(InputFile audio) {
		this.audio = audio;
	}

	public Long getChatId() {
		return chatId;
	}

}
