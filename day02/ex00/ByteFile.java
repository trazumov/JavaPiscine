import java.io.FileInputStream;
import java.io.IOException;

public class ByteFile {
    private byte[]  buffer = new byte[8];
    private String signature = "";

    public ByteFile(String filepath) {
        try (FileInputStream istream = new FileInputStream(filepath)) {
            istream.read(buffer, 0, 8);

            for (byte b : buffer) {
                int l = b;

                if (l < 0) {
                    l += 256;
                }

                if (Integer.toHexString(l).length() == 1) {
                    signature += "0" + Integer.toHexString(l).toUpperCase() + " ";
                } else {
                    signature += Integer.toHexString(l).toUpperCase() + " ";
                }
            }
            signature = signature.trim();
        } catch(IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public String getSignature() {
        return signature;
    }
}