package RobtArm;

import lejos.utility.Delay;

public class GripperMotor {
   // static final EV3MediumRegulatedMotor motor3 = new EV3MediumRegulatedMotor(MotorPort.C);
public static void colosed(int speed) {
    initComp.motor3.setSpeed(speed);
    Delay.msDelay(1);
    initComp.motor3.forward();
    Delay.msDelay(500);
    initComp.motor3.stop();
    System.out.println(initComp.motor3.getPosition());
}
    public static int open(int speed) {
        initComp.motor3.setSpeed(speed);
        int position = (int) initComp.motor3.getPosition();
        Delay.msDelay(1);
        initComp.motor3.backward();
   return position; }
    public static void resetPos() {
        Delay.msDelay(1);
        initComp.motor3.rotateTo(0);
    }
    public static void stopgripper() {
        initComp.motor3.stop();
    }
}
