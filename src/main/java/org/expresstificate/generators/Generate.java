package org.expresstificate.generators;

import org.expresstificate.interfaces.Generator;
import org.expresstificate.interfaces.annotations.NotNull;
import org.expresstificate.structures.Certificate;
import org.expresstificate.structures.Template;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Generate extends Thread implements Generator {
  @NotNull
  protected Certificate certificate;

  private Map<String, byte[]> content = new HashMap<>();

  /*
    when the signal is true, the generator will create automatically a new id for each name in the nameList
   */

  @NotNull
  protected boolean signal;

  public Generate(Certificate certificate, boolean signal) {
    this.certificate = certificate;
    this.signal = signal;
  }

  public void setTemplate(Template newTemplate) {
    this.certificate.setTemplate(newTemplate);
  }

  public Map<String, byte[]> getContent() {
    return content;
  }

  @Override
  public void run() {
    try {
      verify(validate());
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
    if (signal) {
      certificate.getTemplate().getPadding().setId(generateUUID());
    }
    try {
      String receiverName = certificate
              .getTemplate()
              .getBody()
              .getReceiverName()
              .replaceAll(" ", "");
      content.put(receiverName, create(certificate));
      certificate.getTheme().closeDocument();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
