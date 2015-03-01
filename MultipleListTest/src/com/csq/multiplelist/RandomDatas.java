/**
 * description :
 * Created by csq E-mail:csqwyyx@163.com
 * github:https://github.com/John-Chen
 * 15-2-28
 * Created with IntelliJ IDEA
 */

package com.csq.multiplelist;

import com.csq.multiplelist.models.File;
import com.csq.multiplelist.models.Folder;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomDatas {

    // ------------------------ Constants ------------------------


    // ------------------------- Fields --------------------------


    // ----------------------- Constructors ----------------------


    // -------- Methods for/from SuperClass/Interfaces -----------


    // --------------------- Methods public ----------------------

    public static List<Object> randomTestDatas(){

        Random random = new Random();

        int folderNum = random.nextInt(20);
        int fileNum = random.nextInt(30);

        List<Object> datas = new ArrayList<Object>(folderNum + fileNum);

        if(folderNum > 0){
            for(int i = 1; i <= folderNum; i++){
                datas.add(new Folder("文件夹" + i));
            }
        }

        if(fileNum > 0){
            for(int i = 1; i <= fileNum; i++){
                datas.add(new File("文件" + i));
            }
        }

        return datas;
    }

    // --------------------- Methods private ---------------------


    // --------------------- Getter & Setter ---------------------


    // --------------- Inner and Anonymous Classes ---------------


    // --------------------- logical fragments -------------------

}