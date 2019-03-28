/* Little Lost Robots
   Core Devs: Caden
*/

package org.firstinspires.ftc.teamcode.controllers;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.support.annotation.Nullable;
import java.nio.Buffer;
import java.nio.ByteBuffer;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.vuforia.Image;
import com.vuforia.PIXEL_FORMAT;
import com.vuforia.Vuforia;

import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.internal.vuforia.VuforiaLocalizerImpl;

        // This function finds color using a set of code.
public class ColorFinder {

    private VuforiaLocalizer vuforia;
    public enum ColorTarget{
        Yellow, Red, Blue, White, Green
    }
    HardwareMap hwMap;

    public void init(HardwareMap hwMap) {

        this.hwMap = hwMap;
        /*
         * To start up Vuforia, tell it the view that we wish to use for camera monitor (on the RC phone);
         */
        int cameraMonitorViewId = hwMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hwMap.appContext.getPackageName());
        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters(cameraMonitorViewId);

        // OR...  Do Not Activate the Camera Monitor View, to save power
        // VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters()
        parameters.vuforiaLicenseKey = "Adqx6DT/////AAABmZlkTzS5r0pXkD69c956JPRphYPsOI8mY5p+KC7CmdxkdZcT8LaXbgvfDIigrnO/PtTjO70OelYnZ8Ch085qgo0syqNC1QYXs35CcbtxvYxBC5givpm8vLxnxgo+3Fd+O4XUFiUS2EDBKxlANbBCXm8yEuXYXxWJtwlzY92ivgQkdqedHoz/uzSBTgK8rnGhgiklZqKTBU9mJTbCJ9uEXTXH+w5w3p6UQw9uMXnT+DMZQE6OGfYkL19zxaI/nAfkgUaFFcuKlQamQC+MceMshEFhqogJtGoeUhj7Nrv8+DcBhkNeju8u1WV6FlZAD6OyWbdgPsHjKlALNDhvecd95mWDa1lflssHFgbtPhPHUIMo";

        /*
         * We also indicate which camera on the RC that we wish to use.
         * Here we chose the back (HiRes) camera (for greater range), but
         * for a competition robot, the front camera might be more convenient.
         */
        parameters.cameraDirection = VuforiaLocalizer.CameraDirection.BACK;
        vuforia = new VuforiaLocalizerImpl(parameters);
        Vuforia.setFrameFormat(PIXEL_FORMAT.RGB565, true);
        vuforia.setFrameQueueCapacity(1);
    }

    /*
     * This function has an image and what it does is it skips a few inches below the height to avoid it focusing on other things like
     * people or other objects. It also converts the rgb to Hsv which is really useful to be specific when you scan an object.
     * As you can see below, I added a lot of colors.
     * https://www.rapidtables.com/convert/color/rgb-to-hsv.html - Great website for finding colors
     */
    @Nullable
    public Image getVuforiaImagefromFrame()  throws InterruptedException {
        VuforiaLocalizer.CloseableFrame frame = vuforia.getFrameQueue().take();
        int format = PIXEL_FORMAT.RGB565;
        long numImgs = frame.getNumImages();
        for (int i = 0; i <numImgs; i++) {
            if (frame.getImage(i).getFormat() == format) {
                return frame.getImage(i);
            }
        }
        return null;
    }

    public Bitmap getBitmapToAnalyze(Image img) throws InterruptedException {
        Bitmap bm_img = Bitmap.createBitmap(img.getWidth(), img.getHeight(), Bitmap.Config.RGB_565);
        bm_img.copyPixelsFromBuffer(img.getPixels());
        return bm_img;
    }

    public int FindColor (Bitmap bm_img, ColorTarget colorTarget) {
        Color cur_color = null;
        int cur_color_int, rgb[] = new int[3];
        float hsv[] = new float[3];
        int hueMax = 0;

        int width = bm_img.getWidth(); // width in landscape mode
        int height = bm_img.getHeight(); // height in landscape mode
        int columnWidth = width / 5;

        int columnFound = -1;
        int columnMaxValue = 0;

        //ByteBuffer pixelBuffer = ByteBuffer.allocate(bm_img.getHeight() * bm_img.getRowBytes());
        //bm_img.copyPixelsToBuffer(pixelBuffer);

        for (int column = 0; column < 5; column++) {
            int columnCounter = 0;

            for (int i = 50; i < height; i += 3) {
                for (int j = column * columnWidth; j < (column + 1) * columnWidth; j += 3) {
                    cur_color_int = bm_img.getPixel(j, i);

//                    cur_color_int = pixelBuffer.get(j + (i * width))  ;
                    rgb[0] = cur_color.red(cur_color_int);
                    rgb[1] = cur_color.green(cur_color_int);
                    rgb[2] = cur_color.blue(cur_color_int);

                    Color.RGBToHSV(rgb[0], rgb[1], rgb[2], hsv);

                    hueMax = Math.max((int) hsv[0], hueMax);

                    // Sites used for determining these values:
                    // http://colorizer.org/
                    if (hsv[2] > .15) {
                        if (colorTarget == ColorTarget.Yellow) {
                            if ((hsv[0] > 30) && (hsv[0] < 50) && (hsv[1] >= .75) && (hsv[1] <= 1) && (hsv[2] >= 0.8) ) {
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
            }
            if (columnCounter > 25) {
                if (columnCounter > columnMaxValue) {
                    columnMaxValue = columnCounter;
                    columnFound = column;
                }
            }
        }
        return columnFound;
    }
}