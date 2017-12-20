package exceptionhandling;

public interface ExFunction<E, F> {
  F apply(E e) throws Throwable;
}
