package school;

import java.util.*;

public class School {

  public static StudentCriterion moreThan(Comparator<Student> comp, Student reference) {
    return s -> comp.compare(reference, s) < 0;
  }

  public static List<Student> filter(Iterable<Student> in, StudentCriterion crit) {
    List<Student> rv = new ArrayList<>();
    for (Student s : in) {
      if (crit.test(s)) {
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
    showAll(filter(roster, Student.getSmartCriterion()));
    System.out.println("Enthusiastic:");
    showAll(filter(roster, Student.getEnthusiasticCriterion(3)));

    Student sheila = roster.get(1);
    boolean b = ((StudentCriterion)(s -> s.getName().length() > 4)).test(sheila)  ;
    System.out.println("Sheila is smart? " + b);

    StudentCriterion longNameCriterion = s -> s.getName().length() > 4;
    StudentCriterion notLongNameCriterion = longNameCriterion.negate();

    System.out.println("Long names:");
    showAll(filter(roster, longNameCriterion));

    System.out.println("Not long names:");
    showAll(filter(roster, notLongNameCriterion));

    Student bert = roster.get(6);
    StudentCriterion smarterThanBert = moreThan(new SmartnessComparator(), bert);
    System.out.println("Smarter than Bert:");
    showAll(filter(roster, smarterThanBert));
  }
}
