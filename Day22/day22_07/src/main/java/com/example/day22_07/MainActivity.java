package com.example.day22_07;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.DragEvent;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity implements View.OnDragListener, View.OnLongClickListener {
    ImageView mivGoods, mivCart, mivShare;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        setListener();


    }

    private void setListener() {
        mivCart.setOnDragListener(this);
        mivShare.setOnDragListener(this);
        mivGoods.setOnDragListener(this);

        mivGoods.setOnLongClickListener(this);
    }

    private void initView() {
        mivCart = (ImageView) findViewById(R.id.ivCart);
        mivShare = (ImageView) findViewById(R.id.ivShare);
        mivGoods = (ImageView) findViewById(R.id.ivGoods);
    }

    @Override
    public boolean onDrag(View v, DragEvent event) {
        switch (event.getAction()) {
            case DragEvent.ACTION_DRAG_STARTED:
                Log.i("main","嘿，你选择的这件大衣。");
                break;
            case DragEvent.ACTION_DRAG_ENTERED:
                Log.i("main","哈哈，选对了，放到购物车里吧");
                break;
            case DragEvent.ACTION_DRAG_LOCATION:
                Log.i("main","别犹豫，就他了");
                break;
            case DragEvent.ACTION_DRAG_EXITED:
                if (v.getId() == R.id.ivCart) {
                    Log.i("main","别走呀，再聊一会儿");

                }
                break;
            case DragEvent.ACTION_DROP:
                if (v.getId() == R.id.ivCart) {
                    Goods goods = (Goods) event.getLocalState();
                    Log.i("main","好的，你选择的"+goods.toString()+"，快去结算吧");
                }
                break;
            case DragEvent.ACTION_DRAG_ENDED:
                Log.i("main","拖放结束");
                break;

        }


        return true;
    }

    @Override
    public boolean onLongClick(View v) {
        // 创建用于绘制影子图形的对象
        View.DragShadowBuilder builder = new View.DragShadowBuilder(v);
        Goods goods = new Goods(100015, "女式大衣");

        // 启动拖放操作
        v.startDrag(null, builder, goods, 0);
        return false;
    }


    class Goods {
        private int id;
        private String name;

        public Goods(int id, String name) {
            this.id = id;
            this.name = name;
        }

        @Override
        public String toString() {
            return "Goods{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    '}';
        }
    }


}
