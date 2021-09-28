package web;

import java.io.*;

/**
 * A Utility class that provides IO helper methods.
 */
public class IOUtils {
    /**
     * A reasonable default copy buffer length.
     */
    private static final int COPY_BUFFER_LENGTH = 4096;

    /**
     * Helper method that copies data from the source stream to the
     * destination stream.
     *
     * @param in  Input source stream.
     * @param out Output destination stream.
     * @return The number of bytes copied.
     */
    public static long copy(InputStream in, OutputStream out) {
        try {
            long bytesCopied = 0;
            final byte[] buffer = new byte[COPY_BUFFER_LENGTH];

            // While there is unread data from the inputStream,
            // continue writing data to the output stream.
            int bytes;
            while ((bytes = in.read(buffer)) > 0) {
                out.write(buffer, 0, bytes);
                bytesCopied += bytes;
            }

            // Return the total number of copied bytes.
            return bytesCopied;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Copies data from the source stream into a new byte array.
     *
     * @param in Input source stream.
     * @return The copied bytes.
     */
    public static byte[] toBytes(InputStream in) {
        // Creates a new ByteArrayOutputStream to write the downloaded
        // contents to a byte array, which is a generic form of the
        // image.
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        copy(in, out);
        return out.toByteArray();
    }

    /**
     * Copies data from the passed file into a new byte array.
     *
     * @param file An file object.
     * @return A byte array containing all the bytes in the passed file.
     * @throws IOException
     */
    public static byte[] toBytes(File file) throws IOException {
        return toBytes(new FileInputStream(file));
    }
}