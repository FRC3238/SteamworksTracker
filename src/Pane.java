import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;

/**
 * Created by Julee on 2/19/2017.
 */
public class Pane extends JPanel {
    private Image field;
    String imageName = "SteamworksField.jpg";
    private static final int IMG_WIDTH = 1470;
    private static final int IMG_HEIGHT = 691;
    double conversion = 1/2.028;
    private static final int Conv_Factor = 4;
    private static final int Position_Space = 5;
    String command = "";
    int selectedEntity = -1;
    int currentX = 0, currentY = 0;
    BufferedImage initialImage, resizedImage;
    EntityCollection Manager;
    CommandDistributor cmd;
    public Pane() throws IOException {
        Manager = new EntityCollection();
        cmd = new CommandDistributor();
        init();
        setDoubleBuffered(true);
        addKeyListener(new KeyAdapter() {
            @Override public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER) {
                    Manager.sendCommand(command, selectedEntity);
                    command = "";

                }
                if(e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
                    if (command.length() > 0)
                        command = command.substring(0, command.length() - 1);
                }
                    else
                        if(e.getKeyCode() != KeyEvent.VK_SHIFT && e.getKeyCode() != KeyEvent.VK_ENTER)
                        command+=e.getKeyChar();
//                EntityCollection.wait = true;
                repaint();
            }
            @Override public void keyReleased(KeyEvent e) {}
            @Override public void keyTyped(KeyEvent e) {

            }
        });
        setFocusable(true);
        addMouseListener(new MouseAdapter()
        {
            @Override public void mousePressed(MouseEvent e) {}
            @Override public void mouseReleased(MouseEvent e){}
            @Override public void mouseClicked(MouseEvent e) {
                selectedEntity = Manager.checkEntity(e.getX(), e.getY());
                repaint();
            }
        });
        addMouseMotionListener(new MouseAdapter()
        {
            @Override public void mouseMoved(MouseEvent e) {
                currentX = e.getX();
                currentY = e.getY();
                repaint();
            }
            @Override public void mouseDragged(MouseEvent e) {}
            @Override public void mouseEntered(MouseEvent e){}
            @Override public void mouseExited(MouseEvent e) {}
        });
    }
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.drawImage(field, 0, 0, null);
        Manager.drawEntityLinearConnections(g, selectedEntity);
        Manager.drawCommand(selectedEntity, command, g);
        drawPosition(g);
    }

    public void init() throws IOException {
        loadImage();
        int width = field.getWidth(this);
        int height = field.getHeight(this);
        setPreferredSize(new Dimension(width, height));
    }
    public void loadImage() throws IOException {
          ImageIcon field = new ImageIcon(getClass().getResource(imageName));
        System.out.print(field.toString());
        this.field = field.getImage();
    }
    public void drawPosition(Graphics g) {
        double x = ToRobotXInches(currentY);
        double y = ToRobotYInches(currentX);
        int xft = (int) x/12;
        int yft = (int) y/12;
        x-=xft*12;
        y-=yft*12;
        DecimalFormat df = new DecimalFormat("#.#");
        String xin = df.format(x);

        String yin = df.format(y);
        g.drawString("("+xft+"'"+xin+"\""+", " +yft+"'"+yin+"\""+")",currentX-Position_Space,currentY-Position_Space);
    }
    public double ToRobotYInches(int displayX) {
        return (double)(displayX-Entity.OriginX)*conversion;
    }
    public double ToRobotXInches(int displayY) {
        return (double)(displayY-Entity.OriginY)*conversion;
    }

}
