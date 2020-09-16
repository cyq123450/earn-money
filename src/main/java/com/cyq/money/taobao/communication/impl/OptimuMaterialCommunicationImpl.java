package com.cyq.money.taobao.communication.impl;

import com.cyq.money.taobao.commons.TaoBaoPropertiesReader;
import com.cyq.money.taobao.communication.OptimuMaterialCommunication;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.TbkDgMaterialOptionalRequest;
import com.taobao.api.response.TbkDgMaterialOptionalResponse;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author cyq
 * @description 精选物料通信接口实现类
 * @date 2020/9/16 21:55
 */
@Service
public class OptimuMaterialCommunicationImpl implements OptimuMaterialCommunication {

    private static final String URL = TaoBaoPropertiesReader.getPros("taobao.url");
    private static final String APP_KEY = TaoBaoPropertiesReader.getPros("taobao.app-key");
    private static final String SECRET = TaoBaoPropertiesReader.getPros("taobao.app-secret");
    private static final String ADZONE_ID = TaoBaoPropertiesReader.getPros("taobao.adzone-id");

    @Override
    public Map<String, Object> getOptimuMaterial(Map<String, String> params) throws Exception {
        TaobaoClient client = new DefaultTaobaoClient(URL, APP_KEY, SECRET);

        TbkDgMaterialOptionalRequest req = new TbkDgMaterialOptionalRequest();
        req.setAdzoneId(Long.valueOf(ADZONE_ID));

       for(String key : params.keySet()) {
           String value = params.get(key);
           if (value == null || value.equals("")) {
               continue;
           }

           switch (key) {
               case "material_id": req.setMaterialId(Long.valueOf(value)); break;
               case "q": req.setQ(value);
               default:break;
           }

       }
        TbkDgMaterialOptionalResponse rsp = client.execute(req);
        String body = rsp.getBody();
        System.out.println(body);
        return null;
    }
}
