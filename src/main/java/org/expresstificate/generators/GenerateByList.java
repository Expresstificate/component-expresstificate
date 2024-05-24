package org.expresstificate.generators;

import org.expresstificate.interfaces.Generator;
import org.expresstificate.interfaces.annotations.NotNull;
import org.expresstificate.structures.Template;

import java.util.ArrayList;
import java.util.UUID;

public class GenerateByList extends Generate implements Generator {
  @NotNull
  final ArrayList<String> nameList;

  final ArrayList<String> idList;

  /*
    when the signal is true, the generator will create automatically a new id for each name in the nameList
   */
  final boolean signal;

  public GenerateByList(Template template, ArrayList<String> nameList, ArrayList<String> idList, boolean signal) throws Exception {
    super(template);
    this.nameList = nameList;
    this.idList = idList;
    this.signal = signal;
  }

  public GenerateByList(Template template, ArrayList<String> nameList, boolean signal) throws Exception {
    this(template, nameList, null, signal);
  }

  @Override
  public void run() {
    if (idList != null) {
      try {
        processWithIdList();
      } catch (Exception e) {
        throw new RuntimeException(e);
      }
    } else {
      if (signal) {
        processWithSignal();
      } else {
        processWithoutIdList();
      }
    }
  }

  private void processWithIdList() throws Exception {
    comparator(nameList.size(), idList.size(), "size: nameList and idList must contain the same number of elements");
    for (int i = 0; i < nameList.size(); i++) {
      template.getBody().setReceiverName(nameList.get(i));
      template.getPadding().setId(idList.get(i));
      super.run();
    }
  }

  private void processWithSignal() {
    for (String s : nameList) {
      template.getBody().setReceiverName(s);
      template.getPadding().setId(UUID.randomUUID().toString());
      super.run();
    }
  }

  private void processWithoutIdList() {
    for (String s : nameList) {
      template.getBody().setReceiverName(s);
      super.run();
    }
  }
}
