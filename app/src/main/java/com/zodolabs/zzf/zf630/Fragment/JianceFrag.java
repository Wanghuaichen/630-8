package com.zodolabs.zzf.zf630.Fragment;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bill.ultimatefram.ui.UltimateFragment;
import com.bill.ultimatefram.view.dialog.IOSListDialog;
import com.bill.ultimatefram.view.recycleview.UltimateItemDecoration;
import com.bill.ultimatefram.view.recycleview.adapter.UltimateRecycleAdapter;
import com.bill.ultimatefram.view.recycleview.adapter.UltimateRecycleHolder;
import com.zodolabs.zzf.zf630.App;
import com.zodolabs.zzf.zf630.Beans.db.Dictionary;
import com.zodolabs.zzf.zf630.Beans.db.ItemBean;
import com.zodolabs.zzf.zf630.Beans.db.place;
import com.zodolabs.zzf.zf630.Beans.db.unit;
import com.zodolabs.zzf.zf630.Beans.normal.JianceBean;
import com.zodolabs.zzf.zf630.BluetoothChatService;
import com.zodolabs.zzf.zf630.R;
import com.zodolabs.zzf.zf630.Utils.Constants;
import com.zodolabs.zzf.zf630.Utils.LineCoefficient;
import com.zodolabs.zzf.zf630.Utils.Utils;
import com.zodolabs.zzf.zf630.greendao.gen.DaoSession;
import com.zodolabs.zzf.zf630.greendao.gen.DictionaryDao;
import com.zodolabs.zzf.zf630.greendao.gen.ItemBeanDao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by zzf on 17-12-6.
 */

public class JianceFrag extends UltimateFragment implements View.OnClickListener {
    Button jiance, tiaolin, qinkong, print, search, video, back;
    EditText edypbh;
    TextView tvjcxm, tvlydw, tvypcd, tvypfl, tvypmc;
    TextView tvMessage;
    RecyclerView lv;

    DaoSession session;
    List<ItemBean> itemBeanList;
    List<unit> unitList;
    List<place> placeList;
    List<Dictionary> dictionaryList;
    List<JianceBean> jianceBeanList;
    JianceAdapter jianceAdapter;
    ItemBean curItemBean;
    String[] mesf = new String[]{"425", "450", "520", "550", "600", "630"};

    SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmssSSS");
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    BluetoothAdapter bluetoothAdapter;
    BluetoothDevice curDevice;
    private BluetoothChatService mChatService = null;
    ProgressDialog materialDialog;

    List<Integer> zijianlist;    //自检数据
    List<Integer> jianlist;   //检测数据
    boolean zijian = true;

    List<HashMap<Integer, JianceBean>> dataList;

    @Override
    protected int setContentView() {
        return R.layout.frag_jiance;
    }

    @Override
    protected void initView() {
        getFlexibleBar().setVisibility(View.GONE);
        jiance = findViewById(R.id.btn_jiance);
        tiaolin = findViewById(R.id.btn_tiaolin);
        qinkong = findViewById(R.id.btn_clear);
        print = findViewById(R.id.btn_print);
        search = findViewById(R.id.btn_search);
        video = findViewById(R.id.btn_video);
        back = findViewById(R.id.btn_back);
        edypbh = findViewById(R.id.ed_ypbh);
        tvjcxm = findViewById(R.id.tv_jcxm_v);
        tvlydw = findViewById(R.id.tv_lydw_v);
        tvypcd = findViewById(R.id.tv_ypcd_v);
        tvypfl = findViewById(R.id.tv_ypfl_v);
        tvypmc = findViewById(R.id.tv_ypmc_v);
        tvMessage = findViewById(R.id.tv_message);
        lv = findViewById(R.id.lv);
        jiance.setOnClickListener(this);
        tiaolin.setOnClickListener(this);
        qinkong.setOnClickListener(this);
        print.setOnClickListener(this);
        search.setOnClickListener(this);
        video.setOnClickListener(this);
        back.setOnClickListener(this);
        tvjcxm.setOnClickListener(this);
        tvlydw.setOnClickListener(this);
        tvypcd.setOnClickListener(this);
        tvypfl.setOnClickListener(this);
        tvypmc.setOnClickListener(this);
    }

