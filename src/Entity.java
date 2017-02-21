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
    double angle = 90.0;
    public static final double conversion = 1/2.028;
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
        int backLx, backLy, frontLx, frontLy, backRx, backRy, frontRx, frontRy;
        double rAngle = Math.toRadians(angle);
        double length = ProfilingConstants.kRobotLengthWithBumpers/2;
        double width = ProfilingConstants.kRobotWidthWithBumpers/2;
        double xCompWidth, yCompWidth, xCompLength, yCompLength;
        xCompWidth = (width*Math.cos(rAngle))/conversion;
        yCompWidth = (width*Math.sin(rAngle))/conversion;
        yCompLength = (length*Math.cos(rAngle))/conversion;
        xCompLength = (length*Math.sin(rAngle))/conversion;
        backLx = (int) (xCompWidth-xCompLength)+x;
        backLy = (int) (-yCompWidth-yCompLength)+y;

        frontLx = (int) (xCompWidth+xCompLength)+x;
        frontLy = (int) (yCompLength-yCompWidth)+y;

        backRx = (int) 2*x-backLx;
        backRy = (int) 2*y-backLy;

        frontRx = (int) 2*x-frontLx;
        frontRy = (int) 2*y-frontLy;
        g.setColor(Color.BLUE);
        g.drawLine(x, y,(int) (x+VectorLength*Math.sin(rAngle)),(int)(y+VectorLength*Math.cos(rAngle)));
               g.setColor(Color.DARK_GRAY);
               g.drawLine(backLx, backLy, frontLx, frontLy);
               g.drawLine(backLx, backLy, frontRx, frontRy);
        g.drawLine(frontLx, frontLy, backRx, backRy);

        g.drawLine(backRx, backRy, frontRx, frontRy);


        g.setColor(Color.BLACK);

    }
    public void changeParametersToCoordinates(int x, int y) {
        x = (int) fromRobotYInchesToPixels(y);
        y = (int) fromRobotXInchesToPixels(x);
    }
    public void changeParameters(int x, int y) {
//        x = (int)getYInches((double)x);
//        y = getYInches((double)y);
        if(x<OriginX+ProfilingConstants.kRobotLengthWithBumpers) x = (int)(OriginX+ProfilingConstants.kRobotLengthWithBumpers);
        if(x > maxX) x = maxX;
        this.x = x;
        if(y < minY + ProfilingConstants.kRobotLengthWithBumpers) y = (int)(minY+ProfilingConstants.kRobotLengthWithBumpers);
        if(y > maxY-ProfilingConstants.kRobotLengthWithBumpers) y = (int)(maxY-ProfilingConstants.kRobotLengthWithBumpers);
        this.y = y;

    }
    public double getXInches(double pixelY) {
        return (double)(pixelY-OriginY)*conversion;
    }
    public double getXInches() {return (double) (y-OriginY)*conversion;}
    public double getYInches() {return (double) (x-OriginX)*conversion;}
    public double getYInches(double pixelX) {
        return (double)(pixelX-OriginX)*conversion;
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
