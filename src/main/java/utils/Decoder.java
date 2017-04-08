package utils;

import datatypes.Message;
import model.Connections;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.SocketException;
import java.util.Arrays;

public class Decoder {

    public static Message decode(byte[] res) {
        return Message.decode(res);
    }

    public static Message readMessage() {
        byte[] res = new byte[17];
        int counter = 0;
        try {
            while (counter < res.length) {
                int a = Connections.getSocket().getInputStream().read();
                if (a != -1) {
                    res[counter] = (byte) a;
                    counter++;
                    if (counter == 17) {
                        long length = ProtocolUtils.bytesToLong(Arrays.copyOfRange(res, 1, 9));
                        res = Arrays.copyOf(res, (int) (17 + length));
                    }
                }
            }
            Decoder.decode(res);
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return decode(res);
    }

}
