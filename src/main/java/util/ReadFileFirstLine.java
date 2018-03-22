package util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class ReadFileFirstLine {

    public static String readFirstLine(String filename){

        String result = "";
        try {
            FileInputStream in = new FileInputStream(filename);
            InputStreamReader inReader;
            inReader = new InputStreamReader(in, "UTF-8");
            BufferedReader bufReader = new BufferedReader(inReader);
            result = bufReader.readLine();
            bufReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
