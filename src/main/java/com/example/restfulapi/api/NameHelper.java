package com.example.restfulapi.api;

import org.apache.commons.lang3.StringUtils;

public class HelperName {
    public static String formatName(String firstName, String midName, String lastName) {
        return String.join(" ", StringUtils.capitalize(lastName), StringUtils.capitalize(midName), StringUtils.capitalize(firstName));
    }

    public static boolean isValidName(String name) {
        for (int i = 0; i < name.length(); i++) {
            char c = name.charAt(i);
            if ((c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z')) {
                continue;
            } else {
                return false;
            }
        }
        return true;
    }
}
