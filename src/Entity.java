import java.awt.*;

/**
 * Created by Julee on 2/19/2017.
 */
public class Entity {
    public int x, y;
    public static final int OriginX = 74, OriginY = 359,
    maxX = 1397, minY = 31, maxY = 688;
    int VectorLength = 14;
    int plausible_range = 14;
    double angle = 90.0, conversion = 1/2.028;
    int cDim = 9, commandSize = 8, commandHeight = 17, commandSpacing = 13, commandExtraSpacing = 3;
    public void drawConnection(Graphics2D g, Entity e) {
        g.drawLine(e.x, e.y, x, y);
    }
    public void setAngle(double angle) {
        this.angle = angle;
    }
    public void drawAngle(Graphics g) {
        g.drawString(""+angle, x-cDim/2, y-cDim);
    }
    public void drawSelf(Graphics g) {
        g.fillOval(x-cDim/2, y-cDim/2, cDim, cDim);
    }
    public Entity(int x, int y) {
        changeParameters(x, y);

    }
    public void drawAngleVector(Graphics g) {
        g.setColor(Color.BLUE);
        g.drawLine(x, y,(int) (x+VectorLength*Math.sin(Math.toRadians(angle))),(int)(y+VectorLength*Math.cos(Math.toRadians(angle))));
                g.setColor(Color.BLACK);
    }
    public void changeParametersToCoordinates(int x, int y) {
        x = (int) fromRobotYInchesToPixels(y);
        y = (int) fromRobotXInchesToPixels(x);
    }
    public void changeParameters(int x, int y) {
//        x = (int)getYInches((double)x);
//        y = getYInches((double)y);
        if(x<OriginX) x = OriginX;
        if(x > maxX) x = maxX;
        this.x = x;
        if(y < minY) y = minY;
        if(y > maxY) y = maxY;
        this.y = y;

    }
    public double getXInches(double pixelY) {
        return (double)(pixelY-OriginY)*conversion;
    }

    public double getYInches(double pixelX) {
        return (double)(x-pixelX)*conversion;
    }
    public int fromRobotXInchesToPixels(double inches) {
        return (int) ((inches)/conversion+OriginY);
    }
    public int fromRobotYInchesToPixels(double inches) {
        return (int) ((inches)/conversion+OriginX);
    }
    public boolean withinRange(int x, int y) {
        if(Math.abs(this.x-x) < plausible_range && Math.abs(this.y-y) < plausible_range) {
            return true;
        }
        return false;
    }
    public void drawCommand(String command, Graphics g) {
        if(command.length() > 0) {
            g.setColor(Color.BLACK);
            g.drawRect(x - cDim, y - cDim-commandSpacing-commandExtraSpacing, command.length() * commandSize, commandHeight);
            g.setColor(Color.BLUE);
            g.drawString(command, x-cDim, y-commandSpacing);

            g.setColor(Color.BLACK);
        }
    }
    public void setX(int x) {
        changeParameters(x, this.y);
    }
    public void setY(int y) {
        changeParameters(this.x, y);
    }
}
