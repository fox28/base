package com.example.day18_05;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    TextView mtvNewGoods, mtvBoutique, mtvCategory, mtvCart, mtvContact;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        setListener();
    }

    private void setListener() {
        mtvNewGoods.setOnClickListener(this);
        mtvBoutique.setOnClickListener(this);
        mtvCategory.setOnClickListener(this);
        mtvCart.setOnClickListener(this);
        mtvContact.setOnClickListener(this);
    }

    // 实例化各个TextView
    private void initView() {
        mtvNewGoods = (TextView) findViewById(R.id.tvNewGoods);
        mtvBoutique = (TextView) findViewById(R.id.tvBoutique);
        mtvCategory = (TextView) findViewById(R.id.tvCategory);
        mtvCart = (TextView) findViewById(R.id.tvCart);
        mtvContact = (TextView) findViewById(R.id.tvContact);
    }

    @Override
    public void onClick(View v) {
        // 设置菜单按钮未选中时的状态
        initDrawable();
        switch (v.getId()) {
            case R.id.tvNewGoods:
                setDrawable(mtvNewGoods,Color.rgb(0xff,0x66,0xff),R.drawable.menu_item_new_goods_selected);
                break;
            case R.id.tvBoutique:
                setDrawable(mtvBoutique,Color.rgb(0xff,0x66,0xff),R.drawable.menu_item_boutique_selected);
                break;
            case R.id.tvCategory:
                setDrawable(mtvCategory,Color.rgb(0xff,0x66,0xff),R.drawable.menu_item_category_selected);
                break;
            case R.id.tvCart:
                setDrawable(mtvCart,Color.rgb(0xff,0x66,0xff),R.drawable.menu_item_cart_selected);
                break;
            case R.id.tvContact:
                setDrawable(mtvContact,Color.rgb(0xff,0x66,0xff),R.drawable.menu_item_contact_selected);
                break;
        }

    }

    private void initDrawable() {
        setDrawable(mtvNewGoods, Color.BLACK, R.drawable.menu_item_new_goods_normal);
        setDrawable(mtvBoutique, Color.BLACK, R.drawable.menu_item_boutique_normal);
        setDrawable(mtvCategory, Color.BLACK, R.drawable.menu_item_category_normal);
        setDrawable(mtvCart, Color.BLACK, R.drawable.menu_item_cart_normal);
        setDrawable(mtvContact, Color.BLACK, R.drawable.menu_item_contact_normal);
    }

    private void setDrawable(TextView tv, int textColor , int drawableId) {
        tv.setTextColor(textColor);
        Drawable drawable = ContextCompat.getDrawable(this, drawableId);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        tv.setCompoundDrawables(null,drawable,null,null);
    }
}
