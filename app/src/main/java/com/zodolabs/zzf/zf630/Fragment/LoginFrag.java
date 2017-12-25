package com.zodolabs.zzf.zf630.Fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import com.bill.ultimatefram.ui.UltimateFragment;
import com.zodolabs.zzf.zf630.App;
import com.zodolabs.zzf.zf630.Beans.db.UserInfo;
import com.zodolabs.zzf.zf630.CheckActivity;
import com.zodolabs.zzf.zf630.MainActivity;
import com.zodolabs.zzf.zf630.R;
import com.zodolabs.zzf.zf630.greendao.gen.DaoSession;
import com.zodolabs.zzf.zf630.greendao.gen.UserInfoDao;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

/**
 * Created by zzf on 17-12-6.
 */

public class LoginFrag extends UltimateFragment {
    Button loginBtn, exitBtn;
    EditText nameEd, pwdEd;
    CheckBox checkBox;

    Boolean checked = true;
    DaoSession session;
    private QueryBuilder<UserInfo> builder;


    @Override
    protected int setContentView() {
        return R.layout.frag_login;
    }

    @Override
    protected void initView() {
        getFlexibleBar().setVisibility(View.GONE);
        loginBtn = findViewById(R.id.btn_login);
        exitBtn = findViewById(R.id.btn_exit);
        nameEd = findViewById(R.id.ed_name);
        pwdEd = findViewById(R.id.ed_pwd);
        checkBox = findViewById(R.id.checkbox);
    }

    @Override
    protected void initEvent(Bundle savedInstanceState) {
        nameEd.setSelection(nameEd.getText().length());
        session = App.getInstance().getDaosession();
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!nameEd.getText().toString().isEmpty() && !pwdEd.getText().toString().isEmpty()) {
                    String name = nameEd.getText().toString().trim();
                    String pwd = pwdEd.getText().toString().trim();
                    builder = session.getUserInfoDao().queryBuilder();
                    List<UserInfo> list = builder.where(
                            builder.and(UserInfoDao.Properties.Username.eq(name),
                                    UserInfoDao.Properties.Password.eq(pwd))).list();
                    if (list.size() > 0) {
                        App.getInstance().setUsername(list.get(0).getUsername());
                        App.getInstance().setPassword(list.get(0).getPassword());
                        if (checked) {
                            startActivity(CheckActivity.class, true);
                        } else {
                            startActivity(MainActivity.class, true);
                        }
                    } else {
                        toast("用户名或密码不正确");
                    }
                } else {
                    toast("用户名或密码不能为空");
                }
            }
        });
        exitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                checked = isChecked;
            }
        });
    }
}
