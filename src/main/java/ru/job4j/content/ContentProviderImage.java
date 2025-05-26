package ru.job4j.content;

import java.io.File;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.InputFile;

@Component
public class ContentProviderImage implements ContentProvider {
	@Override
	public Content byMood(Long chatId, Long moodId) {
		var content = new Content(chatId);
		var imageFile = new File("./images/logo.png");
		content.setPhoto(new InputFile(imageFile));
		return content;
	}
}
