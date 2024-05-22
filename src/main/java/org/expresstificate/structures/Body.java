package org.expresstificate.structures;

import org.expresstificate.interfaces.Structure;
import org.expresstificate.interfaces.annotations.NotNull;

public class Body implements Structure {
  @NotNull
  private String introduction;

  @NotNull
  private String text;

  @NotNull
  private String institution;

  private String receiverName;

  public Body(String introduction, String text, String institution, String receiverName) throws Exception {
    this.introduction = introduction;
    this.institution = institution;
    this.text = text;
    this.receiverName = receiverName;
    verify(validate());
  }
  public Body(String institution) throws Exception {
    this("is awarded to", "", institution, null);
  }
  public Body(String institution, String receiverName) throws Exception {
    this("is awarded to", "", institution, receiverName);
  }
  public Body() throws Exception {
    this("is awarded to", "", null, null);
  }

  public String getIntroduction() {
    return introduction;
  }

  public void setIntroduction(String introduction) {
    this.introduction = introduction;
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

  public String getInstitution() {
    return institution;
  }

  public void setInstitution(String institution) {
    this.institution = institution;
  }

  public String getReceiverName() {
    return receiverName;
  }

  public void setReceiverName(String receiverName) {
    this.receiverName = receiverName;
  }
}
