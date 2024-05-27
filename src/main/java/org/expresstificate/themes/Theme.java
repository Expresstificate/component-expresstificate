package org.expresstificate.themes;

import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.font.Standard14Fonts;
import org.expresstificate.structures.Template;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Theme {
  private final PDDocument document;
  private final PDPage page;

  private final PDFont font = new PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD);
  private final int fontSize = 20;

  private final float height;
  private final float width;

  private final Template template;

  public Theme(Template template, int themeIndex) throws IOException {
    Path path = verifyPath(themeIndex);
    File file = path.toFile();

    this.document = Loader.loadPDF(file);
    this.page = document.getPage(0);

    this.height = page.getMediaBox().getHeight();
    this.width = page.getMediaBox().getWidth();

    this.template = template;
  }

  private Path verifyPath(int value) throws IOException {
    if (value <= 0 || value > 7) {
      throw new IOException("null theme exception");
    }
    return Paths.get("src", "main", "java", "org", "expresstificate", "themes", "assets", "theme-" + value + ".pdf");
  }

  public PDDocument getDocument() throws IOException {
    setHeaderText();
    setBodyText();
    setPaddingText();
    return document;
  }

  private void setHeaderText() throws IOException {
    PDPageContentStream contentStream = new PDPageContentStream(document, page, PDPageContentStream.AppendMode.APPEND, true, true);
    String text = template.getHeader().getTitle();

    contentStream.beginText();
    contentStream.setFont(font, fontSize * 2);
    contentStream.setNonStrokingColor(Color.BLACK);
    contentStream.newLineAtOffset((width - textWidth(text, 2)) / 2, (float) (height * 0.8));
    contentStream.showText(text);
    contentStream.endText();

    text = template.getHeader().getSubTitle();
    contentStream.beginText();
    contentStream.setFont(font, fontSize);
    contentStream.setNonStrokingColor(Color.BLACK);
    contentStream.newLineAtOffset((width - textWidth(text)) / 2, (float) (height * 0.73));
    contentStream.showText(text);
    contentStream.endText();

    contentStream.close();
  }

  private void setBodyText() throws IOException {
    PDPageContentStream contentStream = new PDPageContentStream(document, page, PDPageContentStream.AppendMode.APPEND, true, true);

    String text = template.getBody().getIntroduction() + " " + template.getBody().getReceiverName();

    contentStream.beginText();
    contentStream.setFont(font, fontSize);
    contentStream.setNonStrokingColor(Color.BLACK);
    contentStream.newLineAtOffset((width - textWidth(text)) / 2, (float) (height * 0.60));
    contentStream.showText(text);
    contentStream.endText();

    text = template.getBody().getInstitution();
    contentStream.beginText();
    contentStream.setFont(font, (float) (fontSize * 1.4));
    contentStream.setNonStrokingColor(Color.BLACK);
    contentStream.newLineAtOffset((width - textWidth(text, (float) 1.4)) / 2, (float) (height * 0.50));
    contentStream.showText(text);
    contentStream.endText();

    text = template.getBody().getText();
    contentStream.beginText();
    contentStream.setFont(font, fontSize);
    contentStream.setNonStrokingColor(Color.BLACK);
    contentStream.newLineAtOffset((width - textWidth(text)) / 2, (float) (height * 0.40));
    contentStream.showText(text);
    contentStream.endText();

    contentStream.close();
  }

  private void setPaddingText() throws IOException {
    PDPageContentStream contentStream = new PDPageContentStream(document, page, PDPageContentStream.AppendMode.APPEND, true, true);

    String text = template.getPadding().getEmitterName();

    contentStream.beginText();
    contentStream.setFont(font, (float) (fontSize * 0.6));
    contentStream.setNonStrokingColor(Color.BLACK);
    contentStream.newLineAtOffset(((width - textWidth(text, (float) 0.8)) / 2), (float) (height * 0.30));
    contentStream.showText(text);
    contentStream.endText();

    text = template.getPadding().getEmitterPosition();
    contentStream.beginText();
    contentStream.setFont(font, (float) (fontSize * 0.6));
    contentStream.setNonStrokingColor(Color.BLACK);
    contentStream.newLineAtOffset(((width - textWidth(text, (float) 0.8)) / 2), (float) (height * 0.27));
    contentStream.showText(text);
    contentStream.endText();


    text = template.getPadding().getId();
    contentStream.beginText();
    contentStream.setFont(font, (float) (fontSize * 0.5));
    contentStream.setNonStrokingColor(Color.BLACK);
    contentStream.newLineAtOffset(((width - textWidth(text, (float) 0.8)) / 2) + ((width/5)*2), (float) (height * 0.08));
    contentStream.showText(text);
    contentStream.endText();


    contentStream.close();
  }

  private float textWidth(String text) throws IOException {
    return (font.getStringWidth(text) / 1000) * fontSize;
  }

  private float textWidth(String text, float m) throws IOException {
    return (font.getStringWidth(text) / 1000) * (fontSize * m);
  }

  public void closeDocument() throws IOException {
    document.close();
  }
}
