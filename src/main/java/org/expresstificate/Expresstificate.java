package org.expresstificate;


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Expresstificate {

  public void generateByJson() {

  }

  public static void generateListByJson() {

  }

  public static void generateByValues() {

  }

  public static void generateListByValues() {

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