
package org.usfirst.frc.team1.robot;


import edu.wpi.first.wpilibj.SampleRobot;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Timer;

/**
 * Created for FRC Team 1672 
 * 
 * This is an example project to teach new members how 
 * to code + deploy to the roboRIO.
 * Refer to any comments for questions or concerns.
 * If any member needs direct help, contact me at dctm1234@gmail.com
 * 
 * @author Kyle Mikolajczyk (Team Member, Team 1672)
 * @since 1.0
 * @version 1.0
 * 
 * 8/25/16
 */
public class Robot extends SampleRobot 
{
	//Create any private variables needed
    RobotDrive robot; //Robot object
    Joystick leftStick; //You create a new joystick object for each joystick you have
    Joystick rightStick;
    
    //Create motor controller objects
    
    Talon leftTalon = new Talon(0); //The # is the gpio id that the motor controller is connected to
    Talon rightTalon = new Talon(1);
    
    /**
     * @return void
     * This is called when the robot is "Enabled", or initiated. 
     * It is called every time (once) when the robot is activated.
     * Here, initiate any private variables created.
     */
    public Robot() 
    {
        robot = new RobotDrive(leftTalon, rightTalon); //Creates a robot object consisting of the LEFT and RIGHT motor controllers. In this case, 2 Talons were used
        robot.setExpiration(0.1); //Needed, cant remembed why
        leftStick = new Joystick(0); //Initialize all joysticks (# is the number of joystick found in the Driver Station)
        rightStick = new Joystick(1);
    }

    /**
     * Drive left & right motors for 2 seconds then stop
     * 
     * Says it all, autonomous is called when the robot is 
     * switched from teleop to autonomous. Put any automaion 
     * code in here.
     */
    public void autonomous() 
    {
    	/**
    	 * MUST SET SAFTEY TO FALSE OTHERWISE 
    	 * AUTONOMOUS WILL NOT WORK!!!
    	 */
    	robot.setSafetyEnabled(false); 
        
        robot.drive(-0.5, 0.0);	// drive forwards half speed
        Timer.delay(2.0);		//    for 2 seconds
        robot.drive(0.0, 0.0);	// stop robot
    }

    /**
     * @return void
     * 
     */
    public void operatorControl()
    {
        robot.setSafetyEnabled(true); //MUST SET TO TRUE INORDER TO DRIVE
        
        while (isOperatorControl() && isEnabled())//Keeps loop running while robot is enabled, this is important
        {
            robot.tankDrive(leftStick, rightStick); // drive with tank drive (2 joysticks)
            Timer.delay(0.005);		// wait for a motor update time
        }
    }

    /**
     * Runs during test mode
     */
    public void test() 
    {
    	
    }
}
