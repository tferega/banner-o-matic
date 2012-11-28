package hr.element.banner.comms.req;

import java.io.Serializable;

public class ReqBanner implements Serializable {
  private static final long serialVersionUID = 1L;

  private final String           font;
  private final ReqJustification justification;
  private final int              outputWidth;

  public ReqBanner(
      final String           font,
      final ReqJustification justification,
      final int              outputWidth) {
    if (font == null) {
      throw new IllegalArgumentException("Font cannot be null!");
    }

    if (justification == null) {
      throw new IllegalArgumentException("Justification cannot be null!");
    }

    if (outputWidth < 1) {
      throw new IllegalArgumentException("Output width must be greater than 1!");
    }

    this.font          = font;
    this.justification = justification;
    this.outputWidth   = outputWidth;
  }

  public String getFont() {
    return font;
  }

  public ReqJustification getJustification() {
    return justification;
  }

  public int getOutputWidth() {
    return outputWidth;
  }

  @Override
  public String toString() {
    return String.format("ReqBanner[%s, %s, %d]", font, justification, outputWidth);
  }
}
