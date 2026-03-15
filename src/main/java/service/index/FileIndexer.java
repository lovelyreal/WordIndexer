package service.index;

import service.tokenizer.AbstractTokenizer;
import util.FileUtilities;

import java.io.File;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;



public class FileIndexer implements AbstractIndexer {
    private final AbstractTokenizer tokenizer;
    private final ConcurrentHashMap<String, Set<File>> fileIndex = new ConcurrentHashMap<>();

    public FileIndexer(AbstractTokenizer abstractTokenizer) {
        this.tokenizer = abstractTokenizer;
    }

    @Override
    public void indexFile(File file) {
        List<String> tokens = tokenizer.tokenize(file);
        if (tokens.isEmpty()) {
            return;
        }
        for (String token : tokens) {
            fileIndex
                    .computeIfAbsent(token, k -> ConcurrentHashMap.newKeySet())
                    .add(file);
        }
    }

    @Override
    public Set<File> search(String word) {
        word = word.toLowerCase();
        word = FileUtilities.wordNormalizer(word);
        return fileIndex.getOrDefault(word, Set.of());
    }
}
