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
        System.out.println(fileIndex.toString());
    }

    @Override
    public void indexDirectory(File directory) {
        File[] files = directory.listFiles();
        if (files == null) {
            return;
        }
        for (File file : files) {
            if (file.isFile()) {
                indexFile(file);
            }
        }
    }

    @Override
    public Set<File> search(String word) {
        word = word.toLowerCase();
        word = FileUtilities.wordNormalizer(word);
        System.out.println("word: " + word);
        return fileIndex.getOrDefault(word, Set.of());
    }
}
