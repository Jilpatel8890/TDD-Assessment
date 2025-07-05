package com.example;

import java.util.ArrayList;
import java.util.List;

public class StringCal {

    public static int add(String numbers) {
        if (numbers == null || numbers.isEmpty()) {
            return 0;
        }
        
        String delimiter = getDelimiter(numbers);
        String numsPart = getNumbersPart(numbers, delimiter);
        
        return calculateSum(numsPart, delimiter);
    }
    
    private static String getDelimiter(String numbers) {
        if (numbers.startsWith("//")) {
            // Handle custom delimiter
            int delimiterEnd = numbers.indexOf("\n");
            if (delimiterEnd == -1) {
                throw new IllegalArgumentException("Invalid delimiter format");
            }
            return numbers.substring(2, delimiterEnd);
        }
        return "[,\n]";
    }
    
    private static String getNumbersPart(String numbers, String delimiter) {
        if (numbers.startsWith("//")) {
            int newLinePos = numbers.indexOf("\n");
            if (newLinePos == -1 || newLinePos == numbers.length() - 1) {
                return "";  // No numbers after delimiter
            }
            return numbers.substring(newLinePos + 1);
        }
        return numbers;
    }
    
    private static int calculateSum(String numbers, String delimiter) {
        if (numbers.isEmpty()) {
            return 0;
        }
        
        String[] nums = numbers.split("[" + delimiter + "]");
        int sum = 0;
        List<String> negatives = new ArrayList<String>();
        
        for (String num : nums) {
            if (num.trim().isEmpty()) {
                continue;  // Skip empty entries
            }
            try {
                int n = Integer.parseInt(num.trim());
                checkForNegative(n, negatives);
                sum += n;
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Invalid number format: " + num);
            }
        }
        
        handleNegatives(negatives);
        return sum;
    }
    
    private static void checkForNegative(int number, List<String> negatives) {
        if (number < 0) {
            negatives.add(String.valueOf(number));
        }
    }
    
    private static void handleNegatives(List<String> negatives) {
        if (!negatives.isEmpty()) {
            throw new RuntimeException("negative numbers not allowed: " + String.join(",", negatives));
        }
    }
}