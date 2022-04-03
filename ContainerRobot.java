package Agents;

import RobtArm.initComp;
import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.util.ExtendedProperties;
import jade.util.leap.Properties;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;

public class ContainerRobot {
    public static void main(String[] args) {


        try {
            Runtime runtime = Runtime.instance();
            String A="192.168.0.119";//the @ip of the machine which contains the main container
            String B="192.168.0.111";// the local @ ip of the machine
            ProfileImpl p=new ProfileImpl(A, 1099, null, false);
            p.setParameter(Profile.LOCAL_HOST, B);
            p.setParameter(Profile.LOCAL_PORT, "1099");
            AgentContainer agentContainer=runtime.createAgentContainer(p);
            ContainerRobot.start();

            AgentController robotAgent=agentContainer.createNewAgent("ArmRobotAgent",
                    "Agents.ArmRobotAgent",new Object[]{});

            robotAgent.start();

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void start() {

        initComp.initComponents();
    }
}
