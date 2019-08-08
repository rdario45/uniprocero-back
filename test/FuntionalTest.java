import io.vavr.concurrent.Future;
import io.vavr.control.Either;
import io.vavr.control.Option;
import org.junit.Test;

import java.util.function.Function;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

public class FuntionalTest {

  @Test
  public void fromEitherFutureOptionToEitherSuccess(){
    Either<String, Future<Option<String>>> either = Either.right(Future.successful(Option.of("yes")));

    Either<String, String> finalEither = either.flatMap(future ->
      future.map(option -> option.toEither("option fail"))
        .toEither("future fail")
        .flatMap(Function.identity()));

    assertTrue(finalEither.isRight());
    assertEquals(finalEither.get(), "yes");
  }
  @Test
  public void fromEitherFutureOptionToEitherOptionFailure(){
    Either<String, Future<Option<String>>> either = Either.right(Future.successful(Option.none()));

    Either<String, String> finalEither = either.flatMap(future ->
      future.map(option -> option.toEither("option fail"))
        .toEither("future fail")
        .flatMap(Function.identity()));

    assertTrue(finalEither.isLeft());
    assertEquals(finalEither.getLeft(), "option fail");
  }

  @Test
  public void fromEitherFutureOptionToEitherFutureFailure(){
    Either<String, Future<Option<String>>> either = Either.right(Future.failed(new Error()));

    Either<String, String> finalEither = either.flatMap(future ->
      future.map(option -> option.toEither("option fail"))
        .toEither("future fail")
        .flatMap(Function.identity()));

    assertTrue(finalEither.isLeft());
    assertEquals(finalEither.getLeft(), "future fail");
  }

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