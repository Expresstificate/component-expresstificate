package org.expresstificate.generators;

import org.expresstificate.interfaces.Generator;
import org.expresstificate.interfaces.annotations.NotNull;
import org.expresstificate.structures.Body;
import org.expresstificate.structures.Certificate;
import org.expresstificate.structures.Padding;
import org.expresstificate.structures.Template;

import java.util.ArrayList;
import java.util.UUID;

public class GenerateByList extends Generate implements Generator {
  @NotNull
  final ArrayList<String> nameList;

  final ArrayList<String> idList;

  private final Template template;

  /*
    when the signal is true, the generator will create automatically a new id for each name in the nameList
   */
  final boolean signal;

  public GenerateByList(Certificate certificate, ArrayList<String> nameList, ArrayList<String> idList, boolean signal) throws Exception {
    super(certificate);
    this.nameList = nameList;
    this.idList = idList;
    this.signal = signal;
    this.template = certificate.getTemplate();
  }

  public GenerateByList(Certificate certificate, ArrayList<String> nameList, boolean signal) throws Exception {
    this(certificate, nameList, null, signal);
  }

  @Override
  public void run() {
    if (idList != null) {
      try {
        processWithIdList(template.getBody(), template.getPadding());
      } catch (Exception e) {
        throw new RuntimeException(e);
      }
    } else {
      processWithGenerateUUID(template.getBody(), template.getPadding());
    }
  }

  private void processWithIdList(Body body, Padding padding) throws Exception {
    comparator(nameList.size(), idList.size(), "size: nameList and idList must contain the same number of elements");
    for (int i = 0; i < nameList.size(); i++) {
      body.setReceiverName(nameList.get(i));
      padding.setId(idList.get(i));
      super.run();
    }
  }

  private void processWithGenerateUUID(Body body, Padding padding) {
    for (String s : nameList) {
      body.setReceiverName(s);
      if (signal) {
        padding.setId(generateUUID());
      }
      super.run();
    }
  }
}
