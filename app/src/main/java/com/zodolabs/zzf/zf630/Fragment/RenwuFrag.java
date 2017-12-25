package com.zodolabs.zzf.zf630.Fragment;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

import com.bill.ultimatefram.ui.UltimateFragment;
import com.zodolabs.zzf.zf630.R;
import com.zodolabs.zzf.zf630.Utils.DateUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by zzf on 17-12-6.
 */

public class RenwuFrag extends UltimateFragment {
    TextView tvDate;

    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy年MM月dd日");
    @Override
    protected int setContentView() {
        return R.layout.frag_renwu;
    }

    @Override
    protected void initView() {
        getFlexibleBar().setVisibility(View.GONE);
        tvDate = findViewById(R.id.tv_date);
    }

    @Override
    protected void initEvent(Bundle savedInstanceState) {
        tvDate.setText(dateFormat.format(new Date()));
        tvDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDate(tvDate);
            }
        });
    }

    private void setDate(final TextView textView) {
        try {
            Date date = DateUtils.getDateFromString(textView.getText().toString(), "yyyy年MM月dd");
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
}
