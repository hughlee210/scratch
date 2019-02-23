package com.hlee.scratch.corejava;

import com.google.common.base.CaseFormat;

import java.util.ArrayList;
import java.util.List;

public class RegexEx {

    public static void main(String[] args) {

        //testStringRegex();

        String input = "CamelCaseToSomethingElse";
        System.out.println(input + " -> " + convertCamelCaseToSnakecase(input));
        System.out.println(input + " -> " + convertUpperCamelcaseToSnakecase(input));
        System.out.println(input + " -> " + split(input));
        System.out.println(input + " -> " + convertUpperCamelToSnake(input));

        input = "FQADistributionMethod";
        System.out.println(input + " -> " + convertCamelCaseToSnakecase(input));
        System.out.println(input + " -> " + convertUpperCamelcaseToSnakecase(input));
        System.out.println(input + " -> " + split(input));
        System.out.println(input + " -> " + convertUpperCamelToSnake(input));

        input = "fqa_distribution_method";
        System.out.println(input + " -> " + split(input));
        System.out.println(input + " -> " + convertUpperCamelToSnake(input));
    }

    private static void testStringRegex() {

        String gmtOffset = "+00:00";
        String val = gmtOffset.replaceAll("\\+|\\-|:", "");
        System.out.println("gmtOffset: " + gmtOffset + ", number only: " + val);

        char sign = gmtOffset.charAt(0) == '-' ? '-' : '+';
        String hour = val.substring(0, 2);
        String minute = val.substring(2, 4);

        int hourNum = Integer.valueOf(sign + hour);
        int minNum = Integer.valueOf(sign + minute);

        System.out.println("hour=" + hourNum + ", minute=" + minNum);
    }

    private static String convertCamelCaseToSnakecase(String input) {
        String regex = "([a-z])([A-Z]+)";
        String replacement = "$1_$2";
        //System.out.println(input.replaceAll(regex, replacement).toLowerCase());
        return input.replaceAll(regex, replacement).toLowerCase();
    }

    private static String convertUpperCamelcaseToSnakecase(String input) {
        //System.out.println(CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, "input_in_snake_case"));

        return CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, input);
    }

    private static final String REGEX_CAMELCASE = "(?<!(^|[A-Z]))(?=[A-Z])|(?<!^)(?=[A-Z][a-z])";
    private static final String REGEX_CAMELCASE_OR_UNDERSCORE = "(?<!(^|[A-Z]))(?=[A-Z])|(?<!^)(?=[A-Z][a-z])|_";

    private static List<String> split(String string) {
        List<String> words = new ArrayList<>();
        for (String word : string.split(REGEX_CAMELCASE_OR_UNDERSCORE)) {
            if (!word.isEmpty()) {
                words.add(word);
            }
        }
        return words;
    }

    private static String convertUpperCamelToSnake(String input) {
        StringBuilder sb = new StringBuilder();
        String[] words = input.split(REGEX_CAMELCASE);
        for (int i = 0; i < words.length; i++) {
            if (i > 0) {
                sb.append('_');
            }
            sb.append(words[i]);
        }

        return sb.toString().toLowerCase();
    }

}
