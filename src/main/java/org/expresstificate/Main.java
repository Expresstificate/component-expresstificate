package org.expresstificate;

import org.expresstificate.generators.Generate;
import org.expresstificate.generators.GenerateByList;
import org.expresstificate.structures.*;

import java.util.ArrayList;

public class Main {
  public static void main(String[] args) throws Exception {
    Header header = new Header();
    Body body = new Body("is awarded to", "for their exceptional participation in the DSA course", "Universidade Católica do Salvador", null);
    Padding padding = new Padding("Mario Jorge", "Professor");
    Template template = new Template(header, body, padding);

    Certificate certificate = new Certificate(template, 5);
    ArrayList<String> nameList = new ArrayList<>();

    nameList.add("Leonardo Gabriel Cabral Fernandes Gusmão");

    Generate generate = new GenerateByList(certificate, nameList, true);
    generate.start();
  }
}
