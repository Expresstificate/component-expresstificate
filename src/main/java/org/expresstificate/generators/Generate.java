package org.expresstificate.generators;

import org.expresstificate.interfaces.Generator;
import org.expresstificate.structures.Template;

import java.util.ArrayList;

public class Generate extends Thread implements Generator {
  private Template template;

  public Generate(Template template) {
    this.template = template;
  }

  @Override
  public void run() {

  }
}
