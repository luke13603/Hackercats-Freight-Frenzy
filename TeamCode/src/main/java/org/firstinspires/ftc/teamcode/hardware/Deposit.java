package org.firstinspires.ftc.teamcode.hardware;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

@Config
public class Deposit {

    private Servo pusher;
    private Servo door;

    double pusherhome; // The retracted position of the pusher
    double doorhome; // The closed position of the door

    //time to wait before we close the door and reset
    // the pusher after the freight has fallen out
    public static int falltime = 300;

    public void init(HardwareMap hwmap) {
        pusher = hwmap.get(Servo.class, "pusher");
        door = hwmap.get(Servo.class, "door");

        pusherhome = 0.05;
        doorhome = 0.1;

        pusher.setPosition(pusherhome);
        door.setPosition(doorhome);
    }

    public void dump(ElapsedTime time){
        if (time.milliseconds() > 0 && time.milliseconds() < falltime){
            doorOpen();
            push();// Open the door if the timer is less than pusherlag
        }
        if (time.milliseconds() > falltime) reset(); // Reset deposit after falltime is over
    }

    public void reset(){
        pusher.setPosition(pusherhome);
        door.setPosition(doorhome);
    }
    public void doorOpen(){
        door.setPosition(0.6);
    }
    public void push(){
        pusher.setPosition(0.45);
    }
}
