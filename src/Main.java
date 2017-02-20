

import javax.swing.JFrame;
import java.awt.*;
import java.io.IOException;


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
