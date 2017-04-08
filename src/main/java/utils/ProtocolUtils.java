package utils;

import datatypes.MessageType;

import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ProtocolUtils {

    private static ByteBuffer fromlong = ByteBuffer.allocate(Long.BYTES);
    private static ByteBuffer tolong = ByteBuffer.allocate(Long.BYTES);

    public static synchronized byte[] longToBytes(long x) {
        fromlong.putLong(0, x);
        return fromlong.array();
    }

    public static synchronized long bytesToLong(byte[] bytes) {
        assert bytes.length==8;
        tolong.clear();
        tolong.put(bytes, 0, bytes.length);
        tolong.flip();
        return tolong.getLong();
    }
}
