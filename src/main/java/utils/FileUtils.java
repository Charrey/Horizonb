package utils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class FileUtils {

    public static File fromResources(String path) throws IOException {
        URL url = FileUtils.class.getClassLoader().getResource(path);
        if (url == null) {
            throw new IOException("Resource file " + path + " not found.");
        }
        return new File(url.getFile());
    }

    public static InputStream fromResourcesStream(String path) throws IOException {
        InputStream url = FileUtils.class.getClassLoader().getResourceAsStream(path);
        if (url == null) {
            throw new IOException("Resource file " + path + " not found.");
        }
        return url;
    }

}
