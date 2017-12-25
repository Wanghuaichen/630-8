package com.zodolabs.zzf.zf630.Fragment;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.bill.ultimatefram.ui.UltimateFragment;
import com.bill.ultimatefram.view.recycleview.UltimateItemDecoration;
import com.bill.ultimatefram.view.recycleview.adapter.UltimateRecycleAdapter;
import com.bill.ultimatefram.view.recycleview.adapter.UltimateRecycleHolder;
import com.zodolabs.zzf.zf630.App;
import com.zodolabs.zzf.zf630.Beans.db.Concentration;
import com.zodolabs.zzf.zf630.Beans.db.Dictionary;
import com.zodolabs.zzf.zf630.Beans.db.people;
import com.zodolabs.zzf.zf630.Beans.db.place;
import com.zodolabs.zzf.zf630.Beans.db.tanwei;
import com.zodolabs.zzf.zf630.Beans.db.type;
import com.zodolabs.zzf.zf630.Beans.db.unit;
import com.zodolabs.zzf.zf630.Beans.db.xianzhi;
import com.zodolabs.zzf.zf630.R;
import com.zodolabs.zzf.zf630.greendao.gen.DaoSession;

import java.util.List;

/**
 * Created by zzf on 17-12-6.
 */

public class ZidianFrag extends UltimateFragment implements View.OnClickListener {
    TextView tv1, tv2;
    RadioGroup radioGroup;
    RecyclerView lv;
    Button btnSave, btnDel, btnBack;
    TextView tvName, tvType;
    EditText edNum, edName, edType;
    LinearLayout linType;

    List<Dictionary> listdicts;
    List<place> listplaces;
    List<unit> listunits;
    List<people> listpeoples;
    List<type> listtypes;
    List<Concentration> listcons;
    List<xianzhi> listxianzhi;
    List<tanwei> listtanwei;
    NameAdapter nameAdapter;
    PlaceAdapter placeAdapter;
    UnitAdapter unitAdapter;
    PeopleAdapter peopleAdapter;
    TypeAdapter typeAdapter;
    ConAdapter conAdapter;
    XianAdapter xianAdapter;
    TanweiAdapter tanweiAdapter;

    int checkNum, checkNum2, checkNum3, checkNum4, checkNum5, checkNum6, checkNum7, checkNum8; //单选item的确定位置
    //    int[] radioIds = {R.id.samplename, R.id.sampleplace, R.id.unit, R.id.people, R.id.type, R.id.concentration, R.id.limited, R.id.tanwei};
//    UltimateRecycleAdapter[] adapters = {(UltimateRecycleAdapter) nameAdapter, (UltimateRecycleAdapter) placeAdapter, (UltimateRecycleAdapter) unitAdapter, (UltimateRecycleAdapter) peopleAdapter, (UltimateRecycleAdapter) typeAdapter, (UltimateRecycleAdapter) conAdapter, (UltimateRecycleAdapter) xianAdapter, (UltimateRecycleAdapter) tanweiAdapter};
    private DaoSession daoSession;

    @Override
    protected int setContentView() {
        return R.layout.frag_zidian;
    }

    @Override
    protected void initView() {
        getFlexibleBar().setVisibility(View.GONE);
        lv = findViewById(R.id.lv);
        tv1 = findViewById(R.id.tv1);
        tv2 = findViewById(R.id.tv2);
        radioGroup = findViewById(R.id.radiogroup);
        btnSave = findViewById(R.id.btn_save);
        btnDel = findViewById(R.id.btn_del);
        btnBack = findViewById(R.id.btn_back);
        tvName = findViewById(R.id.tv_name);
        tvType = findViewById(R.id.tv_type);
        edNum = findViewById(R.id.edit_num);
        edName = findViewById(R.id.edit_name);
        edType = findViewById(R.id.edit_type);
        linType = findViewById(R.id.lin_type);
        tv1.setText("序号");
        tv2.setText("样品名称");
    }

    @Override
    protected void initEvent(Bundle savedInstanceState) {
        initButtonClick();
        initListView();
        setRadioGroup();
    }

    private void initButtonClick() {
        btnSave.setOnClickListener(this);
        btnDel.setOnClickListener(this);
        btnBack.setOnClickListener(this);
    }

