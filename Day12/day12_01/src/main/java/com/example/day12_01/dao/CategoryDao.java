package com.example.day12_01.dao;

import com.example.day12_01.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * Created by yao on 2017/2/20.
 */

public class CategoryDao {
    /**获取大类的图片集合*/
    public static ArrayList<Integer> getGroupList() {
        Integer[] groupArr={
            R.drawable.c_01, R.drawable.c_02,R.drawable.c_03,R.drawable.c_04,
        };
        List<Integer> list = Arrays.asList(groupArr);
        ArrayList<Integer> groupList = new ArrayList<>(list);
        return groupList;
    }

    /**
     * 获取小类别的二维集合-图片
     * @return
     */

    public static  ArrayList<ArrayList<Integer>> getChildList() {
        Integer[][] childArr={
            {R.drawable.c_11,R.drawable.c_12,R.drawable.c_13,R.drawable.c_14},
            {R.drawable.c_21,R.drawable.c_22,R.drawable.c_23,R.drawable.c_24},
            {R.drawable.c_31,R.drawable.c_32,R.drawable.c_33,R.drawable.c_34},
            {R.drawable.c_41,R.drawable.c_42,R.drawable.c_43,R.drawable.c_44,R.drawable.c_45,R.drawable.c_46},
        };
        ArrayList<ArrayList<Integer>> childList = new ArrayList<>();
        for(int i=0;i<childArr.length;i++) {
            List<Integer> list = Arrays.asList(childArr[i]);
            childList.add(new ArrayList<>(list));
        }
        return childList;
    }

}
