package com.graczdev.ipcalculator.calculator;

import java.util.Arrays;
import java.util.function.Function;

import panda.std.stream.PandaStream;

public class NetworkUtils {

    public static final String SPLIT_FOR_8_PARTS = "(?<=\\G.{8})";

    public static final Function<String, String> TO_BINARY_FUNCTION =
            octet -> String.format("%8s", Integer.toBinaryString(Integer.parseInt(octet)))
                    .replace(" ", "0");

    public static final Function<String, String> TO_DECIMAL_FUNCTION =
            octet -> String.valueOf(Integer.parseInt(octet, 2));

    public static String[] toBinary(String[] decimal) {
        return convert(decimal, TO_BINARY_FUNCTION);
    }

    public static String toBinary(String decimal) {
        return String.join("." , convert(decimal.split("\\."), TO_BINARY_FUNCTION));
    }

    public static String[] toDecimal(String[] binary) {
        return convert(binary, TO_DECIMAL_FUNCTION);
    }

    public static String toDecimal(String binary) {
        String[] binaryParts = binary.contains(".")
                ? binary.split("\\.")
                : PandaStream.of(binary)
                .flatMapStream(s -> Arrays.stream(s.split("(?<=\\G.{16})")))
                .flatMapStream(s -> Arrays.stream(s.split("(?<=\\G.{8})")))
                .toArray(String[]::new);

        return String.join(".", convert(binaryParts, TO_DECIMAL_FUNCTION));
    }

    static String[] convert(String[] octetsArr, Function<String, String> runnable) {
        String[] result = new String[octetsArr.length];

        for (int i = 0; i < octetsArr.length; i++) {
            result[i] = runnable.apply(octetsArr[i]);
        }

        return result;
    }

}
