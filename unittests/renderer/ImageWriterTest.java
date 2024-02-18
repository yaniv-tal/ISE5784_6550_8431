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
        //We will go by the pixels we received and create longitude and latitude lines in black
        for (int i = 0; i < 500; i++)
            for (int j = 0; j < 800; j++)
                imageTest.writePixel(j, i, new Color(64, 228, 204));//turquoise
        for (int j = 0; j < 800; j+=50)
            for (int i = 0; i < 500; i++)
                imageTest.writePixel(j, i, Color.BLACK);
        for (int i = 0; i < 500; i+=50)
            for (int j = 0; j < 800; j++)
                imageTest.writePixel(j, i, Color.BLACK);
        imageTest.writeToImage();
    }
}
