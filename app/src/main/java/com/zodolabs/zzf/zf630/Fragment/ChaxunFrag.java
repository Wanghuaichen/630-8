package com.zodolabs.zzf.zf630.Fragment;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bill.ultimatefram.ui.UltimateFragment;
import com.bill.ultimatefram.util.ExternalFileHelper;
import com.bill.ultimatefram.util.JsonFormat;
import com.bill.ultimatefram.view.recycleview.UltimateItemDecoration;
import com.bill.ultimatefram.view.recycleview.adapter.UltimateRecycleAdapter;
import com.bill.ultimatefram.view.recycleview.adapter.UltimateRecycleHolder;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.MPPointF;
import com.zodolabs.zzf.zf630.App;
import com.zodolabs.zzf.zf630.Beans.normal.JianceBean;
import com.zodolabs.zzf.zf630.R;
import com.zodolabs.zzf.zf630.Utils.DateUtils;
import com.zodolabs.zzf.zf630.greendao.gen.DaoSession;
import com.zodolabs.zzf.zf630.greendao.gen.JianceBeanDao;

import org.greenrobot.greendao.query.QueryBuilder;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okio.BufferedSink;
import okio.Okio;

/**
 * Created by zzf on 17-12-6.
 */

public class ChaxunFrag extends UltimateFragment implements View.OnClickListener, RadioGroup.OnCheckedChangeListener, OnChartValueSelectedListener {
    RecyclerView lv;
    EditText edypbh, edjcxm, edypmc, edlydw;
    RadioGroup radioGrouptime, radioGroupdx, radioGroupscbz;
    TextView tvtimestart, tvtimeend;
    Button btnall, btnchaxun, btntongji, btndel, btndaochu, btnupload, btnback;

    List<JianceBean> jianceBeanList;
    ChaxunAdapter chaxunAdapter;
    DaoSession session;

    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    int curpostion;
    String dxjg = "全部", scbz = "2"; //定性结果，上传标志
    private Typeface mTfRegular, mTfLight;

    protected String[] mParties = new String[]{
            "合格", "可疑", "不合格"
    };

    @Override
    protected int setContentView() {
        return R.layout.frag_chaxun;
    }

    @Override
    protected void initView() {
        getFlexibleBar().setVisibility(View.GONE);
        lv = findViewById(R.id.lv_chaxun);
        edypbh = findViewById(R.id.tv_ypbh_v);
        edjcxm = findViewById(R.id.tv_jcxm_v);
        edypmc = findViewById(R.id.tv_ypmc_v);
        edlydw = findViewById(R.id.tv_lydw_v);
        radioGroupdx = findViewById(R.id.radiogroup_dx);
        radioGrouptime = findViewById(R.id.radiogroup_time);
        radioGroupscbz = findViewById(R.id.radiogroup_scbj);
        tvtimestart = findViewById(R.id.tv_time_start);
        tvtimeend = findViewById(R.id.tv_time_end);
        btnall = findViewById(R.id.btn_all);
        btnchaxun = findViewById(R.id.btn_chaxun);
        btntongji = findViewById(R.id.btn_tongji);
        btndel = findViewById(R.id.btn_del);
        btndaochu = findViewById(R.id.btn_daochu);
        btnupload = findViewById(R.id.btn_upload);
        btnback = findViewById(R.id.btn_back);
    }

    @Override
    protected void initEvent(Bundle savedInstanceState) {
        jianceBeanList = new ArrayList<>();
        session = App.getInstance().getDaosession();
        session.clear();
        lv.setLayoutManager(new LinearLayoutManager(getActivity()));
        lv.addItemDecoration(new UltimateItemDecoration(getActivity(), RecyclerView.VERTICAL, 0.5f, getColor(R.color.c_70000000)));
        lv.setItemAnimator(new DefaultItemAnimator());
        lv.getItemAnimator().setChangeDuration(0);
        lv.setAdapter(chaxunAdapter = new ChaxunAdapter(getActivity(), jianceBeanList, R.layout.chaxun_item));
        chaxunAdapter.setOnItemClickListener(new UltimateRecycleAdapter.OnItemClickListener<JianceBean>() {
            @Override
            public void onRecycleItemClickListener(JianceBean jianceBean, View view, int position, long id, int type) {
                chaxunAdapter.notifyItemChanged(curpostion);
                curpostion = position;
                chaxunAdapter.notifyItemChanged(curpostion);
            }
        });
        tvtimestart.setText(dateFormat.format(DateUtils.getDayBegin()));
        tvtimeend.setText(dateFormat.format(DateUtils.getDayEnd()));
        tvtimestart.setTag(DateUtils.getDayBegin());
        tvtimeend.setTag(DateUtils.getDayEnd());
        btnall.setOnClickListener(this);
        btnchaxun.setOnClickListener(this);
        btntongji.setOnClickListener(this);
        btndel.setOnClickListener(this);
        btndaochu.setOnClickListener(this);
        btnupload.setOnClickListener(this);
        btnback.setOnClickListener(this);
        tvtimestart.setOnClickListener(this);
        tvtimeend.setOnClickListener(this);
        radioGroupscbz.setOnCheckedChangeListener(this);
        radioGroupdx.setOnCheckedChangeListener(this);
        radioGrouptime.setOnCheckedChangeListener(this);
        getalldata();
    }

