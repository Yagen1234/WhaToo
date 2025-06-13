package Yagen.co.demo;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@SpringBootApplication
public class WhaTooApplication {
	public static void main(String[] args) {
		TelegramBotsApi telegramBotsApi;
		try {
			telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
			telegramBotsApi.registerBot(new WhaTooBot());
		} catch (TelegramApiException e) {
			e.printStackTrace();
		}
	}
}
