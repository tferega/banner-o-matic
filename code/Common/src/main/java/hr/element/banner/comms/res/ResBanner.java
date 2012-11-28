package hr.element.banner.comms.res;

import java.io.Serializable;

public class ResBanner implements Serializable {
  private static final long serialVersionUID = 1L;

  private final String[] lines;

  public ResBanner(
      final String[] lines) {
    if (lines == null) {
      throw new IllegalArgumentException("Lines cannot be null!");
    }

    this.lines = lines;
  }

  public String[] getLines() {
    return lines;
  }

  @Override
  public String toString() {
    return String.format("ResBanner[%d lines]", lines.length);
  }
}
