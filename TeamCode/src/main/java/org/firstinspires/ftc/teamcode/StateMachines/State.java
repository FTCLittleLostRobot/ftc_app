package org.firstinspires.ftc.teamcode.StateMachines;

public class State {
    public enum RobotState
    {
        Drop,
        WaitForDrop,
        Unhook,
        WaitForUnhook,
        Up,
        WaitForUp,
        StepOut,
        WaitForStepOut,
        StrafingLeft,
        WaitForStrafeLeft,
        CheckScreen,
        ConvertImageFromScreen,
        DetectColorFromImage,
        CheckForGold,
        WaitForScoot,
        PushBloock,
        Done,

    }
}
