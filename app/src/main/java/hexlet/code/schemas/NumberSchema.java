package hexlet.code.schemas;

public class NumberSchema extends BaseSchema {

    public NumberSchema required() {
        addToConditionList(x -> x instanceof Integer && x != null);
        setRequired();
        return this;
    }
    public NumberSchema positive() {
        addToConditionList(x -> x == null || x instanceof Integer && (Integer) x > 0);
        return this;
    }
    public NumberSchema range(int beginRange, int endRange) {
        addToConditionList(x -> x instanceof Integer && (Integer) x >= beginRange && (Integer) x <= endRange);
        return this;
    }
}
