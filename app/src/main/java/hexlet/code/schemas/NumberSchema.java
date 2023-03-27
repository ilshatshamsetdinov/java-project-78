package hexlet.code.schemas;

public class NumberSchema extends BaseSchema {

    public NumberSchema required() {
        setValidators(x -> x instanceof Integer && x != null);
        return this;
    }
    public NumberSchema positive() {
        setValidators(x -> x == null || x instanceof Integer && (Integer) x > 0);
        return this;
    }
    public NumberSchema range(int beginRange, int endRange) {
        setValidators(x -> (Integer) x >= beginRange && (Integer) x <= endRange);
        return this;
    }
}
