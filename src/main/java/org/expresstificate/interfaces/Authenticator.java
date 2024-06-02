package org.expresstificate.interfaces;

import org.expresstificate.validators.Validator;

public interface Authenticator {
  default String validate() {
    StringBuilder log = new StringBuilder();
    try {
      log.append(Validator.validate(this));
    } catch (Exception e) {
      e.printStackTrace();
    }
    return log.toString();
  }

  default void verify(String log) throws Exception {
    if (!log.isEmpty()) {
      throw new Exception(log);
    }
  }
}
