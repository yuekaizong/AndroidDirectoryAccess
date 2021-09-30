package com.aghajari.app.androidr.utils;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;

import androidx.core.content.FileProvider;

import com.aghajari.app.androidr.adapter.FileInterface;

import java.io.File;

public class FileHandler {

    private Activity activity;

    public FileHandler(Activity activity) {
        this.activity = activity;
    }

    public void open(FileInterface fileInterface) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        File file = new File(fileInterface.getAbsolutePath(activity));
        if (Build.VERSION.SDK_INT >= 24) {//大于7.0使用此方法
            Uri apkUri =FileProvider.getUriForFile(activity, "com.aghajari.app.androidr.fileProvider", file);///-----ide文件提供者名
            //添加这一句表示对目标应用临时授权该Uri所代表的文件
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            intent.setDataAndType(apkUri, "*/*");
        }else {//小于7.0就简单了
            // 由于没有在Activity环境下启动Activity,设置下面的标签
            intent.setDataAndType(Uri.fromFile(file),"*/*");
        }
        activity.startActivity(intent);
    }
}
