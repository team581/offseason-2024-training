// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.util;

import dev.doglog.DogLog;
import edu.wpi.first.hal.HALUtil;
import java.util.HashMap;
import java.util.Map;

public class Stopwatch {
  private static Stopwatch instance;

  public static synchronized Stopwatch getInstance() {
    if (instance == null) {
      instance = new Stopwatch();
    }

    return instance;
  }

  private static double getTimestamp() {
    return HALUtil.getFPGATime() / 1e3;
  }

  private final Map<String, Double> lastTimestamps = new HashMap<>();

  private Stopwatch() {}

  public void start(String name) {
    lastTimestamps.put(name, getTimestamp());
  }

  public void stop(String name) {
    double timestamp = getTimestamp();
    double lastTimestamp = lastTimestamps.get(name);
    DogLog.log(name, timestamp - lastTimestamp);
  }

  public void skip(String name) {
    DogLog.log(name, -1.0);
  }
}
