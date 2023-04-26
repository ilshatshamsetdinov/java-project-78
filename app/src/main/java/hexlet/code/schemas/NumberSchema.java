package hexlet.code.schemas;

import java.util.function.Predicate;

public class NumberSchema extends BaseSchema {

    public NumberSchema required() {
        addToConditionList(x -> x instanceof Integer && !isEmptyValue(x));
        setRequired();
        return this;
    }
    public NumberSchema positive() {
        Predicate<Integer> predicate = x -> x == null || x > 0;
        addToConditionList(predicate);
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
