package school;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

interface StudentCriterion {
  boolean test(Student s);
}

interface Silly {
  boolean daft(Student s);
}

public class School {
  public static List<Student> filter(Iterable<Student> in, StudentCriterion crit) {
    List<Student> rv = new ArrayList<>();
    for (Student s : in) {
      if (crit.test(s)) {
        rv.add(s);
      }
    }
    return rv;
  }

//  public static List<Student> getSmartList(Iterable<Student> in, float threshold) {
//    List<Student> rv = new ArrayList<>();
//    for (Student s : in) {
//      if (s.getGpa() > threshold) {
//        rv.add(s);
//      }
//    }
//    return rv;
//  }
//
//  public static List<Student> getEnthusiasticList(Iterable<Student> in, int threshold) {
//    List<Student> rv = new ArrayList<>();
//    for (Student s : in) {
//      if (s.getCourses().size() > threshold) {
//        rv.add(s);
//      }
//    }
//    return rv;
//  }
//
  public static void showAll(List<Student> in) {
    for (Student s : in) {
      System.out.println("> " + s);
    }
    System.out.println("--------------------------------------------");
  }

  public static void main(String[] args) {

    List<Student> roster = Arrays.asList(
        Student.ofNameGpaCoursesScholarships("Fred", 2.7F, "Math", "Art", "French"),
        Student.ofNameGpaCoursesScholarships("Sheila", 3.9F,
            "Math", "Physics", "Astrophysics", "Quantum Physics"),
        Student.ofNameGpaCoursesScholarships("Jim", 3.7F, "Math", "Art"),
        Student.ofNameGpaCourses("Mary", 3.8F, "Math", "Art", "Journalism"),
        Student.ofNameGpaCourses("Phil", 2.2F, "History"),
        Student.ofNameGpaCoursesScholarships("Sarah", 3.8F, "Journalism"),
        Student.ofNameGpaCourses("Bert", 2.8F,
            "Journalism", "History", "Basket weaving", "PT")
    );

    System.out.println("All:");
    showAll(roster);
//    System.out.println("Smart:");
//    showAll(getSmartList(roster, 2.7F));
//    System.out.println("Enthusiastic:");
//    showAll(getEnthusiasticList(roster, 2));
//
//    System.out.println("Sorted By Gpa");
//    Collections.sort(roster, new SmartnessComparator());
//    showAll(roster);

    System.out.println("Smart:");
    showAll(filter(roster, Student.getSmartCriterion()));
    System.out.println("Enthusiastic:");
    showAll(filter(roster, Student.getEnthusiasticCriterion()));

    Student sheila = roster.get(1);
    boolean b = ((StudentCriterion)(s -> s.getName().length() > 4)).test(sheila)  ;
    System.out.println("Sheila is smart? " + b);

    System.out.println("Long names:");
    showAll(filter(roster, s -> s.getName().length() > 4));
  }
}
