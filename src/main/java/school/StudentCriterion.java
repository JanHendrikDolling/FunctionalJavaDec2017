package school;

@FunctionalInterface
public interface StudentCriterion {
  boolean test(Student s);
//  static StudentCriterion negate(StudentCriterion crit) {
//    return s -> !crit.test(s);
//  }
  default StudentCriterion negate() {
    return s -> !this.test(s);
  }
}
