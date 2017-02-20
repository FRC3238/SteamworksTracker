import javax.swing.*;
import java.awt.*;
import java.io.IOException;

/**
 * Created by Julee on 2/19/2017.
 */
public class DisplayScreen extends JFrame {
    JFrame Display;
    public static final String UIName = "Steamworks Motion Profiler";
    public DisplayScreen() throws IOException {

        BasicConfiguration();
    }
    public void BasicConfiguration() throws IOException {
        add(new Pane());
        pack();
        setTitle(UIName);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1476,728);
        setVisible(true);
        setLocationRelativeTo(null);
        setResizable(false);
    }

}
