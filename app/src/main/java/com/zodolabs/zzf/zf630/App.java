package com.zodolabs.zzf.zf630;

import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.Drawable;

import com.bill.ultimatefram.app.UltimateApplication;
import com.bill.ultimatefram.util.ExternalFileHelper;
import com.bill.ultimatefram.util.UltimatePreferenceHelper;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.zodolabs.zzf.zf630.Utils.Utils;
import com.zodolabs.zzf.zf630.greendao.gen.DaoMaster;
import com.zodolabs.zzf.zf630.greendao.gen.DaoSession;


/**
 * Created by zzf on 17-12-6.
 */

public class App extends UltimateApplication {
    public ExternalFileHelper.Builder builder;
    private SQLiteDatabase sqliteDatabase;
    private DaoMaster daomaster;
    private DaoSession daosession;
    String username, password;

    public static App getInstance() {
        return (App) getAppInstance();
    }

    @Override
    protected void init() {
    /*外部文件存储*/
        builder = new ExternalFileHelper.Builder();
        builder.setFileFolderName("zodolabs_File");
        builder.setImageFolderName("zodolabs_Images");
        builder.setRootFolderName("zodolabs");
        ExternalFileHelper.getInstance().builder(builder);
        UltimatePreferenceHelper.getInstance();
        if (ExternalFileHelper.getInstance().isFileExists(Utils.getDBpath())) {
            sqliteDatabase = SQLiteDatabase.openDatabase(Utils.getDBpath(), null, SQLiteDatabase.OPEN_READWRITE);
            daomaster = new DaoMaster(sqliteDatabase);
            daosession = daomaster.newSession();
        }
    }

    @Override
    protected void buildImageOptions(DisplayImageOptions.Builder builder) {
        Drawable drawable = getResources().getDrawable(R.mipmap.ic_launcher);
        builder.showImageForEmptyUri(drawable)
                .showImageOnFail(drawable)
                .showImageOnLoading(drawable);
    }

    @Override
    protected void buildImageConfig(ImageLoaderConfiguration.Builder builder) {
        builder.memoryCacheExtraOptions(480, 800);
    }

    public DaoSession getDaosession() {
        return daosession;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
