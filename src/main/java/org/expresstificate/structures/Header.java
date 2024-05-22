package org.expresstificate.structures;

import org.expresstificate.interfaces.Structure;
import org.expresstificate.interfaces.annotations.NotNull;

public class Header implements Structure {
  @NotNull
  private String title;

  @NotNull
  private String subTitle;

  public Header(String title, String subTitle) throws Exception {
    this.title = title;
    this.subTitle = subTitle;
    verify(validate());
  }
  public Header() throws Exception {
    this("CERTIFICATE", "of achievement");
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getSubTitle() {
    return subTitle;
  }

  public void setSubTitle(String subTitle) {
    this.subTitle = subTitle;
  }
}
