package org.expresstificate;

import org.expresstificate.generators.Generate;
import org.expresstificate.generators.GenerateByList;
import org.expresstificate.structures.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Main {
  public static void main(String[] args) throws Exception {
    Header header = new Header();
    Body body = new Body("is awarded to", "for their exceptional participation in the DSA course", "Universidade Católica do Salvador", null);
    Padding padding = new Padding("Mario Jorge", "Professor");

    Template template = new Template(header, body, padding);

    Certificate certificate = new Certificate(template, 5);
    ArrayList<String> nameList = new ArrayList<>();

    nameList.add("Leonardo");
    nameList.add("Illana");

    Generate generate = new GenerateByList(certificate, nameList, true);
    generate.start();

    generate.join();

    Map<String, byte[]> values = generate.getContent();

    byte[][] pdfs = values.values().toArray(new byte[0][]);
    String[] names = values.keySet().toArray(new String[0]);

    byte[] zip = createZipWithPDFs(pdfs, names);
    Files.write(Paths.get("output.zip"), zip);
  }

  public static byte[] createZipWithPDFs(byte[][] pdfs, String[] names) throws IOException {
    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
    try (ZipOutputStream zos = new ZipOutputStream(byteArrayOutputStream)) {
      for (int i = 0; i < pdfs.length; i++) {
        zos.putNextEntry(new ZipEntry(names[i] + ".pdf"));
        zos.write(pdfs[i]);
        zos.closeEntry();
      }
      zos.finish();
      return byteArrayOutputStream.toByteArray();
    }
  }
}
