package school;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public final class Student {

  private final String name;
  private final float gpa;
  private final List<String> courses;
  private final List<String> scholarships;

  private Student(String name, float gpa, List<String> courses, List<String> scholarships) {
    this.name = name;
    this.gpa = gpa;
    this.courses = courses;
    this.scholarships = scholarships;
  }

  public static Student ofNameGpaCourses(
      String name, float gpa, String... courses) {
    return new Student(name, gpa, Arrays.asList(courses), null);
  }

  public static Student ofNameGpaCoursesScholarships(
      String name, float gpa, String... courses) {
    return new Student(name, gpa, Arrays.asList(courses),
        Arrays.asList("Small", "Helpful"));
  }

  public String getName() {
    return name;
  }

  public float getGpa() {
    return gpa;
  }

  public List<String> getCourses() {
    return courses;
  }

  public List<String> getscholarships() {
    return scholarships;
  }

  @Override
  public String toString() {
    return "Student{" +
        "name='" + name + '\'' +
        ", gpa=" + gpa +
        ", courses=" + courses +
        (scholarships == null
            ? ", No trunk"
            : ", scholarships=" + scholarships)
        + '}';
  }

  //    private static final StudentCriterion smartCriterion = new SmartCriterion();
  private static final Predicate<Student> smartCriterion = s -> s.getGpa() > 3.0F;

  public static Predicate<Student> getSmartCriterion() {
    return smartCriterion;
  }

  public static Predicate<Student> getEnthusiasticCriterion(final int threshold) {
    int myThreshold = threshold + 1;
    return s -> s.getCourses().size() > threshold;
  }

  private class NameLengthCriterion implements Predicate<Student> {
    private int threshold;
    public NameLengthCriterion(int threshold) {
      this.threshold = threshold;
    }

    @Override
    public boolean test(Student s) {
      return s.getName().length() > threshold;
    }
  }
}
