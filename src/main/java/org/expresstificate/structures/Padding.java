package org.expresstificate.structures;

import org.expresstificate.interfaces.Structure;
import org.expresstificate.interfaces.annotations.NotNull;

public class Padding implements Structure {
  @NotNull
  private String emitterName;

  @NotNull
  private String emitterPosition;

  private String id;

  public Padding(String emitterName, String emitterPosition, String id) {
    this.emitterName = emitterName;
    this.emitterPosition = emitterPosition;
    this.id = id;
    verify(validate());
  }
  public Padding(String emitterName, String emitterPosition) {
    this(emitterName, emitterPosition, null);
  }
  public Padding() {
    this(null, null, null);
  }

  /*
  getters ->
   */

  public String getEmitterName() {
    return emitterName;
  }

  public String getEmitterPosition() {
    return emitterPosition;
  }

  public String getId() {
    return id;
  }

  /*
  setters ->
   */

  public void setEmitterName(String emitterName) {
    this.emitterName = emitterName;
  }

  public void setEmitterPosition(String emitterPosition) {
    this.emitterPosition = emitterPosition;
  }

  public void setId(String id) {
    this.id = id;
  }
}
