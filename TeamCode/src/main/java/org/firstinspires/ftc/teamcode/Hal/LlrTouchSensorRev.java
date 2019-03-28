package org.firstinspires.ftc.teamcode.Hal;

import com.qualcomm.hardware.rev.RevTouchSensor;
import com.qualcomm.robotcore.hardware.DigitalChannelController;
import com.qualcomm.robotcore.hardware.HardwareDevice;

public class LlrTouchSensorRev extends RevTouchSensor {
    public LlrTouchSensorRev(DigitalChannelController digitalChannelController, int physicalPort) {
        super(digitalChannelController, physicalPort);
    }

    @Override
    public double getValue() {
        return super.getValue();
    }

    @Override
    public boolean isPressed() {
        return super.isPressed();
    }

    @Override
    public Manufacturer getManufacturer() {
        return super.getManufacturer();
    }

    @Override
    public String getDeviceName() {
        return super.getDeviceName();
    }

    @Override
    public String getConnectionInfo() {
        return super.getConnectionInfo();
    }

    @Override
    public int getVersion() {
        return super.getVersion();
    }

    @Override
    public void resetDeviceConfigurationForOpMode() {
        super.resetDeviceConfigurationForOpMode();
    }

    @Override
    public void close() {
        super.close();
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
