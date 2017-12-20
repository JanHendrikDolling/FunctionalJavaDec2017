package streams;

import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Consumer;
import java.util.stream.DoubleStream;

class Average {
  private double sum;
  private long count;

  public Average() {}

  public void include(double d) {
    sum += d;
    count ++;
  }

  public void merge(Average other) {
    sum += other.sum;
    count += other.count;
  }

  public double get() {
    return sum / count;
  }

  public void use(Consumer<Double> op) {
    op.accept(get());
  }
}

public class Averager {
  public static void main(String[] args) {
    long start = System.nanoTime();
    DoubleStream.generate(() -> ThreadLocalRandom.current().nextDouble(-Math.PI, +Math.PI))
// Stream runs either parallel, or sequential, based on last invocation!!!
        .parallel()
//        .sequential()
//        .parallel()
//        .limit(1_000_000_000)
        .limit(400_000_000)
//        .map(v -> Math.sin(v))
        .map(Math::sin)
//        .collect(
//            () -> new Average(),
//            (b, i) -> b.include(i),
//            (bf, bi) -> bf.merge(bi))
        .collect(
            Average::new,
            Average::include,
            Average::merge)
        .use(System.out::println);
//        .use(v -> System.out.println("Mean is " + v));

    long time = System.nanoTime() - start;
    System.out.println("Time taken was " + (time / 1_000_000_000.0) + " seconds");

//        .forEach(v -> System.out.println(v));
  }
}
