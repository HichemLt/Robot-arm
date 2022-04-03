package RobtArm;




import lejos.utility.Delay;

public class HorizontaleArm {

    public static void resetPos(int speed) {
        Delay.msDelay(1);
        initComp.motor2.rotateTo(0);
    }
public static void stop(){
       initComp.motor2.stop();
    }
    public static int goDown(int speed) {
        int getpos = (int) initComp.motor2.getPosition();
        initComp.motor2.setSpeed(speed);
        Delay.msDelay(1);
        initComp.motor2.forward();
   return getpos;
    }
    public static int goUp(int speed) {
        int getposition = (int) initComp.motor2.getPosition();
        initComp.motor2.setSpeed(speed);
        Delay.msDelay(1);
        initComp.motor2.backward();
  return getposition;
    }


}