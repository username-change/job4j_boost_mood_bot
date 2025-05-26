package ru.job4j.content;

import java.io.File;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.InputFile;

@Component
public class ContentProviderAudio implements ContentProvider {
	@Override
	public Content byMood(Long chatId, Long moodId) {
		var content = new Content(chatId);
		content.setAudio(new InputFile(new File("./audio/music.mp3")));
		return content;
	}
}
