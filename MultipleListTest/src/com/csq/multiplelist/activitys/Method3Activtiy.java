/**
 * description :
 * Created by csq E-mail:csqwyyx@163.com
 * github:https://github.com/John-Chen
 * 15-2-28
 * Created with IntelliJ IDEA
 */

package com.csq.multiplelist.activitys;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.csq.multiplelist.R;
import com.csq.multiplelist.RandomDatas;
import com.csq.multiplelist.models.File;
import com.csq.multiplelist.models.Folder;
import java.util.Collections;
import java.util.List;

public class Method3Activtiy extends Activity{

    // ------------------------ Constants ------------------------


    // ------------------------- Fields --------------------------

    private ListView lvList;
    private MyAdapter mAdapter;

    private LayoutInflater mInflater;

    // ----------------------- Constructors ----------------------


    // -------- Methods for/from SuperClass/Interfaces -----------

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview);

        lvList = (ListView) findViewById(R.id.lvList);
        mInflater = LayoutInflater.from(this);

        mAdapter = new MyAdapter();
        lvList.setAdapter(mAdapter);

        mAdapter.setDatas(RandomDatas.randomTestDatas());
    }

    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btnChangeDatas:
                mAdapter.setDatas(RandomDatas.randomTestDatas());
                break;

            default:

                break;
        }
    }


    // --------------------- Methods public ----------------------

    public static void launchActivity(Context mContext){
        Intent intent = new Intent();
        intent.setClass(mContext, Method3Activtiy.class);
        if(!(mContext instanceof Activity)){
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        mContext.startActivity(intent);
    }

    // --------------------- Methods private ---------------------


    // --------------------- Getter & Setter ---------------------


    // --------------- Inner and Anonymous Classes ---------------

    private class MyAdapter extends BaseAdapter{

        private static final int TYPE_FOLDER = 0;
        private static final int TYPE_FILE = 1;

        private List<Object> datas = Collections.EMPTY_LIST;

        public void setDatas(List<Object> datas) {
            if(datas == null){
                datas = Collections.EMPTY_LIST;
            }
            this.datas = datas;
            notifyDataSetChanged();
        }

        @Override
        public int getCount() {
            return datas.size();
        }

        @Override
        public Object getItem(int position) {
            return datas.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public int getViewTypeCount() {
            return 2;
        }

        @Override
        public int getItemViewType(int position) {
            if(getItem(position) instanceof Folder){
                return TYPE_FOLDER;
            }else{
                return TYPE_FILE;
            }
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            Object data = getItem(position);

            if(data instanceof Folder){
                //TYPE_FOLDER，文件夹，应该返回R.layout.listitem1对应的View
                FolderViewHolder holder = null;
                if(convertView != null){
                    //View与数据类型一致
                    holder = (FolderViewHolder) convertView.getTag();
                }else{
                    //重新inflate
                    if(convertView == null){
                        convertView = mInflater.inflate(R.layout.listitem1, null);
                        holder = new FolderViewHolder(convertView);
                        convertView.setTag(holder);
                    }
                }

                holder.setData((Folder) data);

            }else{
                //TYPE_FILE，文件，应该返回R.layout.listitem2对应的View
                FileViewHolder holder = null;
                if(convertView != null){
                    //View与数据类型一致
                    holder = (FileViewHolder) convertView.getTag();
                }else{
                    //重新inflate
                    if(convertView == null){
                        convertView = mInflater.inflate(R.layout.listitem2, null);
                        holder = new FileViewHolder(convertView);
                        convertView.setTag(holder);
                    }
                }

                holder.setData((File) data);
            }

            return convertView;
        }

    }

    private class FolderViewHolder{
        public TextView tvName;

        public FolderViewHolder(View itemView){
            tvName = (TextView) itemView.findViewById(R.id.tvName);
        }

        public void setData(Folder data) {
            tvName.setText(data.name);
        }
    }

    private class FileViewHolder{
        public TextView tvName;

        public FileViewHolder(View itemView){
            tvName = (TextView) itemView.findViewById(R.id.tvName);
        }

        public void setData(File data) {
            tvName.setText(data.name);
        }
    }

    // --------------------- logical fragments -------------------

}