package util;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

public class FileUtilities {
    public static String fileToString(File file) {

        try (BufferedReader reader = Files.newBufferedReader(file.toPath(), StandardCharsets.UTF_8)) {
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line).append(System.lineSeparator());
            }
            return sb.toString();
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
            return "";
        }
    }
    private static String endingIgnore(String word){
        for (String ending : RussianTokenizerSettings.ENDINGS) {
            if(word.endsWith(ending) && word.length() > ending.length()+2){
                return word.substring(0, word.length()-ending.length());
            }
        }
        return word;
    }
    public static String wordNormalizer(String word){
        word = word.replace('ё', 'е').toLowerCase();
        word = endingIgnore(word);
        return word;
    }
    public static void showMenu(){
        System.out.println("Команды:");
        System.out.println("addDir PATH");
        System.out.println("addFile PATH");
        System.out.println("search WORD");
        System.out.println("exit");
    }
}
