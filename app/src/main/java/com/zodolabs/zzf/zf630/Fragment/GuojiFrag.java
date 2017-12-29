package com.zodolabs.zzf.zf630.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.bill.ultimatefram.ui.UltimateFragment;
import com.bill.ultimatefram.view.recycleview.UltimateItemDecoration;
import com.bill.ultimatefram.view.recycleview.adapter.UltimateRecycleAdapter;
import com.bill.ultimatefram.view.recycleview.adapter.UltimateRecycleHolder;
import com.zodolabs.zzf.zf630.App;
import com.zodolabs.zzf.zf630.Beans.db.ItemBean;
import com.zodolabs.zzf.zf630.R;
import com.zodolabs.zzf.zf630.greendao.gen.DaoSession;
import com.zodolabs.zzf.zf630.greendao.gen.ItemBeanDao;

import java.util.List;

/**
 * Created by zzf on 17-12-6.
 */

public class GuojiFrag extends UltimateFragment {
    RecyclerView lv;
    TextView tvContent;
    TextView tv1, tv2, tv3;

    DaoSession session;
    List<ItemBean> list;
    GuoAdapter guoAdapter;

    int curitem;

    @Override
    protected int setContentView() {
        return R.layout.frag_guoji;
    }

    @Override
    protected void initView() {
        getFlexibleBar().setVisibility(View.GONE);
        lv = findViewById(R.id.lv);
        tvContent = findViewById(R.id.tv_content);
        tv1 = findViewById(R.id.tv1);
        tv2 = findViewById(R.id.tv2);
        tv3 = findViewById(R.id.tv3);
        tv1.setText("序号");
        tv2.setText("项目名称");
        tv3.setText("样品分类");
    }

    @Override
    protected void initEvent(Bundle savedInstanceState) {
        session = App.getInstance().getDaosession();
        list = session.getItemBeanDao().queryBuilder().where(ItemBeanDao.Properties.Reserve.notEq("")).orderAsc(ItemBeanDao.Properties.Print).list();
        tvContent.setText(list.get(0).getReserve());
        lv.setLayoutManager(new LinearLayoutManager(getActivity()));
        lv.addItemDecoration(new UltimateItemDecoration(getActivity(), RecyclerView.VERTICAL, 0.5f, getColor(R.color.c_70000000)));
        lv.setAdapter(guoAdapter = new GuoAdapter(getActivity(), list, R.layout.national_item));
        guoAdapter.setOnItemClickListener(new UltimateRecycleAdapter.OnItemClickListener<ItemBean>() {
            @Override
            public void onRecycleItemClickListener(ItemBean itemBean, View view, int position, long id, int type) {
                tvContent.setText(itemBean.getReserve());
                guoAdapter.notifyItemChanged(curitem);
                curitem = position;
                guoAdapter.notifyItemChanged(curitem);
            }
        });
    }

    private class GuoAdapter extends UltimateRecycleAdapter<ItemBean> {
        public GuoAdapter(Context context, List<ItemBean> datum, int resItemID) {
            super(context, datum, resItemID);
        }

        @Override
        protected void convert(ItemBean itemBean, UltimateRecycleHolder holder, int position) {
            holder.setText(R.id.tv1, itemBean.getPrint());
            holder.setText(R.id.tv2, itemBean.getCode());
            holder.setText(R.id.tv3, itemBean.getName());
            if (position == curitem) {
                holder.setBackgroundColor(R.id.lin_item, getColor(R.color.android_holo_blue));
            }else{
                holder.setBackgroundColor(R.id.lin_item, getColor(R.color.white));
            }
        }
    }
}
