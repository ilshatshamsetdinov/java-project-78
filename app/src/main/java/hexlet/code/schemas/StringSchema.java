package hexlet.code.schemas;

public class StringSchema extends BaseSchema {
    public StringSchema() {
        addToConditionList(x -> x instanceof String && !isEmptyValue(x));
    }
    public final StringSchema required() {
        setRequired();
        return this;
    }

    public final StringSchema minLength(int number) {
        addToConditionList(x -> ((String) x).length() >= number);
        return this;
    }

    public final StringSchema contains(String substring) {
        addToConditionList(x -> ((String) x).contains(substring));
        return this;
    }

    @Override
    public final boolean isEmptyValue(Object o) {
        return o == null || o.equals("");
    }
}


