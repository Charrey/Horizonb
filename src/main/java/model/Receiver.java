package model;

import datatypes.Message;
import datatypes.MessageType;
import utils.Decoder;

public class Receiver implements Runnable {

    private static boolean PING = false;

    @Override
    public void run() {
        while(true) {
            if (Connections.getSocket()==null || Connections.getSocket().isClosed()) {
                Thread.yield();
                continue;
            }
            Message m = Decoder.readMessage();
            if (m.getType()== MessageType.PING) {
                PING = true;
            }

        }
    }

    public static boolean pollPing() {
        if (PING) {
            PING = false;
            return true;
        }
        return false;
    }
}
