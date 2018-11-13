/* Little Lost Robots
   Core Devs: Caden
*/

/* Copyright (c) 2017 FIRST. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted (subject to the limitations in the disclaimer below) provided that
 * the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this list
 * of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice, this
 * list of conditions and the following disclaimer in the documentation and/or
 * other materials provided with the distribution.
 *
 * Neither the name of FIRST nor the names of its contributors may be used to endorse or
 * promote products derived from this software without specific prior written permission.
 *
 * NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
 * LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package org.firstinspires.ftc.teamcode;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.support.annotation.Nullable;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.vuforia.Image;
import com.vuforia.PIXEL_FORMAT;
import com.vuforia.Vuforia;

import org.firstinspires.ftc.robotcontroller.external.samples.ConceptVuforiaNavigation;
import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.matrices.OpenGLMatrix;
import org.firstinspires.ftc.robotcore.external.matrices.VectorF;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.robotcore.external.navigation.VuMarkInstanceId;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackableDefaultListener;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;
import org.firstinspires.ftc.robotcore.internal.vuforia.VuforiaLocalizerImpl;

//https://www.rapidtables.com/convert/color/rgb-to-hsv.html
//https://www.youtube.com/watch?v=wckaGJFxwlw

@TeleOp(name="Concept: VuMark Id", group ="Concept")
@Disabled
public class ConceptVuMarkIdentification extends LinearOpMode {

    public static final String TAG = "Vuforia VuMark Sample";

    private OpenGLMatrix lastLocation = null;
    private VuforiaLocalizer vuforia;
    enum ColorTarget{
        Yellow, Red, Blue, White, Green
    }

    @Override public void runOpMode() {

        /*
         * To start up Vuforia, tell it the view that we wish to use for camera monitor (on the RC phone);
         */
        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
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

        telemetry.addData(">", "Press Play to start");
        telemetry.update();
        waitForStart();
        while (opModeIsActive()) {

            try {
                Bitmap bm = getImage();
                int colorMum = FindColor(bm, ColorTarget.Yellow);
                telemetry.addData("Column: ", colorMum);
            } catch(InterruptedException ex){
                telemetry.addData("error", ex.getMessage());
            }

            telemetry.update();
        }
    }

    public Bitmap getImage() throws InterruptedException {
        Image img;
        // get current frame and transform it to Bitmap
        img = getImagefromFrame(vuforia.getFrameQueue().take(), PIXEL_FORMAT.RGB565);
        Bitmap bm_img = Bitmap.createBitmap(img.getWidth(), img.getHeight(), Bitmap.Config.RGB_565);
        bm_img.copyPixelsFromBuffer(img.getPixels());

        return bm_img;

    }

    @Nullable
    private Image getImagefromFrame(VuforiaLocalizer.CloseableFrame frame, int format) {
        long numImgs = frame.getNumImages();
        for (int i = 0; i <numImgs; i++) {
            if (frame.getImage(i).getFormat() == format) {
                return frame.getImage(i);
            }
        }

        return null;
    }
    /*
     * This function has an image and what it does is it skips a few inches below the height to avoid it focusing on other things like
     * people or other objects. It also converts the rgb to Hsv which is really useful to be specific when you scan an object.
     * As you can see below, I added a lot of colors down below.
     * https://www.rapidtables.com/convert/color/rgb-to-hsv.html - Great website for finding colors
     */

    public int FindColor(Bitmap bm_ing, ColorTarget colorTarget)  {
        Color cur_color = null;
        int cur_color_int, rgb[] = new int[3];
        float hsv[] = new float[3];
        int hueMax = 0;

        int width = bm_ing.getWidth(); // width in landscape mode
        int height = bm_ing.getHeight(); // height in landscape mode
        int columnWidth = width / 5;

        int columnFound = -1;
        int columnMaxValue = 0;

        for (int column = 0; column < 5; column++) {
            int columnCounter = 0;

            for (int i = 100; i < height; i += 3) {
                for (int j = column * columnWidth; j < (column + 1) * columnWidth; j++) {
                    cur_color_int = bm_ing.getPixel(j, i);
                    rgb[0] = cur_color.red(cur_color_int);
                    rgb[1] = cur_color.green(cur_color_int);
                    rgb[2] = cur_color.blue(cur_color_int);

                    Color.RGBToHSV(rgb[0], rgb[1], rgb[2], hsv);

                    hueMax = Math.max((int) hsv[0], hueMax);

                    if (colorTarget == ColorTarget.Yellow) {
                        if ((hsv[0] > 30) && (hsv[0] < 50)) {
                            columnCounter++;
                        }
                    }
                    else if (colorTarget == ColorTarget.White)
                    {
                        if ((hsv[0] > 0) && (hsv[0] < 0) && (hsv[1] >= 0) && (hsv[1] < .25 )) {
                            columnCounter++;
                        }
                    }
                    else if (colorTarget == ColorTarget.Red)
                    {
                        if ((hsv[0] > 0) && (hsv[0] < 0) && (hsv[1] > .75) && (hsv[1] <= 1)) {
                            columnCounter++;
                        }
                    }
                    else if (colorTarget == ColorTarget.Blue)
                    {
                        if ((hsv[0] > 220) && (hsv[0] < 250)) {
                            columnCounter++;
                        }
                    }
                    else if (colorTarget == ColorTarget.Green)
                    {
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

    private String format(OpenGLMatrix transformationMatrix) {
        return (transformationMatrix != null) ? transformationMatrix.formatAsTransform() : "null";
    }
}
