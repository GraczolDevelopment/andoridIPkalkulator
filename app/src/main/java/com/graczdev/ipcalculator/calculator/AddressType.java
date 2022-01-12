package com.graczdev.ipcalculator.calculator;

public enum AddressType {

    NETWORK(Bit.ZERO),
    BROADCAST(Bit.ONE);

    private final String fill;

    AddressType(String fill) {
        this.fill = fill;
    }

    public String getFill() {
        return fill;
    }

}
