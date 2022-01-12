package com.graczdev.ipcalculator.calculator;

public class CalculatorIPService {

    public AnaliseIPResult analiseIP(IPAddress address, IPMask mask) {
        IPAddress netAddress = address.make(AddressType.NETWORK, mask);
        IPAddress broadCastAddress = address.make(AddressType.BROADCAST, mask);

        return AnaliseIPResult.builder()
                .ipAddressDecimal(address.decimal().joinDot())
                .ipAddressBinary(address.binary().joinDot())
                .maskDecimal(mask.getAddress().decimal().joinDot())
                .maskBinary(mask.getAddress().binary().joinDot())

                .netAddressDecimal(netAddress.decimal().joinDot())
                .netAddressBinary(netAddress.binary().joinDot())
                .broadCastAddressDecimal(broadCastAddress.decimal().joinDot())
                .broadCastAddressBinary(broadCastAddress.binary().joinDot())

                .maxHostDecimal(address.getMaxHost(mask).decimal().joinDot())
                .maxHostBinary(address.getMaxHost(mask).binary().joinDot())
                .minHostDecimal(address.getMinHost(mask).decimal().joinDot())
                .minHostBinary(address.getMinHost(mask).binary().joinDot())

                .maskNumber(mask.getCidr())
                .subnetNumber(mask.getSubnetNumber())
                .amountOfHosts(mask.getHostCount())
                .networkClass(NetworkClass.of(address))
                .build();
    }

}
