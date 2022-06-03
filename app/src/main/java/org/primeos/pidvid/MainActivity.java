package org.primeos.pidvid;

import android.content.Context;
import android.hardware.usb.UsbManager;
import android.os.Bundle;
import android.view.InputDevice;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    UsbManager mUsbManager = null;
    ListView mListView;
    ArrayList<String> deviceList = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mUsbManager = (UsbManager) getSystemService(Context.USB_SERVICE);
        mListView = findViewById(R.id.product_listview);

        if (mUsbManager != null) {
            for (int i : InputDevice.getDeviceIds()) {

                //Get all attached input devices and convert their product and vendor ids to string
                InputDevice inputdevice = InputDevice.getDevice(i);
                String pid = String.valueOf(inputdevice.getProductId());
                String vid = String.valueOf(inputdevice.getVendorId());

                //Add all the data into ArrayList
                deviceList.add(inputdevice.getDevice(i).toString()
                        + " ProductID: " + pid + " VendorID: " + vid);
            }
        }

        //Create an arrayAdapter
        ArrayAdapter<String> arrayAdapter =
                new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, deviceList);
        mListView.setAdapter(arrayAdapter);
    }
}

