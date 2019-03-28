package org.firstinspires.ftc.javatest;


import java.awt.Color;
import java.awt.image.*;
import java.awt.color.*;
import java.io.File;
import java.io.IOException;
import java.nio.*;

import javax.imageio.ImageIO;

public class ColorFinderTest {
    private static class Color
    {
        public int red(int rgb) { return (rgb >> 16 ) & 0x000000FF; }
        public int green(int rgb) { return (rgb >> 8 ) & 0x000000FF; }
        public int blue(int rgb) { return rgb  & 0x000000FF; }
        public void RGBToHSV(int r, int g, int b, float hsv[]) {
            r /= 255; g /= 255; b /= 255;
            int max = Math.max(Math.max(r, g), b);
            int min = Math.min(Math.min(r, g), b);
            int d = max - min;
            int h = 0;
            if (d == 0) h = 0;
            else if (max == r) h = (g - b) / d % 6;
            else if (max == g) h = (b - r) / d + 2;
            else if (max == b) h = (r - g) / d + 4;

            int l = (min + max) / 2;
            int s = 0;
            if (1 - Math.abs(2 * l - 1) > 0)
            {
                s = (d == 0) ? 0 : d / (1 - Math.abs(2 * l - 1));
            }

            hsv[0] = h * 60;
            hsv[1] = s;
            hsv[2] = l;
        }
    }

    public static ByteBuffer convertImageData(BufferedImage bi) {
        ByteBuffer byteBuffer;
        DataBuffer dataBuffer = bi.getRaster().getDataBuffer();

        if (dataBuffer instanceof DataBufferByte) {
            byte[] pixelData = ((DataBufferByte) dataBuffer).getData();
            byteBuffer = ByteBuffer.wrap(pixelData);
            System.out.println("Buffer Type: wrapped");
        }
        else if (dataBuffer instanceof DataBufferUShort) {
            short[] pixelData = ((DataBufferUShort) dataBuffer).getData();
            byteBuffer = ByteBuffer.allocate(pixelData.length * 2);
            byteBuffer.asShortBuffer().put(ShortBuffer.wrap(pixelData));
            System.out.println("Buffer Type: ushort");
        }
        else if (dataBuffer instanceof DataBufferShort) {
            short[] pixelData = ((DataBufferShort) dataBuffer).getData();
            byteBuffer = ByteBuffer.allocate(pixelData.length * 2);
            byteBuffer.asShortBuffer().put(ShortBuffer.wrap(pixelData));
            System.out.println("Buffer Type: short");
        }
        else if (dataBuffer instanceof DataBufferInt) {
            int[] pixelData = ((DataBufferInt) dataBuffer).getData();
            byteBuffer = ByteBuffer.allocate(pixelData.length * 4);
            byteBuffer.asIntBuffer().put(IntBuffer.wrap(pixelData));
            System.out.println("Buffer Type: int");
        }
        else {
            throw new IllegalArgumentException("Not implemented for data buffer type: " + dataBuffer.getClass());
        }

        return byteBuffer;
    }

    public enum ColorTarget{
        Yellow, Red, Blue, White, Green
    }

    public void Test() {
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File("C:/GitHub/ftc_app/javatest/src/main/java/Images/TestImage.bmp"));
            //img = ImageIO.read(getClass().getResource("javatest/java/Images/TestImage.bmp"));

        } catch (IOException e) {
            System.out.println(e.toString());
        }
        int column = FindColor(img, ColorTarget.Green);
        System.out.println("Column Found:  " + column);
    }

    public static int FindColor (BufferedImage bm_img, ColorTarget colorTarget) {
        Color cur_color = new Color();
        int cur_color_int, rgb[] = new int[3];
        float hsv[] = new float[3];
        int hueMax = 0;

        int width = bm_img.getWidth(); // width in landscape mode
        int height = bm_img.getHeight(); // height in landscape mode
        int columnWidth = width / 5;

        int columnFound = -1;
        int columnMaxValue = 0;

        ByteBuffer pixelBuffer = convertImageData(bm_img);

//        for (int column = 0; column < 5; column++) {
         for (int column = 0; column < 1; column++) {
            int columnCounter = 0;

//            for (int i = 100; i < height; i += 3) {
             for (int i = 100; i < 101; i += 3) {
//                for (int j = column * columnWidth; j < (column + 1) * columnWidth; j++) {
                 for (int j = 403; j < 404; j++) {

                    //cur_color_int = pixelBuffer.get(j + (i * width))  ;
                     cur_color_int = pixelBuffer.get(13);
                     java.awt.Color color = new java.awt.Color(cur_color_int);
                    rgb[0] = color.getRed();
                     rgb[1] = color.getGreen();
                     rgb[2] = color.getBlue();
//                    rgb[1] = cur_color.green(cur_color_int);
//                    rgb[2] = cur_color.blue(cur_color_int);
                    System.out.println("rgb:  " + rgb[0] + " " + rgb[1] + " " + rgb[2]);

                    cur_color.RGBToHSV(rgb[0], rgb[1], rgb[2], hsv);

                    hueMax = Math.max((int) hsv[0], hueMax);

                    if (colorTarget == ColorTarget.Yellow) {
                        if ((hsv[0] > 30) && (hsv[0] < 50)) {
                            columnCounter++;
                        }
                    } else if (colorTarget == ColorTarget.White) {
                        if ((hsv[0] > 0) && (hsv[0] < 0) && (hsv[1] >= 0) && (hsv[1] < .25)) {
                            columnCounter++;
                        }
                    } else if (colorTarget == ColorTarget.Red) {
                        if ((hsv[0] > 0) && (hsv[0] < 0) && (hsv[1] > .75) && (hsv[1] <= 1)) {
                            columnCounter++;
                        }
                    } else if (colorTarget == ColorTarget.Blue) {
                        if ((hsv[0] > 220) && (hsv[0] < 250)) {
                            columnCounter++;
                        }
                    } else if (colorTarget == ColorTarget.Green) {
                        if ((hsv[0] > 100) && (hsv[0] < 120) && (hsv[1] > .75) && (hsv[1] <= 1)) {
                            columnCounter++;
                        }
                    }
                }
            }
            if (columnCounter > 10) {
                if (columnCounter > columnMaxValue) {
                    columnMaxValue = columnCounter;
                    columnFound = column;
                }
            }
        }
        return columnFound;
    }
}
