package service.index;

import java.io.File;
import java.util.Set;

public interface AbstractIndexer {
   void indexFile(File file);
   Set<File> search(String word);
}
