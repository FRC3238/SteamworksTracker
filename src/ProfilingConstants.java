/**
 * Created by Garrett on 2/21/2017.
 */
public class ProfilingConstants {
        //Robot geometry constants
        public static final double kWheelbaseWidth =( 0.61595*(1/0.0254)); //meters
        public static final double kWheelbaseLength = 17.375; //inches
        public static final double kRobotWidthWithBumpers = 36.75; //inches
        public static final double kRobotLengthWithBumpers = 33.75; //inches
        public static final double kFrontOfGearToRobotCenter = 10.875; //inches
        public static final double kWheelDiameter = 7.64; //inches
        //field geometry constants
        public static final double kDriverWallToCenterLift = 112;
        public static final double kDriverWallToSideLift = 130; //inches, from field CAD

        //public static final double kSideWallToBoilerSideLift = 91.2; //inches, from field CAD
        public static final double kSideWallToBoilerSideLift = 100; //inches, from measuring our field setup

        public static final double kSideWallToFeederSideLift = 89.7; //inches, from field CAD


        public static final double kStartPointToBot = kSideWallToBoilerSideLift-66+39;//at -66 thought it was at 0

        public static final double kFirstForwardMoveFar = kDriverWallToSideLift-(kSideWallToBoilerSideLift-kRobotWidthWithBumpers/2)*Math.tan(Math.toRadians(30))-kRobotLengthWithBumpers/2;
        public static final double kSecondForwardMoveFar = (kSideWallToBoilerSideLift-kRobotWidthWithBumpers/2)/Math.cos(Math.toRadians(30))-kRobotLengthWithBumpers/2;

        public static final double kFirstForwardMove = kDriverWallToSideLift-(kStartPointToBot-kRobotWidthWithBumpers/2)*Math.tan(Math.toRadians(30))-kRobotLengthWithBumpers/2;
        public static final double kSecondForwardMove = (kStartPointToBot-kRobotWidthWithBumpers/2)/Math.cos(Math.toRadians(30))-kRobotLengthWithBumpers/2+kFrontOfGearToRobotCenter;


}
