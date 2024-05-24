package org.expresstificate.generators;

import org.expresstificate.interfaces.Generator;
import org.expresstificate.interfaces.annotations.NotNull;
import org.expresstificate.structures.Template;

public class Generate extends Thread implements Generator {
  @NotNull
  protected Template template;

  public Generate(Template template) {
    this.template = template;
  }

  public void setTemplate(Template newTemplate) {
    this.template = newTemplate;
  }

  @Override
  public void run() {
    try {
      verify(validate());
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
    create(template);
  }
}
