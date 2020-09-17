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
import org.springframework.stereotype.Service;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.SocketAddress;
import java.util.Map;

/**
 * @author cyq
 * @description 物料类通信接口实现类
 * @date 2020/9/16 21:55
 */
@Service
public class MaterialCommunicationImpl implements MaterialCommunication {

    private static final String URL = TaoBaoPropertiesReader.getPros("taobao.url");
    private static final String APP_KEY = TaoBaoPropertiesReader.getPros("taobao.app-key");
    private static final String SECRET = TaoBaoPropertiesReader.getPros("taobao.app-secret");
    private static final String ADZONE_ID = TaoBaoPropertiesReader.getPros("taobao.adzone-id");

    @Override
    public Map getOptimuMaterial(PageHelperParamVO params) throws Exception {
        DefaultTaobaoClient client = new DefaultTaobaoClient(URL, APP_KEY, SECRET);

        SocketAddress sa = new InetSocketAddress("proxy.cmcc", 8080);
        Proxy proxy = new Proxy(Proxy.Type.HTTP, sa);

        client.setProxy(proxy);

        // 获取业务参数
        Map<String, String> param = params.getParams();

        TbkDgMaterialOptionalRequest req = new TbkDgMaterialOptionalRequest();
        req.setAdzoneId(Long.valueOf(ADZONE_ID));

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
        System.out.println(body);
        Map map = JSONObject.parseObject(body, Map.class);
        return map;
    }

    public Map getOptimusMaterial(PageHelperParamVO params) throws ApiException {
        DefaultTaobaoClient client = new DefaultTaobaoClient(URL, APP_KEY, SECRET);

        SocketAddress sa = new InetSocketAddress("proxy.cmcc", 8080);
        Proxy proxy = new Proxy(Proxy.Type.HTTP, sa);

        client.setProxy(proxy);

        // 获取业务参数
        Map<String, String> param = params.getParams();

        TbkDgOptimusMaterialRequest req = new TbkDgOptimusMaterialRequest();
        req.setAdzoneId(Long.valueOf(ADZONE_ID));

        for(String key : param.keySet()) {
            String value = param.get(key);
            if (value == null || value.equals("")) {
                continue;
            }

            switch (key) {
                case "material_id": req.setMaterialId(Long.valueOf(value)); break;
                default: break;
            }

        }

        // 设置分页信息(默认为第一页，每页20条数据)
        req.setPageNo(params.getPageNum());
        req.setPageSize(params.getPageSize());

        TbkDgOptimusMaterialResponse rsp = client.execute(req);
        String body = rsp.getBody();
        System.out.println(rsp.getBody());
        Map map = JSONObject.parseObject(body, Map.class);
        return map;
    }

}
