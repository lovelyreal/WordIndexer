package serviceTest;

import org.junit.jupiter.api.Test;
import service.tokenizer.AbstractTokenizer;
import service.tokenizer.RussianTextFileTokenizer;
import service.tokenizer.SimpleTextFileTokenizer;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Locale;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;

public class RussianTokenizerTest {
    @Test
    public void testRussianTokenizer() throws IOException {
        File tempFile = File.createTempFile("test", ".txt");
        AbstractTokenizer tokenizer = new RussianTextFileTokenizer();
        try (FileWriter writer = new FileWriter(tempFile)) {
            writer.write("Машины ездят по городу");
        }
        List<String> tokens = tokenizer.tokenize(tempFile);
        assertNotNull(tokens);
        assertTrue(tokens.stream().anyMatch(t -> t.startsWith("маш")));
        assertTrue(tokens.stream().anyMatch(t -> t.startsWith("езд")));
        assertTrue(tokens.stream().anyMatch(t -> t.startsWith("город")));
    }
}
