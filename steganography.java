import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class Steganography {

    // Encode message into image
    public static void encode(String inputImagePath, String outputImagePath, String message) throws Exception {

        BufferedImage image = ImageIO.read(new File(inputImagePath));
        int width = image.getWidth();
        int height = image.getHeight();

        // Add delimiter to message
        message += "#END#";
        byte[] msgBytes = message.getBytes();
        int msgBitIndex = 0;

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {

                int pixel = image.getRGB(x, y);

                if (msgBitIndex < msgBytes.length * 8) {
                    int bit = (msgBytes[msgBitIndex / 8] >> (7 - (msgBitIndex % 8))) & 1;

                    int red = (pixel >> 16) & 0xFF;
                    red = (red & 0xFE) | bit; // Modify LSB

                    pixel = (pixel & 0xFF00FFFF) | (red << 16);
                    image.setRGB(x, y, pixel);

                    msgBitIndex++;
                }
            }
        }

        ImageIO.write(image, "png", new File(outputImagePath));
        System.out.println("Message encoded successfully!");
    }

    // Decode message from image
    public static void decode(String imagePath) throws Exception {

        BufferedImage image = ImageIO.read(new File(imagePath));
        StringBuilder message = new StringBuilder();
        int currentByte = 0;
        int bitCount = 0;

        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {

                int pixel = image.getRGB(x, y);
                int red = (pixel >> 16) & 0xFF;
                int bit = red & 1;

                currentByte = (currentByte << 1) | bit;
                bitCount++;

                if (bitCount == 8) {
                    char c = (char) currentByte;
                    message.append(c);
                    if (message.toString().endsWith("#END#")) {
                        System.out.println("Decoded Message: " +
                                message.substring(0, message.length() - 5));
                        return;
                    }
                    bitCount = 0;
                    currentByte = 0;
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        encode("input.png", "stego.png", "Hello Steganography");
        decode("stego.png");
    }
}
