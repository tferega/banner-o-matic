package hr.element.banner.serialization;

import java.io.*;

public class JavaSerializator {
  public static byte[] serialize(Serializable ser) throws IOException {
    final byte[] obj;

    try {
      final ByteArrayOutputStream bos = new ByteArrayOutputStream();
      try {
        final ObjectOutputStream oos = new ObjectOutputStream(bos);
        try {
          oos.writeObject(ser);
          obj = bos.toByteArray();
        } finally {
          oos.close();
        }
      } finally {
        bos.close();
      }
    } catch (IOException e) {
      throw new IOException("An error occured during serializtion!", e);
    }

    return obj;
  }

  public static Serializable deserialize(byte[] obj) throws IOException {
    final Serializable ser;

    try {
      final ByteArrayInputStream bis = new ByteArrayInputStream(obj);
      try {
        final ObjectInputStream ois = new ObjectInputStream(bis);
        try {
          ser = (Serializable) ois.readObject();
        } finally {
          ois.close();
        }
      } finally {
        bis.close();
      }
    } catch (IOException e) {
      throw new IOException("An error occured during deserialization!", e);
    } catch (ClassNotFoundException e) {
      throw new IOException("An error occured during deserialization!", e);
    }

    return ser;
  }
}
