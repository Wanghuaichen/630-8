package com.zodolabs.zzf.zf630.Fragment;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.bill.ultimatefram.ui.UltimateFragment;
import com.bill.ultimatefram.view.dialog.IOSListDialog;
import com.bill.ultimatefram.view.recycleview.UltimateItemDecoration;
import com.bill.ultimatefram.view.recycleview.adapter.UltimateRecycleAdapter;
import com.bill.ultimatefram.view.recycleview.adapter.UltimateRecycleHolder;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.zodolabs.zzf.zf630.App;
import com.zodolabs.zzf.zf630.Beans.db.ItemBean;
import com.zodolabs.zzf.zf630.Beans.normal.HuiGuiBean;
import com.zodolabs.zzf.zf630.R;
import com.zodolabs.zzf.zf630.greendao.gen.DaoSession;
import com.zodolabs.zzf.zf630.greendao.gen.ItemBeanDao;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by zzf on 17-12-6.
 */

public class XiangmuFrag extends UltimateFragment implements View.OnClickListener {
    RecyclerView lv;
    EditText edName, edXuhao, edBianhao, edKongbz, edJiancsj, edQita, edYang, edYing;
    TextView tvYangpfl, tvJianclb, tvNongd, tvJieglb, tvJisff, tvBoc, tvYang, tvYing, tvHuigui, tvShuliang;
    TextView tv1, tv2, tv3;
    Button btnNew, btnSave, btnDel, btnBack;
    RecyclerView lvHuigui;
    LineChart lineChart;

    DaoSession session;
    List<ItemBean> list;
    List<HuiGuiBean> huiGuiBeanList;
    XianMuAdapter adapter;
    HuiguiAdapter huiguiadapter;
    int itemPostion; //单选位置

