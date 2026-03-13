package service.tokenizer;

import java.io.File;
import java.util.List;

public interface AbstractTokenizer {
    List<String> tokenize(File file);
    List<String> tokenizeWord(String word);
}
