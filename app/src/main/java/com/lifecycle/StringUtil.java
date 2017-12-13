package com.lifecycle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class StringUtil {

    public static String justify(String phrase, int formattedPhraseWidth) throws IllegalArgumentException {
        if (phrase.length() > formattedPhraseWidth) {
            throw new IllegalArgumentException("margin width does not extend phrase width");
        }

        int spacesToAdd = formattedPhraseWidth - phrase.length();
        StringBuilder builder = new StringBuilder(phrase);
        Collection<String> foundWords = new ArrayList(Arrays.asList(phrase.split(" ")));
        for (int i = 0; i < spacesToAdd; i++) {
            for (String entry : foundWords) {
                int indexOf = builder.indexOf(entry);
                if (indexOf > 0) {
                    builder.insert(indexOf, ' ');
                }
                if (builder.length() == formattedPhraseWidth) {
                    return builder.toString();
                }
            }
        }
        return builder.toString();
    }
}
