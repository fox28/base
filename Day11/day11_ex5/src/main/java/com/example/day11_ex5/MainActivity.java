package com.example.day11_ex5;

import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.day11_ex5.bean.AppBean;
import com.example.day11_ex5.dao.AppDao;

import java.util.ArrayList;








public class MainActivity extends AppCompatActivity {
    RecyclerView mrvApp;
    ArrayList<AppBean> mArrayList;
    AppAdapter mAdapter;
    ImageView mivMoveBottom, mivMoveTop, mivAddApp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initData();
        initView();

        setListener();
    }


    /**
     * 设置监听事件：
     * 置顶、
     * 置底
     * 添加app
     */
    private void setListener() {
        setMoveTopListener();
        setMoveBottomListener();
        setAddAppListener(); 

       

    }

    private void setAddAppListener() {
        findViewById(R.id.ivAddApp).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View layout = View.inflate(MainActivity.this, R.layout.app_add, null);

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                final AlertDialog dialog = builder.setTitle("添加app")
                        .setView(layout)
                        .create();
                dialog.show();

                final EditText etName = (EditText) layout.findViewById(R.id.etAppName);
                final EditText etVersion = (EditText) layout.findViewById(R.id.etAppVersion);
                final EditText etFileSize = (EditText) layout.findViewById(R.id.etAppFileSize);
                final EditText etIntro = (EditText) layout.findViewById(R.id.etAppIntro);

                layout.findViewById(R.id.btnOK).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String name=etName.getText().toString();
                        String version=etVersion.getText().toString();
                        int filesize = Integer.parseInt(etFileSize.getText().toString());
                        String intro=etIntro.getText().toString();
                        AppBean app = new AppBean(name, version, R.drawable.tencent_safe, filesize, intro);
                        mAdapter.addApp(app);
                        dialog.dismiss();
                    }
                });
            }
        });


        mivAddApp = (ImageView) findViewById(R.id.ivAddApp);
        mivAddApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View view = View.inflate(MainActivity.this, R.layout.app_add, null);
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                final AlertDialog dialog = builder.setTitle("添加app")
                        .setView(view)
                        .create();
                dialog.show();

                final EditText etName = (EditText) view.findViewById(R.id.etAppName);
                final EditText etVersion = (EditText) view.findViewById(R.id.etAppVersion);
                final EditText etFileSize = (EditText) view.findViewById(R.id.etAppFileSize);
                final EditText etIntro = (EditText) view.findViewById(R.id.etAppIntro);

                view.findViewById(R.id.btnOK).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String name = etName.getText().toString();
                        String version = etVersion.getText().toString();
                        int fileSize = Integer.parseInt(etFileSize.getText().toString());
                        String intro = etIntro.getText().toString();
                        AppBean app = new AppBean(name, version, R.drawable.an_doctor, fileSize, intro);
                        mAdapter.addApp(app);
                        dialog.dismiss();
                    }
                });


            }
        });
    }

    private void setMoveBottomListener() {
        mivMoveBottom = (ImageView) findViewById(R.id.ivMoveBottom);
        mivMoveBottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mrvApp.scrollToPosition(mAdapter.getItemCount()-1);

            }
        });
    }

    private void setMoveTopListener() {
        mivMoveTop = (ImageView) findViewById(R.id.ivMoveTop);
        mivMoveTop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mrvApp.scrollToPosition(0);
            }
        });
        
    }

    private void initView() {
        mrvApp = (RecyclerView) findViewById(R.id.rvApp);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mrvApp.setLayoutManager(layoutManager);

        mAdapter = new AppAdapter(this, mArrayList);
        mrvApp.setAdapter(mAdapter);


    }

    private void initData() {
        mArrayList = AppDao.getData(this);
    }

    class AppViewHolder extends RecyclerView.ViewHolder {
        ImageView ivThumb, ivDelete, ivUpdate;
        TextView tvName, tvVersion, tvFileSize;
        public AppViewHolder(View itemView) {
            super(itemView);
            ivThumb = (ImageView) itemView.findViewById(R.id.ivThumb);
            ivDelete = (ImageView) itemView.findViewById(R.id.ivDelete);
            ivUpdate = (ImageView) itemView.findViewById(R.id.ivUpdate);

            tvName = (TextView) itemView.findViewById(R.id.tvAppName);
            tvVersion = (TextView) itemView.findViewById(R.id.tvAppVersion);
            tvFileSize = (TextView) itemView.findViewById(R.id.tvAppFileSize);

        }
    }

    class FooterViewHolder extends RecyclerView.ViewHolder {
        TextView tvFooter;
        public FooterViewHolder(View itemView) {
            super(itemView);
            tvFooter = (TextView) itemView.findViewById(R.id.tvFooter);
        }
    }

    class AppAdapter extends RecyclerView.Adapter {
        static final int TYPE_ITEM = 0;
        static final int TYPE_FOOTER = 1;
        Context context;
        ArrayList<AppBean> arrayList;

        View.OnClickListener mOnItemClickListener;

        public AppAdapter(Context context, final ArrayList<AppBean> arrayList) {
            this.context = context;
            this.arrayList = arrayList;
            mOnItemClickListener = new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = (int) v.getTag();
                    arrayList.remove(position);
                    notifyDataSetChanged();// 通知更改适配器

                }
            };

            /*
            new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    arrayList.remove(position);
                    notifyDataSetChanged();// 通知刷新适配器
                }
            }
             */


        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(context);
            switch (viewType) {
                case TYPE_FOOTER:
                    View view = inflater.inflate(R.layout.item_footer, null);
                    return new FooterViewHolder(view);
                case TYPE_ITEM:
                    View view2 = inflater.inflate(R.layout.app_item, null);
                    AppViewHolder holder = new AppViewHolder(view2);
                    holder.ivDelete.setOnClickListener(mOnItemClickListener);


                    return holder;


            }
            return null;
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder parentHolder, final int position) {
            if (getItemViewType(position) == TYPE_FOOTER) {
                FooterViewHolder holder = (FooterViewHolder) parentHolder;
                holder.tvFooter.setVisibility(View.VISIBLE);
                return;
            }
            AppViewHolder holder = (AppViewHolder) parentHolder;
            AppBean app = arrayList.get(position);
            holder.ivThumb.setImageResource(app.getPhotoId());
            holder.tvName.setText(app.getName());
            holder.tvVersion.setText(app.getVersion());
            holder.tvFileSize.setText(app.getFileSize()+"kb");


            /**
             *  删除某个数据项
             * 优化这部分代码
             */
            holder.ivDelete.setTag(position);
            /*holder.ivDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    arrayList.remove(position);
                    notifyDataSetChanged();// 通知刷新适配器
                }
            });*/

            /**
             * 单击编辑按钮——响应事件为显示app简介
             */
            holder.ivUpdate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, arrayList.get(position).getIntro(), Toast.LENGTH_SHORT).show();
                }
            });
        }




        @Override
        public int getItemCount() {
            return arrayList.size()+1;
        }

        @Override
        public int getItemViewType(int position) {
            if (position == getItemCount()-1) {
                return TYPE_FOOTER;
            }
            return TYPE_ITEM;
        }

        public void addApp(AppBean app) {
            arrayList.add(app);
            notifyDataSetChanged();// 通知刷新适配器
        }
    }

}
