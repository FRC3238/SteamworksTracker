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
        for(int i = 0; i < Entities.size(); i++) {
            if(i == 0) {
                Entity Fencepost = Entities.get(0);
                if(0 == SelectedEntity) g.setColor(Color.RED);
                Fencepost.drawSelf(g);
                g.setColor(Color.BLACK);
                Fencepost.drawAngle(g);
                Fencepost.drawAngleVector(g);
                continue;
            }
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
        else
            (new Entity(600, 250)).drawCommand(command,g);
    }
    public void sendCommand(String command, int selected) {
System.out.println(command);
        try {
            if (command.toLowerCase().substring(0, "x".length()).equals("x")) {
                double x = Double.parseDouble(command.substring("x".length() + 1, command.length()));
                System.out.println(x);
                Entities.get(selected).setY(fromRobotXInchesToPixels(x));
            } else if (command.toLowerCase().substring(0, "y".length()).equals("y")) {

                double y = Double.parseDouble(command.substring("y".length() + 1, command.length()));
                System.out.println(y);
                Entities.get(selected).setX(fromRobotYInchesToPixels(y));
            }else if (command.toLowerCase().substring(0, "save".length()).equals("save")) {


            Main.UserInterface.saveCoordinatesDirect();
        }else if (command.toLowerCase().substring(0, "name".length()).equals("name")) {
                String name = (command.substring("name".length()+1, command.length()));

                Main.UserInterface.updateName(name);
            }else if (command.toLowerCase().substring(0, "open".length()).equals("open")) {
                String name = (command.substring("open".length() + 1, command.length()));
System.out.println(name);
clearEntities();
                Entities = Main.UserInterface.openFile(name);
            }
            else if (command.toLowerCase().substring(0, "clear".length()).equals("clear")) {
                clearEntities();

            }
             else if (command.toLowerCase().substring(0, "angle".length()).equals("angle")) {
                double ang = Double.parseDouble(command.substring("angle".length()+1, command.length()));
                Entities.get(selected).setAngle(ang);
            }
            else if (command.toLowerCase().substring(0, "remove".length()).equals("remove")) {
                Main.UserInterface.Operator.selectedEntity = -1;
                Entities.remove(selected);
            }
        }catch(Exception e) {
            System.out.println("Invalid! " + e.getMessage());
        }
    }
    public int fromRobotXInchesToPixels(double inches) {
        return (int) ((inches)/Entity.conversion+Entity.OriginY);
    }
    public int fromRobotYInchesToPixels(double inches) {
        return (int) ((inches)/Entity.conversion+Entity.OriginX);
    }
    public void clearEntities() {
            Main.UserInterface.Operator.selectedEntity = -1;
            Entities.clear();
    }
}
