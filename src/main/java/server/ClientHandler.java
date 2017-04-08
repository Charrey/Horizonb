package server;

import utils.Decoder;
import utils.ProtocolUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.util.Arrays;

public class ClientHandler implements Runnable {

    private final Server server;
    private Socket socket = null;

    private InputStream input = null;
    private OutputStream output = null;

    private InputStream soundoutput = null;

    public ClientHandler(Socket clientSocket, Server server) throws IOException {
        this.socket = clientSocket;
        this.input = socket.getInputStream();
        this.output = socket.getOutputStream();
        this.server = server;
    }


    public void addAsSoundSocket(ClientHandler isSoundSocket) {
        soundoutput = isSoundSocket.getInputStream();
    }


    @Override
    public void run() {
        while (true) {
            byte[] res = new byte[17];
            int counter = 0;
            try {
                while (counter < res.length) {
                    int a = input.read();
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
                System.out.println("Byebye");
                break;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public InputStream getInputStream() {
        return input;
    }
}
