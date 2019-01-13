package frcteam3255.robotbase;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

/**
 * SuperNURDs encapsulation of the TalonSRX class
 */
public class SN_TalonSRX extends WPI_TalonSRX {
    public SN_TalonSRX(int deviceNumber) {
        super(deviceNumber);

        setSafetyEnabled(false);
        setNeutralMode(NeutralMode.Brake);
    }
}