package service.tokenizer;
import util.FileUtilities;
import util.RussianTokenizerSettings;

import java.util.regex.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static util.FileUtilities.wordNormalizer;

public class RussianTextFileTokenizer implements AbstractTokenizer{
    @Override
    public List<String> tokenize(File file) {
        String strFile = FileUtilities.fileToString(file);
        List<String> tokens = new ArrayList<>();
        Matcher matcher = RussianTokenizerSettings.WORD_PATTERN.matcher(strFile);
        while(matcher.find()){
            String word = matcher.group(); // Слово, которое вытащили из текста по паттерну
            word = FileUtilities.wordNormalizer(word);
            if(!RussianTokenizerSettings.STOP_WORDS.contains(word) && word.length() > 2){
                tokens.add(word);
            }
        }
        return tokens;
    }
    @Override
    public List<String> tokenizeWord(String word) {
        word = word.toLowerCase();
        word = wordNormalizer(word);
        return List.of(word);
    }


}
