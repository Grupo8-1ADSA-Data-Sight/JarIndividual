package util;

import org.json.JSONObject;

import java.io.IOException;

public class MainSlack {
    public static void main(String[] args) throws IOException, InterruptedException {
        JSONObject json = new JSONObject();

        json.put("text","Mensagem desejada");

        Slack.sendMessage(json);

    }
}
