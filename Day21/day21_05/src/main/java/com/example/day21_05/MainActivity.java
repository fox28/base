package com.example.day21_05;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.day21_05.fragment.BoutiqueFragment;
import com.example.day21_05.fragment.CartFragment;
import com.example.day21_05.fragment.CategoryFragment;
import com.example.day21_05.fragment.CollectFragment;
import com.example.day21_05.fragment.NewGoodsFragment;



public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Fragment[] mFragment;
    ViewPager mViewPager;
    TextView mtvCategory, mtvCart, mtvContact;// mtvNewGoods, mtvBoutique,
    Button mtvNewGoods,mtvBoutique;

    GoodsAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initFragment();
        initView();

        setListener();
    }

    private void setListener() {
        mtvNewGoods.setOnClickListener(this);
        mtvBoutique.setOnClickListener(this);
        mtvCategory.setOnClickListener(this);
        mtvCart.setOnClickListener(this);
        mtvContact.setOnClickListener(this);

        setOnPageChangeListener();
    }

    private void setOnPageChangeListener() {
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                initDrawables();
                switch (position) {
                    case 0:
                        setDrawable(mtvNewGoods, R.drawable.menu_item_conversation_selected, Color.rgb(0xff, 0x66, 0xff));
                        break;
                    case 1:
                        setDrawable(mtvBoutique, R.drawable.menu_item_boutique_selected, Color.rgb(0xff, 0x66, 0xff));
                        break;
                    case 2:
                        setDrawable(mtvCategory, R.drawable.menu_item_category_selected, Color.rgb(0xff, 0x66, 0xff));
                        break;
                    case 3:
                        setDrawable(mtvCart, R.drawable.menu_item_cart_selected, Color.rgb(0xff, 0x66, 0xff));
                        break;
                    case 4:
                        setDrawable(mtvContact, R.drawable.menu_item_contact_selected, Color.rgb(0xff, 0x66, 0xff));
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }




    private void initDrawables() {
        setDrawable(mtvNewGoods, R.drawable.menu_item_new_goods_normal,Color.WHITE);
        setDrawable(mtvBoutique,R.drawable.menu_item_boutique_normal, Color.WHITE);
        setDrawable(mtvCategory,R.drawable.menu_item_category_normal, Color.WHITE);
        setDrawable(mtvCart,R.drawable.menu_item_cart_normal, Color.WHITE);
        setDrawable(mtvContact,R.drawable.menu_item_contact_normal, Color.WHITE);
    }

    /**
     * 设置指定TextVi显示的图片（drawable）和文字的颜色
     * @param textView  菜单项view
     * @param drawableId    图片的资源Id
     * @param textColor     菜单项文字的颜色
     */
    private void setDrawable(TextView textView, int drawableId, int textColor) {
        textView.setTextColor(textColor);

        // 通过图片的id获取图片
        Drawable drawableTop = ContextCompat.getDrawable(this, drawableId);

        // 设置drawable的尺寸
        drawableTop.setBounds(0, 0, drawableTop.getMinimumWidth(),drawableTop.getMinimumHeight());

        // 设置TextView为上图下字
        textView.setCompoundDrawables(null, drawableTop, null, null);
    }

    private void initView() {
        mViewPager = (ViewPager) findViewById(R.id.vpGoods);
        mtvNewGoods = (Button) findViewById(R.id.tvNewGoods);
        mtvBoutique = (Button) findViewById(R.id.tvBoutique);
        mtvCategory = (TextView) findViewById(R.id.tvCategory);
        mtvCart = (TextView) findViewById(R.id.tvCart);
        mtvContact = (TextView) findViewById(R.id.tvContacts);


        mAdapter = new GoodsAdapter(getSupportFragmentManager(), mFragment);
        mViewPager.setAdapter(mAdapter);
    }

    private void initFragment() {
        mFragment = new Fragment[5];
        mFragment[0] = new NewGoodsFragment();
        mFragment[1] = new BoutiqueFragment();
        mFragment[2] = new CategoryFragment();
        mFragment[3] = new CartFragment();
        mFragment[4] = new CollectFragment();
    }



    @Override
    public void onClick(View v) {
        initDrawables();
        switch (v.getId()) {
            case R.id.tvNewGoods:
                setDrawable(mtvNewGoods, R.drawable.menu_item_new_goods_selected, Color.rgb(0xff, 0x66, 0xff));
                mViewPager.setCurrentItem(0);// 设置当前页码
                break;
            case R.id.tvBoutique:
                setDrawable(mtvBoutique, R.drawable.menu_item_boutique_selected, Color.rgb(0xff, 0x66, 0xff));
                mViewPager.setCurrentItem(1);
                break;
            case R.id.tvCategory:
                setDrawable(mtvCategory, R.drawable.menu_item_category_selected, Color.rgb(0xff, 0x66, 0xff));
                mViewPager.setCurrentItem(2);
                break;
            case R.id.tvCart:
                setDrawable(mtvCart, R.drawable.menu_item_category_selected, Color.rgb(0xff, 0x66, 0xff));
                mViewPager.setCurrentItem(3);
                break;
            case R.id.tvContacts:
                setDrawable(mtvContact, R.drawable.menu_item_category_selected, Color.rgb(0xff, 0x66, 0xff));
                mViewPager.setCurrentItem(4);
                break;
        }


    }



    class GoodsAdapter extends FragmentPagerAdapter {
        Fragment[] fragments;

        public GoodsAdapter(FragmentManager fm, Fragment[] fragments) {
            super(fm);
            this.fragments = fragments;
        }

        @Override
        public Fragment getItem(int position) {
            return fragments[position];
        }

        @Override
        public int getCount() {
            return fragments.length;
        }
    }
}

