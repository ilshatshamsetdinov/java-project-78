package hexlet.code.schemas;

import java.util.Map;

public class MapSchema extends BaseSchema {
    public MapSchema required() {
        addToConditionList(x -> x instanceof Map && x != null);
        setRequired();
        return this;
    }
    public MapSchema sizeof(int pairsCount) {
        addToConditionList(x -> x instanceof Map && ((Map) x).size() == pairsCount);
        return this;
    }
    public final MapSchema shape(Map<String, BaseSchema> map) {
        addToConditionList(mapForCheck -> map.entrySet().stream()
                .allMatch(keyValuePair -> {
                    Object valueOfMapForCheck = ((Map<?, ?>) mapForCheck).get(keyValuePair.getKey());
                    return keyValuePair.getValue().isValid(valueOfMapForCheck);
                })
        );
        return this;
    }
}