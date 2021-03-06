package classpath;

import java.io.InputStream;
import java.util.zip.ZipFile;

public class ZipEntry implements Entry {
    String absPath;

    public ZipEntry(String path) {
        absPath = path;
    }

    public byte[] readClass(String className) {
        try {
            ZipFile zFile = new ZipFile(absPath);
            java.util.zip.ZipEntry classFile = zFile.getEntry(className);
            InputStream in = zFile.getInputStream(classFile);

            byte[] result = new byte[(int) classFile.getSize()];

            int hasRead = 0;
            int n = 0;

            do {
                n = in.read(result, hasRead, result.length);
                hasRead += n;
            } while (n != -1 && hasRead < result.length);

            in.close();
            zFile.close();

            return result;

        } catch (Exception e) {
            return null;
        }
    }
}
