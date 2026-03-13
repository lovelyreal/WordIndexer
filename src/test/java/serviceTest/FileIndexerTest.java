package serviceTest;

import org.junit.jupiter.api.Test;
import service.index.FileIndexer;
import service.tokenizer.SimpleTextFileTokenizer;

import java.io.*;
import java.util.*;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

public class FileIndexerTest {

    @Test
    void shouldIndexFileAndFindWord() throws Exception {

        File tempFile1 = File.createTempFile("test1", ".txt");
        File tempFile2 = File.createTempFile("test2", ".txt");
        File tempFile3 = File.createTempFile("test3", ".txt");

        try (FileWriter writer1 = new FileWriter(tempFile1);
             FileWriter writer2 = new FileWriter(tempFile2);
             FileWriter writer3 = new FileWriter(tempFile3)) {
                writer1.write("java python java cplusplus csharp django");
                writer2.write("java");
                writer3.write("java python java cplusplus csharp");
        }

        FileIndexer indexer = new FileIndexer(new SimpleTextFileTokenizer());

        indexer.indexFile(tempFile1);
        indexer.indexFile(tempFile2);
        indexer.indexFile(tempFile3);

        Set<File> result = indexer.search("django");

        assertFalse(result.isEmpty());
        assertTrue(result.contains(tempFile1));
        result.stream().map(File::getName).forEach(System.out::println);
    }
}
