package com.graczdev.ipcalculator.calculator;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@Builder(access = AccessLevel.PACKAGE)
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class AnaliseIPResult {

    private final String ipAddressDecimal;
    private final String ipAddressBinary;
    private final String maskDecimal;
    private final String maskBinary;

    private final String netAddressDecimal;
    private final String netAddressBinary;
    private final String broadCastAddressDecimal;
    private final String broadCastAddressBinary;

    private final String maxHostDecimal;
    private final String maxHostBinary;
    private final String minHostDecimal;
    private final String minHostBinary;

    private final int maskNumber;
    private final int subnetNumber;
    private final long amountOfHosts;
    private final NetworkClass networkClass;

}
