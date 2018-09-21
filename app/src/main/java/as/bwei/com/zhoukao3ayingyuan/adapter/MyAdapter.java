package as.bwei.com.zhoukao3ayingyuan.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import as.bwei.com.zhoukao3ayingyuan.R;
import as.bwei.com.zhoukao3ayingyuan.bean.UserBean;

/**
 * Created by HP on 2018/9/21.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>{
    private Context context;
    private List<UserBean.ResultBean.NearbyCinemaListBean> list;

    public MyAdapter(Context context, List<UserBean.ResultBean.NearbyCinemaListBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_layout,parent,false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyAdapter.MyViewHolder holder, int position) {
        Glide.with(context).load(list.get(position).getLogo().toString()).into(holder.adapter_img);
        holder.name_text.setText(list.get(position).getName());
        holder.address_text.setText(list.get(position).getAddress());
        holder.iv_distance.setText(list.get(position).getDistance());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView name_text,address_text,iv_distance;
        private ImageView adapter_img;

        public MyViewHolder(View itemView) {
            super(itemView);
            iv_distance = (TextView) itemView.findViewById(R.id.iv_distance);
            adapter_img = (ImageView) itemView.findViewById(R.id.adapter_image);
            name_text = (TextView) itemView.findViewById(R.id.name_text);
            address_text = (TextView) itemView.findViewById(R.id.address_text);

        }
    }
}
