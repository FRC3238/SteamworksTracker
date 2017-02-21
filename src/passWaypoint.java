
import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.Trajectory;
import jaci.pathfinder.Waypoint;
import jaci.pathfinder.modifiers.TankModifier;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

/**
 * Created by Garrett on 2/21/2017.
 */
public class passWaypoint {
    public static Waypoint[] waypoints;
    public static Trajectory.Config config;
    public static Trajectory trajectory;
    public static void setWaypoint(Waypoint[] WaypointsS) {
        waypoints = WaypointsS;
    }
    private static void genTrajectory() {
        if(waypoints.length < 0) throw new IndexOutOfBoundsException("No Indeces");
        trajectory = Pathfinder.generate(waypoints, config);

    }
    public static void setConfig(Trajectory.Config traj) {
        config = traj;
    }
    public static void WriteToNative(File Left, File Right) throws FileNotFoundException {
        genTrajectory();
        PrintWriter pw = new PrintWriter(Left),
        pwr = new PrintWriter(Right);
        TankModifier mod = new TankModifier(trajectory);
        mod.modify(ProfilingConstants.kWheelbaseWidth);
        Trajectory lTraj, rTraj;
        lTraj = mod.getLeftTrajectory();
        rTraj = mod.getRightTrajectory();
        pw.append("package org.usfirst.frc.team3238.robot.Autonomous.Profile;\npublic static class " + Left.getName() + " {\npublic static double[][] Points = new double[][]{");
        for(int i = 0; i < lTraj.length(); i++) {
            pw.append("{"+(lTraj.segments[i].position / (Math.PI * ProfilingConstants.kWheelDiameter)) + ", " +
                            (lTraj.segments[i].velocity * 60 / (Math.PI * ProfilingConstants.kWheelDiameter)) + ", " +
                    (lTraj.segments[i].dt * 1000)+ "} ");
            pwr.append("{"+(rTraj.segments[i].position / (Math.PI * ProfilingConstants.kWheelDiameter)) + ", " +
                    (rTraj.segments[i].velocity * 60 / (Math.PI * ProfilingConstants.kWheelDiameter)) + ", " +
                    (rTraj.segments[i].dt * 1000)+ "} ");
            if(i!=lTraj.length()-1)
            {
                pw.append(",");
                pwr.append(",");
            }
            else {
                pw.append("};\n}");
                pwr.append("};\n}");
            }
        }
        pw.close();
        pwr.close();


    }

}