    private void getalldata() {
        Observable.create(new ObservableOnSubscribe<List<JianceBean>>() {
            @Override
            public void subscribe(ObservableEmitter<List<JianceBean>> e) throws Exception {
                e.onNext(doQuery());
                e.onComplete();
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<JianceBean>>() {
                    @Override
                    public void accept(List<JianceBean> jianceBeans) throws Exception {
                        jianceBeanList.clear();
                        jianceBeanList.addAll(jianceBeans);
                        chaxunAdapter.notifyDataSetChanged();
                        for(JianceBean jianceBean:jianceBeans){
                            Log.d(TAG,"jiancebeans::"+jianceBean.toString());
                        }
                    }
                });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_all:
                getalldata();
                break;
            case R.id.btn_chaxun:
                Observable.create(new ObservableOnSubscribe<List<JianceBean>>() {
                    @Override
                    public void subscribe(ObservableEmitter<List<JianceBean>> e) throws Exception {
                        e.onNext(doQuery());
                        e.onComplete();
                    }
                })
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<List<JianceBean>>() {
                            @Override
                            public void accept(List<JianceBean> jianceBeans) throws Exception {
                                jianceBeanList.clear();
                                jianceBeanList.addAll(jianceBeans);
                                chaxunAdapter.notifyDataSetChanged();
                            }
                        });
                break;
            case R.id.btn_tongji:
                tongji();
                break;
            case R.id.btn_del:
                try {
                    Long ypbh = chaxunAdapter.getItem(curpostion).getYpbh();
                    session.getJianceBeanDao().deleteByKey(ypbh);
                    getalldata();
                }catch (NullPointerException e){
                    toast("请选择要删除的项目！");
                }
                break;
            case R.id.btn_daochu:
                daochu();
                break;
            case R.id.btn_upload:
                JianceBean data = chaxunAdapter.getItem(curpostion);
                upload(data);
                break;
            case R.id.btn_back:
                onLeftClickListener();
                break;
            case R.id.tv_time_start:
                setDate(tvtimestart);
                break;
            case R.id.tv_time_end:
                setDate(tvtimeend);
                break;
        }
    }

    private void upload(final JianceBean data) {
        if(!data.getUpload().equals("0")){
            toast("请不要重复上传！");
            return;
        }
        OkHttpClient mOkHttpClient = new OkHttpClient();
        RequestBody formBody = new FormBody.Builder()
                .add("采样编号", String.valueOf(data.getYpbh()))
                .add("检测项目", data.getJcxm())
                .add("检测方法", "分光光度")
                .add("标准值", "0.12")
                .add("结果值", data.getJcz())
                .add("检测结果", data.getJcjg())
                .add("_pw", App.getInstance().getPassword())
                .add("_检测员用户ID", App.getInstance().getUsername())
                .add("检测时间", format.format(data.getDate()))
                .build();
        Request request = new Request.Builder()
                .url("http://58.215.219.74:8083/zodobase/do/anon.api.UploadJiance")
                .post(formBody)
                .build();
        Call call = mOkHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.i(TAG, e.getMessage());
                toast("请检查网络连接！");
            }

