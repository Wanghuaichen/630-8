package com.zodolabs.zzf.zf630.Utils;

import android.util.Log;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by zzf on 17-12-21.
 */

public class DayAxisValueFormatter implements IAxisValueFormatter {
    SimpleDateFormat dateFormat;
    public DayAxisValueFormatter() {
        dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    }

    @Override
    public String getFormattedValue(float value, AxisBase axis) {
        Log.d("chart:",""+value);
        return dateFormat.format(value);
    }
}
