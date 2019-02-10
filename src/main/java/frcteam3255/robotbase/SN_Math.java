/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frcteam3255.robotbase;

public class SN_Math {
    /**
     * 
     * @param input       - the input value used to determine the output value
     * @param minInput    - the input value that should map to the outputAtMin value
     * @param maxInput    - the input value that should map to the outputAtMax value
     * @param outputAtMin - the output value when input = minInput
     * @param outputAtMax - the output value when input = maxInput
     * @return
     */
    public static double interpolate(double input, double minInput, double maxInput, double outputAtMin,
            double outputAtMax) {

        double output = input;

        if (input <= minInput) {
            output = outputAtMin;
        } else if (input >= maxInput) {
            output = outputAtMax;
        } else {
            // output is somewhere between minOutput and maxOutput
            output = outputAtMin + (((input - minInput) / (maxInput - minInput)) * (outputAtMax - outputAtMin));

        }
        return output;
    }
}
