package com.devkm;

import com.devkm.simulator.DeviceSimulator;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Simulator extends JFrame{
    private JButton startSimulationButton;
    private JButton stopSimulationButton;
    private JPanel mainPanel;

    private boolean isSimulating;
    private Thread simulationThread;

    public Simulator() {
        startSimulationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!isSimulating) {
                    isSimulating = true;
                    simulationThread = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            DeviceSimulator.simulate(isSimulating);
                        }
                    });
                    simulationThread.start();
                }
            }
        });

        stopSimulationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isSimulating) {
                    isSimulating = false;
                    if (simulationThread != null && simulationThread.isAlive()) {
                        simulationThread.interrupt();
                    }
                }
            }
        });
    }

    public static void main(String[] args) {
        Simulator simulator = new Simulator();
        simulator.setContentPane(simulator.mainPanel);
        simulator.setTitle("Smart Urban Traffic Control System");
        simulator.setSize(300,400);
        simulator.setVisible(true);
        simulator.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}
