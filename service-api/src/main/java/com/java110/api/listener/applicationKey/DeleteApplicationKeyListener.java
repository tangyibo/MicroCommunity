package com.java110.api.listener.applicationKey;

import com.alibaba.fastjson.JSONObject;
import com.java110.api.bmo.applicationKey.IApplicationKeyBMO;
import com.java110.api.listener.AbstractServiceApiPlusListener;
import com.java110.core.annotation.Java110Listener;
import com.java110.core.context.DataFlowContext;
import com.java110.core.event.service.api.ServiceDataFlowEvent;
import com.java110.utils.constant.ServiceCodeApplicationKeyConstant;
import com.java110.utils.util.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;

/**
 * 保存小区侦听
 * add by wuxw 2019-06-30
 */
@Java110Listener("deleteApplicationKeyListener")
public class DeleteApplicationKeyListener extends AbstractServiceApiPlusListener {

    @Autowired
    private IApplicationKeyBMO applicationKeyBMOImpl;

    @Override
    protected void validate(ServiceDataFlowEvent event, JSONObject reqJson) {
        //Assert.hasKeyAndValue(reqJson, "xxx", "xxx");
        Assert.hasKeyAndValue(reqJson, "communityId", "必填，请填写小区");

        Assert.hasKeyAndValue(reqJson, "applicationKeyId", "钥匙申请ID不能为空");

    }

    @Override
    protected void doSoService(ServiceDataFlowEvent event, DataFlowContext context, JSONObject reqJson) {


        //删除钥匙
        applicationKeyBMOImpl.deleteApplicationKey(reqJson, context);

    }

    @Override
    public String getServiceCode() {
        return ServiceCodeApplicationKeyConstant.DELETE_APPLICATIONKEY;
    }

    @Override
    public HttpMethod getHttpMethod() {
        return HttpMethod.POST;
    }

    @Override
    public int getOrder() {
        return DEFAULT_ORDER;
    }


}
