package service.tokenizer;
import util.FileUtilities;
import util.RussianTokenizerSettings;

import java.util.regex.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class RussianTextFileTokenizer implements AbstractTokenizer{
    @Override
    public List<String> tokenize(File file) {
        String strFile = FileUtilities.fileToString(file);
        List<String> tokens = new ArrayList<>();
        Matcher matcher = RussianTokenizerSettings.WORD_PATTERN.matcher(strFile);
        while(matcher.find()){
            String word = matcher.group(); // Слово, которое вытащили из текста по паттерну
            word = wordNormalizer(word);
            if(!RussianTokenizerSettings.STOP_WORDS.contains(word) && word.length() > 2){
                tokens.add(word);
            }
        }
        return tokens;
    }

    private String endingIgnore(String word){
        for (String ending : RussianTokenizerSettings.ENDINGS) {
            if(word.endsWith(ending) && word.length() > ending.length()+2){
                return word.substring(0, word.length()-ending.length());
            }
        }
        return word;
    }
    private String wordNormalizer(String word){
        word = word.replace('ё', 'е');
        word = endingIgnore(word);
        return word;
    }
}
