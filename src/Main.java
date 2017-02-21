

import javax.swing.JFrame;
import java.awt.*;
import java.io.IOException;

/*
*
*
*
* Copy this to robot.java
*public double[][] getProfile(String st) {
    Scanner s = new Scanner(new File(st));
    int modder = 0, length = s.nextInt();
    double[][] returnArray = new double[length][3];
    while(s.hasNextDouble()) {
            returnArray[modder][modder%3] = s.nextDouble();
            modder++;
        }
        return returnArray;
        }
*
* */

public class Main extends JFrame
{
    static DisplayScreen UserInterface;
    public Main() throws IOException {
        UserInterface = new DisplayScreen();

    }

    public static void main(String[] args) throws java.lang.InterruptedException
    {

        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    UserInterface = new DisplayScreen();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                UserInterface.setVisible(true);
            }
        });
    }

}
