package service.index;

import service.tokenizer.AbstractTokenizer;

import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public interface AbstractIndexer {
    ConcurrentHashMap<String, Set<String>> index(AbstractTokenizer tokenizer );
}
