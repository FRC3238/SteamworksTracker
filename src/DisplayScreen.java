import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Julee on 2/19/2017.
 */
public class DisplayScreen extends JFrame {
    JFrame Display;
    public static final String UIName = "Steamworks Motion Profiler", filePath="C:\\Users\\Garrett\\Documents\\MotionProfileOutput\\";
    static String name = "First Version";
    Pane Operator;
    ArrayList<Double> OperatorPass = new ArrayList<Double>();
    public DisplayScreen() throws IOException {

        BasicConfiguration();
    }

    public void updateName(String newName) {
        this.name = newName;
        setTitle(UIName+" - " + newName);
    }
    public static String getLeftName() {return filePath+name+"LEFT";}
    public static String getRightName() {return filePath+name+"RIGHT";}
    public void BasicConfiguration() throws IOException {
        Operator = new Pane();
        add(Operator);
        pack();
        setTitle(UIName+" - " + name);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1476,828);
        setVisible(true);
        setLocationRelativeTo(null);
        setResizable(false);
    }
    public void saveCoordinatesDirect() throws FileNotFoundException {
        File f = new File(filePath+name);
        PrintWriter Scribe = new PrintWriter(f );
        for(int i = 0; i < Operator.Manager.Entities.size(); i++) {
            Entity Current = Operator.Manager.Entities.get(i);
            System.out.println(Current.x);
            System.out.println(Current.getYInches(Current.x));
            Scribe.append(Current.getXInches(Current.y) + " " +
                        Current.getYInches(Current.x) + " " + Current.angle+ " ");
        }
        Scribe.close();
        System.out.println("Saved to: " + f.getAbsolutePath());
    }
    public ArrayList<Entity> openFile(String filename) throws FileNotFoundException {


    File f = new File(filePath + filename);
    System.out.println(f.exists());
    System.out.println(f.getAbsolutePath());

    Scanner Reader = new Scanner(f);
    if(OperatorPass.size() > 0)
    OperatorPass.clear();
    while (Reader.hasNextLine()) {

        while (Reader.hasNext()) {

            while (Reader.hasNextDouble()) {

                OperatorPass.add(Reader.nextDouble());
            }
            if(Reader.hasNext())
            Reader.next();
        }
        if(Reader.hasNextLine())
        Reader.nextLine();
    }

    System.out.println(OperatorPass.toString());
    ArrayList<Entity> PassFinal = new ArrayList<Entity>();
    for (int i = 0; i < (OperatorPass.size()); i += 3) {
        PassFinal.add(new Entity(fromRobotYInchesToPixels(OperatorPass.get(i + 1)), fromRobotXInchesToPixels(OperatorPass.get(i ))));
        PassFinal.get(i / 3).setAngle(OperatorPass.get(i + 2));

    }

        return PassFinal;
    }
    public int fromRobotXInchesToPixels(double inches) {
        return (int) ((inches)/Entity.conversion+Entity.OriginY);
    }
    public int fromRobotYInchesToPixels(double inches) {
        return (int) ((inches)/Entity.conversion+Entity.OriginX);
    }

}
