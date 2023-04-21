package hexlet.code.schemas;

public class StringSchema extends BaseSchema {


    public StringSchema required() {
        addToConditionList(x -> x instanceof String && x != null && !x.equals(""));
        setRequired();
        return this;
    }

    public StringSchema minLength(int number) {
        addToConditionList(x -> ((String) x).length() >= number);
        return this;
    }

    public StringSchema contains(String substring) {
        addToConditionList(x -> x.toString().contains(substring));
        return this;
    }
}

