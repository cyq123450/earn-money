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
        Map<String, String> param = params.getParams();

        TbkDgMaterialOptionalRequest req = new TbkDgMaterialOptionalRequest();
        req.setAdzoneId(Long.valueOf(taoBaoPropertiesReader.getVal("adzone-id")));

       for(String key : param.keySet()) {
           String value = param.get(key);
           if (value == null || value.equals("")) {
               continue;
           }
           switch (key) {
               case "material_id": req.setMaterialId(Long.valueOf(value)); break;
               case "q": req.setQ(value); break;
               default: break;
           }

       }

        // 设置分页信息(默认为第一页，每页20条数据)
        req.setPageNo(params.getPageNum());
        req.setPageSize(params.getPageSize());

        TbkDgMaterialOptionalResponse rsp = client.execute(req);
        String body = rsp.getBody();
        if (body == null || body.equals("")) {
            return null;
        }
        System.out.println(body);
        Map map = JSONObject.parseObject(body, Map.class);
        return map;
    }

    public Map getOptionalMaterial(PageHelperParamVO params) throws ApiException {
        DefaultTaobaoClient client = new DefaultTaobaoClient(taoBaoPropertiesReader.getVal("url"), taoBaoPropertiesReader.getVal("app-key"), taoBaoPropertiesReader.getVal("app-secret"));
        SocketAddress sa = new InetSocketAddress("proxy.cmcc", 8080);
        Proxy proxy = new Proxy(Proxy.Type.HTTP, sa);
        client.setProxy(proxy);
        // 获取业务参数
        TbkDgMaterialOptionalRequest req = new TbkDgMaterialOptionalRequest();
        req.setAdzoneId(Long.valueOf(taoBaoPropertiesReader.getVal("adzone-id")));
        paramReBuild(params, req);
        TbkDgMaterialOptionalResponse response = client.execute(req);
        String body = response.getBody();
        if (body == null || body.equals("")) {
            return null;
        }
        System.out.println(response.getBody());
        Map map = JSONObject.parseObject(body, Map.class);
        // 查数据过多出现
        String subCode = (String)map.get("sub_code");
        if (subCode!= null && subCode.equals("50001")) {
            return null;
        }
        return map;
    }

    /**
     * 淘宝客-推广者-物料搜索对接参数封装
     * @param paramVO
     * @param req
     */
    private void paramReBuild(PageHelperParamVO paramVO, TbkDgMaterialOptionalRequest req) {
        List<Map<String, String>> params = paramVO.getParams();
        if (params != null && params.size() > 0) {
            for(Map<String, String> map : params) {
                for (String key : map.keySet()) {
                    switch (key) {
                        case "searchTerm":
                            req.setQ(map.get(key)); break;  // 搜索关键词
                        default:
                            break;
                    }
                }
            }
        }
        req.setPageNo(paramVO.getPageNum());    // 页数
        req.setPageSize(paramVO.getPageSize()); // 每页数量
    }

}
