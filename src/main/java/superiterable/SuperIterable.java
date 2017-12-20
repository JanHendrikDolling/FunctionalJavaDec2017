package superiterable;

import school.Student;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class SuperIterable<E> implements Iterable<E> {
  private Iterable<E> self;

  public SuperIterable(Iterable<E> target) {
    this.self = target;
  }

  // this is roughly how forEach works :)
//  public void forEvery(Consumer<E> op) {
//    for (E e : self) {
//      op.accept(e);
//    }
//  }

  public SuperIterable<E> filter(Predicate<? super E> crit) {
    List<E> out = new ArrayList<>();
    self.forEach(x -> {
      if (crit.test(x)) {
        out.add(x);
      }
    });
    return new SuperIterable<>(out);
  }

  public <F> SuperIterable<F> map(Function<? super E, ? extends F> op) {
    List<F> out = new ArrayList<>();
    self.forEach(x -> out.add(op.apply(x)));
    return new SuperIterable<>(out);
  }

  public <F> SuperIterable<F> flatMap(Function<? super E, SuperIterable<? extends F>> op) {
    List<F> out = new ArrayList<>();
    self.forEach(x -> op.apply(x).forEach(y -> out.add(y)));
    return new SuperIterable<>(out);
  }

  public SuperIterable<E> distinct() {
    Set<E> seen = new HashSet<>();
    self.forEach(x -> seen.add(x));
    return new SuperIterable(seen);
  }

  @Override
  public Iterator<E> iterator() {
    return self.iterator();
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
    SuperIterable<Student> roster = new SuperIterable<>(students);

    roster.forEach(s -> System.out.println("> " + s));
    System.out.println("----------------------------------");
    roster
        .filter(s -> s.getGpa() > 3.0F)
        .forEach(s -> System.out.println("> " + s));
    System.out.println("----------------------------------");
    roster
        .filter(s -> s.getGpa() > 3.0F)
        .map(s -> s.getName())
        .forEach(s -> System.out.println("> " + s));
    System.out.println("----------------------------------");
    roster
        .filter(s -> s.getGpa() > 3.0F)
        .map(s -> s.getName())
        .map(s -> s.toUpperCase())
        .forEach(s -> System.out.println("> " + s));
    System.out.println("----------------------------------");
    roster
        .filter(s -> s.getGpa() > 3.0F)
        .flatMap(s -> new SuperIterable(s.getCourses()))
        .distinct()
        .forEach(s -> System.out.println("> " + s));
    System.out.println("----------------------------------");

    System.out.println("Stream:");
    students.stream().forEach(s -> System.out.println("> " + s));
    System.out.println("----------------------------------");
    students.stream()
        .filter(s -> s.getGpa() > 3.0F)
        .forEach(s -> System.out.println("> " + s));
    System.out.println("----------------------------------");
    students.stream()
        .filter(s -> s.getGpa() > 3.0F)
        .map(s -> s.getName())
        .forEach(s -> System.out.println("> " + s));
    System.out.println("----------------------------------");
    students.stream()
        .filter(s -> s.getGpa() > 3.0F)
        .map(s -> s.getName())
        .map(s -> s.toUpperCase())
        .forEach(s -> System.out.println("> " + s));
    System.out.println("----------------------------------");
    students.stream()
        .filter(s -> s.getGpa() > 3.0F)
        .flatMap(s -> s.getCourses().stream())
        .distinct()
        .forEach(s -> System.out.println("> " + s));
    System.out.println("----------------------------------");
  }
}
