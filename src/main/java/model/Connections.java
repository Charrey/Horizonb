package model;

import datatypes.Message;
import datatypes.MessageType;
import utils.Encoder;

import java.io.IOException;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Random;

public class Connections {

    public static final long ID = new Random().nextLong();
    private static Socket socket;

    public static boolean checkConnection(String IP, String port) throws IncorrectFieldException{
        if (!validIP(IP)) {
            throw new IncorrectFieldException("IP");
        }
        try {
            socket = new Socket(InetAddress.getByName(IP), Integer.parseInt(port));
        } catch (IOException e) {
            return false;
        } catch (IllegalArgumentException e) {
            throw new IncorrectFieldException("Port");
        }
        Message m1 = new Message(MessageType.PING, 0, ID);
        try {
            Encoder.streamOut(socket, m1);
        } catch (IOException e) {
            return false;
        }
        try {
            return awaitPing(1000);
        } catch (InterruptedException e) {
            return false;
        }
    }

    private static boolean awaitPing(long timeout) throws InterruptedException {
        if (Receiver.pollPing()) {
            return true;
        } else {
            Thread.sleep(1000);
        }
        if (Receiver.pollPing()) {
            return true;
        }
        return false;
    }

    public static Socket getSocket() {
        return socket;
    }

    public static boolean checkConnection(ServerInfo info) {
        try {
            return checkConnection(info.getIP(), String.valueOf(info.getPort()));
        } catch (IncorrectFieldException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static class IncorrectFieldException extends Exception {
        private final String what;

        public IncorrectFieldException(String what) {
            this.what = what;
        }
        @Override
        public String getMessage() {
            return what + " incorrect";
        }
    }


    public static boolean validIP (String ip) {
        try {
            if ( ip == null || ip.isEmpty() ) {
                return false;
            }

            String[] parts = ip.split( "\\." );
            if ( parts.length != 4 ) {
                return false;
            }

            for ( String s : parts ) {
                int i = Integer.parseInt( s );
                if ( (i < 0) || (i > 255) ) {
                    return false;
                }
            }
            if ( ip.endsWith(".") ) {
                return false;
            }

            return true;
        } catch (NumberFormatException nfe) {
            return false;
        }
    }
}
