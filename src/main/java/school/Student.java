package school;

import java.util.Arrays;
import java.util.List;

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

    private static final StudentCriterion smartCriterion = new SmartCriterion();

    public static StudentCriterion getSmartCriterion() {
      return smartCriterion;
    }

    private static class SmartCriterion implements StudentCriterion {
      @Override
      public boolean test(Student s) {
        return s.getGpa() > 3.0F;
      }
    }

    public static StudentCriterion getEnthusiasticCriterion() {
      return s -> s.getCourses().size() > 3;
    }

//    // deriving lambda *expression* (as distinguished from "block lambda")
//    public static StudentCriterion getEnthusiasticCriterion() {
//      return /*(*//*Student*/ s/*)*/ -> /*{*/
//          /*return*/ s.getCourses().size() > 3/*;*/
//        /*}*/ ;
//    }
//
//    // deriving lambda expression v1
//    public static StudentCriterion getEnthusiasticCriterion() {
//      return /*new StudentCriterion() {*/
//        /*@Override
//        public boolean test*/(Student s) -> {
//          System.out.println("lambda criterion");
//          return s.getCourses().size() > 3;
//        }
//      /*}*/;
//    }
//
//    // anonymous inner class
//    public static StudentCriterion getEnthusiasticCriterion() {
//      return new StudentCriterion() {
//        @Override
//        public boolean test(Student s) {
//          System.out.println("anonymous inner criterion");
//          return s.getCourses().size() > 3;
//        }
//      };
//    }
//
//    // anonymous inner class
//    public static StudentCriterion getEnthusiasticCriterion() {
//      return new /*EnthusiasticCriterion();
//
//      private static class EnthusiasticCriterion implements*/ StudentCriterion() {
//        @Override
//        public boolean test(Student s) {
//          return s.getCourses().size() > 3;
//        }
//      }; // add semicolon, end of return statement
//
//    } // end of get method...
//
    //    public static StudentCriterion getEnthusiasticCriterion() {
//      return new EnthusiasticCriterion();
//    }
//
//    private static class EnthusiasticCriterion implements StudentCriterion {
//      @Override
//      public boolean test(Student s) {
//        return s.getCourses().size() > 3;
//      }
//    }
}
