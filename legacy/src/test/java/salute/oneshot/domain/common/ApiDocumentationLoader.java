package salute.oneshot.domain.common;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.InputStream;
import java.util.Map;

public class ApiDocumentationLoader {
    private static Map<String, Object> documentation;

    static {
        try {
            ObjectMapper mapper = new ObjectMapper();
            InputStream is = ApiDocumentationLoader.class.getResourceAsStream("/api-documentation.json");
            documentation = mapper.readValue(is, Map.class);
        } catch (Exception e) {
            throw new RuntimeException("API 문서 로드 실패", e);
        }
    }

    public static String getSummary(String domain, String apiKey) {
        Map<String, Object> summaryMap = (Map<String, Object>) documentation.get("summary");
        if (summaryMap == null) return null;

        Map<String, String> domainMap = (Map<String, String>) summaryMap.get(domain);
        if (domainMap == null) return null;

        return domainMap.get(apiKey);
    }

    public static String getDescription(String domain, String apiKey) {
        Map<String, Object> descriptionMap = (Map<String, Object>) documentation.get("description");
        if (descriptionMap == null) return null;

        Map<String, String> domainMap = (Map<String, String>) descriptionMap.get(domain);
        if (domainMap == null) return null;

        return domainMap.get(apiKey);
    }
}