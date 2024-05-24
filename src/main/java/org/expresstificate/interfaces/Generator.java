package org.expresstificate.interfaces;

import org.expresstificate.structures.Template;

public interface Generator extends Authenticator {
  default void create(Template template) {
    System.out.println(template.getBody().getReceiverName() + "\n" + template.getPadding().getId());
    System.out.println();
  }
  default void comparator(int a, int b, String message) throws Exception {
    if (a != b) {
      throw new Exception(message);
    }
  }
}
