package xyz.ruoxue.base.ruoxueUtil;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.ruoxue.basic.R;

import org.xutils.common.util.DensityUtil;

/**
 * Created by ruoxue_ye on 2015/9/10.
 */
public class T {
    private T()
    {
		/* cannot be instantiated */
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    private static Toast mToast;

    private static TextView tv_content;

    public static void showToast(Context context, String msg) {
        try {

            if (mToast == null) {
                mToast = Toast.makeText(context, msg, Toast.LENGTH_SHORT);
                mToast.setGravity(Gravity.TOP, 0,
                        DensityUtil.dip2px(3.0f));
                View view = View.inflate(context, R.layout.m_toast, null);
                tv_content = (TextView) view.findViewById(R.id.tv_content);
                mToast.setView(view);
                tv_content.setText(msg);
            } else {
                tv_content.setText(msg);
            }
            mToast.show();
        } catch (Exception e) {

        }
    }
}