            @Override
            public void onResponse(Call call, okhttp3.Response response) throws IOException {
                final String str = response.body().string();
                Log.i(TAG, str);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Map<String, Object> map = JsonFormat.formatJson(str, new String[]{"code", "message", "body"});
                        if (map.get("code").equals(200)) {
                            Toast.makeText(getApplicationContext(), "上传成功！", Toast.LENGTH_SHORT).show();
                            JianceBean jianceBean = data;
                            jianceBean.setUpload("1");
                            session.getJianceBeanDao().update(jianceBean);
                            chaxunAdapter.notifyItemChanged(curpostion);
                        } else {
                            if (map.get("message") != null) {
                                toast(map.get("message").toString());
                            }
                        }
                    }
                });
            }
        });
    }

    private void tongji() {
        List<JianceBean> listdata = chaxunAdapter.getAdapterData();
        Dialog dialog = new Dialog(getActivity());
        dialog.setTitle("统计图(合格率)");
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_chart, null, false);
        setBarChart(view);
        dialog.setContentView(view);
        dialog.show();
        dialog.setCanceledOnTouchOutside(true);
    }

    private void setBarChart(View view) {
        mTfRegular = Typeface.createFromAsset(getActivity().getAssets(), "OpenSans-Regular.ttf");
        mTfLight = Typeface.createFromAsset(getActivity().getAssets(), "OpenSans-Light.ttf");
        PieChart mChart = view.findViewById(R.id.pie_chat);
        mChart.setUsePercentValues(true);
        mChart.getDescription().setEnabled(false);
        mChart.setExtraOffsets(5, 10, 5, 5);

        mChart.setDragDecelerationFrictionCoef(0.95f);

        mChart.setCenterText(generateCenterSpannableText());

        mChart.setDrawHoleEnabled(true);
        mChart.setHoleColor(Color.WHITE);

        mChart.setTransparentCircleColor(Color.WHITE);
        mChart.setTransparentCircleAlpha(110);

        mChart.setHoleRadius(58f);
        mChart.setTransparentCircleRadius(61f);

        mChart.setDrawCenterText(true);

        mChart.setRotationAngle(0);
        // enable rotation of the chart by touch
        mChart.setRotationEnabled(true);
        mChart.setHighlightPerTapEnabled(true);

        // mChart.setUnit(" €");
        // mChart.setDrawUnitsInChart(true);

        // add a selection listener
        mChart.setOnChartValueSelectedListener(this);

        setData(mChart, 3, 100);

        mChart.animateY(1400, Easing.EasingOption.EaseInOutQuad);
        // mChart.spin(2000, 0, 360);

        Legend l = mChart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        l.setOrientation(Legend.LegendOrientation.VERTICAL);
        l.setDrawInside(false);
        l.setXEntrySpace(7f);
        l.setYEntrySpace(0f);
        l.setYOffset(0f);

        // entry label styling
        mChart.setEntryLabelColor(Color.WHITE);
        mChart.setEntryLabelTypeface(mTfRegular);
        mChart.setEntryLabelTextSize(12f);
    }

    private SpannableString generateCenterSpannableText() {

        SpannableString s = new SpannableString("样品合格率\n检测日期：" + tvtimestart.getText().toString() + "～" + tvtimeend.getText().toString());
        s.setSpan(new RelativeSizeSpan(1.7f), 0, 5, 0);
        s.setSpan(new StyleSpan(Typeface.NORMAL), 5, 11, 0);
        s.setSpan(new ForegroundColorSpan(Color.GRAY), 5, 11, 0);
        s.setSpan(new RelativeSizeSpan(1.1f), 5, 11, 0);
        s.setSpan(new StyleSpan(Typeface.ITALIC), 11, s.length(), 0);
        s.setSpan(new ForegroundColorSpan(ColorTemplate.getHoloBlue()), 11, s.length(), 0);
        return s;
    }

    private void setData(PieChart mChart, int count, float range) {

        float mult = range;

        ArrayList<PieEntry> entries = new ArrayList<PieEntry>();

        // NOTE: The order of the entries when being added to the entries array determines their position around the center of
        // the chart.
    /*    for (int i = 0; i < count; i++) {
            entries.add(new PieEntry((float) ((Math.random() * mult) + mult / 5),
                    mParties[i % mParties.length],
                    getResources().getDrawable(R.mipmap.star)));
        }*/
        List<JianceBean> datalist = chaxunAdapter.getAdapterData();
        float total = datalist.size();
        float hege = 0, keyi = 0, buhege = 0;
        for (JianceBean jianceBean : datalist) {
            if (jianceBean.getJcjg().equals("合格")) {
                hege = hege + 1;
            } else if (jianceBean.getJcjg().equals("可疑")) {
                keyi = keyi + 1;
            } else if (jianceBean.getJcjg().equals("不合格")) {
                buhege = buhege + 1;
            }
        }

        entries.add(new PieEntry(hege / total, mParties[0], getResources().getDrawable(R.mipmap.star)));
        entries.add(new PieEntry(keyi / total, mParties[1], getResources().getDrawable(R.mipmap.star)));
        entries.add(new PieEntry(buhege / total, mParties[2], getResources().getDrawable(R.mipmap.star)));

        PieDataSet dataSet = new PieDataSet(entries, "合格率 %");

        dataSet.setDrawIcons(false);

        dataSet.setSliceSpace(3f);
        dataSet.setIconsOffset(new MPPointF(0, 40));
        dataSet.setSelectionShift(5f);

        // add a lot of colors

        ArrayList<Integer> colors = new ArrayList<Integer>();

        for (int c : ColorTemplate.VORDIPLOM_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.JOYFUL_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.COLORFUL_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.LIBERTY_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.PASTEL_COLORS)
            colors.add(c);

        colors.add(ColorTemplate.getHoloBlue());

        dataSet.setColors(colors);
        //dataSet.setSelectionShift(0f);

        PieData data = new PieData(dataSet);
        data.setValueFormatter(new PercentFormatter());
        data.setValueTextSize(11f);
        data.setValueTextColor(Color.WHITE);
        data.setValueTypeface(mTfLight);
        mChart.setData(data);

        // undo all highlights
        mChart.highlightValues(null);

        mChart.invalidate();
    }

    private void daochu() {
        ProgressDialog dialog = new ProgressDialog(getActivity());
        dialog.show();
        File folder = ExternalFileHelper.getStorageDirectory(true, ExternalFileHelper.STORAGE_TYPE.CUSTOM, "导出文件");
        String path = folder.getPath() + File.separator + "output.csv";
        try {
            BufferedSink sink = Okio.buffer(Okio.sink(new File(path)));
            List<JianceBean> list = chaxunAdapter.getAdapterData();
            for (JianceBean jianceBean : list) {
                sink.writeUtf8(jianceBean.toString());
            }
            sink.flush();
            sink.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        dialog.dismiss();
        Toast.makeText(getActivity(), "导出成功！导出目录：" + path, Toast.LENGTH_LONG).show();
    }

    private void setDate(final TextView textView) {
        try {
            Date date = DateUtils.getDateFromString(textView.getText().toString(), "yyyy-MM-dd");
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    Calendar c = Calendar.getInstance();
                    c.set(year, month, dayOfMonth);
                    textView.setText(dateFormat.format(c.getTime()));
                    textView.setTag(c.getTime());
                }
            }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE)).show();
        } catch (ParseException e) {
            e.printStackTrace();
            toast("日期解析错误");
        }
    }

    private List<JianceBean> doQuery() {
        QueryBuilder<JianceBean> builder = session.getJianceBeanDao().queryBuilder();
        if (!TextUtils.isEmpty(edypbh.getText())) {
            builder.where(JianceBeanDao.Properties.Ypbh.like("%" + edypbh.getText().toString() + "%"));
        }
        if (!TextUtils.isEmpty(edjcxm.getText())) {
            builder.where(JianceBeanDao.Properties.Jcxm.like("%" + edjcxm.getText().toString() + "%"));
        }
        if (!TextUtils.isEmpty(edypmc.getText())) {
            builder.where(JianceBeanDao.Properties.Ypmc.like("%" + edypmc.getText().toString() + "%"));
        }
        if (!TextUtils.isEmpty(edlydw.getText())) {
            builder.where(JianceBeanDao.Properties.Lydw.like("%" + edlydw.getText().toString() + "%"));
        }
        if (dxjg.equals("合格") || dxjg.equals("不合格")) {
            builder.where(JianceBeanDao.Properties.Jcjg.eq(dxjg));
        }
        if (scbz.equals("0") || scbz.equals("1")) {
            builder.where(JianceBeanDao.Properties.Upload.eq(scbz));
        }
        List<JianceBean> list = builder.where(
                builder.and(JianceBeanDao.Properties.Date.lt((Date) tvtimeend.getTag()),
                        JianceBeanDao.Properties.Date.ge((Date) tvtimestart.getTag())
                )
        ).orderAsc(JianceBeanDao.Properties.Ypbh).list();
        return list;
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (group.getId()) {
            case R.id.radiogroup_time:
                if (checkedId == R.id.rb_day) {
                    tvtimestart.setText(dateFormat.format(DateUtils.getDayBegin()));
                    tvtimeend.setText(dateFormat.format(DateUtils.getDayEnd()));
                    tvtimestart.setTag(DateUtils.getDayBegin());
                    tvtimeend.setTag(DateUtils.getDayEnd());
                } else if (checkedId == R.id.rb_week) {
                    tvtimestart.setText(dateFormat.format(DateUtils.getBeginDayOfWeek()));
                    tvtimeend.setText(dateFormat.format(DateUtils.getEndDayOfWeek()));
                    tvtimestart.setTag(DateUtils.getBeginDayOfWeek());
                    tvtimeend.setTag(DateUtils.getEndDayOfWeek());
                } else if (checkedId == R.id.rb_month) {
                    tvtimestart.setText(dateFormat.format(DateUtils.getBeginDayOfMonth()));
                    tvtimeend.setText(dateFormat.format(DateUtils.getEndDayOfMonth()));
                    tvtimestart.setTag(DateUtils.getBeginDayOfMonth());
                    tvtimeend.setTag(DateUtils.getEndDayOfMonth());
                } else if (checkedId == R.id.rb_year) {
                    tvtimestart.setText(dateFormat.format(DateUtils.getBeginDayOfYear()));
                    tvtimeend.setText(dateFormat.format(DateUtils.getEndDayOfYear()));
                    tvtimestart.setTag(DateUtils.getBeginDayOfYear());
                    tvtimeend.setTag(DateUtils.getEndDayOfYear());
                }
                break;
            case R.id.radiogroup_dx:
                if (checkedId == R.id.radio_qb) {
                    dxjg = "全部";
                } else if (checkedId == R.id.radio_hg) {
                    dxjg = "合格";
                } else if (checkedId == R.id.radio_bhg) {
                    dxjg = "不合格";
                }
                break;
            case R.id.radiogroup_scbj:
                if (checkedId == R.id.radio_all) {
                    scbz = "2";  //全部
                } else if (checkedId == R.id.radio_ysc) {
                    scbz = "1";  //已上传
                } else if (checkedId == R.id.radio_wsc) {
                    scbz = "0";  //未上传
                }
                break;
        }
    }

    @Override
    public void onValueSelected(Entry e, Highlight h) {
        if (e == null)
            return;
        Log.i("VAL SELECTED",
                "Value: " + e.getY() + ", index: " + h.getX()
                        + ", DataSet index: " + h.getDataSetIndex());
    }

    @Override
    public void onNothingSelected() {
        Log.i("PieChart", "nothing selected");
    }

    private class ChaxunAdapter extends UltimateRecycleAdapter<JianceBean> {
        public ChaxunAdapter(Context context, List<JianceBean> datum, int resItemID) {
            super(context, datum, resItemID);
        }

        @Override
        protected void convert(JianceBean jianceBean, UltimateRecycleHolder holder, int position) {
            Log.d(TAG, "" + jianceBean.getYpbh());
            holder.setText(R.id.item_ypbh, jianceBean.getYpbh());
            holder.setText(R.id.item_ypmc, jianceBean.getYpmc());
            holder.setText(R.id.item_ypfl, jianceBean.getYpfl());
            holder.setText(R.id.item_jcxm, jianceBean.getJcxm());
            holder.setText(R.id.item_ypcd, jianceBean.getYpcd());
            holder.setText(R.id.item_lydw, jianceBean.getLydw());
            holder.setText(R.id.item_jcrq, dateFormat.format(jianceBean.getDate()));
            holder.setText(R.id.item_xgd, jianceBean.getXgd());
            holder.setText(R.id.item_jcz, jianceBean.getJcz());
            holder.setText(R.id.item_jcjg, jianceBean.getJcjg());
            holder.setText(R.id.item_upload, jianceBean.getUpload().equals("0") ? "否" : "是");
            if (position == curpostion) {
                holder.setBackgroundColor(R.id.lin_chaxun, getColor(R.color.android_holo_blue));
            } else {
                holder.setBackgroundColor(R.id.lin_chaxun, getColor(R.color.white));
            }
        }
    }
}