    @Override
    protected void initEvent(Bundle savedInstanceState) {
        zijianlist = new ArrayList<>();
        jianlist = new ArrayList<>();
        dataList = new ArrayList<>();
        session = App.getInstance().getDaosession();
        itemBeanList = session.getItemBeanDao().queryBuilder().orderAsc(ItemBeanDao.Properties.Print).list();
        unitList = session.getUnitDao().loadAll();
        placeList = session.getPlaceDao().loadAll();
        dictionaryList = session.getDictionaryDao().loadAll();
        edypbh.setText("" + format.format(new Date()));
        if (!itemBeanList.isEmpty()) {
            tvjcxm.setText(itemBeanList.get(0).getCode() + "|" + itemBeanList.get(0).getName());
            tvMessage.setText("当前项目：" + itemBeanList.get(0).getCode() + "\n" +
                    "检测方法：" + itemBeanList.get(0).getMethod() + "\n" +
                    "计算方法：" + itemBeanList.get(0).getAglo() + "\n" +
                    "空白值：" + itemBeanList.get(0).getBlank());
            curItemBean = itemBeanList.get(0);
        }
        if (!unitList.isEmpty()) {
            tvlydw.setText(unitList.get(0).getName());
        }
        if (!placeList.isEmpty()) {
            tvypcd.setText(placeList.get(0).getName());
        }
        if (!dictionaryList.isEmpty()) {
            tvypfl.setText(dictionaryList.get(0).getBelong());
            tvypmc.setText(dictionaryList.get(0).getContent());
        }
        jianceBeanList = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            jianceBeanList.add(new JianceBean(0l));
        }
        lv.setLayoutManager(new LinearLayoutManager(getActivity()));
        lv.addItemDecoration(new UltimateItemDecoration(getActivity(), RecyclerView.VERTICAL, 0.5f, getColor(R.color.c_70000000)));
        lv.setAdapter(jianceAdapter = new JianceAdapter(getActivity(), jianceBeanList, R.layout.jiance_item));
        jianceAdapter.setOnItemClickListener(new UltimateRecycleAdapter.OnItemClickListener<JianceBean>() {
            @Override
            public void onRecycleItemClickListener(JianceBean jianceBean, View view, int position, long id, int type) {
                String time = format.format(new Date());
                edypbh.setText(time);
                ((TextView) view.findViewById(R.id.it_ypbh)).setText(time);
                ((TextView) view.findViewById(R.id.it_jcxm)).setText(tvjcxm.getText());
                ((TextView) view.findViewById(R.id.it_ypmc)).setText(tvypmc.getText());
                ((TextView) view.findViewById(R.id.it_ypfl)).setText(tvypfl.getText());
                ((TextView) view.findViewById(R.id.it_ypcd)).setText(tvypcd.getText());
                ((TextView) view.findViewById(R.id.it_lydw)).setText(tvlydw.getText());
            }
        });
        initBlue();
    }

    /*初始化蓝牙，建立连接*/
    private void initBlue() {
        if (mChatService == null) {
            mChatService = new BluetoothChatService(getActivity(), mHandler);
        }
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        Set<BluetoothDevice> devices = bluetoothAdapter.getBondedDevices();
        for (BluetoothDevice device : devices) {
            Log.d(TAG, device.getName() + "----" + device.getAddress());
            if (device.getName().equals("HC-06")) {
                curDevice = device;
                materialDialog = new ProgressDialog(getActivity());
                materialDialog.show();
                connectBlue(device);
            }
        }
    }

    private void connectBlue(BluetoothDevice device) {
        mChatService.connect(device, false);
    }

    private void sendNum(String bochang) {
        int num = 0;
        for (int i = 0; i < mesf.length; i++) {
            if (mesf[i].equals(curItemBean.getMesF())) {
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

    private void sendNum() {
        Integer inum = 0;
        byte jiaoyan = Utils.getXor(new byte[]{0x00, 0x00, 0x03, 0x01, 0x01, inum.byteValue()});
        Integer a5 = new Integer(0xa5);
        mChatService.write(new byte[]{a5.byteValue(), 0x00, 0x00, 0x03, 0x01, 0x01, inum.byteValue(), jiaoyan});
    }


    /*向下位机发送检测信号*/
    public void jianceNum() {
        zijian = false;
        jianlist.clear();
        sendNum();
    }

    /*向下位机发送自检信号,切换项目时用*/
    public void zijianNum(String bochang) {
        zijian = true;
        jianlist.clear();
        zijianlist.clear();
        sendNum(bochang);
    }

    /*向下位机发送自检信号,调零时用*/
    public void zijianNum() {
        zijian = true;
        jianlist.clear();
        zijianlist.clear();
        sendNum();
    }

    /**
     * The Handler that gets information back from the BluetoothChatService
     */
    @SuppressLint("HandlerLeak")
    private final Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            FragmentActivity activity = getActivity();
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
                    if (materialDialog != null) {
                        materialDialog.dismiss();
                    }
                    String Mes = ((StringBuffer) msg.obj).toString();
                    Log.d(TAG, Mes);
                    splitStr(Mes, zijian);
                    if (!jianlist.isEmpty() && !zijianlist.isEmpty()) {
                        for (int i = 0; i < 8; i++) {
                            Log.d(TAG, "jian" + jianlist.get(i) + "zijian" + zijianlist.get(i));
                            String ypbh = getTextViewText(((TextView) lv.getChildAt(i).findViewById(R.id.it_ypbh)), "0");
                            if (ypbh.equals("0")) {
//                                toast("样品编号不能为0！");
                            } else {
                                double xgd = Math.log10((double) zijianlist.get(i) / (double) jianlist.get(i));
                                double blank = curItemBean.getBlank();
                                double xgdvalue = (xgd + blank) > 0 ? xgd + blank : 0;
                                ((TextView) lv.getChildAt(i).findViewById(R.id.it_xgd)).setText(String.format("%.3f", xgdvalue));
                                double nongdu = setNongdu(xgd);
                                ((TextView) lv.getChildAt(i).findViewById(R.id.it_jcz)).setText(String.format("%.3f", nongdu));
                                double biaozun = curItemBean.getRTvalue();
                                double biaozunmin = curItemBean.getRTvalue_min();
                                String[] arr = curItemBean.getRef().split("\\|");
                                if (arr[0].trim().equals("阳性")) {
                                    if (nongdu > biaozun) {
                                        ((TextView) lv.getChildAt(i).findViewById(R.id.it_jcjg)).setText("不合格");
                                    } else if (nongdu <= biaozun && nongdu >= biaozunmin) {
                                        ((TextView) lv.getChildAt(i).findViewById(R.id.it_jcjg)).setText("可疑");
                                    } else {
                                        ((TextView) lv.getChildAt(i).findViewById(R.id.it_jcjg)).setText("合格");
                                    }
                                } else if (arr[0].trim().equals("阴性")) {
                                    if (nongdu < biaozun) {
                                        ((TextView) lv.getChildAt(i).findViewById(R.id.it_jcjg)).setText("不合格");
                                    } else if (nongdu <= biaozun && nongdu >= biaozunmin) {
                                        ((TextView) lv.getChildAt(i).findViewById(R.id.it_jcjg)).setText("可疑");
                                    } else {
                                        ((TextView) lv.getChildAt(i).findViewById(R.id.it_jcjg)).setText("合格");
                                    }
                                }
                                String result = ((TextView) lv.getChildAt(i).findViewById(R.id.it_jcjg)).getText().toString();
                                saveJianceBean2DB(i, xgdvalue, nongdu, result);
                            }
                        }
                    } else {
                        for (int i = 0; i < 8; i++) {
                            ((TextView) lv.getChildAt(i).findViewById(R.id.it_xgd)).setText("");
                            ((TextView) lv.getChildAt(i).findViewById(R.id.it_jcz)).setText("");
                            ((TextView) lv.getChildAt(i).findViewById(R.id.it_jcjg)).setText("");
                        }
                    }
                    break;
                case Constants.MESSAGE_DEVICE_NAME:
                    // save the connected device's name
                    String mConnectedDeviceName = msg.getData().getString(Constants.DEVICE_NAME);
                    if (null != activity) {
                        Toast.makeText(activity, "Connected to "
                                + mConnectedDeviceName, Toast.LENGTH_SHORT).show();
                        zijianNum(curItemBean.getMesF());
                    }
                    break;
                case Constants.MESSAGE_TOAST:
                    if (null != activity) {
                        if (materialDialog != null) {
                            materialDialog.dismiss();
                        }
                        Toast.makeText(activity, msg.getData().getString(Constants.TOAST),
                                Toast.LENGTH_SHORT).show();
                    }
                    break;
            }
        }
    };

    private void saveJianceBean2DB(int i, double xgdvalue, double nongdu, String result) {
        String jcbh = getTextViewText((TextView) lv.getChildAt(i).findViewById(R.id.it_ypbh));
        String jcxm = getTextViewText((TextView) lv.getChildAt(i).findViewById(R.id.it_jcxm));
        String ypmc = getTextViewText((TextView) lv.getChildAt(i).findViewById(R.id.it_ypmc));
        String ypfl = getTextViewText((TextView) lv.getChildAt(i).findViewById(R.id.it_ypfl));
        String ypcd = getTextViewText((TextView) lv.getChildAt(i).findViewById(R.id.it_ypcd));
        String lydw = getTextViewText((TextView) lv.getChildAt(i).findViewById(R.id.it_lydw));
        session.getJianceBeanDao().insertOrReplace(new JianceBean(Long.valueOf(jcbh), jcxm, ypmc, ypfl,
                ypcd, lydw, String.format("%.3f",xgdvalue), String.format("%.3f",nongdu), result, new Date(), "0"));
        Log.d(TAG,""+session.getJianceBeanDao().loadAll().size());
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
                    Integer a1 = Integer.valueOf(arr[7]) * 256 + Integer.valueOf(arr[8]);
                    Integer a2 = Integer.valueOf(arr[9]) * 256 + Integer.valueOf(arr[10]);
                    Integer a3 = Integer.valueOf(arr[11]) * 256 + Integer.valueOf(arr[12]);
                    Integer a4 = Integer.valueOf(arr[13]) * 256 + Integer.valueOf(arr[14]);
                    Integer a5 = Integer.valueOf(arr[15]) * 256 + Integer.valueOf(arr[16]);
                    Integer a6 = Integer.valueOf(arr[17]) * 256 + Integer.valueOf(arr[18]);
                    Integer a7 = Integer.valueOf(arr[19]) * 256 + Integer.valueOf(arr[20]);
                    Integer a8 = Integer.valueOf(arr[21]) * 256 + Integer.valueOf(arr[22]);
                    Log.d(TAG, " " + a1 + " " + a2 + " " + a3 + " " + a4 + " " + a5 + " " + a6 + " " + a7 + " " + a8);
                    if (zj) {
                        zijianlist.add(a1);
                        zijianlist.add(a2);
                        zijianlist.add(a3);
                        zijianlist.add(a4);
                        zijianlist.add(a5);
                        zijianlist.add(a6);
                        zijianlist.add(a7);
                        zijianlist.add(a8);

                    } else {
                        jianlist.add(a1);
                        jianlist.add(a2);
                        jianlist.add(a3);
                        jianlist.add(a4);
                        jianlist.add(a5);
                        jianlist.add(a6);
                        jianlist.add(a7);
                        jianlist.add(a8);
                    }

                } else {
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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_jcxm_v:
                if (itemBeanList.isEmpty())
                    return;
                IOSListDialog dialog1 = new IOSListDialog(getActivity());
                for (ItemBean itemBean : itemBeanList) {
                    dialog1.addListItem(itemBean.getCode() + "|" + itemBean.getName(), getColor(R.color.c_70000000));
                }
                dialog1.show();
                dialog1.setOnIOSItemClickListener(new IOSListDialog.OnIOSItemClickListener() {
                    @Override
                    public void onIOSItemClick(IOSListDialog.IOSListItem data, TextView item, int position, Object tag) {
                        tvjcxm.setText(item.getText());
                        curItemBean = itemBeanList.get(position);
                        Log.d(TAG, curItemBean.getCode());
                        tvMessage.setText("当前项目：" + curItemBean.getCode() + "\n" +
                                "检测方法：" + curItemBean.getMethod() + "\n" +
                                "计算方法：" + curItemBean.getAglo() + "\n" +
                                "空白值：" + curItemBean.getBlank());
                        clearlist();
                        materialDialog = new ProgressDialog(getActivity());
                        materialDialog.show();
                        zijianNum(curItemBean.getMesF());
                    }
                });
                break;
            case R.id.tv_lydw_v:
                if (unitList.isEmpty())
                    return;
                IOSListDialog dialog2 = new IOSListDialog(getActivity());
                for (unit u : unitList) {
                    dialog2.addListItem(u.getName(), getColor(R.color.c_70000000));
                }
                dialog2.show();
                dialog2.setOnIOSItemClickListener(new IOSListDialog.OnIOSItemClickListener() {
                    @Override
                    public void onIOSItemClick(IOSListDialog.IOSListItem data, TextView item, int position, Object tag) {
                        tvlydw.setText(item.getText());
                    }
                });
                break;
            case R.id.tv_ypcd_v:
                if (placeList.isEmpty())
                    return;
                IOSListDialog dialog3 = new IOSListDialog(getActivity());
                for (place p : placeList) {
                    dialog3.addListItem(p.getName(), getColor(R.color.c_70000000));
                }
                dialog3.show();
                dialog3.setOnIOSItemClickListener(new IOSListDialog.OnIOSItemClickListener() {
                    @Override
                    public void onIOSItemClick(IOSListDialog.IOSListItem data, TextView item, int position, Object tag) {
                        tvypcd.setText(item.getText());
                    }
                });
                break;
            case R.id.tv_ypfl_v:
                if (dictionaryList.isEmpty())
                    return;
                IOSListDialog dialog4 = new IOSListDialog(getActivity());
                Set<String> stringSet = new HashSet<>();
                for (Dictionary dictionary : dictionaryList) {
                    stringSet.add(dictionary.getBelong());
                }
                for (String s : stringSet) {
                    dialog4.addListItem(s, getColor(R.color.c_70000000));
                }
                dialog4.show();
                dialog4.setOnIOSItemClickListener(new IOSListDialog.OnIOSItemClickListener() {
                    @Override
                    public void onIOSItemClick(IOSListDialog.IOSListItem data, TextView item, int position, Object tag) {
                        tvypfl.setText(item.getText());
                    }
                });
                break;
            case R.id.tv_ypmc_v:
                if (dictionaryList.isEmpty())
                    return;
                IOSListDialog dialog5 = new IOSListDialog(getActivity());
                List<Dictionary> dictionaries = session.getDictionaryDao().queryBuilder().where(DictionaryDao.Properties.Belong.eq(tvypfl.getText().toString())).list();
                for (Dictionary dictionary : dictionaries) {
                    dialog5.addListItem(dictionary.getContent(), getColor(R.color.c_70000000));
                }
                dialog5.show();
                dialog5.setOnIOSItemClickListener(new IOSListDialog.OnIOSItemClickListener() {
                    @Override
                    public void onIOSItemClick(IOSListDialog.IOSListItem data, TextView item, int position, Object tag) {
                        tvypmc.setText(item.getText());
                    }
                });
                break;
            case R.id.btn_jiance:
                materialDialog = new ProgressDialog(getActivity());
                materialDialog.show();
                jianceNum();
                break;
            case R.id.btn_tiaolin:
                materialDialog = new ProgressDialog(getActivity());
                materialDialog.show();
                zijianNum();
                break;
            case R.id.btn_clear:
                clearlist();
                break;
            case R.id.btn_print:
                break;
            case R.id.btn_search:
                replaceFragment(ChaxunFrag.class, true);
                break;
            case R.id.btn_video:
                replaceFragment(ShipinFrag.class, true);
                break;
            case R.id.btn_back:
                onLeftClickListener();
                break;
        }
    }

    private void clearlist() {
        jianceBeanList.clear();
        for (int i = 0; i < 8; i++) {
            jianceBeanList.add(new JianceBean(0L));
        }
        jianceAdapter.notifyDataSetChanged();
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

    private double setNongdu(double xgd) {
        Integer sdnum = Integer.valueOf(curItemBean.getSDNum());
        float blank = curItemBean.getBlank();
        double xgdvalue = (xgd + blank) > 0 ? xgd + blank : 0;//测出的吸光度加上blank（负数）,为需要的y轴所要的数值
        if (xgdvalue == 0) {
            return 0;
        }
        switch (sdnum) {
            case 1:
                return LineCoefficient.estimate(new double[]{curItemBean.getABS1()}, new double[]{curItemBean.getDepth1()}, xgdvalue);
            case 2:
                return LineCoefficient.estimate(new double[]{curItemBean.getABS1(), curItemBean.getABS2()},
                        new double[]{curItemBean.getDepth1(), curItemBean.getDepth2()}, xgdvalue);
            case 3:
                return LineCoefficient.estimate(new double[]{curItemBean.getABS1(), curItemBean.getABS2(), curItemBean.getABS3()},
                        new double[]{curItemBean.getDepth1(), curItemBean.getDepth2(), curItemBean.getDepth3()}, xgdvalue);
            case 4:
                return LineCoefficient.estimate(new double[]{curItemBean.getABS1(), curItemBean.getABS2(), curItemBean.getABS3(),
                                curItemBean.getABS4()},
                        new double[]{curItemBean.getDepth1(), curItemBean.getDepth2(), curItemBean.getDepth3(),
                                curItemBean.getDepth4()}, xgdvalue);
            case 5:
                return LineCoefficient.estimate(new double[]{curItemBean.getABS1(), curItemBean.getABS2(), curItemBean.getABS3(),
                                curItemBean.getABS4(), curItemBean.getABS5()},
                        new double[]{curItemBean.getDepth1(), curItemBean.getDepth2(), curItemBean.getDepth3(),
                                curItemBean.getDepth4(), curItemBean.getDepth5()}, xgdvalue);
            case 6:
                return LineCoefficient.estimate(new double[]{curItemBean.getABS1(), curItemBean.getABS2(), curItemBean.getABS3(),
                                curItemBean.getABS4(), curItemBean.getABS5(), curItemBean.getABS6()},
                        new double[]{curItemBean.getDepth1(), curItemBean.getDepth2(), curItemBean.getDepth3(),
                                curItemBean.getDepth4(), curItemBean.getDepth5(), curItemBean.getDepth6()}, xgdvalue);
            case 7:
                return LineCoefficient.estimate(new double[]{curItemBean.getABS1(), curItemBean.getABS2(), curItemBean.getABS3(),
                                curItemBean.getABS4(), curItemBean.getABS5(), curItemBean.getABS6(), curItemBean.getABS7()},
                        new double[]{curItemBean.getDepth1(), curItemBean.getDepth2(), curItemBean.getDepth3(),
                                curItemBean.getDepth4(), curItemBean.getDepth5(), curItemBean.getDepth6(), curItemBean.getDepth7()}, xgdvalue);
            case 8:
                return LineCoefficient.estimate(new double[]{curItemBean.getABS1(), curItemBean.getABS2(), curItemBean.getABS3(),
                                curItemBean.getABS4(), curItemBean.getABS5(), curItemBean.getABS6(), curItemBean.getABS7(), curItemBean.getABS8()},
                        new double[]{curItemBean.getDepth1(), curItemBean.getDepth2(), curItemBean.getDepth3(),
                                curItemBean.getDepth4(), curItemBean.getDepth5(), curItemBean.getDepth6(), curItemBean.getDepth7(), curItemBean.getDepth8()}, xgdvalue);
        }
        return 0;
    }

    private class JianceAdapter extends UltimateRecycleAdapter<JianceBean> {
        public JianceAdapter(Context context, List<JianceBean> datum, int resItemID) {
            super(context, datum, resItemID);
        }

        @Override
        protected void convert(JianceBean jianceBean, UltimateRecycleHolder holder, int position) {
            holder.setText(R.id.it_ypbh, jianceBean.getYpbh() == 0 ? "" : "" + jianceBean.getYpbh());
            holder.setText(R.id.it_jcxm, jianceBean.getJcxm());
            holder.setText(R.id.it_ypmc, jianceBean.getYpmc());
            holder.setText(R.id.it_ypfl, jianceBean.getYpfl());
            holder.setText(R.id.it_ypcd, jianceBean.getYpcd());
            holder.setText(R.id.it_lydw, jianceBean.getLydw());
            holder.setText(R.id.it_xgd, jianceBean.getXgd());
            holder.setText(R.id.it_jcz, jianceBean.getJcz());
            holder.setText(R.id.it_jcjg, jianceBean.getJcjg());
        }
    }
}
