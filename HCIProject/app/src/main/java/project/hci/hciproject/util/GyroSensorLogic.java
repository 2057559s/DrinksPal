package project.hci.hciproject.util;

import android.hardware.SensorEvent;

import static java.lang.Math.cos;
import static java.lang.Math.sin;
import static java.lang.Math.sqrt;

/**
 * @author jake degiovanni
 */

public class GyroSensorLogic {

    public static void listenerLogic(SensorEvent event,
                                     long timestamp,
                                     float[] deltaRotationVector) {
        final float NS2S = 1.0f / 1000000000.0f;
        final float dT = (event.timestamp - timestamp) * NS2S;
        // Axis of the rotation sample, not normalized yet.
        float axisX = event.values[0];
        float axisY = event.values[1];
        float axisZ = event.values[2];

        // Calculate the angular speed of the sample
        float omegaMagnitude = (float) sqrt(axisX * axisX + axisY * axisY + axisZ * axisZ);

        // Normalize the rotation vector if it's big enough to get the axis
        // (that is, EPSILON should represent your maximum allowable margin of error)
        float EPSILON = 0.1f;
        if (omegaMagnitude > EPSILON) {
            axisX /= omegaMagnitude;
            axisY /= omegaMagnitude;
            axisZ /= omegaMagnitude;
        }

        // Integrate around this axis with the angular speed by the timestep
        // in order to get a delta rotation from this sample over the timestep
        // We will convert this axis-angle representation of the delta rotation
        // into a quaternion before turning it into the rotation matrix.
        float thetaOverTwo = omegaMagnitude * dT / 2.0f;
        float sinThetaOverTwo = (float) sin(thetaOverTwo);
        float cosThetaOverTwo = (float) cos(thetaOverTwo);
        deltaRotationVector[0] = sinThetaOverTwo * axisX;
        deltaRotationVector[1] = sinThetaOverTwo * axisY;
        deltaRotationVector[2] = sinThetaOverTwo * axisZ;
        deltaRotationVector[3] = cosThetaOverTwo;
    }
}
