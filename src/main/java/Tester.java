import datatypes.Message;
import datatypes.MessageType;
import model.Connections;
import server.Server;
import utils.Encoder;
import utils.ProtocolUtils;

import java.io.*;
import java.net.Socket;
import java.nio.charset.Charset;
import java.util.Arrays;

public class Tester {

    public static void main(String[] args) throws IOException, InterruptedException {
        Server server = new Server();
        new Thread(server).start();

        Socket clientSocket = new Socket("localhost", 2772);
        Socket clientSocket2 = new Socket("localhost", 2772);
        OutputStream out = clientSocket.getOutputStream();
        OutputStream out2 = clientSocket2.getOutputStream();


        Message m1 = new Message(MessageType.PING, 0, Connections.ID);
        Message m2 = new Message(MessageType.MESSAGE, 0, "Hello!".getBytes(Charset.forName("UTF-8")));
        Message m3 = new Message(MessageType.MESSAGE, 0, "Hi!".getBytes(Charset.forName("UTF-8")));

        Encoder.streamOut(out, m1);
        Encoder.streamOut(out, m2);
        Encoder.streamOut(out2, m3);
        Encoder.streamOut(out, m2);

        clientSocket.close();
        clientSocket2.close();
    }
}
