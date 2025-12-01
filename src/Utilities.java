import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class Utilities {

    private Utilities() {

    }

    public static String getInput(String fileName) throws IOException {
        try (InputStream in = Utilities.class.getResourceAsStream(fileName)) {
            return in == null ? "" :  new String(in.readAllBytes(), StandardCharsets.UTF_8);
        }
    }

}