    @SuppressLint("HandlerLeak")
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 0) {
                list.addAll((List<ItemBean>) msg.obj);
                Log.d(TAG, "list size:" + list.size());
                adapter.notifyDataSetChanged();
                updateUI(list.get(0));
            } else if (msg.what == 1) {
                ItemBean itemBean = (ItemBean) msg.obj;
                SetHuiguiList(itemBean);
            }
        }
    };

    protected void SetHuiguiList(ItemBean itemBean) {
        int sdNum = Integer.parseInt(itemBean.getSDNum());
        huiGuiBeanList.clear();
        switch (sdNum) {
            case 1:
                huiGuiBeanList.add(new HuiGuiBean("1", itemBean.getABS1(), itemBean.getDepth1()));
                break;
            case 2:
                huiGuiBeanList.add(new HuiGuiBean("1", itemBean.getABS1(), itemBean.getDepth1()));
                huiGuiBeanList.add(new HuiGuiBean("2", itemBean.getABS2(), itemBean.getDepth2()));
                break;
            case 3:
                huiGuiBeanList.add(new HuiGuiBean("1", itemBean.getABS1(), itemBean.getDepth1()));
                huiGuiBeanList.add(new HuiGuiBean("2", itemBean.getABS2(), itemBean.getDepth2()));
                huiGuiBeanList.add(new HuiGuiBean("3", itemBean.getABS3(), itemBean.getDepth3()));
                break;
            case 4:
                huiGuiBeanList.add(new HuiGuiBean("1", itemBean.getABS1(), itemBean.getDepth1()));
                huiGuiBeanList.add(new HuiGuiBean("2", itemBean.getABS2(), itemBean.getDepth2()));
                huiGuiBeanList.add(new HuiGuiBean("3", itemBean.getABS3(), itemBean.getDepth3()));
                huiGuiBeanList.add(new HuiGuiBean("4", itemBean.getABS4(), itemBean.getDepth4()));
                break;
            case 5:
                huiGuiBeanList.add(new HuiGuiBean("1", itemBean.getABS1(), itemBean.getDepth1()));
                huiGuiBeanList.add(new HuiGuiBean("2", itemBean.getABS2(), itemBean.getDepth2()));
                huiGuiBeanList.add(new HuiGuiBean("3", itemBean.getABS3(), itemBean.getDepth3()));
                huiGuiBeanList.add(new HuiGuiBean("4", itemBean.getABS4(), itemBean.getDepth4()));
                huiGuiBeanList.add(new HuiGuiBean("5", itemBean.getABS5(), itemBean.getDepth5()));
                break;
            case 6:
                huiGuiBeanList.add(new HuiGuiBean("1", itemBean.getABS1(), itemBean.getDepth1()));
                huiGuiBeanList.add(new HuiGuiBean("2", itemBean.getABS2(), itemBean.getDepth2()));
                huiGuiBeanList.add(new HuiGuiBean("3", itemBean.getABS3(), itemBean.getDepth3()));
                huiGuiBeanList.add(new HuiGuiBean("4", itemBean.getABS4(), itemBean.getDepth4()));
                huiGuiBeanList.add(new HuiGuiBean("5", itemBean.getABS5(), itemBean.getDepth5()));
                huiGuiBeanList.add(new HuiGuiBean("6", itemBean.getABS6(), itemBean.getDepth6()));
                break;
            case 7:
                huiGuiBeanList.add(new HuiGuiBean("1", itemBean.getABS1(), itemBean.getDepth1()));
                huiGuiBeanList.add(new HuiGuiBean("2", itemBean.getABS2(), itemBean.getDepth2()));
                huiGuiBeanList.add(new HuiGuiBean("3", itemBean.getABS3(), itemBean.getDepth3()));
                huiGuiBeanList.add(new HuiGuiBean("4", itemBean.getABS4(), itemBean.getDepth4()));
                huiGuiBeanList.add(new HuiGuiBean("5", itemBean.getABS5(), itemBean.getDepth5()));
                huiGuiBeanList.add(new HuiGuiBean("6", itemBean.getABS6(), itemBean.getDepth6()));
                huiGuiBeanList.add(new HuiGuiBean("7", itemBean.getABS7(), itemBean.getDepth7()));
                break;
            case 8:
                huiGuiBeanList.add(new HuiGuiBean("1", itemBean.getABS1(), itemBean.getDepth1()));
                huiGuiBeanList.add(new HuiGuiBean("2", itemBean.getABS2(), itemBean.getDepth2()));
                huiGuiBeanList.add(new HuiGuiBean("3", itemBean.getABS3(), itemBean.getDepth3()));
                huiGuiBeanList.add(new HuiGuiBean("4", itemBean.getABS4(), itemBean.getDepth4()));
                huiGuiBeanList.add(new HuiGuiBean("5", itemBean.getABS5(), itemBean.getDepth5()));
                huiGuiBeanList.add(new HuiGuiBean("6", itemBean.getABS6(), itemBean.getDepth6()));
                huiGuiBeanList.add(new HuiGuiBean("7", itemBean.getABS7(), itemBean.getDepth7()));
                huiGuiBeanList.add(new HuiGuiBean("8", itemBean.getABS8(), itemBean.getDepth8()));
                break;
        }
        List<Entry> entityList = new ArrayList<>();
        for (HuiGuiBean huiGuiBean : huiGuiBeanList) {
            entityList.add(new Entry(huiGuiBean.getXiguangdu(), huiGuiBean.getNongdu()));
        }
        Log.d(TAG, "" + entityList.size());
        LineDataSet lineDataSet = new LineDataSet(entityList, itemBean.getCode());
        lineDataSet.setColor(getColor(R.color.android_holo_blue));
        lineDataSet.setValueTextColor(getColor(R.color.c_70000000));
        LineData lineData = new LineData(lineDataSet);
        Description description = new Description();
        description.setTextSize(10f);
        description.setTextColor(getColor(R.color.android_holo_blue));
        description.setText("X轴:吸光度  Y轴:浓度");
        lineChart.setDescription(description);
        lineChart.getAxisLeft().setSpaceTop(20f);
        lineChart.setNoDataText("暂无数据");
        lineChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        lineChart.getAxisRight().setEnabled(false);
        lineChart.setData(lineData);
        lineChart.invalidate();
        huiguiadapter.notifyDataSetChanged();
    }

    @Override
    protected int setContentView() {
        return R.layout.frag_xiangmu;
    }

    @Override
    protected void initView() {
        getFlexibleBar().setVisibility(View.GONE);
        lv = findViewById(R.id.lv_xiangmu);
        edName = findViewById(R.id.ed_name);
        edXuhao = findViewById(R.id.ed_xuhao);
        edBianhao = findViewById(R.id.ed_bianhao);
        edKongbz = findViewById(R.id.ed_kongbai);
        edJiancsj = findViewById(R.id.ed_jianceshijian);
        edQita = findViewById(R.id.ed_qita);
        tvYangpfl = findViewById(R.id.tv_yangpinfenlei);
        tvJianclb = findViewById(R.id.tv_jianceleibie);
        tvNongd = findViewById(R.id.tv_nongdu);
        tvJieglb = findViewById(R.id.tv_jieguoleibie);
        tvJisff = findViewById(R.id.tv_jisuanfangfa);
        tvBoc = findViewById(R.id.tv_bochang);
        tvYang = findViewById(R.id.tv_yang);
        tvYing = findViewById(R.id.tv_ying);
        edYang = findViewById(R.id.ed_yang_value);
        edYing = findViewById(R.id.ed_ying_value);
        tvHuigui = findViewById(R.id.tv_huigui);
        tvShuliang = findViewById(R.id.tv_shuliang);
        lvHuigui = findViewById(R.id.lv_huigui);
        lineChart = findViewById(R.id.lin_chat);
        tv1 = findViewById(R.id.tv1);
        tv2 = findViewById(R.id.tv2);
        tv3 = findViewById(R.id.tv3);
        btnNew = findViewById(R.id.btn_new);
        btnSave = findViewById(R.id.btn_save);
        btnDel = findViewById(R.id.btn_del);
        btnBack = findViewById(R.id.btn_back);
        tv1.setText("序号");
        tv2.setText("浓度值");
        tv3.setText("吸光度");
    }

    @Override
    protected void initEvent(Bundle savedInstanceState) {
        session = App.getInstance().getDaosession();
        list = new ArrayList<>();
        huiGuiBeanList = new ArrayList<>();
        lv.setLayoutManager(new LinearLayoutManager(getActivity()));
        lv.addItemDecoration(new UltimateItemDecoration(getActivity(), RecyclerView.VERTICAL, 0.5f, getColor(R.color.c_70000000)));
        lv.setAdapter(adapter = new XianMuAdapter(getActivity(), list, R.layout.xiangmu_item));
        lvHuigui.setLayoutManager(new LinearLayoutManager(getActivity()));
        lvHuigui.addItemDecoration(new UltimateItemDecoration(getActivity(), RecyclerView.VERTICAL, 0.5f, getColor(R.color.c_70000000)));
        lvHuigui.setAdapter(huiguiadapter = new HuiguiAdapter(getActivity(), huiGuiBeanList, R.layout.huigui_item));
        adapter.setOnItemClickListener(new UltimateRecycleAdapter.OnItemClickListener<ItemBean>() {
            @Override
            public void onRecycleItemClickListener(ItemBean itemBean, View view, int position, long id, int type) {
                adapter.notifyItemChanged(itemPostion);
                itemPostion = position;
                adapter.notifyItemChanged(itemPostion);
                updateUI(itemBean);
                SetHuiguiList(itemBean);
            }
        });
        new Thread(new Runnable() {
            @Override
            public void run() {
                Message msg = new Message();
                msg.what = 0;
                msg.obj = session.getItemBeanDao().queryBuilder().orderAsc(ItemBeanDao.Properties.Print).list();
                handler.sendMessage(msg);
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                Message msg = new Message();
                msg.what = 1;
                msg.obj = session.getItemBeanDao().queryBuilder().orderAsc(ItemBeanDao.Properties.Print).list().get(0);
                handler.sendMessage(msg);
            }
        }).start();
        tvYangpfl.setOnClickListener(this);
        tvJianclb.setOnClickListener(this);
        tvNongd.setOnClickListener(this);
        tvJieglb.setOnClickListener(this);
        tvJisff.setOnClickListener(this);
        tvBoc.setOnClickListener(this);
        tvYang.setOnClickListener(this);
        tvYing.setOnClickListener(this);
        tvHuigui.setOnClickListener(this);
        tvShuliang.setOnClickListener(this);
        btnNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateUI(new ItemBean("", "", "", "", 0L, "", "", "", 0, "",
                        "", "", "阳性|可疑|阴性", 0, 0, 0, "", "", "", 0,
                        0, 0, "", "", "", "", "", "6", 0,
                        0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                        0, 0, 0, 0, "", "", "", "", "", ""));
                huiGuiBeanList.clear();
                for (int i = 0; i < 6; i++) {
                    String num = String.valueOf(i + 1);
                    huiGuiBeanList.add(new HuiGuiBean(num, 0f, 0f));
                }
                huiguiadapter.notifyDataSetChanged();
                lineChart.clear();
            }
        });
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(edName.getText())){
                    saveItemBean();
                }else{
                    saveItemBean(list.get(itemPostion).getSDNum());
                }
                list.clear();
                list.addAll(session.getItemBeanDao().queryBuilder().orderAsc(ItemBeanDao.Properties.Print).list());
                adapter.notifyDataSetChanged();
            }
        });
        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog dialog = new AlertDialog.Builder(getActivity())
                        .setTitle("信息提示")
                        .setMessage("您确定要删除" + list.get(itemPostion).getCode() + "项目?")
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                session.getItemBeanDao().delete(list.get(itemPostion));
                                list.clear();
                                list.addAll(session.getItemBeanDao().queryBuilder().orderAsc(ItemBeanDao.Properties.Print).list());
                                adapter.notifyDataSetChanged();
                                dialog.dismiss();
                            }
                        })
                        .show();
                dialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(getColor(R.color.android_holo_blue));
                dialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(getColor(R.color.android_holo_blue));
            }
        });
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onLeftClickListener();
            }
        });
    }

    private void saveItemBean() {
        String code = getTextViewText(edName, "");
        Long print = Long.valueOf(getTextViewText(edXuhao, "0"));
        String pid = getTextViewText(edBianhao, "");
        String name = getTextViewText(tvYangpfl, "全部");
        String t_type = getTextViewText(tvJianclb, "分光光度");
        String unit = getTextViewText(tvNongd, "mg/kg");
        String rtype = getTextViewText(tvJieglb, "定量");
        String method = getTextViewText(tvJisff, "终点法");
        String mesf = getTextViewText(tvBoc, "520");
        String testtime = getTextViewText(edJiancsj, "0");
        String yang = getTextViewText(tvYang, "阳性");
        String ying = getTextViewText(tvYing, "阴性");
        String ref = yang + "|" + "可疑" + "|" + ying;
        float rt_value = Float.valueOf(getTextViewText(edYang, "0"));
        float rt_value_min = Float.valueOf(getTextViewText(edYing, "0"));
        String aglo = getTextViewText(tvHuigui, "线性回归");
        String sdnum = "6";
        float abs1 = Float.parseFloat(getTextViewText(lvHuigui.getChildAt(0).findViewById(R.id.tv3), "0"));
        float abs2 = Float.parseFloat(getTextViewText(lvHuigui.getChildAt(1).findViewById(R.id.tv3), "0"));
        float abs3 = Float.parseFloat(getTextViewText(lvHuigui.getChildAt(2).findViewById(R.id.tv3), "0"));
        float abs4 = Float.parseFloat(getTextViewText(lvHuigui.getChildAt(3).findViewById(R.id.tv3), "0"));
        float abs5 = Float.parseFloat(getTextViewText(lvHuigui.getChildAt(4).findViewById(R.id.tv3), "0"));
        float abs6 = Float.parseFloat(getTextViewText(lvHuigui.getChildAt(5).findViewById(R.id.tv3), "0"));
        float depth1 = Float.parseFloat(getTextViewText(lvHuigui.getChildAt(0).findViewById(R.id.tv2), "0"));
        float depth2 = Float.parseFloat(getTextViewText(lvHuigui.getChildAt(1).findViewById(R.id.tv2), "0"));
        float depth3 = Float.parseFloat(getTextViewText(lvHuigui.getChildAt(2).findViewById(R.id.tv2), "0"));
        float depth4 = Float.parseFloat(getTextViewText(lvHuigui.getChildAt(3).findViewById(R.id.tv2), "0"));
        float depth5 = Float.parseFloat(getTextViewText(lvHuigui.getChildAt(4).findViewById(R.id.tv2), "0"));
        float depth6 = Float.parseFloat(getTextViewText(lvHuigui.getChildAt(5).findViewById(R.id.tv2), "0"));
        ItemBean itemBean = new ItemBean("", "", code, pid, print, t_type, name, unit, 0, method,
                mesf, "", ref, 0, 0, 0, "", "", rtype, rt_value,
                rt_value_min, 0, "", testtime, "", "", aglo, sdnum, abs1,
                abs2, abs3, abs4, abs5, abs6, 0, 0, depth1, depth2, depth3, depth4,
                depth5, depth6, 0, 0, "", "", "", "", "", "");
        session.getItemBeanDao().insertOrReplace(itemBean);
        SetHuiguiList(itemBean);
    }

    private void saveItemBean(String SDnum) {
        String code = getTextViewText(edName, "");
        Long print = Long.valueOf(getTextViewText(edXuhao, "0"));
        String pid = getTextViewText(edBianhao, "");
        String name = getTextViewText(tvYangpfl, "全部");
        String t_type = getTextViewText(tvJianclb, "分光光度");
        String unit = getTextViewText(tvNongd, "mg/kg");
        String rtype = getTextViewText(tvJieglb, "定量");
        String method = getTextViewText(tvJisff, "终点法");
        String mesf = getTextViewText(tvBoc, "520");
        String testtime = getTextViewText(edJiancsj, "0");
        String yang = getTextViewText(tvYang, "阳性");
        String ying = getTextViewText(tvYing, "阴性");
        String ref = yang + "|" + "可疑" + "|" + ying;
        float rt_value = Float.valueOf(getTextViewText(edYang, "0"));
        float rt_value_min = Float.valueOf(getTextViewText(edYing, "0"));
        String aglo = getTextViewText(tvHuigui, "线性回归");
        ItemBean itemBean = null;
        if ("1".equals(SDnum)) {
            float abs1 = Float.parseFloat(getTextViewText(lvHuigui.getChildAt(0).findViewById(R.id.tv3), "0"));
            float depth1 = Float.parseFloat(getTextViewText(lvHuigui.getChildAt(0).findViewById(R.id.tv2), "0"));
            itemBean = new ItemBean("", "", code, pid, print, t_type, name, unit, 0, method,
                    mesf, "", ref, 0, 0, 0, "", "", rtype, rt_value,
                    rt_value_min, 0, "", testtime, "", "", aglo, SDnum, abs1,
                    0, 0, 0, 0, 0, 0, 0, depth1, 0, 0, 0,
                    0, 0, 0, 0, "", "", "", "", "", "");
        } else if ("2".equals(SDnum)) {
            float abs1 = Float.parseFloat(getTextViewText(lvHuigui.getChildAt(0).findViewById(R.id.tv3), "0"));
            float abs2 = Float.parseFloat(getTextViewText(lvHuigui.getChildAt(1).findViewById(R.id.tv3), "0"));
            float depth1 = Float.parseFloat(getTextViewText(lvHuigui.getChildAt(0).findViewById(R.id.tv2), "0"));
            float depth2 = Float.parseFloat(getTextViewText(lvHuigui.getChildAt(1).findViewById(R.id.tv2), "0"));
            itemBean = new ItemBean("", "", code, pid, print, t_type, name, unit, 0, method,
                    mesf, "", ref, 0, 0, 0, "", "", rtype, rt_value,
                    rt_value_min, 0, "", testtime, "", "", aglo, SDnum, abs1,
                    abs2, 0, 0, 0, 0, 0, 0, depth1, depth2, 0, 0,
                    0, 0, 0, 0, "", "", "", "", "", "");
        } else if ("3".equals(SDnum)) {
            float abs1 = Float.parseFloat(getTextViewText(lvHuigui.getChildAt(0).findViewById(R.id.tv3), "0"));
            float abs2 = Float.parseFloat(getTextViewText(lvHuigui.getChildAt(1).findViewById(R.id.tv3), "0"));
            float abs3 = Float.parseFloat(getTextViewText(lvHuigui.getChildAt(2).findViewById(R.id.tv3), "0"));
            float depth1 = Float.parseFloat(getTextViewText(lvHuigui.getChildAt(0).findViewById(R.id.tv2), "0"));
            float depth2 = Float.parseFloat(getTextViewText(lvHuigui.getChildAt(1).findViewById(R.id.tv2), "0"));
            float depth3 = Float.parseFloat(getTextViewText(lvHuigui.getChildAt(2).findViewById(R.id.tv2), "0"));
            itemBean = new ItemBean("", "", code, pid, print, t_type, name, unit, 0, method,
                    mesf, "", ref, 0, 0, 0, "", "", rtype, rt_value,
                    rt_value_min, 0, "", testtime, "", "", aglo, SDnum, abs1,
                    abs2, abs3, 0, 0, 0, 0, 0, depth1, depth2, depth3, 0,
                    0, 0, 0, 0, "", "", "", "", "", "");
        } else if ("4".equals(SDnum)) {
            float abs1 = Float.parseFloat(getTextViewText(lvHuigui.getChildAt(0).findViewById(R.id.tv3), "0"));
            float abs2 = Float.parseFloat(getTextViewText(lvHuigui.getChildAt(1).findViewById(R.id.tv3), "0"));
            float abs3 = Float.parseFloat(getTextViewText(lvHuigui.getChildAt(2).findViewById(R.id.tv3), "0"));
            float abs4 = Float.parseFloat(getTextViewText(lvHuigui.getChildAt(3).findViewById(R.id.tv3), "0"));
            float depth1 = Float.parseFloat(getTextViewText(lvHuigui.getChildAt(0).findViewById(R.id.tv2), "0"));
            float depth2 = Float.parseFloat(getTextViewText(lvHuigui.getChildAt(1).findViewById(R.id.tv2), "0"));
            float depth3 = Float.parseFloat(getTextViewText(lvHuigui.getChildAt(2).findViewById(R.id.tv2), "0"));
            float depth4 = Float.parseFloat(getTextViewText(lvHuigui.getChildAt(3).findViewById(R.id.tv2), "0"));
            itemBean = new ItemBean("", "", code, pid, print, t_type, name, unit, 0, method,
                    mesf, "", ref, 0, 0, 0, "", "", rtype, rt_value,
                    rt_value_min, 0, "", testtime, "", "", aglo, SDnum, abs1,
                    abs2, abs3, abs4, 0, 0, 0, 0, depth1, depth2, depth3, depth4,
                    0, 0, 0, 0, "", "", "", "", "", "");
        } else if ("5".equals(SDnum)) {
            float abs1 = Float.parseFloat(getTextViewText(lvHuigui.getChildAt(0).findViewById(R.id.tv3), "0"));
            float abs2 = Float.parseFloat(getTextViewText(lvHuigui.getChildAt(1).findViewById(R.id.tv3), "0"));
            float abs3 = Float.parseFloat(getTextViewText(lvHuigui.getChildAt(2).findViewById(R.id.tv3), "0"));
            float abs4 = Float.parseFloat(getTextViewText(lvHuigui.getChildAt(3).findViewById(R.id.tv3), "0"));
            float abs5 = Float.parseFloat(getTextViewText(lvHuigui.getChildAt(4).findViewById(R.id.tv3), "0"));
            float depth1 = Float.parseFloat(getTextViewText(lvHuigui.getChildAt(0).findViewById(R.id.tv2), "0"));
            float depth2 = Float.parseFloat(getTextViewText(lvHuigui.getChildAt(1).findViewById(R.id.tv2), "0"));
            float depth3 = Float.parseFloat(getTextViewText(lvHuigui.getChildAt(2).findViewById(R.id.tv2), "0"));
            float depth4 = Float.parseFloat(getTextViewText(lvHuigui.getChildAt(3).findViewById(R.id.tv2), "0"));
            float depth5 = Float.parseFloat(getTextViewText(lvHuigui.getChildAt(4).findViewById(R.id.tv2), "0"));
            itemBean = new ItemBean("", "", code, pid, print, t_type, name, unit, 0, method,
                    mesf, "", ref, 0, 0, 0, "", "", rtype, rt_value,
                    rt_value_min, 0, "", testtime, "", "", aglo, SDnum, abs1,
                    abs2, abs3, abs4, abs5, 0, 0, 0, depth1, depth2, depth3, depth4,
                    depth5, 0, 0, 0, "", "", "", "", "", "");
        } else if ("6".equals(SDnum)) {
            float abs1 = Float.parseFloat(getTextViewText(lvHuigui.getChildAt(0).findViewById(R.id.tv3), "0"));
            float abs2 = Float.parseFloat(getTextViewText(lvHuigui.getChildAt(1).findViewById(R.id.tv3), "0"));
            float abs3 = Float.parseFloat(getTextViewText(lvHuigui.getChildAt(2).findViewById(R.id.tv3), "0"));
            float abs4 = Float.parseFloat(getTextViewText(lvHuigui.getChildAt(3).findViewById(R.id.tv3), "0"));
            float abs5 = Float.parseFloat(getTextViewText(lvHuigui.getChildAt(4).findViewById(R.id.tv3), "0"));
            float abs6 = Float.parseFloat(getTextViewText(lvHuigui.getChildAt(5).findViewById(R.id.tv3), "0"));
            float depth1 = Float.parseFloat(getTextViewText(lvHuigui.getChildAt(0).findViewById(R.id.tv2), "0"));
            float depth2 = Float.parseFloat(getTextViewText(lvHuigui.getChildAt(1).findViewById(R.id.tv2), "0"));
            float depth3 = Float.parseFloat(getTextViewText(lvHuigui.getChildAt(2).findViewById(R.id.tv2), "0"));
            float depth4 = Float.parseFloat(getTextViewText(lvHuigui.getChildAt(3).findViewById(R.id.tv2), "0"));
            float depth5 = Float.parseFloat(getTextViewText(lvHuigui.getChildAt(4).findViewById(R.id.tv2), "0"));
            float depth6 = Float.parseFloat(getTextViewText(lvHuigui.getChildAt(5).findViewById(R.id.tv2), "0"));
            itemBean = new ItemBean("", "", code, pid, print, t_type, name, unit, 0, method,
                    mesf, "", ref, 0, 0, 0, "", "", rtype, rt_value,
                    rt_value_min, 0, "", testtime, "", "", aglo, SDnum, abs1,
                    abs2, abs3, abs4, abs5, abs6, 0, 0, depth1, depth2, depth3, depth4,
                    depth5, depth6, 0, 0, "", "", "", "", "", "");
        } else if ("7".equals(SDnum)) {
            float abs1 = Float.parseFloat(getTextViewText(lvHuigui.getChildAt(0).findViewById(R.id.tv3), "0"));
            float abs2 = Float.parseFloat(getTextViewText(lvHuigui.getChildAt(1).findViewById(R.id.tv3), "0"));
            float abs3 = Float.parseFloat(getTextViewText(lvHuigui.getChildAt(2).findViewById(R.id.tv3), "0"));
            float abs4 = Float.parseFloat(getTextViewText(lvHuigui.getChildAt(3).findViewById(R.id.tv3), "0"));
            float abs5 = Float.parseFloat(getTextViewText(lvHuigui.getChildAt(4).findViewById(R.id.tv3), "0"));
            float abs6 = Float.parseFloat(getTextViewText(lvHuigui.getChildAt(5).findViewById(R.id.tv3), "0"));
            float abs7 = Float.parseFloat(getTextViewText(lvHuigui.getChildAt(6).findViewById(R.id.tv3), "0"));
            float depth1 = Float.parseFloat(getTextViewText(lvHuigui.getChildAt(0).findViewById(R.id.tv2), "0"));
            float depth2 = Float.parseFloat(getTextViewText(lvHuigui.getChildAt(1).findViewById(R.id.tv2), "0"));
            float depth3 = Float.parseFloat(getTextViewText(lvHuigui.getChildAt(2).findViewById(R.id.tv2), "0"));
            float depth4 = Float.parseFloat(getTextViewText(lvHuigui.getChildAt(3).findViewById(R.id.tv2), "0"));
            float depth5 = Float.parseFloat(getTextViewText(lvHuigui.getChildAt(4).findViewById(R.id.tv2), "0"));
            float depth6 = Float.parseFloat(getTextViewText(lvHuigui.getChildAt(5).findViewById(R.id.tv2), "0"));
            float depth7 = Float.parseFloat(getTextViewText(lvHuigui.getChildAt(6).findViewById(R.id.tv2), "0"));
            itemBean = new ItemBean("", "", code, pid, print, t_type, name, unit, 0, method,
                    mesf, "", ref, 0, 0, 0, "", "", rtype, rt_value,
                    rt_value_min, 0, "", testtime, "", "", aglo, SDnum, abs1,
                    abs2, abs3, abs4, abs5, abs6, abs7, 0, depth1, depth2, depth3, depth4,
                    depth5, depth6, depth7, 0, "", "", "", "", "", "");
        } else if ("8".equals(SDnum)) {
            float abs1 = Float.parseFloat(getTextViewText(lvHuigui.getChildAt(0).findViewById(R.id.tv3), "0"));
            float abs2 = Float.parseFloat(getTextViewText(lvHuigui.getChildAt(1).findViewById(R.id.tv3), "0"));
            float abs3 = Float.parseFloat(getTextViewText(lvHuigui.getChildAt(2).findViewById(R.id.tv3), "0"));
            float abs4 = Float.parseFloat(getTextViewText(lvHuigui.getChildAt(3).findViewById(R.id.tv3), "0"));
            float abs5 = Float.parseFloat(getTextViewText(lvHuigui.getChildAt(4).findViewById(R.id.tv3), "0"));
            float abs6 = Float.parseFloat(getTextViewText(lvHuigui.getChildAt(5).findViewById(R.id.tv3), "0"));
            float abs7 = Float.parseFloat(getTextViewText(lvHuigui.getChildAt(6).findViewById(R.id.tv3), "0"));
            float abs8 = Float.parseFloat(getTextViewText(lvHuigui.getChildAt(7).findViewById(R.id.tv3), "0"));
            float depth1 = Float.parseFloat(getTextViewText(lvHuigui.getChildAt(0).findViewById(R.id.tv2), "0"));
            float depth2 = Float.parseFloat(getTextViewText(lvHuigui.getChildAt(1).findViewById(R.id.tv2), "0"));
            float depth3 = Float.parseFloat(getTextViewText(lvHuigui.getChildAt(2).findViewById(R.id.tv2), "0"));
            float depth4 = Float.parseFloat(getTextViewText(lvHuigui.getChildAt(3).findViewById(R.id.tv2), "0"));
            float depth5 = Float.parseFloat(getTextViewText(lvHuigui.getChildAt(4).findViewById(R.id.tv2), "0"));
            float depth6 = Float.parseFloat(getTextViewText(lvHuigui.getChildAt(5).findViewById(R.id.tv2), "0"));
            float depth7 = Float.parseFloat(getTextViewText(lvHuigui.getChildAt(6).findViewById(R.id.tv2), "0"));
            float depth8 = Float.parseFloat(getTextViewText(lvHuigui.getChildAt(7).findViewById(R.id.tv2), "0"));
            itemBean = new ItemBean("", "", code, pid, print, t_type, name, unit, 0, method,
                    mesf, "", ref, 0, 0, 0, "", "", rtype, rt_value,
                    rt_value_min, 0, "", testtime, "", "", aglo, SDnum, abs1,
                    abs2, abs3, abs4, abs5, abs6, abs7, abs8, depth1, depth2, depth3, depth4,
                    depth5, depth6, depth7, depth8, "", "", "", "", "", "");
        }
        session.getItemBeanDao().insertOrReplace(itemBean);
        SetHuiguiList(itemBean);
    }

    private void updateUI(ItemBean itemBean) {
        edName.setText(itemBean.getCode());
        edXuhao.setText("" + itemBean.getPrint());
        edBianhao.setText(itemBean.getPid());
        tvYangpfl.setText(itemBean.getName());
        tvJianclb.setText(itemBean.getT_type());
        tvNongd.setText(itemBean.getUnit());
        tvJieglb.setText(itemBean.getRTtype());
        tvJisff.setText(itemBean.getMethod());
        tvBoc.setText(itemBean.getMesF());
        edKongbz.setText("" + itemBean.getBlank());
        edJiancsj.setText(itemBean.getTesttime());
        tvYang.setText(itemBean.getRef().trim().split("\\|")[0]);
        tvYing.setText(itemBean.getRef().trim().split("\\|")[2]);
        edYang.setText("" + itemBean.getRTvalue());
        edYing.setText("" + itemBean.getRTvalue_min());
        tvHuigui.setText(itemBean.getAglo());
        tvShuliang.setText(itemBean.getSDNum());
    }

    @Override
    public void onClick(View v) {
        List<ItemBean> itemBeanList = session.getItemBeanDao().loadAll();
        switch (v.getId()) {
            case R.id.tv_yangpinfenlei:
                Set<String> ypfls = new HashSet<>();
                for (ItemBean itemBean : itemBeanList) {
                    ypfls.add(itemBean.getName());
                }
                IOSListDialog dialog = new IOSListDialog(getActivity());
                for (String str : ypfls) {
                    dialog.addListItem(str, getColor(R.color.c_70000000));
                }
                dialog.setOnIOSItemClickListener(new IOSListDialog.OnIOSItemClickListener() {
                    @Override
                    public void onIOSItemClick(IOSListDialog.IOSListItem data, TextView item, int position, Object tag) {
                        tvYangpfl.setText(item.getText());
                    }
                });
                dialog.show();
                break;
            case R.id.tv_jianceleibie:
                Set<String> types = new HashSet<>();
                for (ItemBean itemBean : itemBeanList) {
                    types.add(itemBean.getT_type());
                }
                IOSListDialog dialog1 = new IOSListDialog(getActivity());
                for (String str : types) {
                    dialog1.addListItem(str, getColor(R.color.c_70000000));
                }
                dialog1.setOnIOSItemClickListener(new IOSListDialog.OnIOSItemClickListener() {
                    @Override
                    public void onIOSItemClick(IOSListDialog.IOSListItem data, TextView item, int position, Object tag) {
                        tvJianclb.setText(item.getText());
                    }
                });
                dialog1.show();
                break;
            case R.id.tv_nongdu:
                Set<String> nongdus = new HashSet<>();
                for (ItemBean itemBean : itemBeanList) {
                    nongdus.add(itemBean.getUnit());
                }
                IOSListDialog dialog2 = new IOSListDialog(getActivity());
                for (String str : nongdus) {
                    dialog2.addListItem(str, getColor(R.color.c_70000000));
                }
                dialog2.setOnIOSItemClickListener(new IOSListDialog.OnIOSItemClickListener() {
                    @Override
                    public void onIOSItemClick(IOSListDialog.IOSListItem data, TextView item, int position, Object tag) {
                        tvNongd.setText(item.getText());
                    }
                });
                dialog2.show();
                break;
            case R.id.tv_jieguoleibie:
                Set<String> jieguos = new HashSet<>();
                for (ItemBean itemBean : itemBeanList) {
                    jieguos.add(itemBean.getRTtype());
                }
                IOSListDialog dialog3 = new IOSListDialog(getActivity());
                for (String str : jieguos) {
                    dialog3.addListItem(str, getColor(R.color.c_70000000));
                }
                dialog3.setOnIOSItemClickListener(new IOSListDialog.OnIOSItemClickListener() {
                    @Override
                    public void onIOSItemClick(IOSListDialog.IOSListItem data, TextView item, int position, Object tag) {
                        tvJieglb.setText(item.getText());
                    }
                });
                dialog3.show();
                break;
            case R.id.tv_jisuanfangfa:
                Set<String> jisuanffs = new HashSet<>();
                for (ItemBean itemBean : itemBeanList) {
                    jisuanffs.add(itemBean.getMethod());
                }
                IOSListDialog dialog4 = new IOSListDialog(getActivity());
                for (String str : jisuanffs) {
                    dialog4.addListItem(str, getColor(R.color.c_70000000));
                }
                dialog4.setOnIOSItemClickListener(new IOSListDialog.OnIOSItemClickListener() {
                    @Override
                    public void onIOSItemClick(IOSListDialog.IOSListItem data, TextView item, int position, Object tag) {
                        tvJisff.setText(item.getText());
                    }
                });
                dialog4.show();
                break;
            case R.id.tv_bochang:
                Set<String> bochangs = new HashSet<>();
                for (ItemBean itemBean : itemBeanList) {
                    bochangs.add(itemBean.getMesF());
                }
                IOSListDialog dialog5 = new IOSListDialog(getActivity());
                for (String str : bochangs) {
                    dialog5.addListItem(str, getColor(R.color.c_70000000));
                }
                dialog5.setOnIOSItemClickListener(new IOSListDialog.OnIOSItemClickListener() {
                    @Override
                    public void onIOSItemClick(IOSListDialog.IOSListItem data, TextView item, int position, Object tag) {
                        tvBoc.setText(item.getText());
                    }
                });
                dialog5.show();
                break;
            case R.id.tv_yang:
                String[] arr = {"阴性", "可疑", "阳性"};
                IOSListDialog dialog6 = new IOSListDialog(getActivity());
                for (String str : arr) {
                    dialog6.addListItem(str, getColor(R.color.c_70000000));
                }
                dialog6.setOnIOSItemClickListener(new IOSListDialog.OnIOSItemClickListener() {
                    @Override
                    public void onIOSItemClick(IOSListDialog.IOSListItem data, TextView item, int position, Object tag) {
                        tvYang.setText(item.getText());
                    }
                });
                dialog6.show();
                break;
            case R.id.tv_ying:
                String[] arr2 = {"阴性", "可疑", "阳性"};
                IOSListDialog dialog7 = new IOSListDialog(getActivity());
                for (String str : arr2) {
                    dialog7.addListItem(str, getColor(R.color.c_70000000));
                }
                dialog7.setOnIOSItemClickListener(new IOSListDialog.OnIOSItemClickListener() {
                    @Override
                    public void onIOSItemClick(IOSListDialog.IOSListItem data, TextView item, int position, Object tag) {
                        tvYing.setText(item.getText());
                    }
                });
                dialog7.show();
                break;
            case R.id.tv_huigui:
                String[] arr4 = {"单点定标", "折线回归", "线性回归", "对数回归", "双对数回归", "指数回归", "幂回归"};
                IOSListDialog dialog8 = new IOSListDialog(getActivity());
                for (String str : arr4) {
                    dialog8.addListItem(str, getColor(R.color.c_70000000));
                }
                dialog8.setOnIOSItemClickListener(new IOSListDialog.OnIOSItemClickListener() {
                    @Override
                    public void onIOSItemClick(IOSListDialog.IOSListItem data, TextView item, int position, Object tag) {
                        tvHuigui.setText(item.getText());
                    }
                });
                dialog8.show();
                break;
            case R.id.tv_shuliang:
                String[] arr3 = {"1", "2", "3", "4", "5", "6", "7", "8"};
                IOSListDialog dialog9 = new IOSListDialog(getActivity());
                for (String str : arr3) {
                    dialog9.addListItem(str, getColor(R.color.c_70000000));
                }
                dialog9.setOnIOSItemClickListener(new IOSListDialog.OnIOSItemClickListener() {
                    @Override
                    public void onIOSItemClick(IOSListDialog.IOSListItem data, TextView item, int position, Object tag) {
                        tvShuliang.setText(item.getText());
                    }
                });
                dialog9.show();
                break;
        }
    }

    private class XianMuAdapter extends UltimateRecycleAdapter<ItemBean> {
        public XianMuAdapter(Context context, List<ItemBean> datum, int resItemID) {
            super(context, datum, resItemID);
        }

        @Override
        protected void convert(ItemBean itemBean, UltimateRecycleHolder holder, int position) {
            holder.setText(R.id.tv1, itemBean.getPrint());
            holder.setText(R.id.tv2, itemBean.getCode());
            holder.setText(R.id.tv3, itemBean.getName());
            holder.setText(R.id.tv4, itemBean.getT_type());
            if (itemPostion == position) {
                holder.setBackgroundColor(R.id.lin_xiangmu_content, getColor(R.color.android_holo_blue));
            } else {
                holder.setBackgroundColor(R.id.lin_xiangmu_content, getColor(R.color.white));
            }

        }
    }

    private class HuiguiAdapter extends UltimateRecycleAdapter<HuiGuiBean> {
        public HuiguiAdapter(Context context, List<HuiGuiBean> datum, int resItemID) {
            super(context, datum, resItemID);
        }

        @Override
        protected void convert(HuiGuiBean huiGuiBean, UltimateRecycleHolder holder, int position) {
            holder.setText(R.id.tv1, huiGuiBean.getNum());
            holder.setText(R.id.tv2, huiGuiBean.getNongdu());
            holder.setText(R.id.tv3, huiGuiBean.getXiguangdu());
        }
    }
}
