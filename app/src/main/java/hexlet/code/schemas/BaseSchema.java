package hexlet.code.schemas;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class BaseSchema {
    private final List<Predicate> conditions = new ArrayList<>();
    private boolean isRequired;

    public void setRequired() {
        isRequired = true;
    }

    public void addToConditionList(Predicate predicate) {
        conditions.add(predicate);
    }

    public boolean isValid(Object o) {
        if (!isRequired) {
            return true;
        }
        for (Predicate p : conditions) {
            if (!p.test(o)) {
                return false;
            }
        }
        return true;
    }
}
