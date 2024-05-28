package org.expresstificate.interfaces;

import org.expresstificate.structures.Certificate;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.UUID;

public interface Generator extends Authenticator {
  default byte[] create(Certificate certificate) throws IOException {
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    certificate.setTheme();
    certificate.getDocument().save(outputStream);
    return outputStream.toByteArray();
  }

  default void comparator(int a, int b, String message) throws Exception {
    if (a != b) {
      throw new Exception(message);
    }
  }

  default String generateUUID() {
    return UUID.randomUUID().toString();
  }
}
