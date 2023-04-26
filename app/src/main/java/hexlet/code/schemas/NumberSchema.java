package hexlet.code.schemas;

public class NumberSchema extends BaseSchema {

    public NumberSchema required() {
        addToConditionList(x -> x instanceof Integer && !isEmptyValue(x));
        setRequired();
        return this;
    }
    public NumberSchema positive() {
        addToConditionList(x -> x == null || (Integer) x > 0);
        return this;
    }
    public NumberSchema range(int beginRange, int endRange) {
        addToConditionList(x -> (Integer) x >= beginRange && (Integer) x <= endRange);
        return this;
    }
    @Override
    public boolean isEmptyValue(Object o) {
        return o == null;
    }
}
