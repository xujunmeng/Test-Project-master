package com.aihuishou.service.client;

import javax.ws.rs.core.MediaType;
import java.util.HashMap;
import java.util.List;

/**
 * Created by james on 2017/5/31.
 */
public class AbstractRequest implements IRequest {

    @Override
    public MediaType getMediaType() {
        return MediaType.APPLICATION_JSON_TYPE;
    }

    @Override
    public EnumMethod getMethod() {
        return EnumMethod.GET;
    }

    @Override
    public HashMap<String, List<String>> getQueryParams() {
        return null;
    }

    @Override
    public HashMap<String, List<String>> getFormParams() {
        return null;
    }

    @Override
    public HashMap<String, String> getHeaderParams() {
        return null;
    }

    @Override
    public Object getBody() {
        return null;
    }

    @Override
    public String getPath() {
        return null;
    }
}
