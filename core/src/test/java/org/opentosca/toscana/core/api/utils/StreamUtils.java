package org.opentosca.toscana.core.api.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class StreamUtils {
    public static void writeTo(InputStream csarStream, OutputStream fout) throws IOException {
        byte[] data = new byte[512];
        int bytesRead = 0;
        while (bytesRead != -1) {
            bytesRead = csarStream.read(data);
            if (bytesRead != -1) {
                fout.write(data, 0, bytesRead);
            }
        }
        csarStream.close();
        fout.close();
    }
}
