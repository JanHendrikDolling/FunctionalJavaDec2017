package school;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class School {
  public static List<Student> getSmartList(List<Student> in) {
    List<Student> rv = new ArrayList<>();
    for (Student s : in) {
      if (s.getGpa() > 3.0F) {
        rv.add(s);
      }
    }
    return rv;
  }


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
    System.out.println("Smart:");
    showAll(getSmartList(roster));
  }
}
