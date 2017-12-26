package com.zodolabs.zzf.zf630.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.bill.ultimatefram.ui.UltimateFragment;
import com.bill.ultimatefram.view.dialog.IOSListDialog;
import com.bill.ultimatefram.view.recycleview.UltimateItemDecoration;
import com.bill.ultimatefram.view.recycleview.adapter.UltimateRecycleAdapter;
import com.bill.ultimatefram.view.recycleview.adapter.UltimateRecycleHolder;
import com.zodolabs.zzf.zf630.App;
import com.zodolabs.zzf.zf630.Beans.db.UserInfo;
import com.zodolabs.zzf.zf630.R;
import com.zodolabs.zzf.zf630.greendao.gen.DaoSession;
import com.zodolabs.zzf.zf630.greendao.gen.UserInfoDao;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zzf on 17-12-6.
 */

public class ShezhiFrag extends UltimateFragment implements View.OnClickListener {
    RecyclerView lv;
    EditText edName, edPwd, edid;
    TextView tvPremiss;
    Button btnSave, btnAdd, btnDel;

    DaoSession session;
    List<UserInfo> userInfoList;
    private UserAdapter userAdapter;
    UserInfo userInfoItem;//被选中的item
    int curPosition = 0;

    @Override
    protected int setContentView() {
        return R.layout.frag_setting;
    }

    @Override
    protected void initView() {
        getFlexibleBar().setVisibility(View.GONE);
        lv = findViewById(R.id.lv_user);
        edName = findViewById(R.id.ed_name);
        edPwd = findViewById(R.id.ed_pwd);
        tvPremiss = findViewById(R.id.tv_premess);
        btnAdd = findViewById(R.id.btn_add);
        btnSave = findViewById(R.id.btn_save);
        btnDel = findViewById(R.id.btn_del);
        edid = findViewById(R.id.ed_id);
    }

    @Override
    protected void initEvent(Bundle savedInstanceState) {
        userInfoList = new ArrayList<>();
        session = App.getInstance().getDaosession();
        lv.setLayoutManager(new LinearLayoutManager(getActivity()));
        lv.setItemAnimator(new DefaultItemAnimator());
        lv.addItemDecoration(new UltimateItemDecoration(getActivity(), RecyclerView.VERTICAL, 0.5f, getColor(R.color.c_70000000)));
        lv.setAdapter(userAdapter = new UserAdapter(getActivity(), userInfoList, R.layout.dic_item));
        List<UserInfo> list = session.getUserInfoDao().queryBuilder().orderAsc(UserInfoDao.Properties.Id).list();
        userInfoList.addAll(list);
        userAdapter.notifyDataSetChanged();
        if (userInfoList.size() > 0) {
            edid.setText(String.valueOf(userInfoList.get(0).getId()));
            edName.setText(userInfoList.get(0).getUsername());
            edPwd.setText(userInfoList.get(0).getPassword());
            tvPremiss.setText(userInfoList.get(0).getPermission());
            userInfoItem = userInfoList.get(0);
        }
        btnSave.setOnClickListener(this);
        btnAdd.setOnClickListener(this);
        btnDel.setOnClickListener(this);
        tvPremiss.setOnClickListener(this);
        userAdapter.setOnItemClickListener(new UltimateRecycleAdapter.OnItemClickListener<UserInfo>() {
            @Override
            public void onRecycleItemClickListener(UserInfo userInfo, View view, int position, long id, int type) {
                edid.setText(String.valueOf(userInfo.getId()));
                edName.setText(userInfo.getUsername());
                edPwd.setText(userInfo.getPassword());
                tvPremiss.setText(userInfo.getPermission());
                userInfoItem = userInfo;
                userAdapter.notifyItemChanged(curPosition);
                curPosition = position;
                userAdapter.notifyItemChanged(curPosition);
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_premess:
                IOSListDialog dialog = new IOSListDialog(getActivity());
                dialog.addListItem("普通", getColor(R.color.c_70000000));
                dialog.addListItem("高级", getColor(R.color.c_70000000));
                dialog.show();
                dialog.setOnIOSItemClickListener(new IOSListDialog.OnIOSItemClickListener() {
                    @Override
                    public void onIOSItemClick(IOSListDialog.IOSListItem data, TextView item, int position, Object tag) {
                        tvPremiss.setText(item.getText());
                    }
                });
                break;
            case R.id.btn_add:
                edName.setText("");
                edPwd.setText("");
                edid.setText("");
                tvPremiss.setText("普通");
                break;
            case R.id.btn_del:
                if (TextUtils.isEmpty(edid.getText())) {
                    toast("请先填写要删除的用户序号");
                } else {
                    if (!TextUtils.isEmpty(edName.getText()) && !TextUtils.isEmpty(edPwd.getText()) && !TextUtils.isEmpty(edid.getText())) {
                        session.getUserInfoDao().delete(new UserInfo(Long.valueOf(edid.getText().toString()),
                                edName.getText().toString(), edPwd.getText().toString(), tvPremiss.getText().toString()));
                        List<UserInfo> list = session.getUserInfoDao().queryBuilder().orderAsc(UserInfoDao.Properties.Id).list();
                        userInfoList.clear();
                        userInfoList.addAll(list);
                        userAdapter.notifyDataSetChanged();
                    } else {
                        toast("用户名和密码、序号不能为空");
                    }
                }
                break;
            case R.id.btn_save:
                if (!TextUtils.isEmpty(edName.getText()) && !TextUtils.isEmpty(edPwd.getText()) && !TextUtils.isEmpty(edid.getText())) {
                    session.getUserInfoDao().insertOrReplace(new UserInfo(Long.valueOf(edid.getText().toString()), edName.getText().toString().trim(),
                            edPwd.getText().toString().trim(), tvPremiss.getText().toString().trim()));
                    List<UserInfo> list = session.getUserInfoDao().queryBuilder().orderAsc(UserInfoDao.Properties.Id).list();
                    userInfoList.clear();
                    userInfoList.addAll(list);
                    userAdapter.notifyDataSetChanged();
                } else {
                    toast("用户名和密码、序号不能为空");
                }
                break;
        }
    }

    private class UserAdapter extends UltimateRecycleAdapter<UserInfo> {
        public UserAdapter(Context context, List<UserInfo> datum, int resItemID) {
            super(context, datum, resItemID);
        }

        @Override
        protected void convert(UserInfo userInfo, UltimateRecycleHolder holder, int position) {
            holder.setText(R.id.tv1, userInfo.getId());
            holder.setText(R.id.tv2, userInfo.getUsername());
            if (curPosition == position) {
                holder.setBackgroundColor(R.id.linear_item, getColor(R.color.android_holo_blue));
            } else {
                holder.setBackgroundColor(R.id.linear_item, getColor(R.color.white));
            }
        }
    }
}
