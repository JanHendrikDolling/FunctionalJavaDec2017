package aravind;

import java.util.stream.IntStream;

public class Aravind {
  public static void main(String[] args) {
    IntStream.of(1, 9, 32, 27, 33, 11, 9)
        .boxed()
        .map(x -> String.format("Your value is %05d", x))
        .forEach(System.out::println);
  }
}
