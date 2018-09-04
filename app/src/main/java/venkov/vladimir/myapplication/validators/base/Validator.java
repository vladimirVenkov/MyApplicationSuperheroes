package venkov.vladimir.myapplication.validators.base;

public interface Validator<T> {
    boolean isValid(T object);
}
