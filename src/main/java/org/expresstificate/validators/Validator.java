package org.expresstificate.validators;
import org.expresstificate.interfaces.annotations.NotNull;

import java.lang.reflect.Field;

public class Validator {
  public static String validate(Object obj) throws IllegalAccessException {
    Class<?> classObject = obj.getClass();
    StringBuilder log = new StringBuilder();
    for (Field field : classObject.getDeclaredFields()) {
      field.setAccessible(true);
      if (field.isAnnotationPresent(NotNull.class) && field.get(obj) == null) {
        log.append(field.getName()).append(" must not be null").append("\n");
      }
    }
    return log.toString();
  }
}
