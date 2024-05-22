package org.expresstificate.generators;

import org.expresstificate.interfaces.Generator;
import org.expresstificate.structures.Template;

public class Generate extends Thread implements Generator {
  protected Template template;

  public Generate(Template template) {
    this.template = template;
  }

  public void setTemplate(Template newTemplate) {
    this.template = newTemplate;
  }

  @Override
  public void run() {
    create(template);
  }
}
