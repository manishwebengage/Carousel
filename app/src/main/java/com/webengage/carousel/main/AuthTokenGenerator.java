package com.webengage.carousel.main;

import com.google.auth.oauth2.GoogleCredentials;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AuthTokenGenerator {


    public static void main(String[] args) {
        try {
            System.out.println(getAccessToken());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void getToken(){
        try {
            System.out.println(getAccessToken());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private static String getAccessToken() throws IOException {
        List<String> SCOPES = new ArrayList<>();
        SCOPES.add("https://www.googleapis.com/auth/firebase.messaging");
        GoogleCredentials googleCredentials = GoogleCredentials
                .fromStream(new FileInputStream("/Users/manishverma/Downloads/prod-69bc0-firebase-adminsdk-i9hyw-3d34e5cf78.json"))
                .createScoped(SCOPES);
        googleCredentials.refresh();
        return googleCredentials.getAccessToken().getTokenValue();
    }

}
