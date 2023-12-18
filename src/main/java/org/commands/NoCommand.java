package org.commands;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.commands.interfaces.ICommand;
import org.config.ConfigService;
import org.example.interfaces.IBot;
import org.offensive.Classifier;
import org.offensive.interfaces.IClassifier;
import org.serializers.FilterResponse;
import org.serializers.QueryString;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class NoCommand extends AbstractCommand implements ICommand {
    private final String text;
    private final ConfigService configService = ConfigService.getConfigService();
    private final IClassifier classifier;
    public NoCommand(Long chatId,String text) {
        super(false, "");
        this.setChatId(chatId);
        this.text = text;
        this.classifier = Classifier.getClassifier();
    }

    @Override
    public void execute(IBot bot) {
        QueryString data = new QueryString(text,this.getChatId());
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
                System.out.println(response.body());
                System.out.println("Could not send " + text);
                return;
            }
            System.out.println("Sent successfully");
            ObjectMapper mapResponse = new ObjectMapper();
            FilterResponse filterResponse = mapResponse.readValue(response.body(), FilterResponse.class);
            this.classifier.classify(filterResponse, bot);

        } catch (JsonProcessingException e) {
            System.out.println("Error: " + e);
        } catch (IOException | InterruptedException e) {
            System.out.println("" + e);
        }
    }
}
