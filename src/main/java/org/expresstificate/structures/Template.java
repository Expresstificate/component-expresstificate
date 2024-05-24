package org.expresstificate.structures;


import org.expresstificate.interfaces.Structure;
import org.expresstificate.interfaces.annotations.NotNull;

public class Template implements Structure {
  @NotNull
  private Header header;
  @NotNull
  private Body body;
  @NotNull
  private Padding padding;

  public Template(Header header, Body body, Padding padding) throws Exception {
    this.header = header;
    this.body = body;
    this.padding = padding;
    verify(validate());
  }
  public Template() {}

  public Header getHeader() {
    return header;
  }

  public void setHeader(Header header) {
    this.header = header;
  }

  public Body getBody() {
    return body;
  }

  public void setBody(Body body) {
    this.body = body;
  }

  public Padding getPadding() {
    return padding;
  }

  public void setPadding(Padding padding) {
    this.padding = padding;
  }

  public void setTheme(int themeIndex) {
    // set theme by themes folder
  }
}
