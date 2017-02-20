import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Julee on 2/19/2017.
 */
public class EntityCollection {
    ArrayList<Entity> Entities = new ArrayList<Entity>();

    int thickness = 2;
    static boolean wait = false;
    public void addEntity(int x, int y) {
        Entities.add(new Entity(x, y));
    }
    public int checkEntity(int x, int y) {
        int entVal = -1;
        for(int i = 0; i < Entities.size(); i++) {
            if(Entities.get(i).withinRange(x, y)) entVal = i;
        }
        if(entVal < 0) {
            if(!wait) {
                entVal = Entities.size();
                addEntity(x, y);
            } else {
                wait = false;
                entVal = -1;
            }
        }
        return entVal;


    }
    public boolean getWaiting() {
        return wait;
    }
    public void drawEntityLinearConnections(Graphics g, int SelectedEntity) {
        g.setColor(Color.BLACK);
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(thickness));
        for(int i = 1; i < Entities.size(); i++) {
            Entity Current = Entities.get(i), Past = Entities.get(i-1);
            if(i == SelectedEntity)
                g.setColor(Color.RED);
            Current.drawSelf(g);
            g.setColor(Color.BLACK);
            Current.drawAngle(g);
            Current.drawConnection(g2, Past);
            Current.drawAngleVector(g);
        }
    }
    public void drawCommand(int entity, String command, Graphics g) {
        if(entity > -1)
            Entities.get(entity).drawCommand(command, g);

    }
    public void sendCommand(String command, int selected) {

        try {
            if (command.toLowerCase().substring(0, "x".length()).equals("x")) {
                int x = Integer.parseInt(command.substring("x".length() + 1, command.length()));
                System.out.println(x);
                Entities.get(selected).setX(x);
            } else if (command.toLowerCase().substring(0, "y".length()).equals("y")) {

                int y = Integer.parseInt(command.substring("y".length() + 1, command.length()));
                System.out.println(y);
                Entities.get(selected).setY(y);
            }
            else if (command.toLowerCase().substring(0, "clear".length()).equals("clear")) {
                Entities.clear();

            }
             else if (command.toLowerCase().substring(0, "angle".length()).equals("angle")) {
                double ang = Double.parseDouble(command.substring("angle".length()+1, command.length()));
                Entities.get(selected).setAngle(ang);
            }
        }catch(Exception e) {
            System.out.println("Invalid! " + e.getMessage());
        }
    }
}
