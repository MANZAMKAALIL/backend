package com.example.demo.Util;

import java.util.Base64;

public class Base64Encoder {
    public static void main(String[] args) {
        String rawSecretKey = "mySecretKey12345";  // Replace this with your actual raw secret key
        String base64EncodedKey = Base64.getEncoder().encodeToString(rawSecretKey.getBytes());
        System.out.println("Base64 Encoded Secret Key: " + base64EncodedKey);
    }
}
