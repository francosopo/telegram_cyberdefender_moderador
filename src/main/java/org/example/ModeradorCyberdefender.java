package org.example;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.config.ConfigService;
import org.json.JSONObject;
import org.serializers.FilterResponse;
import org.serializers.QueryString;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.HashMap;

public class ModeradorCyberdefender extends TelegramLongPollingBot {

    private final ConfigService configService;
    private final HttpClient httpClient;
    ModeradorCyberdefender()
    {
        this.configService = ConfigService.getConfigService();
        this.httpClient = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_1_1)
                .build();
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
        System.out.println(text);
        sendToAnalyze(text, chatID);

    }

    private void sendToAnalyze(String text, Long chatId)
    {
        QueryString data = new QueryString(text,chatId);
        ObjectMapper objData = new ObjectMapper();
        try{
            String json = objData.writeValueAsString(data);
            System.out.println(json);
            HttpRequest req = HttpRequest.newBuilder()
                    .uri(URI.create(this.configService.getParameter("URL_FILTER")))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(json))
                    .build();
            HttpClient client = HttpClient.newBuilder()
                    .version(HttpClient.Version.HTTP_1_1)
                    .build();
            HttpResponse<String> response = client.send(req, HttpResponse.BodyHandlers.ofString());
            int statusCode = response.statusCode();
            System.out.println(response.body());
            if (statusCode != 200)
            {
                System.out.println("Could not send " + text);
                return;
            }
            System.out.println("Sent successfully");
            ObjectMapper mapResponse = new ObjectMapper();
            FilterResponse filterResponse = mapResponse.readValue(response.body(), FilterResponse.class);

            if (!filterResponse.getClase().equals("NO"))
            {
                Long[] responsible = filterResponse.getResponsibles();
                for (int i = 0; i < filterResponse.getResponsibles().length; i++)
                {
                    sendText(responsible[i], "Su pupilo fue víctima de lenguaje ofensivo");
                }
            }

        } catch (JsonProcessingException e) {
            System.out.println("Error: " + e);
        } catch (IOException | InterruptedException e) {
            System.out.println("" + e);
        }
    }

    public void sendText(Long who, String what)
    {
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
        try {
            execute(sm);
        }catch(TelegramApiException e)
        {
            throw new RuntimeException(e);
        }
    }
}
