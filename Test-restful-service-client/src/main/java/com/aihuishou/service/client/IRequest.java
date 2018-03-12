package com.aihuishou.service.client;

import com.aihuishou.service.client.EnumMethod;

import javax.ws.rs.core.MediaType;
import java.util.HashMap;
import java.util.List;

/**
 * Created by james on 2017/5/31.
 */
public interface IRequest {

    MediaType getMediaType();

    EnumMethod getMethod();

    HashMap<String, List<String>> getQueryParams();

    HashMap<String, List<String>> getFormParams();

    HashMap<String, String> getHeaderParams();

    Object getBody();

    String getPath();

}
