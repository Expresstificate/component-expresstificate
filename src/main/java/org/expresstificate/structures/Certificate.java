package org.expresstificate.structures;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.expresstificate.themes.Theme;

import java.io.IOException;

public class Certificate {
  protected Template template;
  private Theme theme;
  private final int themeIndex;

  public Certificate(Template template, int themeIndex) throws IOException {
    this.template = template;
    this.themeIndex = themeIndex;
  }

  public Certificate(Template template) throws IOException {
    this(template, 1);
  }

  public Template getTemplate() {
    return template;
  }

  public void setTemplate(Template template) {
    this.template = template;
  }

  public void setTheme() throws IOException {
    theme = new Theme(template, themeIndex);
  }

  public Theme getTheme() {
    return theme;
  }

  public PDDocument getDocument() throws IOException {
    return theme.getDocument();
  }
}
