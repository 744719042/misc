package utils;

import java.io.Closeable;
import java.io.IOException;

/**
 * Created by Administrator on 2018/3/13.
 */

public class IOUtils {
    public static void close(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
