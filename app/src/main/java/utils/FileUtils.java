package utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

/**
 * Created by Administrator on 2018/3/13.
 */

public class FileUtils {
    public static void copy(InputStream inputStream, File file) {
        FileOutputStream fos = null;
        try {
            if (!file.exists()) {
                file.createNewFile();
            }

            fos = new FileOutputStream(file);
            byte[] bytes = new byte[1024];
            int length = -1;
            while ((length = inputStream.read(bytes)) != -1) {
                fos.write(bytes, 0, length);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            IOUtils.close(fos);
        }
    }
}
