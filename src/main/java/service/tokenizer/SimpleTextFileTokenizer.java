package service.tokenizer;

import util.FileUtilities;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


public class SimpleTextFileTokenizer implements AbstractTokenizer {
    @Override
    public List<String> tokenize(File fileToTokenize) {
        String strFile = FileUtilities.fileToString(fileToTokenize);
        if(strFile.isEmpty()){return null;}
        return Arrays.stream(strFile.split("\\W+"))
                .map(String::toLowerCase)
                .collect(Collectors.toList());
    }
}
