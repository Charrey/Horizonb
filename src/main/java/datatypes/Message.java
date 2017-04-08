package datatypes;

import utils.Pair;
import utils.ProtocolUtils;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;

public class Message {

    private int type;
    private long par;
    private byte[] content;

    public Message(int type, long par, long content) {
        this(type, par, ProtocolUtils.longToBytes(content));
    }

    public Message(int type, long par, byte[] content) {
        this.type = type;
        long pars = par;
        this.par = pars;
        this.content = content;
    }

    public static Message decode(byte[] res) {
        int type = res[0];
        long pars = ProtocolUtils.bytesToLong(Arrays.copyOfRange(res, 9, 17));
        byte[] content = Arrays.copyOfRange(res, 17, res.length);
        return new Message(type, pars, content);
    }

    public byte[] getTotal() {
        byte[] res = new byte[17+content.length];
        res[0] = new Integer(type).byteValue();
        byte[] long1 = ProtocolUtils.longToBytes(Long.valueOf(content.length));
        res[1] = long1[0];
        res[2] = long1[1];
        res[3] = long1[2];
        res[4] = long1[3];
        res[5] = long1[4];
        res[6] = long1[5];
        res[7] = long1[6];
        res[8] = long1[7];
        byte[] long2 = ProtocolUtils.longToBytes(par);
        res[9] = long2[0];
        res[10] = long2[1];
        res[11] = long2[2];
        res[12] = long2[3];
        res[13] = long2[4];
        res[14] = long2[5];
        res[15] = long2[6];
        res[16] = long2[7];
        for (int i = 0; i<content.length; i++) {
            res[i+17] = content[i];
        }
        return res;
    }

    @Override
    public boolean equals(Object a) {
        if (!(a instanceof Message)) {
            return false;
        }
        Message other = (Message) a;
        if (getType()!=other.getType()) {
            return false;
        }
        if (getPar()!=other.getPar()) {
            return false;
        }
        if (!Arrays.equals(getContent(), other.getContent())) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        String res = "(" + MessageType.toString(type);
        res += " ";
        for(int i = 0; i < Long.numberOfLeadingZeros(par); i++) {
           res += '0';
        }
        res += Long.toBinaryString(par);
        res += " ";

        switch (type) {
            case MessageType.MESSAGE:
                try {
                    res += "[" + new String(content, "UTF-8") + "]";
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                break;
            default:
                res += Arrays.toString(content);
        }

        res += ")";
        return res;
    }


    public int getType() {
        return type;
    }

    public long getPar() {
        return par;
    }

    public byte[] getContent() {
        return content;
    }
}
