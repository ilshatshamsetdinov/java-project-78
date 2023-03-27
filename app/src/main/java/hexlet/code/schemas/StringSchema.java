package hexlet.code.schemas;

public class StringSchema extends BaseSchema {


    public StringSchema required() {
        setValidators(x -> x instanceof String && x != null && !x.equals(""));
        return this;
    }

    public StringSchema minLength(int number) {
        setValidators(x -> ((String) x).length() >= number);
        return this;
    }

    public StringSchema contains(String substring) {
        setValidators(x -> x.toString().contains(substring));
        return this;
    }
}

