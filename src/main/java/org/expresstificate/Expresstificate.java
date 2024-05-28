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

public class Expresstificate {

  public static byte[] generateByJson(Map<String, Object> json) throws Exception {
    Header header = new Header((String) json.get("title"),
            (String) json.get("subTitle"));

    Body body = new Body((String) json.get("introduction"),
            (String) json.get("text"),
            (String) json.get("institution"),
            (String) json.get("receiverName"));

    Padding padding = new Padding((String) json.get("emitterName"),
            (String) json.get("emitterPosition"),
            json.get("id") == null ? null : (String) json.get("id"));

    Template template = new Template(header, body, padding);

    Certificate certificate;
    if (json.get("themeIndex") == null) {
      certificate = new Certificate(template);
    } else {
      certificate = new Certificate(template, (int) json.get("themeIndex"));
    }

    Generate generate = new Generate(certificate, (boolean) json.get("generateId?"));
    generate.start();

    generate.join();
    Map<String, byte[]> values = generate.getContent();

    byte[][] pdfs = values.values().toArray(new byte[0][]);
    String[] names = values.keySet().toArray(new String[0]);

    return createZipWithPDFs(pdfs, names);
  }

  public static byte[] generateListByJson(Map<String, Object> json, ArrayList<String> nameList, ArrayList<String> idList) throws Exception {
    Header header = new Header((String) json.get("title"),
            (String) json.get("subTitle"));

    Body body = new Body((String) json.get("introduction"),
            (String) json.get("receiverName"),
            (String) json.get("text"),
            (String) json.get("institution"));

    Padding padding = new Padding((String) json.get("emitterName"),
            (String) json.get("emitterPosition"),
            json.get("id") == null ? null : (String) json.get("id"));

    Template template = new Template(header, body, padding);

    Certificate certificate;
    if (json.get("themeIndex") == null) {
      certificate = new Certificate(template);
    } else {
      certificate = new Certificate(template, (int) json.get("themeIndex"));
    }

    Generate generate;
    if (idList.isEmpty()) {
      generate = new GenerateByList(certificate, nameList, (boolean) json.get("generateId?"));
    } else {
      generate = new GenerateByList(certificate, nameList, idList, (boolean) json.get("generateId?"));
    }
    generate.start();

    generate.join();
    Map<String, byte[]> values = generate.getContent();

    byte[][] pdfs = values.values().toArray(new byte[0][]);
    String[] names = values.keySet().toArray(new String[0]);

    return createZipWithPDFs(pdfs, names);
  }

  public static byte[] createZipWithPDFs(byte[][] pdfs, String[] names) throws IOException {
    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
    try (ZipOutputStream zos = new ZipOutputStream(byteArrayOutputStream)) {
      for (int i = 0; i < pdfs.length; i++) {
        zos.putNextEntry(new ZipEntry("certificate-" + names[i] + ".pdf"));
        zos.write(pdfs[i]);
        zos.closeEntry();
      }
      zos.finish();
      return byteArrayOutputStream.toByteArray();
    }
  }

  public static void createFile(byte[] zip) throws IOException {
    Files.write(Paths.get("output.zip"), zip);
  }
}