package org.CurrencyConvertor;

import org.json.JSONObject;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;
import java.util.Map;

public class ConfigService {

    private static final String CONFIG_FILE = "src/main/resources/config.json";

    public String getApiKey() throws IOException {
        String content = new String(Files.readAllBytes(Paths.get(CONFIG_FILE)));
        JSONObject json = new JSONObject(content);
        String apiKeyVariableName = json.getString("api_key");

        Map<String, String> env = System.getenv();
        return env.get(apiKeyVariableName);
    }
}