package org.expresstificate.interfaces;

import org.expresstificate.validators.Validator;


public interface Structure {
  default String validate() {
    String log = "";
    try {
      log = Validator.validate(this);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return log;
  }

  default void verify(String log) throws Exception {
    if (!log.isEmpty()) {
      throw new Exception(log);
    }
  }
}
