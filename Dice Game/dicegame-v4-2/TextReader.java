import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class TextReader
{
    public TextReader() {
        
    }

    String readFile(String fileName) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        String fileString = "";
        try {
            String line = reader.readLine();
            while (line != null) {
                fileString += line + "\n";
                line = reader.readLine();
            }
            return fileString;
        } finally {
            reader.close();
        }
    }

}