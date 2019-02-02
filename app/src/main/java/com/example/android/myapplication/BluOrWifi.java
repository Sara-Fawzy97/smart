package com.example.android.myapplication;

import android.app.AlertDialog;
import android.app.Dialog;
import android.bluetooth.BluetoothAdapter;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatDialogFragment;

public class BluOrWifi extends AppCompatDialogFragment {

    BluetoothAdapter mbt;

    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        mbt= BluetoothAdapter.getDefaultAdapter();

        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        builder.setMessage("Control with Bluetooth or Wifi");
        builder.setPositiveButton("Blutooth", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                // TURN ON BT
                if (!mbt.isEnabled())
                {
                    Intent enableBT= new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                    startActivity(enableBT);
                }
            }
        });
        builder.setNegativeButton("Wifi", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(Settings.ACTION_WIFI_SETTINGS);

                //this function handle if that app exist on device or not this called implicit intent
                startActivity(intent);
                  //if (intent.resolveActivity(getPackageManager()) != null) {
                    // startActivity(intent);
                //}
            }
        });
        return builder.create();
    }
}
