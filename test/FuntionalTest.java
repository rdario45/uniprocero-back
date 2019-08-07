import io.vavr.concurrent.Future;
import io.vavr.control.Either;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

public class FuntionalTest {

  @Test
  public void fromEitherStringToFutureSuccess() {
    Either<String, String> either = Either.right("yes");
    Future<String> future = Future.successful("future yes");
    Either<String, Future<String>> futureEither = either.map(s -> future);
    Either<String, String> finalEither = futureEither
      .flatMap(stringFuture -> stringFuture.toEither("future error"));

    assertTrue(finalEither.isRight());
    assertEquals(finalEither.get(), "future yes");
  }

  @Test
  public void fromEitherStringToFutureFailure() {
    Either<String, String> either = Either.right("yes");
    Future<String> future = Future.failed(new Error());
    Either<String, Future<String>> futureEither = either.map(s -> future);
    Either<String, String> finalEither = futureEither
      .flatMap(stringFuture -> stringFuture.toEither("future error"));

    assertTrue(finalEither.isLeft());
    assertEquals(finalEither.getLeft(), "future error");
  }
}