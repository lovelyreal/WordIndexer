package service.watcher;

import service.index.FileIndexer;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.concurrent.ExecutorService;

public class FileWatcher implements Runnable {

    private final Path path;
    private final FileIndexer indexer;
    private final ExecutorService executor;

    public FileWatcher(File directory, FileIndexer indexer, ExecutorService executor) {

        this.path = directory.toPath();
        this.indexer = indexer;
        this.executor = executor;
    }

    @Override
    public void run() {

        try {

            WatchService watchService =
                    FileSystems.getDefault().newWatchService();

            path.register(
                    watchService,
                    StandardWatchEventKinds.ENTRY_CREATE,
                    StandardWatchEventKinds.ENTRY_MODIFY
            );

            while (true) {
                WatchKey key = watchService.take();
                for (WatchEvent<?> event : key.pollEvents()) {
                    Path changed = (Path) event.context();
                    File file = path.resolve(changed).toFile();
                    if (file.isFile()) {
                        executor.submit(() -> indexer.indexFile(file));
                    }
                }
                key.reset();
            }

        } catch (IOException | InterruptedException e) {

            e.printStackTrace();

        }
    }
}