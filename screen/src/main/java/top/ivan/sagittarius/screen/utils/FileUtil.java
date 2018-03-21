package top.ivan.sagittarius.screen.utils;

import java.io.*;

public class FileUtil {
    public static String loadFile(String path) throws IOException {
        return loadFile(new File(path));
    }

    public static String loadFile(File file) throws IOException {
        return loadReader(new FileReader(file));
    }

    public static String loadReader(Reader reader) throws IOException {
        BufferedReader bufferedReader;
        if(reader instanceof BufferedReader) {
            bufferedReader = (BufferedReader) reader;
        } else {
            bufferedReader = new BufferedReader(reader);
        }
        StringBuilder builder = new StringBuilder();
        String temp;
        while ((temp = bufferedReader.readLine()) != null) {
            builder.append(temp).append("\n");
        }
        bufferedReader.close();
        return builder.deleteCharAt(builder.length() - 1).toString();
    }

    public static String loadStream(InputStream inputStream) throws IOException {
        return loadReader(new InputStreamReader(inputStream));
    }

}
