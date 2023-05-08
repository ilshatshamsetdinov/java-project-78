package hexlet.code.schemas;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public abstract class BaseSchema  {
    private final List<Predicate> conditions = new ArrayList<>();
    private boolean isRequired;

    public final void setRequired() {
        isRequired = true;
    }
    public final void addToConditionList(Predicate predicate) {
        conditions.add(predicate);
    }
    abstract boolean isEmptyValue(Object o);

    public final boolean isValid(Object o) {
        if (!isRequired && isEmptyValue(o))  {
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
