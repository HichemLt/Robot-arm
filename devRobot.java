package RobtArm;

public class devRobot {
    private static ev3dev.actuators.lego.motors.EV3LargeRegulatedMotor EV3LargeRegulatedMotor;
    private static ev3dev.actuators.lego.motors.EV3MediumRegulatedMotor EV3MediumRegulatedMotor;

    public static void goCanal1() {
        VerticalArm.turnright1(150);
    }
    public static int turn() {
       return VerticalArm.turn(20);
    }
    public static void stopverticalarrm() {
        VerticalArm.stop();
    }
    public static void turn2() {
        VerticalArm.turn2(50);
    }
    public static void goCanal2() {
        VerticalArm.turnright2(150);
    }

    public static void goCanal3() {
       VerticalArm.turnright3(150);
    }
    public static void goCanal4() {
        VerticalArm.turnright4(150);
    }

    public static void verleft3() {

        VerticalArm.turnleft3(50);
    }
    public static void verleft4() {

        VerticalArm.turnleft4(50);
    }
    public static void verright1() {
        VerticalArm.turnright1(150);
    }
    public static int turnToIntput() {
      return VerticalArm.resetPos(50);
    }
    public static void verright2() {

          VerticalArm.turnright2(250);

    }
    public static void verright3() {

        VerticalArm.turnright3(250);

    }
    public static void verrigh4t() {

         VerticalArm.turnright4(100);
    }
    public static void gripper() {
        GripperMotor.colosed(100);
    }
    public static int asent() {
     return    HorizontaleArm.goUp(20);
    }
    public static int descentForProduct() {
      return   HorizontaleArm.goDown(20);
    }
    public static void descentForIntput() {
        HorizontaleArm.goDown(50);
    }
    public static int opengripper() {
     return    GripperMotor.open(100);
    }
    public static void StopGripper() {
        GripperMotor.stopgripper();
    }
    public static void closedgripper() {
        GripperMotor.colosed(200);
    }
    public static void resetposverArm() {
        VerticalArm.resetPos(50);
    }
    public static void resetposorArm() {
       HorizontaleArm.resetPos(0);
    }
    public static int checkprodect() {
      return ChekProduct.decideColor();
    }
    public static boolean CheckBlue() {
        float colorSamples=0;
        int index = 0;
        float y =0;
        while (index < 3) {
            int idcolor = ChekProduct.check();
         //   System.out.println("clrID"+idcolor);
            colorSamples+=idcolor;
            index = index + 1;
            y=(colorSamples)/3;
            System.out.println(y);
        }
        if (Math.round(y)==3||Math.round(y)==2) {
            return true;
        }
        else return false;
    /*    if (ChekProduct.decideColor()==2||ChekProduct.decideColor()==3) {
            return true;
        }
        else return false;*/
    }
    public static boolean CheckGreen() {
       float colorSamples=0;
        int index = 0;
        float y =0;
        while (index < 3) {
            int idcolor = ChekProduct.check();
            System.out.println("clrID"+idcolor);
            colorSamples+=idcolor;
            index = index + 1;
            y=(colorSamples)/3;

        }

    if (Math.round(y)==3) {
            return true;
        }
        else return false;
 }
    public static boolean CheckRed() {
        float colorSamples=0;
        int index = 0;
        float y =0;
        while (index < 9) {
            int idcolor = ChekProduct.check();
            colorSamples+=idcolor;
            index = index + 1;
            y=(colorSamples)/10;
            //  System.out.println(y);
        }
        if (Math.round(y)==5) {
            return true;

        }
        return false;
    }
    public static boolean CheckYellow() {
        float colorSamples=0;
        int index = 0;
        float y =0;
        while (index < 1) {
            int idcolor = ChekProduct.check();
            colorSamples+=idcolor;
            index = index + 1;
            y=(colorSamples)/2;
          //  System.out.println(y);
        }
        if (Math.round(y)==4||Math.round(y)==5||Math.round(y)==7) {
            return true;

        }
        else return false;
      /*  if (ChekProduct.decideColor()==4||ChekProduct.decideColor()==7||ChekProduct.decideColor()==5) {
            return true;
        }
        else return false;*/
    }
    public static void stopHoriwentalarm() {
        HorizontaleArm.stop();
    }
    public static boolean checkplace() {
        return ChekProduct.place();
    }

    public static void init_positions() {
        VerticalArm.resetPos(100);
        HorizontaleArm.resetPos(100);
        GripperMotor.resetPos();
    }
}
