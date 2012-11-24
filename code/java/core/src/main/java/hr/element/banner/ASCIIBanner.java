package hr.element.banner;

public class ASCIIBanner {
  public static String getBanner(String text) {
    if (text.equals("KRAPI")) {
      return Precompiled.KRAPI;
    }
    else if (text.equals("CLIENT")) {
      return Precompiled.CLIENT;
    }
    else if (text.equals("RAW DATA")) {
      return Precompiled.RAW_DATA;
    }
    else if (text.equals("BSP")) {
      return Precompiled.BSP;
    }
    else {
      throw new UnsupportedOperationException("Cannot render "+ text);
    }
  }

  public static void printBanner(String text) throws InterruptedException {
    final String banner = getBanner(text);
    final String[] bannerLineList = banner.split("[\\r\\n]+");
    for (String bannerLine : bannerLineList) {
      System.out.println(bannerLine);
      Thread.sleep(15);
    }
  }
}