    private void initListView() {
        lv.setLayoutManager(new LinearLayoutManager(getActivity()));
        daoSession = App.getInstance().getDaosession();
        listdicts = daoSession.getDictionaryDao().loadAll();
        lv.addItemDecoration(new UltimateItemDecoration(getActivity(), RecyclerView.VERTICAL, 0.5f, Color.parseColor("#700000")));
        lv.setAdapter(nameAdapter = new NameAdapter(getActivity(), listdicts, R.layout.dic_item));
        nameAdapter.setOnItemClickListener(new UltimateRecycleAdapter.OnItemClickListener<Dictionary>() {
            @Override
            public void onRecycleItemClickListener(Dictionary dictionary, View view, int position, long id, int type) {
                checkNum = position;
                edNum.setText(String.valueOf(dictionary.getNo()));
                edName.setText(dictionary.getContent());
                edType.setText(dictionary.getBelong());
                nameAdapter.notifyDataSetChanged();
            }
        });
    }

    private void setRadioGroup() {
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.samplename:
                        tv2.setText("样品名称");
                        tvName.setText("样品名称");
                        tvType.setText("样品类型");
                        edNum.setText("");
                        edName.setText("");
                        edType.setText("");
                        linType.setVisibility(View.VISIBLE);
                        lv.setAdapter(nameAdapter);
                        break;
                    case R.id.sampleplace:
                        tv2.setText("样品产地");
                        tvName.setText("样品产地");
                        edNum.setText("");
                        edName.setText("");
                        linType.setVisibility(View.GONE);
                        listplaces = daoSession.getPlaceDao().loadAll();
                        lv.setAdapter(placeAdapter = new PlaceAdapter(getActivity(), listplaces, R.layout.dic_item));
                        placeAdapter.setOnItemClickListener(new UltimateRecycleAdapter.OnItemClickListener<place>() {
                            @Override
                            public void onRecycleItemClickListener(place place, View view, int position, long id, int type) {
                                checkNum2 = position;
                                edNum.setText(String.valueOf(place.getId()));
                                edName.setText(place.getName());
                                placeAdapter.notifyDataSetChanged();
                            }
                        });
                        break;
                    case R.id.unit:
                        tv2.setText("送检单位");
                        tvName.setText("送检单位");
                        edNum.setText("");
                        edName.setText("");
                        linType.setVisibility(View.GONE);
                        listunits = daoSession.getUnitDao().loadAll();
                        lv.setAdapter(unitAdapter = new UnitAdapter(getActivity(), listunits, R.layout.dic_item));
                        unitAdapter.setOnItemClickListener(new UltimateRecycleAdapter.OnItemClickListener<unit>() {
                            @Override
                            public void onRecycleItemClickListener(unit unit, View view, int position, long id, int type) {
                                checkNum3 = position;
                                edNum.setText(String.valueOf(unit.getId()));
                                edName.setText(unit.getName());
                                unitAdapter.notifyDataSetChanged();
                            }
                        });
                        break;
                    case R.id.people:
                        tv2.setText("检测人员");
                        tvName.setText("检测人员");
                        edNum.setText("");
                        edName.setText("");
                        linType.setVisibility(View.GONE);
                        listpeoples = daoSession.getPeopleDao().loadAll();
                        lv.setAdapter(peopleAdapter = new PeopleAdapter(getActivity(), listpeoples, R.layout.dic_item));
                        peopleAdapter.setOnItemClickListener(new UltimateRecycleAdapter.OnItemClickListener<people>() {
                            @Override
                            public void onRecycleItemClickListener(people people, View view, int position, long id, int type) {
                                checkNum4 = position;
                                edNum.setText(String.valueOf(people.getId()));
                                edName.setText(people.getName());
                                peopleAdapter.notifyDataSetChanged();
                            }
                        });
                        break;
                    case R.id.type:
                        tv2.setText("样品类型");
                        tvName.setText("样品类型");
                        edNum.setText("");
                        edName.setText("");
                        linType.setVisibility(View.GONE);
                        listtypes = daoSession.getTypeDao().loadAll();
                        lv.setAdapter(typeAdapter = new TypeAdapter(getActivity(), listtypes, R.layout.dic_item));
                        typeAdapter.setOnItemClickListener(new UltimateRecycleAdapter.OnItemClickListener<type>() {
                            @Override
                            public void onRecycleItemClickListener(type type, View view, int position, long id, int type2) {
                                checkNum5 = position;
                                edNum.setText(String.valueOf(type.getId()));
                                edName.setText(type.getTypename());
                                typeAdapter.notifyDataSetChanged();
                            }
                        });
                        break;
                    case R.id.concentration:
                        tv2.setText("浓度单位");
                        tvName.setText("浓度单位");
                        edNum.setText("");
                        edName.setText("");
                        linType.setVisibility(View.GONE);
                        listcons = daoSession.getConcentrationDao().loadAll();
                        lv.setAdapter(conAdapter = new ConAdapter(getActivity(), listcons, R.layout.dic_item));
                        conAdapter.setOnItemClickListener(new UltimateRecycleAdapter.OnItemClickListener<Concentration>() {
                            @Override
                            public void onRecycleItemClickListener(Concentration concentration, View view, int position, long id, int type) {
                                checkNum6 = position;
                                edNum.setText(String.valueOf(concentration.getId()));
                                edName.setText(concentration.getName());
                                conAdapter.notifyDataSetChanged();
                            }
                        });
                        break;
                    case R.id.limited:
                        tv2.setText("检测限值");
                        tvName.setText("检测限值");
                        edNum.setText("");
                        edName.setText("");
                        linType.setVisibility(View.GONE);
                        listxianzhi = daoSession.getXianzhiDao().loadAll();
                        lv.setAdapter(xianAdapter = new XianAdapter(getActivity(), listxianzhi, R.layout.dic_item));
                        xianAdapter.setOnItemClickListener(new UltimateRecycleAdapter.OnItemClickListener<xianzhi>() {
                            @Override
                            public void onRecycleItemClickListener(xianzhi xianzhi, View view, int position, long id, int type) {
                                checkNum7 = position;
                                edNum.setText(String.valueOf(xianzhi.getId()));
                                edName.setText(xianzhi.getLimitvalue());
                                xianAdapter.notifyDataSetChanged();
                            }
                        });
                        break;
                    case R.id.tanwei:
                        tv2.setText("摊位号");
                        tvName.setText("摊位号");
                        edNum.setText("");
                        edName.setText("");
                        linType.setVisibility(View.GONE);
                        listtanwei = daoSession.getTanweiDao().loadAll();
                        lv.setAdapter(tanweiAdapter = new TanweiAdapter(getActivity(), listtanwei, R.layout.dic_item));
                        tanweiAdapter.setOnItemClickListener(new UltimateRecycleAdapter.OnItemClickListener<tanwei>() {
                            @Override
                            public void onRecycleItemClickListener(tanwei tanwei, View view, int position, long id, int type) {
                                checkNum8 = position;
                                edNum.setText(String.valueOf(tanwei.getId()));
                                edName.setText(tanwei.getTanweinum());
                                tanweiAdapter.notifyDataSetChanged();
                            }
                        });
                        break;
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_save:
                saveordel(1);
                break;
            case R.id.btn_del:
                saveordel(0);
                break;
            case R.id.btn_back:
                onLeftClickListener();
                break;
        }
    }

    //sod==1,保存,sod==0,删除
    private void saveordel(int sod) {
        Long inum = TextUtils.isEmpty(edNum.getText()) ? null : Long.valueOf(edNum.getText().toString());
        String content = TextUtils.isEmpty(edName.getText()) ? "" : edName.getText().toString();
        String belong = TextUtils.isEmpty(edType.getText()) ? "" : edType.getText().toString();
        switch (radioGroup.getCheckedRadioButtonId()) {
            case R.id.samplename:
                if (sod == 1) {
                    daoSession.getDictionaryDao().insertOrReplace(new Dictionary(0, inum, content, "", "", belong, "", "", "", ""));
                }else if(sod == 0){
                    if(inum == null){
                        toast("请先填写需要删除数据的序号");
                        return;
                    }
                    daoSession.getDictionaryDao().deleteByKey(inum);
                }
                listdicts.clear();
                listdicts.addAll(daoSession.getDictionaryDao().loadAll());
                nameAdapter.notifyDataSetChanged();
                break;
            case R.id.sampleplace:
                if(sod == 1){
                    daoSession.getPlaceDao().insertOrReplace(new place(inum,content,"","",""));
                }else if(sod ==0){
                    if(inum == null){
                        toast("请先填写需要删除数据的序号");
                        return;
                    }
                    daoSession.getPlaceDao().deleteByKey(inum);
                }
                listplaces.clear();
                listplaces.addAll(daoSession.getPlaceDao().loadAll());
                placeAdapter.notifyDataSetChanged();
                break;
            case R.id.unit:
                if(sod == 1){
                    daoSession.getUnitDao().insertOrReplace(new unit(inum,content));
                }else{
                    if(inum == null){
                        toast("请先填写需要删除数据的序号");
                        return;
                    }
                    daoSession.getUnitDao().deleteByKey(inum);
                }
                listunits.clear();
                listunits.addAll(daoSession.getUnitDao().loadAll());
                unitAdapter.notifyDataSetChanged();
                break;
            case R.id.people:
                if(sod == 1){
                    daoSession.getPeopleDao().insertOrReplace(new people(inum,content));
                }else{
                    if(inum == null){
                        toast("请先填写需要删除数据的序号");
                        return;
                    }
                    daoSession.getPeopleDao().deleteByKey(inum);
                }
                listpeoples.clear();
                listpeoples.addAll(daoSession.getPeopleDao().loadAll());
                peopleAdapter.notifyDataSetChanged();
                break;
            case R.id.type:
                if(sod == 1){
                    daoSession.getTypeDao().insertOrReplace(new type(inum,content));
                }else{
                    if(inum == null){
                        toast("请先填写需要删除数据的序号");
                        return;
                    }
                    daoSession.getTypeDao().deleteByKey(inum);
                }
                listtypes.clear();
                listtypes.addAll(daoSession.getTypeDao().loadAll());
                typeAdapter.notifyDataSetChanged();
                break;
            case R.id.concentration:
                if(sod == 1){
                    daoSession.getConcentrationDao().insertOrReplace(new Concentration(inum,content));
                }else{
                    if(inum == null){
                        toast("请先填写需要删除数据的序号");
                        return;
                    }
                    daoSession.getConcentrationDao().deleteByKey(inum);
                }
                listcons.clear();
                listcons.addAll(daoSession.getConcentrationDao().loadAll());
                conAdapter.notifyDataSetChanged();
                break;
            case R.id.limited:
                if(sod == 1){
                    daoSession.getXianzhiDao().insertOrReplace(new xianzhi(inum,content));
                }else{
                    if(inum == null){
                        toast("请先填写需要删除数据的序号");
                        return;
                    }
                    daoSession.getXianzhiDao().deleteByKey(inum);
                }
                listxianzhi.clear();
                listxianzhi.addAll(daoSession.getXianzhiDao().loadAll());
                xianAdapter.notifyDataSetChanged();
                break;
            case R.id.tanwei:
                if(sod == 1){
                    daoSession.getTanweiDao().insertOrReplace(new tanwei(inum,content));
                }else{
                    if(inum == null){
                        toast("请先填写需要删除数据的序号");
                        return;
                    }
                    daoSession.getTanweiDao().deleteByKey(inum);
                }
                listtanwei.clear();
                listtanwei.addAll(daoSession.getTanweiDao().loadAll());
                tanweiAdapter.notifyDataSetChanged();
                break;
        }
    }

    class NameAdapter extends UltimateRecycleAdapter<Dictionary> {
        public NameAdapter(Context context, List<Dictionary> datum, int resItemID) {
            super(context, datum, resItemID);
        }

        @Override
        protected void convert(Dictionary dictionary, final UltimateRecycleHolder holder, final int position) {
            holder.setText(R.id.tv1, dictionary.getNo());
            holder.setText(R.id.tv2, dictionary.getContent());
            if (position == checkNum) {
                holder.setBackgroundColor(R.id.linear_item, getColor(R.color.android_holo_blue));
            } else {
                holder.setBackgroundColor(R.id.linear_item, getColor(R.color.white));
            }
        }
    }

    class PlaceAdapter extends UltimateRecycleAdapter<place> {
        public PlaceAdapter(Context context, List<place> datum, int resItemID) {
            super(context, datum, resItemID);
        }

        @Override
        protected void convert(place dictionary, final UltimateRecycleHolder holder, final int position) {
            holder.setText(R.id.tv1, dictionary.getId());
            holder.setText(R.id.tv2, dictionary.getName());
            if (position == checkNum2) {
                holder.setBackgroundColor(R.id.linear_item, getColor(R.color.android_holo_blue));
            } else {
                holder.setBackgroundColor(R.id.linear_item, getColor(R.color.white));
            }
        }
    }

    class UnitAdapter extends UltimateRecycleAdapter<unit> {
        public UnitAdapter(Context context, List<unit> datum, int resItemID) {
            super(context, datum, resItemID);
        }

        @Override
        protected void convert(unit dictionary, final UltimateRecycleHolder holder, final int position) {
            holder.setText(R.id.tv1, dictionary.getId());
            holder.setText(R.id.tv2, dictionary.getName());
            if (position == checkNum3) {
                holder.setBackgroundColor(R.id.linear_item, getColor(R.color.android_holo_blue));
            } else {
                holder.setBackgroundColor(R.id.linear_item, getColor(R.color.white));
            }
        }
    }

    class PeopleAdapter extends UltimateRecycleAdapter<people> {
        public PeopleAdapter(Context context, List<people> datum, int resItemID) {
            super(context, datum, resItemID);
        }

        @Override
        protected void convert(people dictionary, final UltimateRecycleHolder holder, final int position) {
            holder.setText(R.id.tv1, dictionary.getId());
            holder.setText(R.id.tv2, dictionary.getName());
            if (position == checkNum4) {
                holder.setBackgroundColor(R.id.linear_item, getColor(R.color.android_holo_blue));
            } else {
                holder.setBackgroundColor(R.id.linear_item, getColor(R.color.white));
            }
        }
    }

    class TypeAdapter extends UltimateRecycleAdapter<type> {
        public TypeAdapter(Context context, List<type> datum, int resItemID) {
            super(context, datum, resItemID);
        }

        @Override
        protected void convert(type dictionary, final UltimateRecycleHolder holder, final int position) {
            holder.setText(R.id.tv1, dictionary.getId());
            holder.setText(R.id.tv2, dictionary.getTypename());
            if (position == checkNum5) {
                holder.setBackgroundColor(R.id.linear_item, getColor(R.color.android_holo_blue));
            } else {
                holder.setBackgroundColor(R.id.linear_item, getColor(R.color.white));
            }
        }
    }

    class ConAdapter extends UltimateRecycleAdapter<Concentration> {
        public ConAdapter(Context context, List<Concentration> datum, int resItemID) {
            super(context, datum, resItemID);
        }

        @Override
        protected void convert(Concentration dictionary, final UltimateRecycleHolder holder, final int position) {
            holder.setText(R.id.tv1, dictionary.getId());
            holder.setText(R.id.tv2, dictionary.getName());
            if (position == checkNum6) {
                holder.setBackgroundColor(R.id.linear_item, getColor(R.color.android_holo_blue));
            } else {
                holder.setBackgroundColor(R.id.linear_item, getColor(R.color.white));
            }
        }
    }

    class XianAdapter extends UltimateRecycleAdapter<xianzhi> {
        public XianAdapter(Context context, List<xianzhi> datum, int resItemID) {
            super(context, datum, resItemID);
        }

        @Override
        protected void convert(xianzhi dictionary, final UltimateRecycleHolder holder, final int position) {
            holder.setText(R.id.tv1, dictionary.getId());
            holder.setText(R.id.tv2, dictionary.getLimitvalue());
            if (position == checkNum7) {
                holder.setBackgroundColor(R.id.linear_item, getColor(R.color.android_holo_blue));
            } else {
                holder.setBackgroundColor(R.id.linear_item, getColor(R.color.white));
            }
        }
    }

    class TanweiAdapter extends UltimateRecycleAdapter<tanwei> {
        public TanweiAdapter(Context context, List<tanwei> datum, int resItemID) {
            super(context, datum, resItemID);
        }

        @Override
        protected void convert(tanwei dictionary, final UltimateRecycleHolder holder, final int position) {
            holder.setText(R.id.tv1, dictionary.getId());
            holder.setText(R.id.tv2, dictionary.getTanweinum());
            if (position == checkNum8) {
                holder.setBackgroundColor(R.id.linear_item, getColor(R.color.android_holo_blue));
            } else {
                holder.setBackgroundColor(R.id.linear_item, getColor(R.color.white));
            }
        }
    }
}
