package org.expresstificate.interfaces;

import org.expresstificate.structures.Certificate;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

public interface Generator extends Authenticator {
  default void create(Certificate certificate) throws IOException {
    String receiverName = certificate.getTemplate().getBody().getReceiverName();
    certificate.setTheme();

    Path relativePath = Paths.get("src", "main", "java", "org", "expresstificate", "generated", "certificate-" + receiverName.replaceAll(" ", "") + ".pdf");
    certificate.getDocument().save(relativePath.toFile());
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
