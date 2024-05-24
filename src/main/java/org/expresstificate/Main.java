package org.expresstificate;

import org.expresstificate.generators.Generate;
import org.expresstificate.generators.GenerateByList;
import org.expresstificate.structures.Body;
import org.expresstificate.structures.Header;
import org.expresstificate.structures.Padding;
import org.expresstificate.structures.Template;

import java.util.ArrayList;

public class Main {
  public static void main(String[] args) throws Exception {
    String[] lista = new String[]{"name1", "name2", "name3", "name4", "name5"};

    Header header = new Header();
    Body body = new Body("faculdade", "leo");
    Padding padding = new Padding("genteboa", "professor");

    ArrayList<String> nameList = new ArrayList<>();
    for (int i = 0; i < 4; i++) {
      nameList.add(lista[(int) (Math.random() * lista.length)]);
    }

    ArrayList<String> idList = new ArrayList<>();
    idList.add("1");
    idList.add("2");
    idList.add("3");
    idList.add("4");

    Template template = new Template(header, body, padding);
    Generate generate = new GenerateByList(template, nameList, idList, false);
    generate.start();
  }
}
