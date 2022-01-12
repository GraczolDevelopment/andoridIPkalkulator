package com.graczdev.ipcalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.graczdev.ipcalculator.calculator.AnaliseIPResult;
import com.graczdev.ipcalculator.calculator.CalculatorIPService;
import com.graczdev.ipcalculator.calculator.IPAddress;

import panda.std.Option;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText ip = findViewById(R.id.IP);

        Spinner spinner = findViewById(R.id.spinner1);

        final ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<>(this, R.layout.spinner_item, GuiHelper.masksListStrings);

        spinnerArrayAdapter.setDropDownViewResource(R.layout.spinner_item);
        spinner.setAdapter(spinnerArrayAdapter);


        findViewById(R.id.button).setOnClickListener(view -> {
            Thread thread = new Thread(() -> {
                CalculatorIPService service = new CalculatorIPService();

                Option<IPAddress> addressOption = Option.attempt(RuntimeException.class,
                        () -> new IPAddress(ip.getText().toString()));

                runOnUiThread(() -> {
                    if (addressOption.isEmpty()) {
                        Toast.makeText(this, "Błędne IP", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    if (spinner.getSelectedItem() == null) {
                        Toast.makeText(this, "Wybierz Maskę!", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    new Thread(() -> {
                        int index = GuiHelper.masksListStrings.indexOf(spinner.getSelectedItem().toString());
                        AnaliseIPResult analiseIPResult = service.analiseIP(addressOption.get(), GuiHelper.masksListEnums.get(index));

                        runOnUiThread(() -> {

                            ((TextView) findViewById(R.id.IpDecimalV)).setText(analiseIPResult.getIpAddressDecimal());
                            ((TextView) findViewById(R.id.IpBinaryV)).setText(analiseIPResult.getIpAddressBinary());

                            ((TextView) findViewById(R.id.maskDecimalV)).setText(analiseIPResult.getMaskDecimal());
                            ((TextView) findViewById(R.id.MaskBinaryV)).setText(analiseIPResult.getMaskBinary());

                            ((TextView) findViewById(R.id.NetAddressDecimalV)).setText(analiseIPResult.getNetAddressDecimal());
                            ((TextView) findViewById(R.id.NetAddressBinaryV)).setText(analiseIPResult.getNetAddressBinary());

                            ((TextView) findViewById(R.id.BroadcastAddressBinaryV)).setText(analiseIPResult.getBroadCastAddressBinary());
                            ((TextView) findViewById(R.id.BroadcastAddressDecimallV)).setText(analiseIPResult.getBroadCastAddressDecimal());

                            ((TextView) findViewById(R.id.MaxHostDecimalV)).setText(analiseIPResult.getMaxHostDecimal());
                            ((TextView) findViewById(R.id.MaxHostBinaryV)).setText(analiseIPResult.getMaxHostBinary());

                            ((TextView) findViewById(R.id.MinHostBinaryV)).setText(analiseIPResult.getMinHostBinary());
                            ((TextView) findViewById(R.id.MinHostDecimalV)).setText(analiseIPResult.getMinHostDecimal());

                            ((TextView) findViewById(R.id.MaskNumberV)).setText(String.valueOf(analiseIPResult.getMaskNumber()));
                            ((TextView) findViewById(R.id.AmountOfHostV)).setText(String.valueOf(analiseIPResult.getAmountOfHosts()));

                            ((TextView) findViewById(R.id.NetworkClassV)).setText(analiseIPResult.getNetworkClass().name());
                            ((TextView) findViewById(R.id.SubnetNumberV)).setText(String.valueOf(analiseIPResult.getSubnetNumber()));
                        });
                    }).start();
                });

            });

            thread.start();
        });
    }
}