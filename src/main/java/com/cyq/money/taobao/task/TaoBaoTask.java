package com.cyq.money.taobao.task;

import com.cyq.money.taobao.commons.TaoBaoPropertiesReader;
import com.cyq.money.taobao.service.TaoBaoMaterialService;
import com.cyq.money.vo.PageHelperParamVO;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 淘宝相关的定时任务
 */
// @Component
public class TaoBaoTask {

    // @Autowired
    private TaoBaoMaterialService taoBaoMaterialService;

    // @Autowired
    private TaoBaoPropertiesReader taoBaoPropertiesReader;

    private Logger logger = LoggerFactory.getLogger(TaoBaoTask.class);

    /**
     * 该定时任务主要是为了获取淘宝的一级类目信息
     * 每月的一号3点1分1秒启动定时任务
     */
    // @Scheduled(cron = "1 1 3 1 * ? ")
    public void getTaoBaoFirstCategory() {
        logger.info("启动淘宝平台的定时任务 --- 获取商品一级类目信息");

        // 本次定时任务过滤数据暂存
        Map<String, String> datas = new HashMap<>();

        for(int i = 1; ;i++) {
            try {
                PageHelperParamVO params = new PageHelperParamVO();
                params.setPageNum(i++);
                params.setPageSize(60);
                   // 以实时榜单为基础数据进行获取
                List<Map> realTimeList = taoBaoMaterialService.getRealTimeList(params);
                if (realTimeList == null || realTimeList.size() <= 0) {
                    break;
                }

                for(Map map1 : realTimeList) {
                    // 获取一级类目ID
                    Integer transferVal = (Integer)map1.get("level_one_category_id");
                    String levelOneCategoryId = String.valueOf(String.valueOf(transferVal.intValue()))  ;
                    // 获取一级类目名称
                    String levelOneCategoryName = (String) map1.get("level_one_category_name");

                    if (StringUtils.isNotEmpty(levelOneCategoryId) && StringUtils.isNotEmpty(levelOneCategoryName)) {
                        String val = datas.get(levelOneCategoryId);
                        if (val == null) {
                            datas.put(levelOneCategoryId, levelOneCategoryName);
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        // 过滤数据暂存处理
        List<Map<String,String>> list = new ArrayList<>();
        for(String key : datas.keySet()) {
            Map map = new HashMap<String, String>();
            map.put(key, datas.get(key));
            list.add(map);
        }
        // 赋值
        if (list.size() > 0) {
            // TaoBaoCache.setFistCategory(list);
        }

        logger.info("结束淘宝平台的定时任务 --- 获取商品一级类目信息");
    }

}
