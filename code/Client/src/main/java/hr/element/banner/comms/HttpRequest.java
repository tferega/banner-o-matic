package hr.element.banner.comms;

public class HttpRequest {
  private final String  host;
  private final int     port;
  private final String  path;
  private final byte[]  body;

  public HttpRequest(
      final String  host,
      final int     port,
      final String  path,
      final byte[]  body) {
    if (host == null) {
      throw new IllegalArgumentException("Host cannot be null!");
    }

    if (path == null) {
      throw new IllegalArgumentException("Path cannot be null!");
    }

    this.host = host;
    this.port = port;
    this.path = path;
    this.body = body;
  }

  public String getHost() {
    return host;
  }

  public int getPort() {
    return port;
  }

  public String getPath() {
    return path;
  }

  public byte[] getBody() {
    return body;
  }

  public String getTarget() {
    return String.format("http://%s:%d/%s", host, port, path);
  }

  @Override
  public String toString() {
    return String.format("HttpRequest [%s, body length=%d]", getTarget(), body.length);
  }
}
