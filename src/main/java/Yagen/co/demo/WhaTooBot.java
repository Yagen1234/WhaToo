package Yagen.co.demo;

import lombok.SneakyThrows;
import org.springframework.util.ResourceUtils;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendAudio;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WhaTooBot extends TelegramLongPollingBot {
    @Override
    public String getBotUsername() {
        return BotRegisterData.USERNAME;
    }

    @Override
    public String getBotToken() {
        return BotRegisterData.TOKEN;
    }

    @SneakyThrows
    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String messageText = update.getMessage().getText();
            FileLinks fileLinks = new FileLinks();
            SendMessage message = new SendMessage();
            message.setChatId(update.getMessage().getChatId().toString());
            switch (messageText) {
                case "/start" -> {
                    keyboardMenu(update);
                }
                case "Classical" -> {
                    fileLinks.scanDirectoryForTracks(FileLinks.CLASSICAL_DIRECTORY);
                    String str = FileLinks.getAudioLink(3, FileLinks.CLASSICAL_DIRECTORY);
                    sendMusic(update, str, message);
                }
                case "Country" -> {
                    fileLinks.scanDirectoryForTracks(FileLinks.COUNTRY_DIRECTORY);
                    String str = FileLinks.getAudioLink(3, FileLinks.COUNTRY_DIRECTORY);
                    sendMusic(update, str, message);
                }
                case "Jazz" -> {
                    fileLinks.scanDirectoryForTracks(FileLinks.JAZZ_DIRECTORY);
                    String str = FileLinks.getAudioLink(3, FileLinks.JAZZ_DIRECTORY);
                    sendMusic(update, str, message);
                }
                case "Rock" -> {
                    fileLinks.scanDirectoryForTracks(FileLinks.ROCK_DIRECTORY);
                    String str = FileLinks.getAudioLink(3, FileLinks.ROCK_DIRECTORY);
                    sendMusic(update, str, message);
                }
            }
        }
    }

    private void sendMusic(Update update, String fileName, SendMessage sendMessage) throws FileNotFoundException, TelegramApiException {
        SendAudio sendAudio = new SendAudio();
        sendMessage.setChatId(String.valueOf(update.getMessage().getChatId()));
        sendAudio.setChatId(String.valueOf(update.getMessage().getChatId()));
        sendAudio.setAudio(new InputFile(ResourceUtils.getFile(fileName)));
        sendAudio.setTitle("");
        execute(sendAudio);
    }

    private void keyboardMenu(Update update) {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        SendMessage sendMessage = new SendMessage();
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        replyKeyboardMarkup.setSelective(false);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(true);

        List<KeyboardRow> keyboard = new ArrayList<>();

        KeyboardRow keyboard1Row = new KeyboardRow();
        keyboard1Row.add("Classical");
        keyboard1Row.add("Country");

        KeyboardRow keyboard2Row = new KeyboardRow();
        keyboard2Row.add("Jazz");
        keyboard2Row.add("Rock");

        keyboard.add(keyboard1Row);
        keyboard.add(keyboard2Row);
        replyKeyboardMarkup.setKeyboard(keyboard);

        sendMessage.setChatId(update.getMessage().getChatId().toString());
        sendMessage.setReplyToMessageId(update.getMessage().getMessageId());
        sendMessage.setText("All is working");
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private void keyboardTrackSelect() {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        SendMessage sendMessage = new SendMessage();
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        replyKeyboardMarkup.setSelective(false);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(true);

        List<KeyboardRow> keyboard = new ArrayList<>();

        KeyboardRow keyboard1Row = new KeyboardRow();
        keyboard1Row.addAll(Arrays.asList("1","2","3","4","5","6"));

        KeyboardRow keyboard2Row = new KeyboardRow();
        keyboard2Row.addAll(Arrays.asList("7","8","9","10","11","12"));

        keyboard.add(keyboard1Row);
        keyboard.add(keyboard2Row);
        replyKeyboardMarkup.setKeyboard(keyboard);

    }
}
