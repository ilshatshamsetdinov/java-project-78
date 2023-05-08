package hexlet.code.schemas;

public class StringSchema extends BaseSchema {
    public StringSchema() {
        addToConditionList(x -> x instanceof String && !isEmptyValue(x));
    }
    public StringSchema required() {
        setRequired();
        return this;
    }

    public StringSchema minLength(int number) {
        addToConditionList(x -> ((String) x).length() >= number);
        return this;
    }

    public StringSchema contains(String substring) {
        addToConditionList(x -> ((String) x).contains(substring));
        return this;
    }

    @Override
    public boolean isEmptyValue(Object o) {
        return o == null || o.equals("");
    }
}


