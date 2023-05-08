package hexlet.code.schemas;

public class NumberSchema extends BaseSchema {
    public NumberSchema() {
        addToConditionList(x -> x instanceof Integer && !isEmptyValue(x));
    }
    public final NumberSchema required() {
        setRequired();
        return this;
    }

    public final NumberSchema positive() {
        addToConditionList(x -> ((Integer) x) > 0);
        return this;
    }

    public final NumberSchema range(int beginRange, int endRange) {
        addToConditionList(x -> ((Integer) x) >= beginRange && ((Integer) x) <= endRange);
        return this;
    }

    @Override
    public final boolean isEmptyValue(Object o) {
        return super.isEmptyValue(o);
    }
}
