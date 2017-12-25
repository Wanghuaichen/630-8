package com.zodolabs.zzf.zf630;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.bill.ultimatefram.ui.UltimateActivity;
import com.zodolabs.zzf.zf630.Utils.Constants;
import com.zodolabs.zzf.zf630.Utils.Utils;

import java.util.Set;

/**
 * Created by zzf on 17-12-6.
 */

public class CheckActivity extends UltimateActivity {
    TextView tongdian, wangluo, dianchi, shuju, lvguanglun, guangdian;

    BluetoothAdapter bluetoothAdapter;
    Set<BluetoothDevice> bluetoothDevices;
    static final int REQUEST_ENABLE_BT = 10001;
    Handler handler = new Handler();
    private BluetoothChatService mChatService;
    private String[] mesf = new String[]{"425", "450", "520", "550", "600", "630"};

    @Override
    protected int setContentView() {
        return R.layout.activity_check;
    }

    @Override
    protected void initView() {
        tongdian = findViewById(R.id.tv_tongdian);
        wangluo = findViewById(R.id.tv_wangluo);
        dianchi = findViewById(R.id.tv_dianchi);
        shuju = findViewById(R.id.tv_shuju);
        lvguanglun = findViewById(R.id.tv_lvguanglun);
        guangdian = findViewById(R.id.tv_guangdian);
    }

    @Override
    protected void initEvent(Bundle savedInstanceState) {
        if (mChatService == null) {
            mChatService = new BluetoothChatService(this, mHandler);
        }
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                initall();
            }
        }, 2000);
    }

    private void initall() {
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (!bluetoothAdapter.isEnabled()) {
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
        } else {
            bluetoothDevices = bluetoothAdapter.getBondedDevices();
            for (BluetoothDevice device : bluetoothDevices) {
                Log.d(TAG, "蓝牙:" + device.getAddress() + "......" + device.getName());
                if (device.getName().equals("HC-06")) {
                    connectBlue(device);
                }
            }
        }
    }

    private void connectBlue(BluetoothDevice device) {
        mChatService.connect(device, false);
    }

    @SuppressLint("HandlerLeak")
    private final Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case Constants.MESSAGE_STATE_CHANGE:
                    switch (msg.arg1) {
                        case BluetoothChatService.STATE_CONNECTED:
                            break;
                        case BluetoothChatService.STATE_CONNECTING:
                            break;
                        case BluetoothChatService.STATE_LISTEN:
                        case BluetoothChatService.STATE_NONE:
                            break;
                    }
                    break;
                case Constants.MESSAGE_WRITE:
                    byte[] writeBuf = (byte[]) msg.obj;
                    // construct a string from the buffer
                    String writeMessage = new String(writeBuf);
                    break;
                    /*下位机数据返回*/
                case Constants.MESSAGE_READ:
                    String Mes = ((StringBuffer) msg.obj).toString();
                    Log.d(TAG, Mes);
                    splitStr(Mes,true);
                    break;
                case Constants.MESSAGE_DEVICE_NAME:
                    // save the connected device's name
                    String mConnectedDeviceName = msg.getData().getString(Constants.DEVICE_NAME);
                    Toast.makeText(CheckActivity.this, "Connected to "
                            + mConnectedDeviceName, Toast.LENGTH_SHORT).show();
                    sendNum("520");
                    break;
                case Constants.MESSAGE_TOAST:
                    Toast.makeText(CheckActivity.this, msg.getData().getString(Constants.TOAST),
                            Toast.LENGTH_SHORT).show();
                    tongdian.setText("NO");
                    if (isNetworkAvailable(CheckActivity.this)) {
                        wangluo.setText("YES");
                    } else {
                        wangluo.setText("NO");
                    }
                    dianchi.setText("NO");
                    shuju.setText("NO");
                    lvguanglun.setText("NO");
                    guangdian.setText("NO");
                    break;
            }
        }
    };

    private void sendNum(String bochang) {
        int num = 0;
        for (int i = 0; i < mesf.length; i++) {
            if (mesf[i].equals(bochang)) {
                num = i + 1;
                break;
            }
        }
        Log.d(TAG, "第几块滤光片：" + num);
        Integer inum = num;
        byte jiaoyan = Utils.getXor(new byte[]{0x00, 0x00, 0x03, 0x01, 0x01, inum.byteValue()});
        Integer a5 = new Integer(0xa5);
        mChatService.write(new byte[]{a5.byteValue(), 0x00, 0x00, 0x03, 0x01, 0x01, inum.byteValue(), jiaoyan});
    }


    /*将下位机返回的数据进行剥离*/
    private void splitStr(String response, boolean zj) {
        try {
            String[] arr = response.split(" ");
            Log.d(TAG, "共" + arr.length + "字节");
            int val = 0;
            if (arr.length == 24) {
                for (int i = 1; i < 23; i++) {
                    val ^= Integer.valueOf(arr[i]);
                }
                Log.d(TAG, "" + val + "==" + arr[23]);
                if (val == Integer.valueOf(arr[23])) {
                    tongdian.setText("YES");
                    if (isNetworkAvailable(CheckActivity.this)) {
                        wangluo.setText("YES");
                    } else {
                        wangluo.setText("NO");
                    }
                    dianchi.setText("YES");
                    shuju.setText("YES");
                    lvguanglun.setText("YES");
                    guangdian.setText("YES");
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            startActivity(MainActivity.class, true);
                        }
                    }, 1000);
                } else {
                    tongdian.setText("NO");
                    if (isNetworkAvailable(CheckActivity.this)) {
                        wangluo.setText("YES");
                    } else {
                        wangluo.setText("NO");
                    }
                    dianchi.setText("NO");
                    shuju.setText("NO");
                    lvguanglun.setText("NO");
                    guangdian.setText("NO");
                    toast("数据校验不正确,请重启下位机");
                }
            } else {
                toast("数据校验不正确");
            }
        } catch (Exception e) {
            e.printStackTrace();
            toast("数据校验不正确,请重启下位机");
        }
    }




    private boolean isNetworkAvailable(Context context) {
        if (context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
            if (mNetworkInfo != null) {
                return mNetworkInfo.isAvailable();
            }
        }
        return false;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == REQUEST_ENABLE_BT) {
                bluetoothDevices = bluetoothAdapter.getBondedDevices();
                for (BluetoothDevice device : bluetoothDevices) {
                    Log.d(TAG, "蓝牙:" + device.getAddress() + "......" + device.getName());
                    if (device.getName().equals("HC-06")) {
                        sendNum("520");
                    }
                }
            }
        }
    }

    @Override
    protected boolean canSetSystemBarOnFragment() {
        return false;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mChatService != null) {
            // Only if the state is STATE_NONE, do we know that we haven't started already
            if (mChatService.getState() == BluetoothChatService.STATE_NONE) {
                // Start the Bluetooth chat services
                mChatService.start();
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mChatService != null) {
            mChatService.stop();
        }
    }
}
