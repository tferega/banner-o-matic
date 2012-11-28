package hr.element.banner.comms;

public class HttpResponse {
  private final int    code;
  private final String body;

  public HttpResponse(
      final int    code,
      final String body) {
    this.code = code;
    this.body = body;
  }

  public int getCode() {
    return code;
  }

  public String getBody() {
    return body;
  }

  @Override
  public String toString() {
    final String bodyLen = (body == null) ? "N/A" : Integer.toString(body.length());
    return String.format("HttpResponse[%d, body length=%s]", code, bodyLen);
  }
}
