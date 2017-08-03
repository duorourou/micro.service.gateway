package duorourou.micro.services.demo.gateway.json;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static com.google.common.collect.Lists.newArrayList;

public class JsonCompare {

    public List<String> compare(String origin, String dest) throws IOException {

        ObjectMapper mapper = new ObjectMapper();
        JsonNode originTree = mapper.readTree(origin);
        JsonNode destTree = mapper.readTree(dest);
        List<String> compareResult = newArrayList();
        Iterator<Map.Entry<String, JsonNode>> iterator = originTree.fields();
        while (iterator.hasNext()) {
            Map.Entry<String, JsonNode> entry = iterator.next();
            if (destTree.has(entry.getKey())) {
                JsonNode destValue = destTree.get(entry.getKey());
                if (entry.getValue().getNodeType() != destValue.getNodeType()) {
                    compareResult.add(entry.getKey());
                } else {
                    compareJsonNode(compareResult, entry.getKey(), entry.getValue(), destValue);
                }
            } else {
                compareResult.add(entry.getKey());
            }
        }
        return compareResult;
    }

    private boolean compareValue(Object origin, Object dest) {
        return Objects.equals(origin, dest);
    }

    private void compareArrayValue(List<String> compareResult, String currentKey,
                                   JsonNode origin, JsonNode dest) {
        List<JsonNode> originList = newArrayList(origin.elements());
        List<JsonNode> destList = newArrayList(dest.elements());
        if (originList.size() != destList.size()) {
            compareResult.add(currentKey);
            return;
        }

        for (int index = 0; index < originList.size(); index++) {
            compareJsonNode(compareResult, currentKey + "[" + index + "]", originList.get(index), destList.get(index));
        }
    }

    private void compareJsonNode(List<String> compareResult, String currentKey, JsonNode origin, JsonNode dest) {

        if (origin.isValueNode() && !compareValue(origin.asText(), dest.asText())) {
            compareResult.add(currentKey);
        } else {
            if (origin.isObject()) {
                origin.fields().forEachRemaining(entry -> compareJsonNode(compareResult,
                        currentKey + "." + entry.getKey(), entry.getValue(), dest.get(entry.getKey())));
            } else if (origin.isArray()) {
                compareArrayValue(compareResult, currentKey, origin, dest);
            } else {
                if (!compareValue(origin.asText(), dest.asText())) {
                    compareResult.add(currentKey);
                }
            }
        }

    }
}
