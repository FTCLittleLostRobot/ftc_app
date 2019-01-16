package org.firstinspires.ftc.teamcode.Hal;

import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cGyro;
import com.qualcomm.robotcore.hardware.HardwareDevice;
import com.qualcomm.robotcore.hardware.I2cAddr;
import com.qualcomm.robotcore.hardware.I2cDeviceSynch;
import com.qualcomm.robotcore.hardware.usb.RobotArmingStateNotifier;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AngularVelocity;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Axis;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;

import java.util.Set;

public class LlrGyroRevI2c extends ModernRoboticsI2cGyro {
    public LlrGyroRevI2c(I2cDeviceSynch deviceClient) {
        super(deviceClient);
    }

    @Override
    protected void setOptimalReadWindow() {
        super.setOptimalReadWindow();
    }

    @Override
    protected void registerArmingStateCallback(boolean doInitialCallback) {
        super.registerArmingStateCallback(doInitialCallback);
    }

    @Override
    protected void engage() {
        super.engage();
    }

    @Override
    protected void disengage() {
        super.disengage();
    }

    @Override
    public I2cDeviceSynch getDeviceClient() {
        return super.getDeviceClient();
    }

    @Override
    public void onModuleStateChange(RobotArmingStateNotifier module, RobotArmingStateNotifier.ARMINGSTATE state) {
        super.onModuleStateChange(module, state);
    }

    @Override
    protected synchronized void initializeIfNecessary() {
        super.initializeIfNecessary();
    }

    @Override
    public synchronized boolean initialize() {
        return super.initialize();
    }

    @Override
    protected synchronized boolean doInitialize() {
        return super.doInitialize();
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
    public int getVersion() {
        return super.getVersion();
    }

    @Override
    public String getConnectionInfo() {
        return super.getConnectionInfo();
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
    public byte read8(Register reg) {
        return super.read8(reg);
    }

    @Override
    public void write8(Register reg, byte value) {
        super.write8(reg, value);
    }

    @Override
    public short readShort(Register reg) {
        return super.readShort(reg);
    }

    @Override
    public void writeShort(Register reg, short value) {
        super.writeShort(reg, value);
    }

    @Override
    public void writeCommand(Command command) {
        super.writeCommand(command);
    }

    @Override
    public Command readCommand() {
        return super.readCommand();
    }

    @Override
    public Set<Axis> getAngularVelocityAxes() {
        return super.getAngularVelocityAxes();
    }

    @Override
    public Set<Axis> getAngularOrientationAxes() {
        return super.getAngularOrientationAxes();
    }

    @Override
    public AngularVelocity getAngularVelocity(AngleUnit unit) {
        return super.getAngularVelocity(unit);
    }

    @Override
    public Orientation getAngularOrientation(AxesReference reference, AxesOrder order, AngleUnit angleUnit) {
        return super.getAngularOrientation(reference, order, angleUnit);
    }

    @Override
    public synchronized void setHeadingMode(HeadingMode headingMode) {
        super.setHeadingMode(headingMode);
    }

    @Override
    public HeadingMode getHeadingMode() {
        return super.getHeadingMode();
    }

    @Override
    public int rawX() {
        return super.rawX();
    }

    @Override
    public int rawY() {
        return super.rawY();
    }

    @Override
    public int rawZ() {
        return super.rawZ();
    }

    @Override
    public int getZAxisOffset() {
        return super.getZAxisOffset();
    }

    @Override
    public void setZAxisOffset(short offset) {
        super.setZAxisOffset(offset);
    }

    @Override
    public int getZAxisScalingCoefficient() {
        return super.getZAxisScalingCoefficient();
    }

    @Override
    public void setZAxisScalingCoefficient(int zAxisScalingCoefficient) {
        super.setZAxisScalingCoefficient(zAxisScalingCoefficient);
    }

    @Override
    public int getIntegratedZValue() {
        return super.getIntegratedZValue();
    }

    @Override
    public synchronized int getHeading() {
        return super.getHeading();
    }

    @Override
    protected int truncate(float angle) {
        return super.truncate(angle);
    }

    @Override
    protected float normalize0359(float degrees) {
        return super.normalize0359(degrees);
    }

    @Override
    protected float degreesZFromIntegratedZ(int integratedZ) {
        return super.degreesZFromIntegratedZ(integratedZ);
    }

    @Override
    public void resetZAxisIntegrator() {
        super.resetZAxisIntegrator();
    }

    @Override
    public String status() {
        return super.status();
    }

    @Override
    public void calibrate() {
        super.calibrate();
    }

    @Override
    public boolean isCalibrating() {
        return super.isCalibrating();
    }

    @Override
    public void setI2cAddress(I2cAddr newAddress) {
        super.setI2cAddress(newAddress);
    }

    @Override
    public I2cAddr getI2cAddress() {
        return super.getI2cAddress();
    }

    @Override
    public double getRotationFraction() {
        return super.getRotationFraction();
    }

    @Override
    public MeasurementMode getMeasurementMode() {
        return super.getMeasurementMode();
    }

    @Override
    protected void notSupported() {
        super.notSupported();
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
