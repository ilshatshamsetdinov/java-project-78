package hexlet.code.schemas;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class BaseSchema {
    private final List<Predicate> validators = new ArrayList<>();

    public void setValidators(Predicate predicate) {
        validators.add(predicate);
    }

    public boolean isValid(Object o) {
        for (Predicate validator : validators) {
            if (!validator.test(o)) {
                return false;
            }
        }
        return true;
    }
}
