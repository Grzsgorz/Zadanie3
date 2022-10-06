package com.company;

import org.json.JSONObject;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class Main {
    public static String text(String response){
        JSONObject jsonObject = new JSONObject(response);
        String quote = jsonObject.getString("quote");
        System.out.println(quote);

        return null;
    }
    public static void api(){
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create("https://api.kanye.rest/")).build();
        client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenApply(Main::text)
                .join();
    }
    public static void main(String[] args) {
        boolean run = true;
        Scanner scanner = new Scanner(System.in);
        api();
        while(run) {
            String input = scanner.next();
            if(input.equals("next")) {
                api();
                continue;
            }else{
                break;
            }
        }

    }

}
