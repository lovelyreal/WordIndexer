package service.index;

import service.tokenizer.AbstractTokenizer;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
/*
TO-DO
1. На вход поступает файл
2. Файл переводится в строку
3. Строка токенизируется в соответствии с запросом
4. ConcurrentMap - в которую кладем токен и файл, в котором он встретился (k - токен; v - set(File))
 */
public class FileIndexer implements AbstractIndexer {
    @Override
    public ConcurrentHashMap<String, Set<String>> index(AbstractTokenizer tokenizer) {
        return null;
    }
}
