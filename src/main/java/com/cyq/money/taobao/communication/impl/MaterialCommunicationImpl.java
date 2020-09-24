package com.cyq.money.taobao.communication.impl;

import com.alibaba.fastjson.JSONObject;
import com.cyq.money.taobao.commons.TaoBaoPropertiesReader;
import com.cyq.money.taobao.communication.MaterialCommunication;
import com.cyq.money.vo.PageHelperParamVO;
import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.TbkDgMaterialOptionalRequest;
import com.taobao.api.request.TbkDgOptimusMaterialRequest;
import com.taobao.api.response.TbkDgMaterialOptionalResponse;
import com.taobao.api.response.TbkDgOptimusMaterialResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.SocketAddress;
import java.util.List;
import java.util.Map;

/**
 * @author cyq
 * @description 物料类通信接口实现类
 * @date 2020/9/16 21:55
 */
@Service
public class MaterialCommunicationImpl implements MaterialCommunication {

    @Autowired
    private TaoBaoPropertiesReader taoBaoPropertiesReader;

    @Override
    public Map getOptimuMaterial(PageHelperParamVO params) throws Exception {
        DefaultTaobaoClient client = new DefaultTaobaoClient(taoBaoPropertiesReader.getVal("url"), taoBaoPropertiesReader.getVal("app-key"), taoBaoPropertiesReader.getVal("app-secret"));

        SocketAddress sa = new InetSocketAddress("proxy.cmcc", 8080);
        Proxy proxy = new Proxy(Proxy.Type.HTTP, sa);
        client.setProxy(proxy);

        // 获取业务参数
        TbkDgOptimusMaterialRequest req = new TbkDgOptimusMaterialRequest();
        req.setAdzoneId(Long.valueOf(taoBaoPropertiesReader.getVal("adzone-id")));
        optimusMaterialRequestParamReBuild(params, req);
        TbkDgOptimusMaterialResponse rsp = client.execute(req);

        // 结果集处理
        return processResult(rsp.getBody());
    }

    public Map getOptionalMaterial(PageHelperParamVO params) throws ApiException {
        DefaultTaobaoClient client = new DefaultTaobaoClient(taoBaoPropertiesReader.getVal("url"), taoBaoPropertiesReader.getVal("app-key"), taoBaoPropertiesReader.getVal("app-secret"));

        SocketAddress sa = new InetSocketAddress("proxy.cmcc", 8080);
        Proxy proxy = new Proxy(Proxy.Type.HTTP, sa);
        client.setProxy(proxy);

        // 获取业务参数
        TbkDgMaterialOptionalRequest req = new TbkDgMaterialOptionalRequest();
        req.setAdzoneId(Long.valueOf(taoBaoPropertiesReader.getVal("adzone-id")));
        materialOptionalRequestParamReBuild(params, req);
        TbkDgMaterialOptionalResponse response = client.execute(req);

        // 结果集处理
        return processResult(response.getBody());
    }

    /**
     * 淘宝客-推广者-物料搜索对接参数封装
     * @param paramVO
     * @param req
     */
    private void materialOptionalRequestParamReBuild(PageHelperParamVO paramVO, TbkDgMaterialOptionalRequest req) {
        List<Map<String, String>> params = paramVO.getParams();
        if (params != null && params.size() > 0) {
            for(Map<String, String> map : params) {
                String key = map.get("key");
                String val = map.get("val");
                switch (key) {
                    case "searchTerm":
                        req.setQ(val);       // 搜索关键词
                        break;
                    case "materialId":
                        req.setMaterialId(Long.valueOf(val)); // 精选物料池ID
                        break;
                    case "categoryId":
                        req.setCat(val);      // 类目ID
                        break;
                    default:
                        break;
                }
            }
        }
        req.setPageNo(paramVO.getPageNum());    // 页数
        req.setPageSize(paramVO.getPageSize()); // 每页数量
    }

    /**
     * 淘宝客-推广者-物料精选对接参数封装
     * @param paramVO
     * @param req
     */
    private void optimusMaterialRequestParamReBuild(PageHelperParamVO paramVO, TbkDgOptimusMaterialRequest req) {
        List<Map<String, String>> params = paramVO.getParams();
        if (params != null && params.size() > 0) {
            for(Map<String, String> map : params) {
                String key = map.get("key");
                String val = map.get("val");
                if (key == null || val == null) {
                    continue;
                }
                switch (key) {
                    case "materialId":
                        req.setMaterialId(Long.valueOf(val)); break;  // 物料池ID
                    default:
                        break;
                }
            }
        }
        req.setPageNo(paramVO.getPageNum());    // 页数
        req.setPageSize(paramVO.getPageSize()); // 每页数量
    }

    /**
     * 处理结果集
     * @param body
     * @return
     */
    private Map processResult(String body) {
        if (body == null || body.equals("")) {
            return null;
        }
        System.out.println(body);
        Map map = JSONObject.parseObject(body, Map.class);
        // 查数据过多出现
        String subCode = (String)map.get("sub_code");
        if (subCode!= null && subCode.equals("50001")) {
            return null;
        }
        return map;
    }

}
