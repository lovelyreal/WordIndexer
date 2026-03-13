package serviceTest;

import org.junit.jupiter.api.Test;
import service.tokenizer.AbstractTokenizer;
import service.tokenizer.SimpleTextFileTokenizer;

import java.io.*;
import java.util.List;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;


public class SimpleTokenizerTest {
    @Test
    public void tokensExtract() throws IOException {
        File tempFile = File.createTempFile("test", ".txt");
        AbstractTokenizer tokenizer = new SimpleTextFileTokenizer();
        try (FileWriter writer = new FileWriter(tempFile)) {
            writer.write("Hello world Java Java");
        }
        List<String> tokens = tokenizer.tokenize(tempFile);
        assertNotNull(tokens);
        assertTrue(tokens.contains("hello"));
        assertTrue(tokens.contains("world"));
        assertTrue(tokens.contains("java"));
    }
}
