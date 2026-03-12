package util;

import java.io.*;

public class FileUtilities {
    public static String fileToString(File fileToString){
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(fileToString))){
            StringBuilder stringBuilder = new StringBuilder();
            while(bufferedReader.ready()){
                stringBuilder.append(bufferedReader.readLine());
            }
            return stringBuilder.toString();
        } catch (FileNotFoundException e){
            System.out.println("File not found - " + fileToString.getName());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return "";
    }
}
