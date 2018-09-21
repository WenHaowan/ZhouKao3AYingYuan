package as.bwei.com.zhoukao3ayingyuan;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.List;

import as.bwei.com.zhoukao3ayingyuan.adapter.MyAdapter;
import as.bwei.com.zhoukao3ayingyuan.bean.UserBean;
import as.bwei.com.zhoukao3ayingyuan.presenter.ShowPresenter;
import as.bwei.com.zhoukao3ayingyuan.view.ShowView;

public class MainActivity extends AppCompatActivity implements ShowView {

    private ShowPresenter presenter;
    private RecyclerView recyclerView;
    private TextView haun,ha;
    private MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        haun = (TextView) findViewById(R.id.haun);
        ha = (TextView) findViewById(R.id.ha);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        //切换线性和网格
        haun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                haun.setVisibility(View.GONE);
                ha.setVisibility(View.VISIBLE);
                recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                myAdapter.notifyDataSetChanged();
            }
        });
        ha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                haun.setVisibility(View.VISIBLE);
                ha.setVisibility(View.GONE);
                recyclerView.setLayoutManager(new GridLayoutManager(MainActivity.this,2));
                myAdapter.notifyDataSetChanged();
            }
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this,LinearLayoutManager.VERTICAL,false));
        presenter = new ShowPresenter(this);
        presenter.show();
    }

    @Override
    public void success(final String msg) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Gson gson = new Gson();
                UserBean userBean = gson.fromJson(msg,UserBean.class);
                List<UserBean.ResultBean.NearbyCinemaListBean> list = userBean.getResult().getNearbyCinemaList();
                myAdapter = new MyAdapter(MainActivity.this,list);
                recyclerView.setAdapter(myAdapter);
            }
        });
    }

    @Override
    public void failure(final String msg) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(MainActivity.this,msg,Toast.LENGTH_SHORT).show();
            }
        });
    }
}
