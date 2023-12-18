package org.example;

import org.commands.IdentifierCommand;
import org.commands.NoCommand;
import org.commands.interfaces.ICommand;
import org.config.ConfigService;
import org.example.interfaces.IBot;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
public class ModeradorCyberdefender extends TelegramLongPollingBot implements IBot {

    private final ConfigService configService;
    ModeradorCyberdefender()
    {
        this.configService = ConfigService.getConfigService();
    }
    @Override
    public String getBotUsername() {
        return "cyberdefender_moderador_bot";
    }

    @Override
    public String getBotToken() {
        return this.configService.getParameter("BOT_TOKEN");
    }

    @Override
    public void onUpdateReceived(Update update) {
        var msg = update.getMessage();
        var chatID = msg.getChatId();
        var user = msg.getFrom();
        var text = msg.getText();
        ICommand cmd = this.recognizeText(text, chatID);
        cmd.execute(this);
    }

    public ICommand recognizeText(String text, Long chatId) {
        if (text.equals("/id"))
        {
            return new IdentifierCommand(chatId);
        }
        return new NoCommand(chatId, text);
    }

    public void sendText(Long who, String what)
    {

        try {
            if (who == null)
            {
                throw new RuntimeException("who is null");
            }
            if (what == null)
            {
                throw new RuntimeException("what is null");
            }
            SendMessage sm = SendMessage.builder()
                    .chatId(who.toString())
                    .text(what).build();
            execute(sm);
        }catch(TelegramApiException | RuntimeException e)
        {
            System.out.println("" + e);
        }
    }
}
