package com.example.restfulapi.api;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;

public class UserHelper {
    private static final String API_KEY = "133e7297b4c9feffbb043a19b436ba57";

    public static String normalizeName(String name) {
        String[] splitString = name.split(" ");
        StringBuffer formatString = new StringBuffer();
        for(String word : splitString) {
            formatString.append(StringUtils.capitalize(word)).append(" ");
        }
        return formatString.toString().trim();
    }


    public static int calculateAge(String dateOfBirth) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate formatedDateOfBirth = LocalDate.parse(dateOfBirth, formatter);
        return Period.between(formatedDateOfBirth, LocalDate.now()).getYears();
    }    

    public static String validateEmail(String email) {
        String emailRegex = "[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}";
        if (email.matches(emailRegex)) {
            return email;
        }
        else {
            return "email address is not valid";
        }
    }

    public static String phoneRegion(String phoneNumber) {
        String apiUrl = "http://apilayer.net/api/validate?access_key=" + API_KEY + "&number=" + phoneNumber;
        String region = "";
        try {
            URI uri = new URI(apiUrl);
            HttpURLConnection conn = (HttpURLConnection) uri.toURL().openConnection();
            conn.setRequestMethod("GET");
            conn.connect();
            Scanner scanner = new Scanner(conn.getInputStream());
            StringBuilder response = new StringBuilder();
            while (scanner.hasNextLine()) {
                response.append(scanner.nextLine());
            }
            scanner.close();
            JSONObject jsonObject = new JSONObject(response.toString());
            if (jsonObject.getBoolean("valid")) {
                region = jsonObject.getString("country_name");
            }
            else {
                region = "unknown";
            }
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
        return region;
    }

    public static File createTxtFile(String name, int age, String email, String phoneRegion, String address) {
        try {
            File txtFile = new File("account_info.txt");

            FileWriter fileWriter = new FileWriter(txtFile);

            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            bufferedWriter.write("Tên: " + name);
            bufferedWriter.newLine(); 
            bufferedWriter.write("Tuổi: " + age);
            bufferedWriter.newLine();
            bufferedWriter.write("Địa chỉ: " + address);
            bufferedWriter.newLine();
            bufferedWriter.write("Email: " + email);
            bufferedWriter.newLine();
            bufferedWriter.write("Số điện thoại: " + phoneRegion);

            bufferedWriter.close();

            return txtFile;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }


}
