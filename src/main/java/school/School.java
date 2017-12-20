package school;

import java.util.*;
import java.util.function.Predicate;

public class School {

  public static <E> Predicate<E> moreThan(Comparator<E> comp, E reference) {
    return s -> comp.compare(reference, s) < 0;
  }

  public static <E> List<E> filter(Iterable<E> in, Predicate<E> crit) {
    List<E> rv = new ArrayList<>();
    for (E s : in) {
      if (crit.test(s)) {
        rv.add(s);
      }
    }
    return rv;
  }

  public static <E> void showAll(Iterable<E> in) {
    for (E s : in) {
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
    boolean b = ((Predicate<Student>)(s -> s.getName().length() > 4)).test(sheila)  ;
    System.out.println("Sheila is smart? " + b);

    Predicate<Student> longNameCriterion = s -> s.getName().length() > 4;
    Predicate<Student> notLongNameCriterion = longNameCriterion.negate();

    System.out.println("Long names:");
    showAll(filter(roster, longNameCriterion));

    System.out.println("Not long names:");
    showAll(filter(roster, notLongNameCriterion));

    Student bert = roster.get(6);
    Predicate<Student> smarterThanBert = moreThan(new SmartnessComparator(), bert);
    System.out.println("Smarter than Bert:");
    showAll(filter(roster, smarterThanBert));

    List<String> names = Arrays.asList("Fred", "Jim", "Sheila", "Algernon", "William");
    showAll(filter(names, s -> s.length() > 4));

  }
}
