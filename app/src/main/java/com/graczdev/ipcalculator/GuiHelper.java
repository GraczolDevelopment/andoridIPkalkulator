package com.graczdev.ipcalculator;

import com.graczdev.ipcalculator.calculator.IPMask;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class GuiHelper {

    protected static final String header =          "Kalkulator IP";
    protected static final String ipAddress =       "adres IP";
    protected static final String mask =            "maska";
    protected static final String calculateButton = "Oblicz";
    protected static final List<String> masksListStrings = Arrays.stream(IPMask.values())
            .map(mask -> mask.getAddress().decimal().joinDot())
            .collect(Collectors.toList());

    protected static final List<IPMask> masksListEnums = Arrays.stream(IPMask.values())
            .collect(Collectors.toList());
}
