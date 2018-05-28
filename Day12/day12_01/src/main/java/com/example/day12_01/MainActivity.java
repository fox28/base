package com.example.day12_01;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;

import com.example.day12_01.dao.CategoryDao;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ExpandableListView melvCategory;
    ArrayList<Integer> mGroupList;
    ArrayList<ArrayList<Integer>> mChildList;

    CategoryAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initData();
        initView();
    }

    /*private void initView() {
        melvCategory = (ExpandableListView) findViewById(R.id.elvCategory);
        mAdapter = new CategoryAdapter(this, mGroupList, mChildList);
        melvCategory.setAdapter(mAdapter);
    }

    private void initData() {
        mGroupList= CategoryDao.getGroupList();
        mChildList=CategoryDao.getChildList();
    }*/
    private void initView() {
        melvCategory = (ExpandableListView) findViewById(R.id.elvCategory);
        mAdapter = new CategoryAdapter(this, mGroupList, mChildList);
        melvCategory.setAdapter(mAdapter);
    }


    private void initData() {
        mGroupList = CategoryDao.getGroupList();
        mChildList = CategoryDao.getChildList();

    }

    class CategoryAdapter extends BaseExpandableListAdapter {
        Context context;
        ArrayList<Integer> groupList;
        ArrayList<ArrayList<Integer>> childList;

        public CategoryAdapter(Context context, ArrayList<Integer> groupList, ArrayList<ArrayList<Integer>> childList) {
            this.context = context;
            this.groupList = groupList;
            this.childList = childList;
        }


        @Override
        public int getGroupCount() {
            return groupList.size();
        }

        @Override
        public int getChildrenCount(int groupPosition) {
            return childList.get(groupPosition).size();
        }

        @Override
        public Integer getGroup(int groupPosition) {
            return groupList.get(groupPosition);
        }

        @Override
        public Integer getChild(int groupPosition, int childPosition) {
            return childList.get(groupPosition).get(childPosition);
        }

        @Override
        public long getGroupId(int groupPosition) {
            return 0;
        }

        @Override
        public long getChildId(int groupPosition, int childPosition) {
            return 0;
        }

        @Override
        public boolean hasStableIds() {
            return false;
        }

        @Override
        public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
            GroupHolder holder = null;
            if (convertView == null) {
                convertView = View.inflate(context, R.layout.item_group, null);
                holder = new GroupHolder();
                holder.ivGroup = (ImageView) convertView.findViewById(R.id.ivGroup);
                holder.ivExpand = (ImageView) convertView.findViewById(R.id.ivExpand);
                convertView.setTag(holder);
            } else {
                holder = (GroupHolder) convertView.getTag();
            }
            holder.ivGroup.setImageResource( getGroup(groupPosition));
            if (isExpanded) {
                holder.ivExpand.setImageResource(R.drawable.expand_off);
            } else {
                holder.ivExpand.setImageResource(R.drawable.expand_on);
            }
            return convertView;
        }

        class GroupHolder {
            ImageView ivGroup, ivExpand;
        }

        class ChildHolder {
            ImageView ivChild;
        }


        @Override
        public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
            ChildHolder holder = null;

            if (convertView == null) {
                convertView = View.inflate(context, R.layout.item_child, null);
                holder = new ChildHolder();
                holder.ivChild = (ImageView) convertView.findViewById(R.id.ivChild);
                convertView.setTag(holder);
            } else {
                holder = (ChildHolder) convertView.getTag();
            }
            holder.ivChild.setImageResource(getChild(groupPosition, childPosition));
            return convertView;
        }

        @Override
        public boolean isChildSelectable(int groupPosition, int childPosition) {
            return true;
        }
    }

   /* class CategoryAdapter extends BaseExpandableListAdapter {
        Context context;
        ArrayList<Integer> groupList;
        ArrayList<ArrayList<Integer>> childList;

        public CategoryAdapter(Context context, ArrayList<Integer> groupList, ArrayList<ArrayList<Integer>> childList) {
            this.context = context;
            this.groupList = groupList;
            this.childList = childList;
        }

        @Override
        public int getGroupCount() {
            return groupList.size();
        }

        @Override
        public int getChildrenCount(int groupPosition) {
            return childList.get(groupPosition).size();
        }

        @Override
        public Integer getGroup(int groupPosition) {
            return groupList.get(groupPosition);
        }

        @Override
        public Integer getChild(int groupPosition, int childPosition) {
            return childList.get(groupPosition).get(childPosition);
        }

        @Override
        public long getGroupId(int groupPosition) {
            return 0;
        }

        @Override
        public long getChildId(int groupPosition, int childPosition) {
            return 0;
        }

        @Override
        public boolean hasStableIds() {
            return false;
        }

        @Override
        public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
            GroupHolder holder=null;
            if (convertView == null) {
                convertView = View.inflate(context, R.layout.item_group, null);
                holder = new GroupHolder();
                holder.ivGroup = (ImageView) convertView.findViewById(R.id.ivGroup);
                holder.ivExpand = (ImageView) convertView.findViewById(R.id.ivExpand);
                convertView.setTag(holder);
            } else {
                holder= (GroupHolder) convertView.getTag();
            }
            holder.ivGroup.setImageResource(getGroup(groupPosition));
            if (isExpanded) {
                holder.ivExpand.setImageResource(R.drawable.expand_off);
            } else {
                holder.ivExpand.setImageResource(R.drawable.expand_on);
            }
            return convertView;
        }

        class GroupHolder{
            ImageView ivGroup,ivExpand;
        }
        @Override
        public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
            return null;
        }

        @Override
        public boolean isChildSelectable(int groupPosition, int childPosition) {
            return true;
        }
    }

    */





















}
