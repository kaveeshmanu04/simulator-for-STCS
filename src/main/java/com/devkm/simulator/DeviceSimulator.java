package com.devkm.simulator;


import com.devkm.model.IoTDevice;
import com.devkm.jms.JMSQueueSender;

import java.util.Date;
import java.util.Random;

public class DeviceSimulator {
    private static final Random random = new Random();
    private static int vehicleIdCounter = 0;

    private static String getRandomTrafficLightStatus() {
        int randomInt = random.nextInt(3);
        switch (randomInt) {
            case 0:
                return "Green";
            case 1:
                return "Yellow";
            case 2:
                return "Red";
            default:
                return "Green"; // Default to Green if something goes wrong
        }
    }

    private static String getRandomEnvironmentalConditions() {
        return random.nextBoolean() ? "Polluted" : "Non-polluted";
    }

    public static void simulate(boolean isSimulating) {

        while (isSimulating) {
            Date timestamp = new Date();
            String vehicleId = "Vehicle" + (vehicleIdCounter++);
            double latitude = random.nextDouble() * 180 - 90; // Random latitude between -90 and 90
            double longitude = random.nextDouble() * 360 - 180; // Random longitude between -180 and 180
            double speed = random.nextDouble() * 100; // Random speed between 0 and 100
            String trafficLightStatus = getRandomTrafficLightStatus();
            String environmentalConditions = getRandomEnvironmentalConditions();
            int trafficLightDuration = random.nextInt(60) + 1; // Random duration between 1 and 60 seconds

            IoTDevice iotDevice = new IoTDevice(timestamp, vehicleId, latitude, longitude, speed, trafficLightStatus, environmentalConditions, trafficLightDuration);

            JMSQueueSender.sendSensorData(iotDevice,JMSQueueSender.JMS_VEHICLE_QUEUE_JNDI);


            // Use the generated values for your simulation
            System.out.println("Timestamp: " + timestamp +
                    ", Vehicle ID: " + vehicleId +
                    ", Latitude: " + latitude +
                    ", Longitude: " + longitude +
                    ", Speed: " + speed +
                    ", Traffic Light Status: " + trafficLightStatus +
                    ", Environmental Conditions: " + environmentalConditions +
                    ", Traffic Light Duration: " + trafficLightDuration);

            try {
                Thread.sleep(random.nextInt(3000) + 1000); // Simulate a random delay between 1 and 4 seconds
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}

