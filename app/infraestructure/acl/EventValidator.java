package infraestructure.acl;

import controllers.dto.EventDTO;
import domain.Event;
import domain.Type;
import io.vavr.collection.List;
import io.vavr.control.Either;
import io.vavr.control.Try;
import io.vavr.control.Validation;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;

public class EventValidator {

  public static Either<List<String>, Event> validate(EventDTO dto) {
    return Validation.combine(
      Validation.valid(dto.getId()),
      validateName(dto.getName()),
      validateType(dto.getType()),
      validatePlace(dto.getPlace()),
      validateAddress(dto.getAddress()),
      validateStartDate(dto.getStartDate()),
      validateFinishDate(dto.getFinishDate())
    ).ap(EventBuilder::build)
      .toEither()
      .mapLeft(List::ofAll);
  }

  static Validation<String, String> validateName(String name) {
    return StringUtils.isNotEmpty(name) && name.length() <= 50 ? Validation.valid(name) : Validation.invalid("event.name");
  }

  static Validation<String, Type> validateType(String type) {
    Type value = Type.indexOf(type);
    return value != Type.UNKNOWN ? Validation.valid(value) : Validation.invalid("event.type");
  }

  static Validation<String, String> validatePlace(String place) {
    return StringUtils.isNotEmpty(place) && place.length() <= 50 ? Validation.valid(place) : Validation.invalid("event.place");
  }

  static Validation<String, String> validateAddress(String address) {
    return StringUtils.isNotEmpty(address) && address.length() <= 50 ? Validation.valid(address) : Validation.invalid("event.address");
  }

  static Validation<String, DateTime> validateStartDate(String startDate) {
    return Try.of(() -> DateTime.parse(startDate) ).toEither("event.startDate").toValidation();
  }

  static Validation<String, DateTime> validateFinishDate(String finishDate) {
    return Try.of(() -> DateTime.parse(finishDate) ).toEither("event.finishDate").toValidation();

  }

}
