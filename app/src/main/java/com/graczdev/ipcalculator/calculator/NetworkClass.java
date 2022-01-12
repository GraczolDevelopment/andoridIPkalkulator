package com.graczdev.ipcalculator.calculator;

public enum NetworkClass {
    A(128),
    B(192),
    C(224),
    D(240),
    E;

    private final int max;

    NetworkClass(int max) {
        this.max = max;
    }

    NetworkClass() {
        this.max = Integer.MAX_VALUE;
    }

    public static NetworkClass of(IPAddress address) {
        int firstOctet = Integer.parseInt(address.decimal().getFirst());

        for (NetworkClass networkClass : values()) {
            if (firstOctet < networkClass.max) {
                return networkClass;
            }
        }

        return E;
    }

}
