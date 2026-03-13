package app;

import service.index.FileIndexer;
import service.index.IndexerService;
import service.tokenizer.*;
import util.FileUtilities;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import java.util.Set;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in, "CP866");

        System.out.println("Выберите tokenizer:");
        System.out.println("1 - simple");
        System.out.println("2 - russian");

        int choice = Integer.parseInt(scanner.nextLine());

        AbstractTokenizer tokenizer;

        if (choice == 1) {
            tokenizer = new SimpleTextFileTokenizer();
        } else if(choice == 2) {
            tokenizer = new RussianTextFileTokenizer();
        } else {
            tokenizer = new SimpleTextFileTokenizer();
        }

        FileIndexer indexer = new FileIndexer(tokenizer);
        IndexerService service = new IndexerService(indexer);


        while (true) {
            FileUtilities.showMenu();
            System.out.print("> ");
            String userAnswer = scanner.nextLine();
            String[] partsOfUserAnswer = userAnswer.split(" ", 2);
            String command = partsOfUserAnswer[0];

            try {
                switch (command) {
                    case "addDir":
                        service.indexDirectory(new File(partsOfUserAnswer[1]));
                        break;

                    case "addFile":
                        service.indexFile(new File(partsOfUserAnswer[1]));
                        System.out.println("Файл добавлен");
                        break;
                    case "search":
                        Set<File> result = indexer.search(partsOfUserAnswer[1]);
                        if (result.isEmpty()) {
                            System.out.println("Nothing found");
                        }
                        result.forEach(f -> System.out.println(f.getAbsolutePath()));
                        break;

                    case "exit":
                        service.shutdown();
                        return;

                    default:

                        System.out.println("Unknown command");
                }
            } catch (Exception e) {

                System.out.println("Invalid command format");

            }
        }
    }
}
