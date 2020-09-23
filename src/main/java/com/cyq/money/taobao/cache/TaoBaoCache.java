package com.cyq.money.taobao.cache;

import com.alibaba.fastjson.JSONReader;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 淘宝缓存实体类
 */
public class TaoBaoCache {

    // 缓存淘宝一级商品类目数据
   private static List<Map<String, String>> firstCategoryCacheList = new ArrayList<>();

    /**
     * 获取商品的一级目录
     * @return
     */
   public static List getFirstCategory() {

       if (firstCategoryCacheList == null || firstCategoryCacheList.size() <= 0) {
           synchronized (TaoBaoCache.class) {
               if (firstCategoryCacheList == null || firstCategoryCacheList.size() <= 0) {
                   try {
                       File file = ResourceUtils.getFile("classpath:data/taobao-first-category.json");
                       JSONReader jsonReader = new JSONReader(new FileReader(file));
                       Object object = jsonReader.readObject();
                       firstCategoryCacheList = (List<Map<String, String>>)object;
                   } catch (FileNotFoundException e) {
                       e.printStackTrace();
                   }
               }
           }
       }
       return firstCategoryCacheList;
   }

}
