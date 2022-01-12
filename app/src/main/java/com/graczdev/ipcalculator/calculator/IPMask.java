package com.graczdev.ipcalculator.calculator;

import panda.utilities.StringUtils;

public enum IPMask {

    MASK_8(8),   MASK_9(9),
    MASK_10(10), MASK_11(11), MASK_12(12), MASK_13(13), MASK_14(14),
    MASK_15(15), MASK_16(16), MASK_17(17), MASK_18(18), MASK_19(19),
    MASK_20(20), MASK_21(21), MASK_22(22), MASK_23(23), MASK_24(24),
    MASK_25(25), MASK_26(26), MASK_27(27), MASK_28(28), MASK_29(29),
    MASK_30(30), MASK_31(31), MASK_32(32);

    private final int cidr;
    private final IPAddress address;
    private final long hostCount;

    IPMask(int cidr) {
        String addressBinary = StringUtils.repeated(cidr, Bit.ONE) + StringUtils.repeated(32 - cidr, Bit.ZERO);
        long hostCount = (long) Math.pow(2, 32 - cidr);

        if (hostCount > 2) {
            hostCount -= 2;
        }

        this.cidr = cidr;
        this.address = new IPAddress(NetworkUtils.toDecimal(addressBinary));
        this.hostCount = Math.max(hostCount, 0);
    }

    public int getCidr() {
        return cidr;
    }

    public int getSubnetNumber() {
        return cidr - 8;
    }

    public IPAddress getAddress() {
        return address;
    }

    public long getHostCount() {
        return hostCount;
    }

    public static IPMask getByCidr(int cird) {
        for (IPMask value : values()) {
            if (value.cidr != cird) {
                continue;
            }

            return value;
        }

        throw new IllegalArgumentException();
    }

}
