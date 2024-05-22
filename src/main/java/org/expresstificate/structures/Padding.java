package org.expresstificate.structures;

import org.expresstificate.interfaces.Structure;
import org.expresstificate.interfaces.annotations.NotNull;

public class Padding implements Structure {
  @NotNull
  private String emitterName;

  private String emitterPosition;

  private String id;

  public Padding(String emitterName, String emitterPosition, String id) throws Exception {
    this.emitterName = emitterName;
    this.emitterPosition = emitterPosition;
    this.id = id;
    verify(validate());
  }
  public Padding(String emitterName, String emitterPosition) throws Exception {
    this(emitterName, emitterPosition, null);
  }
  public Padding() throws Exception {
    this(null, null, null);
  }

  public String getEmitterName() {
    return emitterName;
  }

  public void setEmitterName(String emitterName) {
    this.emitterName = emitterName;
  }

  public String getEmitterPosition() {
    return emitterPosition;
  }

  public void setEmitterPosition(String emitterPosition) {
    this.emitterPosition = emitterPosition;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }
}
