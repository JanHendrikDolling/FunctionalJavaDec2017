package exceptionhandling;

import school.Student;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class Wrap {
  public static <E,F> Function<E, Optional<F>> wrap(ExFunction<E, F> op) {
    return e -> {
      try {
        return Optional.ofNullable(op.apply(e));
      } catch (Throwable t) {
        return Optional.empty();
      }
    };
  }

  public static void main(String[] args) {
    List<Student> students = Arrays.asList(
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

    ExFunction<String, String> bad = s -> {
      if (Math.random() > 0.5) {
        System.out.println("******KILLING " + s);
        throw new IOException("Hahaha!");
      }
      return "You survived " + s;
    };

    students.stream()
        .flatMap(s -> s.getCourses().stream())
        .map(wrap(bad))
        .peek(System.out::println)
        .filter(Optional::isPresent)
//        .distinct()
        .map(s -> String.format("> %20s", s.orElse("HUH, not present???")))
        .forEach(System.out::println);
    System.out.println("----------------------------------");
  }
}
