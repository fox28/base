package com.example.day11_06;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.day11_06.bean.AppBean;
import com.example.day11_06.dao.AppDao;
import com.example.day11_06.utils.OkImageLoader;
import com.example.day11_06.utils.OkUtils;
import com.squareup.okhttp.OkHttpClient;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    static final String ROOT_URL = "http://192.168.1.105";
    RecyclerView mrvApp;
    ArrayList<AppBean> mArrayList;
    AppAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        initData();
        initView();
    }

    private void initView() {
        mrvApp = (RecyclerView) findViewById(R.id.rvApp);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mrvApp.setLayoutManager(layoutManager);// 设置布局管理器


        mAdapter = new AppAdapter(this, mArrayList);
        mrvApp.setAdapter(mAdapter);

    }

    private void initData() {
       /* final OkUtils<AppBean[]> utils = new OkUtils<>(this);
        utils.url(ROOT_URL+":8008/app.json")
                .targetClass(AppBean[].class)
                .execute(new OkUtils.OnCompleteListener<AppBean[]>() {
                    @Override
                    public void onSuccess(AppBean[] result) {
                        ArrayList<AppBean> arrayList = utils.array2List(result);
                        mAdapter.addData(arrayList);
                    }

                    @Override
                    public void onError(String error) {
                        Toast.makeText(MainActivity.this, error, Toast.LENGTH_SHORT).show();

                    }
                });*/
        mArrayList = AppDao.getData(this);


    }

    /**
     * 将item_footer.xml解析为一个java对象
     */
    class FooterViewHolder extends RecyclerView.ViewHolder {
        TextView tvFooter;
        public FooterViewHolder(View itemView) {
            super(itemView);
            tvFooter = (TextView) itemView.findViewById(R.id.tvFooter);
        }
    }

    /**
     * 将item_app.xml 解析为一个java对象
     */
    class AppViewHolder extends RecyclerView.ViewHolder {
        ImageView ivThumb;
        TextView tvName, tvVersion, tvFileSize;
        public AppViewHolder(View itemView) {
            super(itemView);
            ivThumb = (ImageView) itemView.findViewById(R.id.ivThumb);
            tvName = (TextView) itemView.findViewById(R.id.tvAppName);
            tvVersion = (TextView) itemView.findViewById(R.id.tvAppVersion);
            tvFileSize = (TextView) itemView.findViewById(R.id.tvAppFileSize);
        }
    }

    class AppAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
        static final int TYPE_FOOTER = 0;
        static final int TYPE_ITEM = 1;

        Context context;
        ArrayList<AppBean> arrayList;

        public AppAdapter(Context context, ArrayList<AppBean> arrayList) {
            this.context = context;
            this.arrayList = arrayList;
        }

        public void addData(ArrayList<AppBean> appList) {
            this.arrayList.addAll(appList);
            notifyDataSetChanged(); //通知刷新适配器
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(context); // 使用LayoutInflater来加载布局
            switch (viewType) {
                case TYPE_FOOTER:
                    return new FooterViewHolder(inflater.inflate(R.layout.item_footer, null));
                case TYPE_ITEM:
                    return new AppViewHolder(inflater.inflate(R.layout.item_app, null));
            }

            return null;
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder parentHolder, int position) {
            if (getItemViewType(position) == TYPE_FOOTER) {
                FooterViewHolder holder = (FooterViewHolder) parentHolder;
                holder.tvFooter.setVisibility(View.VISIBLE);
                return;
            }
            AppBean app = arrayList.get(position);
            AppViewHolder holder = (AppViewHolder) parentHolder;
            holder.tvName.setText(app.getName());
            holder.tvVersion.setText(app.getVersion());
            holder.tvFileSize.setText(app.getFileSize()+"kb");
            holder.ivThumb.setImageResource(app.getPhotoId());

            /*//显示app的缩略图
            OkImageLoader.build(ROOT_URL + app.getThumb())
                    .defaultPicture(R.drawable.nopic)
                    .imageView(holder.ivThumb)
                    .showImage(context);*/

        }

        @Override
        public int getItemCount() {
            return arrayList.size()+1;
        }

        @Override
        public int getItemViewType(int position) {
            if (position == getItemCount() - 1) {
                return TYPE_FOOTER;
            }
            return TYPE_ITEM;
        }
    }




}
