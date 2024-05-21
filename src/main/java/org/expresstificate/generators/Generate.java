package org.expresstificate.generators;

import org.expresstificate.structures.Template;

import java.util.ArrayList;

public class Generate extends Thread {
  private Template template;
  private ArrayList<String> nameList;
  private ArrayList<String> idList;

  public Generate(Template template, ArrayList<String> nameList, ArrayList<String> idList) {
    this.template = template;
    this.nameList = nameList;
    this.idList = idList;
  }
  public Generate(Template template) {
    this.template = template;
  }

  @Override
  public void run() {

  }

  private void validate() {
    if (nameList == null && idList == null) {
      //generate certificate
    } else {
      //iterate through nameList or/and idList and generate each certificate (then zip it)
    }
  }
}
