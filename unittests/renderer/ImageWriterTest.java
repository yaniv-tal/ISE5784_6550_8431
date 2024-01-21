package renderer;

import org.junit.jupiter.api.Test;
import primitives.Color;

import static org.junit.jupiter.api.Assertions.*;
import static primitives.Util.isZero;

/**
 * Unit tests for renderer.ImageWriter class
 * @author Yaniv Tal and Ahuvya Betzalel
 */
class ImageWriterTest {
    @Test
    void imageWriterTest() {
        ImageWriter imageTest = new ImageWriter("imageTest", 800, 500);
        for (int j = 0; j < 800; j++) {
            if (isZero(j % 50))
                for (int i = 0; i < 500; i++) {
                    imageTest.writePixel(j, i, Color.BLACK);
                }

            else
                for (int i = 0; i < 500; i++) {
                    if (isZero(i % 50))
                        imageTest.writePixel(j, i, Color.BLACK);
                    else
                        imageTest.writePixel(j, i, new Color(64, 228, 204));
                }
        }
        imageTest.writeToImage();
    }
}