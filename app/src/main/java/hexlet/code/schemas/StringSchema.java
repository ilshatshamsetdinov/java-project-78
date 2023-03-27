package hexlet.code.schemas;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
public class StringSchema {
    private List<Predicate> predicatesList = new ArrayList<>();

    public StringSchema required() {
        predicatesList.add(x -> x instanceof String && x != null && !x.equals(""));
        return this;
    }

    public StringSchema minLength(int number) {
        predicatesList.add(x -> ((String) x).length() >= number);
        return this;
    }

    public StringSchema contains(String substring) {
        predicatesList.add(x -> x.toString().contains(substring));
        return this;
    }

    public boolean isValid(Object o) {
        for (Predicate predicate : predicatesList) {
            if (!predicate.test(o)) {
                return false;
            }
        }
        return true;
    }
}

