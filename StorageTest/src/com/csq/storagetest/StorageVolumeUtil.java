/**
 * description :
 * Created by csq E-mail:csqwyyx@163.com
 * github:https://github.com/John-Chen
 * 15-1-11
 * Created with IntelliJ IDEA
 */

package com.csq.storagetest;

import android.app.Activity;
import android.content.Context;
import android.os.Environment;
import android.os.StatFs;
import android.os.storage.StorageManager;
import android.text.TextUtils;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class StorageVolumeUtil {

    /**
     * 获取存储设备及容量信息
     */
    public static List<MyStorageVolume> getVolumeList(Context context){
        List<MyStorageVolume> svList = new ArrayList<MyStorageVolume>(3);
        StorageManager mStorageManager = (StorageManager)context
                .getSystemService(Activity.STORAGE_SERVICE);
        try {
            Method mMethodGetPaths = mStorageManager.getClass().getMethod("getVolumeList");
            Object[] list = (Object[]) mMethodGetPaths.invoke(mStorageManager);
            for(Object item : list){
                svList.add(new MyStorageVolume(context, item));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return svList;
    }


    /**
     * 获取已经挂载的存储设备及容量信息
     */
    public static List<MyStorageVolume> getMountedVolumeList(Context context){
        List<MyStorageVolume> result = new ArrayList<MyStorageVolume>(3);
        List<MyStorageVolume> list = getVolumeList(context);
        if(!list.isEmpty()){
            for(MyStorageVolume item : list){
                if(item.isMounted(context)){
                    result.add(item);
                }
            }
        }
        return result;
    }

    /**
     * 根据存储id获得存储卡信息
     */
    public static MyStorageVolume getStorageVolume(Context context, int storageId){
        MyStorageVolume sv = null;
        List<MyStorageVolume> ls = getVolumeList(context);
        if(!ls.isEmpty()){
            for(MyStorageVolume s : ls){
                if(s.mStorageId == storageId){
                    sv = s;
                    break;
                }
            }
        }
        return sv;
    }


    /**
     * 存储设备及容量信息
     */
    public static class MyStorageVolume{
        public int mStorageId;
        public String mPath;
        public String mDescription;
        public boolean mPrimary;
        public boolean mRemovable;
        public boolean mEmulated;
        public int mMtpReserveSpace;
        public boolean mAllowMassStorage;
        public long mMaxFileSize;  //最大文件大小。(0表示无限制)
        public String mState;      //返回null

        public MyStorageVolume(Context context, Object reflectItem){
            try {
                Method fmStorageId = reflectItem.getClass().getDeclaredMethod("getStorageId");
                fmStorageId.setAccessible(true);
                mStorageId = (Integer) fmStorageId.invoke(reflectItem);
            } catch (Exception e) {
            }

            try {
                Method fmPath = reflectItem.getClass().getDeclaredMethod("getPath");
                fmPath.setAccessible(true);
                mPath = (String) fmPath.invoke(reflectItem);
            } catch (Exception e) {
            }

            try {
                Method fmDescriptionId = reflectItem.getClass().getDeclaredMethod("getDescription");
                fmDescriptionId.setAccessible(true);
                mDescription = (String) fmDescriptionId.invoke(reflectItem);
            } catch (Exception e) {
            }
            if(mDescription == null || TextUtils.isEmpty(mDescription)){
                try {
                    Method fmDescriptionId = reflectItem.getClass().getDeclaredMethod("getDescription");
                    fmDescriptionId.setAccessible(true);
                    mDescription = (String) fmDescriptionId.invoke(reflectItem, context);
                } catch (Exception e) {
                }
            }
            if(mDescription == null || TextUtils.isEmpty(mDescription)){
                try {
                    Method fmDescriptionId = reflectItem.getClass().getDeclaredMethod("getDescriptionId");
                    fmDescriptionId.setAccessible(true);
                    int mDescriptionId = (Integer) fmDescriptionId.invoke(reflectItem);
                    if(mDescriptionId != 0){
                        mDescription = context.getResources().getString(mDescriptionId);
                    }
                } catch (Exception e) {
                }
            }

            try {
                Method fmPrimary = reflectItem.getClass().getDeclaredMethod("isPrimary");
                fmPrimary.setAccessible(true);
                mPrimary = (Boolean) fmPrimary.invoke(reflectItem);
            } catch (Exception e) {
            }

            try {
                Method fisRemovable = reflectItem.getClass().getDeclaredMethod("isRemovable");
                fisRemovable.setAccessible(true);
                mRemovable = (Boolean) fisRemovable.invoke(reflectItem);
            } catch (Exception e) {
            }

            try {
                Method fisEmulated = reflectItem.getClass().getDeclaredMethod("isEmulated");
                fisEmulated.setAccessible(true);
                mEmulated = (Boolean) fisEmulated.invoke(reflectItem);
            } catch (Exception e) {
            }

            try {
                Method fmMtpReserveSpace = reflectItem.getClass().getDeclaredMethod("getMtpReserveSpace");
                fmMtpReserveSpace.setAccessible(true);
                mMtpReserveSpace = (Integer) fmMtpReserveSpace.invoke(reflectItem);
            } catch (Exception e) {
            }

            try {
                Method fAllowMassStorage = reflectItem.getClass().getDeclaredMethod("allowMassStorage");
                fAllowMassStorage.setAccessible(true);
                mAllowMassStorage = (Boolean) fAllowMassStorage.invoke(reflectItem);
            } catch (Exception e) {
            }

            try {
                Method fMaxFileSize = reflectItem.getClass().getDeclaredMethod("getMaxFileSize");
                fMaxFileSize.setAccessible(true);
                mMaxFileSize = (Long) fMaxFileSize.invoke(reflectItem);
            } catch (Exception e) {
            }

            try {
                Method fState = reflectItem.getClass().getDeclaredMethod("getState");
                fState.setAccessible(true);
                mState = (String) fState.invoke(reflectItem);
            } catch (Exception e) {
            }
        }

        /**
         * 获取Volume挂载状态, 例如Environment.MEDIA_MOUNTED
         */
        public String getVolumeState(Context context){
            return StorageVolumeUtil.getVolumeState(context, mPath);
        }

        public boolean isMounted(Context context){
            return getVolumeState(context).equals(Environment.MEDIA_MOUNTED);
        }

        public String getDescription(){
            return mDescription;
        }

        /**
         * 获取存储设备的唯一标识
         */
        public String getUniqueFlag(){
            return "" + mStorageId;
        }

        /*public boolean isUsbStorage(){
            return mDescriptionId == android.R.string.storage_usb;
        }*/

        /**
         * 获取目录可用空间大小
         */
        public long getAvailableSize(){
            return StorageVolumeUtil.getAvailableSize(mPath);
        }

        /**
         * 获取目录总存储空间
         */
        public long getTotalSize(){
            return StorageVolumeUtil.getTotalSize(mPath);
        }

        @Override
        public String toString() {
            return "MyStorageVolume{" +
                    "\nmStorageId=" + mStorageId +
                    "\n, mPath='" + mPath + '\'' +
                    "\n, mDescription=" + mDescription +
                    "\n, mPrimary=" + mPrimary +
                    "\n, mRemovable=" + mRemovable +
                    "\n, mEmulated=" + mEmulated +
                    "\n, mMtpReserveSpace=" + mMtpReserveSpace +
                    "\n, mAllowMassStorage=" + mAllowMassStorage +
                    "\n, mMaxFileSize=" + mMaxFileSize +
                    "\n, mState='" + mState + '\'' +
                    '}' + "\n";
        }
    }

    /**
     * 获取Volume挂载状态, 例如Environment.MEDIA_MOUNTED
     */
    public static String getVolumeState(Context context, String path){
        //mountPoint是挂载点名Storage'paths[1]:/mnt/extSdCard不是/mnt/extSdCard/
        //不同手机外接存储卡名字不一样。/mnt/sdcard
        StorageManager mStorageManager = (StorageManager)context
                .getSystemService(Activity.STORAGE_SERVICE);
        String status = null;
        try {
            Method mMethodGetPathsState = mStorageManager.getClass().
                    getMethod("getVolumeState", String.class);
            return (String)mMethodGetPathsState.invoke(mStorageManager, path);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }

    public static boolean isStorageVolumeMounted(Context context, String path){
        return Environment.MEDIA_MOUNTED.equals(getVolumeState(context, path));
    }

    /**
     * 获取目录总存储空间
     */
    public static long getTotalSize(String path){
        try {
            StatFs sf = new StatFs(path);
            long blockSize = sf.getBlockSize();
            long totalCount = sf.getBlockCount();
            return totalCount * blockSize;
        } catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 获取目录可用空间大小
     */
    public static long getAvailableSize(String path){
        try {
            StatFs sf = new StatFs(path);
            long blockSize = sf.getBlockSize();
            long availableCount = sf.getAvailableBlocks();
            return availableCount * blockSize;
        } catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }

    public static String getSizeStr(long fileLength) {
        String strSize = "";
        try {
            if(fileLength >= 1024*1024*1024){
                strSize = (float)Math.round(10*fileLength/(1024*1024*1024))/10 + " GB";
            }else if(fileLength >= 1024*1024){
                strSize = (float)Math.round(10*fileLength/(1024*1024*1.0))/10 + " MB";
            }else if(fileLength >= 1024){
                strSize = (float)Math.round(10*fileLength/(1024))/10 + " KB";
            }else if(fileLength >= 0){
                strSize = fileLength + " B";
            }else {
                strSize = "0 B";
            }
        } catch (Exception e) {
            e.printStackTrace();
            strSize = "0 B";
        }
        return strSize;
    }

}