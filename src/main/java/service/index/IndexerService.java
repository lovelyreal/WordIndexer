package service.index;
import service.watcher.FileWatcher;

import java.io.File;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class IndexerService {

    private final FileIndexer indexer;
    private final ExecutorService executor;

    public IndexerService(FileIndexer indexer) {

        this.indexer = indexer;

        this.executor = Executors.newFixedThreadPool(
                Runtime.getRuntime().availableProcessors()
        );
    }

    public void indexFile(File file) {

        executor.submit(() -> indexer.indexFile(file));

    }

    public void indexDirectory(File directory) {

        File[] files = directory.listFiles();

        if (files == null) return;

        for (File file : files) {

            if (file.isFile()) {

                executor.submit(() -> indexer.indexFile(file));

            }
        }

        startWatcher(directory);
    }

    private void startWatcher(File directory) {

        executor.submit(new FileWatcher(directory, indexer, executor));

    }

    public void shutdown() {

        executor.shutdown();

    }
}