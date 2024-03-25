import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class Preferences {
    public static final String DB_JDBC_CONNECTION_URL = "dbUrl";
    public static final String DEFAULT_PREFS_FILENAME = "prefs.json";

    private Map<String, Object> prefs = new HashMap<>();

    public Preferences() {
        this(DEFAULT_PREFS_FILENAME);
    }

    public Preferences(String fileName) {
        try {
            String json = Files.readString(Paths.get(fileName));

            TypeToken<?> typeToken = TypeToken.getParameterized(Map.class, String.class, Object.class);
            prefs = new Gson().fromJson(json, typeToken.getType());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getPref(String key) {
        return prefs.get(key).toString();
    }
}