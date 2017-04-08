package model;

public class ServerInfo {

    private final int port;
    private final String name;
    private final String IP;

    public ServerInfo(String name, String IP, int port) {
        this.name = name;
        this.IP = IP;
        this.port = port;
    }

    public int getPort() {
        return port;
    }

    public String getIP() {
        return IP;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "(" + name + ", " + IP + ", " + port + ")";
    }
}
