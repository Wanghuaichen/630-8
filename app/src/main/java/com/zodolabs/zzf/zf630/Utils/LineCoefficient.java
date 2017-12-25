package com.zodolabs.zzf.zf630.Utils;

/**
 * Created by zzf on 17-12-19.
 */

public class LineCoefficient {
    /**
     * 预测
     *
     * @param x
     * @param y
     * @param i
     * @return
     */
    public static double estimate(double[] x, double[] y, double i) {
        double a = getXc(x, y);
        double b = getC(x, y, a);
        return a * i + b;
    }

    /**
     * 计算 x 的系数
     *
     * @param x
     * @param y
     * @return
     */
    public static double getXc(double[] x, double[] y) {
        int n = x.length;
        return (n * pSum(x, y) - sum(x) * sum(y))
                / (n * sqSum(x) - Math.pow(sum(x), 2));
    }

    /**
     * 计算常量系数
     *
     * @param x
     * @param y
     * @param a
     * @return
     */
    public static double getC(double[] x, double[] y, double a) {
        int n = x.length;
        return sum(y) / n - a * sum(x) / n;
    }

    /**
     * 计算常量系数
     *
     * @param x
     * @param y
     * @return
     */
    public static double getC(double[] x, double[] y) {
        int n = x.length;
        double a = getXc(x, y);
        return sum(y) / n - a * sum(x) / n;
    }

    private static double sum(double[] ds) {
        double s = 0;
        for (double d : ds) s = s + d;
        return s;
    }

    private static double sqSum(double[] ds) {
        double s = 0;
        for (double d : ds) s = s + Math.pow(d, 2);
        return s;
    }

    private static double pSum(double[] x, double[] y) {
        double s = 0;
        for (int i = 0; i < x.length; i++) s = s + x[i] * y[i];
        return s;
    }
}
