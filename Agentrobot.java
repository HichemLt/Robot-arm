package RobtArm;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.TickerBehaviour;
import jade.lang.acl.ACLMessage;

import java.util.Random;

public class Agentrobot extends Agent {
    @Override
    public void setup() {
        addBehaviour(new TickerBehaviour(this,45000) {
                         @Override
                         protected void onTick() {
                             Random random=new Random();
                             int color = random.nextInt(4);
                             ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
                             msg.addReceiver(new AID("ArmRobotAgent",AID.ISLOCALNAME));
                             msg.setContent(String.valueOf(color));
                             System.out.println(msg.getContent());
                             send(msg);
                         }
                     }
                );
    }
}
