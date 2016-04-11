package xyz.ruoxue.base.ruoxueUtil;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.SystemClock;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by ruoxue_ye on 2015/10/20.
 */
public class FileUtil {

    public static File save(Bitmap bitmap, Context context) {
        String fileName = "";
        if (SDCardUtils.isSDCardEnable()) {
            FileOutputStream b = null;
            File file = new File(SDCardUtils.getSDCardPath() + AppUtils.getAppName(context)+ File.separator);
            file.mkdirs();// 创建文件夹
            fileName = file.getAbsolutePath() + File.separator + SystemClock.currentThreadTimeMillis() + ".jpg";
            try {
                b = new FileOutputStream(fileName);
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, b);// 把数据写入文件


            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } finally {
                try {
                    b.flush();
                    b.close();
                } catch (IOException e) {

                }
            }
        }
        return new File(fileName);
    }
}
