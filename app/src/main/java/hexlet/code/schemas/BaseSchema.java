package hexlet.code.schemas;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class BaseSchema  {
    private final List<Predicate> conditions = new ArrayList<>();
    private boolean isRequired;

    public void setRequired() {
        isRequired = true;
    }
    public void addToConditionList(Predicate predicate) {
        conditions.add(predicate);
    }
    public boolean isEmptyValue(Object o) {
        return o == null;
    }

    public boolean isValid(Object o) {
        if (!isRequired && isEmptyValue(o))  {
            return true;
        }
        if (isRequired && o == null) {
            return false;
        }
        for (Predicate p : conditions) {
            if (!p.test(o)) {
                return false;
            }
        }
        return true;
    }
}
