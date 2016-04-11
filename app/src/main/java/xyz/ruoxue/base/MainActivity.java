package xyz.ruoxue.base;


import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.zhy.changeskin.SkinManager;

import xyz.ruoxue.base.base.activity.BaseActivity;

public class MainActivity extends BaseActivity {
    private Button btn;
    private RecyclerView rcv;

    @Override
    protected void loadDate() {

    }

    @Override
    protected int loadRes() {
        return R.layout.activity_main2;
    }

    @Override
    protected void initView() {
        SkinManager.getInstance().register(this);
        SkinManager.getInstance().changeSkin("green");
        setBack(false);
        btn = $(R.id.btn);
        pd = $(R.id.pb);
//        rcv = $(R.id.rcv);
        pd.setVisibility(View.GONE);
        setCustomText("我");
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SkinManager.getInstance().changeSkin("red");
            }
        });
    }

    @Override
    public void responseImpl(int tag, Object o) {

    }

    @Override
    public void request(int tag) {

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = new MenuInflater(this);
        menuInflater.inflate(R.menu.option_menu, menu);


        return true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SkinManager.getInstance().unregister(this);
    }
}
