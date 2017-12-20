package school;

@FunctionalInterface
public interface Criterion<E> {
  boolean test(E s);
//  static StudentCriterion negate(StudentCriterion crit) {
//    return s -> !crit.test(s);
//  }
  default Criterion<E> negate() {
    return s -> !this.test(s);
  }

  default Criterion<E> and(Criterion<E> second) {
    return s -> this.test(s) && second.test(s);
  }

  default Criterion<E> or(Criterion<E> second) {
    return s -> this.test(s) || second.test(s);
  }
}
