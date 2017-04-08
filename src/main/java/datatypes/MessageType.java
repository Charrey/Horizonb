package datatypes;

import java.lang.reflect.Field;

public abstract class MessageType {

    public static final int PING = 1;
    public static final int MESSAGE = 2;
    public static final int REQUESTACCESS = 3;
    public static final int WARNING = 4;
    public static final int VISCHANGE = 5;






    public static String toString(int input) {
        Field[] a = MessageType.class.getDeclaredFields();
        for (int i = 0; i<a.length; i++) {
            try {
                if (input==a[i].getInt(null)) {
                    return a[i].getName();
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
