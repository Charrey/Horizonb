package utils;

import datatypes.Message;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class Encoder {

    public static void streamOut(Socket socket, Message message) throws IOException {
        streamOut(socket.getOutputStream(), message);
    }

    public static void streamOut(OutputStream stream, Message message) throws IOException {
        stream.write(message.getTotal());
        stream.flush();
    }


}
