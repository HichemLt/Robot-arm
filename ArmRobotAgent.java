package Agents;

import RobtArm.devRobot;
import RobtArm.initComp;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.OneShotBehaviour;
import jade.core.behaviours.SequentialBehaviour;
import jade.lang.acl.ACLMessage;
import lejos.robotics.Color;
import lejos.utility.Delay;

import static RobtArm.initComp.motor2;

public class  ArmRobotAgent extends Agent {
    @Override
    public void setup() {
        /**********
         Recieve tasks
         ************/

        addBehaviour(new CyclicBehaviour() {
            @Override
            public void action() {
                ACLMessage aclMessage = receive();
                if (aclMessage != null) {
                    int number = Integer.parseInt(aclMessage.getContent());
                    /**********
                     Blue color sequence
                     ************/
                                    if (number == 0) {

                        SequentialBehaviour comportementSequentiel = new SequentialBehaviour();
                        addBehaviour(comportementSequentiel);
                                        comportementSequentiel.addSubBehaviour(new Behaviour() {
                                            @Override
                                            public void action() {                       /************  Go to canal 1 ***********/
                                                try {
                                                    devRobot.turn();
                                                    //    devRobot.CheckRed();
                                                } catch (Exception e) {
                                                    e.printStackTrace();
                                                }
                                            }
                                            @Override
                                            public boolean done() {
                                                if (devRobot.turn()==50)  {

                                                    devRobot.stopverticalarrm();
                                                    System.out.println("Step 1 : Go to canal ");
                                                    return true;}
                                                return false;
                                            }
                                        });
                        comportementSequentiel.addSubBehaviour(new Behaviour() {
                            @Override
                            public void action() {                      /************  Descent for product ***********/

                                try {
                                    devRobot.descentForProduct();

                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                            @Override
                            public boolean done() {
                                if (devRobot.descentForProduct() == 60  ) {

                                    devRobot.stopHoriwentalarm();

                                    System.out.println(" Step 2 : Descent for product");
                                    return true;
                                }
                                return false;
                            }
                        });
                        comportementSequentiel.addSubBehaviour(new Behaviour() {
                            @Override
                            public void action() {               /*************** Go up **********/
                                try {
                                    devRobot.asent();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }

                            @Override
                            public boolean done() {
                                if (devRobot.asent()==40) {
                                    devRobot.stopHoriwentalarm();
                                    System.out.println("Step 5 : Go up ");
                                    return true;     }
                                return false;
                            }
                        });
                        /**************************/
                        comportementSequentiel.addSubBehaviour(new Behaviour() {
                            @Override
                            public void action() {                       /************  Go to canal 1 ***********/
                                try {
                                    devRobot.turn();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                            @Override
                            public boolean done() {
                              if (devRobot.CheckYellow())  {
                                  devRobot.stopverticalarrm();
                                  System.out.println("Step 1 : Go to canal ");
                                  return true;}
                              return false;
                            }
                        });

                        comportementSequentiel.addSubBehaviour(new Behaviour() {
                            @Override
                            public void action() {                      /************  Descent for product ***********/

                                try {
                                    devRobot.descentForProduct();

                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }

                            @Override
                            public boolean done() {
                                if (devRobot.descentForProduct()==100) {
                                    devRobot.stopHoriwentalarm();
                                    System.out.println(" Step 2 : Descent for product");
                                    return true;
                                }
                                return false;
                            }
                        });
                        comportementSequentiel.addSubBehaviour(new OneShotBehaviour() {
                            @Override
                            public void action() {
                                try {
                                   Delay.msDelay(1000);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                        comportementSequentiel.addSubBehaviour(new OneShotBehaviour() {
                                        @Override
                                        public void action() {
                                            try {
                                                devRobot.closedgripper();
                                            } catch (Exception e) {
                                                e.printStackTrace();
                                            }
                                        }
                                    });



                        comportementSequentiel.addSubBehaviour(new Behaviour() {
                                            @Override
                                            public void action() {               /*************** Go up **********/
                                                try {
                                                    devRobot.asent();
                                                } catch (Exception e) {
                                                    e.printStackTrace();
                                                }
                                            }

                            @Override
                            public boolean done() {
                                                if (devRobot.asent()==0) {
                                                    devRobot.stopHoriwentalarm();
                                                    System.out.println("Step 5 : Go up ");
                                           return true;     }
                                return false;
                            }
                        });
                        comportementSequentiel.addSubBehaviour(new Behaviour() {
                                            @Override
                                            public void action() {                /***************Turn to Input PL **********/
                                                try {
                                                    devRobot.turnToIntput();

                                                } catch (Exception e) {
                                                    e.printStackTrace();
                                                }
                                            }

                            @Override
                            public boolean done() {
                                                if (devRobot.turnToIntput()==0) {
                                                    devRobot.stopverticalarrm();
                                                    System.out.println("Step 6 : Turn to Input prod");
                                                return true;}
                            else    return false;
                            }
                        });

                        comportementSequentiel.addSubBehaviour(new Behaviour() {
                            @Override
                            public void action() {

                                try {
                                    devRobot.descentForProduct();

                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }

                            @Override
                            public boolean done() {
                                if (devRobot.descentForProduct()==80) {
                                    devRobot.stopHoriwentalarm();
                                    System.out.println(" Step 7 : Descent for Intput ");
                                    return true;
                                }
                                return false;
                            }
                        });
                        comportementSequentiel.addSubBehaviour(new OneShotBehaviour() {
                            @Override
                            public void action() {
                        Delay.msDelay(500);
                             }
                        });
                        comportementSequentiel.addSubBehaviour(new Behaviour() {
                            @Override
                            public void action() {               /*************** open gripper **********/
                                try {
                                    devRobot.opengripper();

                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }

                            @Override
                            public boolean done() {
                                if (devRobot.opengripper()==0){
                                    System.out.println("Job done ");
                          devRobot.StopGripper();
                                    return true;
                                }
                                return false;
                            }
                        });
                        comportementSequentiel.addSubBehaviour(new OneShotBehaviour() {
                            @Override
                            public void action() {
                                SendMessageToDrop();
                            }
                        });
                        comportementSequentiel.addSubBehaviour(new Behaviour() {
                            @Override
                            public void action() {
                                try {
                                    devRobot.asent();


                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }

                            @Override
                            public boolean done() {
                                if (devRobot.asent()==0) {
                                    devRobot.stopHoriwentalarm();

                                    System.out.println("Step : reset positions ");
                             return true;   }
                                return false;
                            }
                        });


                                    }

                    /**********
                     Green color sequence
                     ************/


                    if (number == 1) {
                        SequentialBehaviour comportementSequentiel = new SequentialBehaviour();
                        addBehaviour(comportementSequentiel);
                        comportementSequentiel.addSubBehaviour(new Behaviour() {
                            @Override
                            public void action() {                       /************  Go to canal 1 ***********/
                                try {
                                    devRobot.turn();
                                    //    devRobot.CheckRed();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                            @Override
                            public boolean done() {
                                if (devRobot.turn()==130)  {

                                    devRobot.stopverticalarrm();
                                    System.out.println("Step 1 : Go to canal ");
                                    return true;}
                                return false;
                            }
                        });
                        comportementSequentiel.addSubBehaviour(new Behaviour() {
                            @Override
                            public void action() {                      /************  Descent for product ***********/

                                try {
                                    devRobot.descentForProduct();

                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }

                            @Override
                            public boolean done() {
                                if (devRobot.descentForProduct()==50) {
                                    devRobot.stopHoriwentalarm();
                                    System.out.println(" Step 2 : Descent for product");
                                    return true;
                                }
                                return false;
                            }
                        });
                        comportementSequentiel.addSubBehaviour(new Behaviour() {
                            @Override
                            public void action() {               /*************** Go up **********/
                                try {
                                    devRobot.asent();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }

                            @Override
                            public boolean done() {
                                if (devRobot.asent()==35) {
                                    devRobot.stopHoriwentalarm();
                                    System.out.println("Step 5 : Go up ");
                                    return true;     }
                                return false;
                            }
                        });
                        /**************************/
                        comportementSequentiel.addSubBehaviour(new Behaviour() {
                            @Override
                            public void action() {                       /************  Go to canal 1 ***********/
                                try {
                                    devRobot.turn();
                                    devRobot.CheckRed();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                            @Override
                            public boolean done() {
                                if (devRobot.CheckRed())  {
//Delay.msDelay(50);
                                    devRobot.stopverticalarrm();
                                    System.out.println("Step 1 : Go to canal ");
                                    return true;}
                                return false;
                            }
                        });

                        comportementSequentiel.addSubBehaviour(new Behaviour() {
                            @Override
                            public void action() {                      /************  Descent for product ***********/

                                try {
                                    devRobot.descentForProduct();

                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }

                            @Override
                            public boolean done() {
                                if (devRobot.descentForProduct()==100) {
                                    devRobot.stopHoriwentalarm();
                                    System.out.println(" Step 2 : Descent for product");
                                    return true;
                                }
                                return false;
                            }
                        });
                        comportementSequentiel.addSubBehaviour(new OneShotBehaviour() {
                            @Override
                            public void action() {
                                try {
                                    Delay.msDelay(1000);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                        comportementSequentiel.addSubBehaviour(new OneShotBehaviour() {
                            @Override
                            public void action() {           /***************Close gripper **********/
                                try {
                                    devRobot.closedgripper();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        });



                        comportementSequentiel.addSubBehaviour(new Behaviour() {
                            @Override
                            public void action() {               /*************** Go up **********/
                                try {
                                    devRobot.asent();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }

                            @Override
                            public boolean done() {
                                if (devRobot.asent()==0) {
                                    devRobot.stopHoriwentalarm();
                                    System.out.println("Step 5 : Go up ");
                                    return true;     }
                                return false;
                            }
                        });
                        comportementSequentiel.addSubBehaviour(new Behaviour() {
                            @Override
                            public void action() {                /***************Turn to Input PL **********/
                                try {
                                    devRobot.turnToIntput();

                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }

                            @Override
                            public boolean done() {
                                if (devRobot.turnToIntput()==0) {
                                    devRobot.stopverticalarrm();
                                    System.out.println("Step 6 : Turn to Input prod");
                                    return true;}
                                else    return false;
                            }
                        });

                        comportementSequentiel.addSubBehaviour(new Behaviour() {
                            @Override
                            public void action() {

                                try {
                                    devRobot.descentForProduct();

                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }

                            @Override
                            public boolean done() {
                                if (devRobot.descentForProduct()==80) {
                                    devRobot.stopHoriwentalarm();
                                    System.out.println(" Step 7 : Descent for Intput ");
                                    return true;
                                }
                                return false;
                            }
                        });
                        comportementSequentiel.addSubBehaviour(new OneShotBehaviour() {
                            @Override
                            public void action() {
                                Delay.msDelay(500);
                            }
                        });

                        comportementSequentiel.addSubBehaviour(new Behaviour() {
                            @Override
                            public void action() {               /*************** open gripper **********/
                                try {
                                    devRobot.opengripper();

                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }

                            @Override
                            public boolean done() {
                                if (devRobot.opengripper()==0){
                                    System.out.println("Job done ");
                                    devRobot.StopGripper();
                                    return true;
                                }
                                return false;
                            }
                        });
                        comportementSequentiel.addSubBehaviour(new OneShotBehaviour() {
                            @Override
                            public void action() {
                                SendMessageToDrop();
                            }
                        });
                        comportementSequentiel.addSubBehaviour(new Behaviour() {
                            @Override
                            public void action() {
                                try {
                                    devRobot.asent();


                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }

                            @Override
                            public boolean done() {
                                if (devRobot.asent()==0) {
                                    devRobot.stopHoriwentalarm();

                                    System.out.println("Step : reset positions ");
                                    return true;   }
                                return false;
                            }
                        });
                    }

                    /**********
                     Red color sequence
                     ************/
                    if (number == 2) {
                        SequentialBehaviour comportementSequentiel = new SequentialBehaviour();
                        addBehaviour(comportementSequentiel);
                        comportementSequentiel.addSubBehaviour(new Behaviour() {
                            @Override
                            public void action() {                       /************  Go to canal 1 ***********/
                                try {
                                    devRobot.turn();
                                //    devRobot.CheckRed();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                            @Override
                            public boolean done() {
                                if (devRobot.turn()==175)  {

                                    devRobot.stopverticalarrm();
                                    System.out.println("Step 1 : Go to canal ");
                                    return true;}
                                return false;
                            }
                        });
                        comportementSequentiel.addSubBehaviour(new Behaviour() {
                            @Override
                            public void action() {                      /************  Descent for product ***********/

                                try {
                                    devRobot.descentForProduct();

                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }

                            @Override
                            public boolean done() {
                                if (devRobot.descentForProduct()==40) {
                                //    Delay.msDelay(50);
                                    devRobot.stopHoriwentalarm();
                                    System.out.println(" Step 2 : Descent for product");
                                    return true;
                                }
                                return false;
                            }
                        });
                        comportementSequentiel.addSubBehaviour(new Behaviour() {
                            @Override
                            public void action() {               /*************** Go up **********/
                                try {
                                    devRobot.asent();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }

                            @Override
                            public boolean done() {
                                if (devRobot.asent()==27) {
                                    devRobot.stopHoriwentalarm();
                                    System.out.println("Step 5 : Go up ");
                                    return true;     }
                                return false;
                            }
                        });
                        /**************************/
                        comportementSequentiel.addSubBehaviour(new Behaviour() {
                            @Override
                            public void action() {                       /************  Go to canal 1 ***********/
                                try {
                                    devRobot.turn();
                                    devRobot.CheckBlue();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                            @Override
                            public boolean done() {
                                if (devRobot.CheckBlue() )  {
                                    Delay.msDelay(90);
                                    devRobot.stopverticalarrm();
                                    System.out.println("Step 1 : Go to canal ");
                                    return true;}
                                return false;
                            }
                        });

                        comportementSequentiel.addSubBehaviour(new Behaviour() {
                            @Override
                            public void action() {                      /************  Descent for product ***********/

                                try {
                                    devRobot.descentForProduct();

                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }

                            @Override
                            public boolean done() {
                                if (devRobot.descentForProduct()==100) {
                                    devRobot.stopHoriwentalarm();
                                    System.out.println(" Step 2 : Descent for product");
                                    return true;
                                }
                                return false;
                            }
                        });
                        comportementSequentiel.addSubBehaviour(new OneShotBehaviour() {
                            @Override
                            public void action() {
                                try {
                                    Delay.msDelay(1000);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                        comportementSequentiel.addSubBehaviour(new OneShotBehaviour() {
                            @Override
                            public void action() {           /***************Close gripper **********/
                                try {
                                    devRobot.closedgripper();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        });



                        comportementSequentiel.addSubBehaviour(new Behaviour() {
                            @Override
                            public void action() {               /*************** Go up **********/
                                try {
                                    devRobot.asent();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }

                            @Override
                            public boolean done() {
                                if (devRobot.asent()==0) {
                                    devRobot.stopHoriwentalarm();
                                    System.out.println("Step 5 : Go up ");
                                    return true;     }
                                return false;
                            }
                        });
                        comportementSequentiel.addSubBehaviour(new Behaviour() {
                            @Override
                            public void action() {                /***************Turn to Input PL **********/
                                try {
                                    devRobot.turnToIntput();

                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }

                            @Override
                            public boolean done() {
                                if (devRobot.turnToIntput()==0) {
                                    devRobot.stopverticalarrm();
                                    System.out.println("Step 6 : Turn to Input prod");
                                    return true;}
                                else    return false;
                            }
                        });

                        comportementSequentiel.addSubBehaviour(new Behaviour() {
                            @Override
                            public void action() {

                                try {
                                    devRobot.descentForProduct();

                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }

                            @Override
                            public boolean done() {
                                if (devRobot.descentForProduct()==80) {
                                    devRobot.stopHoriwentalarm();
                                    System.out.println(" Step 7 : Descent for Intput ");
                                    return true;
                                }
                                return false;
                            }
                        });
                        comportementSequentiel.addSubBehaviour(new OneShotBehaviour() {
                            @Override
                            public void action() {
                                Delay.msDelay(500);
                            }
                        });

                        comportementSequentiel.addSubBehaviour(new Behaviour() {
                            @Override
                            public void action() {               /*************** open gripper **********/
                                try {
                                    devRobot.opengripper();

                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }

                            @Override
                            public boolean done() {
                                if (devRobot.opengripper()==0){
                                    System.out.println("Job done ");
                                    devRobot.StopGripper();
                                    return true;
                                }
                                return false;
                            }
                        });
                        comportementSequentiel.addSubBehaviour(new OneShotBehaviour() {
                            @Override
                            public void action() {
                                SendMessageToDrop();
                            }
                        });
                        comportementSequentiel.addSubBehaviour(new Behaviour() {
                            @Override
                            public void action() {
                                try {
                                    devRobot.asent();


                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }

                            @Override
                            public boolean done() {
                                if (devRobot.asent()==0) {
                                    devRobot.stopHoriwentalarm();

                                    System.out.println("Step : reset positions ");
                                    return true;   }
                                return false;
                            }
                        });

                            }


                    /**********
                     Yellow color sequence
                     ************/
                    if (number == 3) {
                        SequentialBehaviour comportementSequentiel = new SequentialBehaviour();
                        addBehaviour(comportementSequentiel);
                        comportementSequentiel.addSubBehaviour(new Behaviour() {
                            @Override
                            public void action() {                       /************  Go to canal 1 ***********/
                                try {
                                    devRobot.turn();
                                    //    devRobot.CheckRed();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                            @Override
                            public boolean done() {
                                if (devRobot.turn()==220)  {

                                    devRobot.stopverticalarrm();
                                    System.out.println("Step 1 : Go to canal ");
                                    return true;}
                                return false;
                            }
                        });
                        comportementSequentiel.addSubBehaviour(new Behaviour() {
                            @Override
                            public void action() {                      /************  Descent for product ***********/

                                try {
                                    devRobot.descentForProduct();

                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }

                            @Override
                            public boolean done() {
                                if (devRobot.descentForProduct()==50) {
                                    devRobot.stopHoriwentalarm();
                                    System.out.println(" Step 2 : Descent for product");
                                    return true;
                                }
                                return false;
                            }
                        });
                        comportementSequentiel.addSubBehaviour(new Behaviour() {
                            @Override
                            public void action() {               /*************** Go up **********/
                                try {
                                    devRobot.asent();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }

                            @Override
                            public boolean done() {
                                if (devRobot.asent()==45) {
                                    devRobot.stopHoriwentalarm();
                                    System.out.println("Step 5 : Go up ");
                                    return true;     }
                                return false;
                            }
                        });
                        /**************************/
                        comportementSequentiel.addSubBehaviour(new Behaviour() {
                            @Override
                            public void action() {                       /************  Go to canal 1 ***********/
                                try {
                                    devRobot.turn();
                                    devRobot.CheckGreen();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                            @Override
                            public boolean done() {
                                if (devRobot.CheckGreen() )  {
                                    devRobot.stopverticalarrm();
                                    System.out.println("Step 1 : Go to canal ");
                                    return true;
                                }
                                return false;
                            }
                        });

                        comportementSequentiel.addSubBehaviour(new Behaviour() {
                            @Override
                            public void action() {                      /************  Descent for product ***********/

                                try {
                                    devRobot.descentForProduct();

                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }

                            @Override
                            public boolean done() {
                                if (devRobot.descentForProduct()==105) {
                                    devRobot.stopHoriwentalarm();
                                    System.out.println(" Step 2 : Descent for product");
                                    return true;
                                }
                                return false;
                            }
                        });
                        comportementSequentiel.addSubBehaviour(new OneShotBehaviour() {
                            @Override
                            public void action() {
                                try {
                                    Delay.msDelay(1000);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                        comportementSequentiel.addSubBehaviour(new OneShotBehaviour() {
                            @Override
                            public void action() {           /***************Close gripper **********/
                                try {
                                    devRobot.closedgripper();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        });



                        comportementSequentiel.addSubBehaviour(new Behaviour() {
                            @Override
                            public void action() {               /*************** Go up **********/
                                try {
                                    devRobot.asent();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }

                            @Override
                            public boolean done() {
                                if (devRobot.asent()==0) {
                                    devRobot.stopHoriwentalarm();
                                    System.out.println("Step 5 : Go up ");
                                    return true;     }
                                return false;
                            }
                        });
                        comportementSequentiel.addSubBehaviour(new Behaviour() {
                            @Override
                            public void action() {                /***************Turn to Input PL **********/
                                try {
                                    devRobot.turnToIntput();

                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }

                            @Override
                            public boolean done() {
                                if (devRobot.turnToIntput()==0) {
                                    devRobot.stopverticalarrm();
                                    System.out.println("Step 6 : Turn to Input prod");
                                    return true;}
                                else    return false;
                            }
                        });
                        comportementSequentiel.addSubBehaviour(new Behaviour() {
                            @Override
                            public void action() {

                                try {
                                    devRobot.descentForProduct();

                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }

                            @Override
                            public boolean done() {
                                if (devRobot.descentForProduct()==80) {
                                    devRobot.stopHoriwentalarm();
                                    System.out.println(" Step 7 : Descent for Intput ");
                                    return true;
                                }
                                return false;
                            }
                        });
                        comportementSequentiel.addSubBehaviour(new OneShotBehaviour() {
                            @Override
                            public void action() {
                                Delay.msDelay(500);
                            }
                        });

                        comportementSequentiel.addSubBehaviour(new Behaviour() {
                            @Override
                            public void action() {               /*************** open gripper **********/
                                try {
                                    devRobot.opengripper();

                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }

                            @Override
                            public boolean done() {
                                if (devRobot.opengripper()==0){
                                    System.out.println("Job done ");
                                    devRobot.StopGripper();
                                    return true;
                                }
                                return false;
                            }
                        });
                        comportementSequentiel.addSubBehaviour(new OneShotBehaviour() {
                            @Override
                            public void action() {
                                SendMessageToDrop();
                            }
                        });
                        comportementSequentiel.addSubBehaviour(new Behaviour() {
                            @Override
                            public void action() {
                                try {
                                    devRobot.asent();


                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }

                            @Override
                            public boolean done() {
                                if (devRobot.asent()==0) {
                                    devRobot.stopHoriwentalarm();
                                    System.out.println("Step : reset positions ");
                                    return true;   }
                                return false;
                            }
                        });

                    }
                    /**   else {
                     System.out.println("No product in the canal");
                     }*/

                }  //end of message content
            }   // end action Cyclic Behaviour
        });

    }
    public void SendMessageToDrop() {
        ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
        msg.addReceiver(new AID("DropAgent",AID.ISLOCALNAME));
        msg.setContent("Check");
        System.out.println(msg.getContent());
        send(msg);
    }
}

/************************************************/
