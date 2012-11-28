package hr.element.banner.comms;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Scanner;

public class Sender {
  public static HttpResponse raw(final HttpRequest request)
      throws MalformedURLException, IOException {
    final String target = request.getTarget();
    final byte[] body   = request.getBody();

    final HttpURLConnection connection = createConnection(target);
    configureConnection(connection, body.length);
    writeToConnection(connection, body);
    final HttpResponse response = readFromConnection(connection);
    closeConnection(connection);

    return response;
  }

  private static HttpURLConnection createConnection(final String target)
      throws MalformedURLException, IOException {
    final URL url = new URL(target);
    final HttpURLConnection connection = (HttpURLConnection) url.openConnection();
    return connection;
  }

  private static void configureConnection(
      final HttpURLConnection connection,
      final int contentLength)
      throws ProtocolException {
    connection.setDoOutput(true);
    connection.setDoInput(true);
    connection.setInstanceFollowRedirects(false);
    connection.setRequestMethod("POST");
    connection.setRequestProperty("Content-Type", "application/octet-stream");
    connection.setRequestProperty("Content-Length", Integer.toString(contentLength));
    connection.setUseCaches(false);
  }

  private static void writeToConnection(
      final HttpURLConnection connection,
      final byte[] body)
      throws IOException {
    final DataOutputStream wr = new DataOutputStream(connection.getOutputStream ());
    wr.write(body);
    wr.flush();
    wr.close();
  }

  private static HttpResponse readFromConnection(final HttpURLConnection connection)
      throws IOException {
    final int    code;
    final String body;

    code = connection.getResponseCode();

    final InputStream is;
    if (code >= 400) {
      is = connection.getErrorStream();
    } else {
      is = connection.getInputStream();
    }

    if (is == null) {
      body = null;
    } else {
      final Scanner scanner = new Scanner(is, "UTF-8").useDelimiter("\\A");
      if (scanner.hasNext()) {
        body = scanner.next();
      } else {
        body = null;
      }
    }

    return new HttpResponse(code, body);
  }

  private static void closeConnection(final HttpURLConnection connection) {
    connection.disconnect();
  }
}
