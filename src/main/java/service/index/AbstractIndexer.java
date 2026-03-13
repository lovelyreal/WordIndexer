package service.index;

import java.io.File;
import java.util.Set;

public interface AbstractIndexer {
   void indexFile(File file);
   void indexDirectory(File directory);
   Set<File> search(String word);
}
