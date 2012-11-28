package hr.element.banner.comms.res;

import java.io.Serializable;

public class ResFonts implements Serializable {
  private static final long serialVersionUID = 1L;

  private final String[] fonts;

  public ResFonts(
      final String[] fonts) {
    if (fonts == null) {
      throw new IllegalArgumentException("Font list cannot be null!");
    }

    this.fonts = fonts;
  }

  public String[] getFonts() {
    return fonts;
  }

  @Override
  public String toString() {
    return String.format("ResFonts[%d fonts]", fonts.length);
  }
}
