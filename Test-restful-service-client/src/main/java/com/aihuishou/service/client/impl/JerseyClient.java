package com.aihuishou.service.client.impl;

import com.aihuishou.service.client.IClient;
import com.aihuishou.service.client.IRequest;
import com.aihuishou.service.client.UniformInvokeException;
import com.aihuishou.service.client.WebResourceName;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.core.util.MultivaluedMapImpl;

import javax.ws.rs.core.MultivaluedMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by james on 2017/5/31.
 */
public class JerseyClient implements IClient {

    private final Client client;

    private final String serviceUrl;

    public JerseyClient(Client client, String serviceUrl) {
        this.client = client;
        this.serviceUrl = serviceUrl;
    }

    public Client getClient() {
        return client;
    }


    @Override
    public <R> R invoke(IRequest request, Class<R> returnType) {
        ClientResponse response = getResponse(request);
        if (response.getStatus() == 200) {
            return response.getEntity(returnType);
        } else if (response.getStatus() == 204) {
            return null;
        } else {
            throw new UniformInvokeException(response.getStatus(), response.getStatusInfo().toString());
        }
    }

    @Override
    public <R> R invoke(IRequest request, GenericType<R> returnType) {
        ClientResponse response = getResponse(request);
        if (response.getStatus() == 200) {
            GenericType<R> genericType = new GenericType<>(returnType.getType());
            return response.getEntity(genericType);
        } else if (response.getStatus() == 204) {
            return null;
        } else {
            throw new UniformInvokeException(response.getStatus(), response.getStatusInfo().toString(),
                    new Exception(response.getEntity(String.class)));
        }
    }

    private ClientResponse getResponse(IRequest request) {
        WebResource resource = getResource(request);
        if (request.getPath() != null) {
            resource = resource.path(request.getPath());
        }
        if (request.getQueryParams() != null) {
            MultivaluedMap<String, String> queryParamMap = convertHashMap(request.getQueryParams());
            resource = resource.queryParams(queryParamMap);
        }
        WebResource.Builder builder = resource.type(request.getMediaType());
        if (request.getHeaderParams() != null) {
            for (Map.Entry<String, String> header : request.getHeaderParams().entrySet()) {
                builder = builder.header(header.getKey(), header.getValue());
            }
        }
        if (request.getBody() != null) {
            builder = builder.entity(request.getBody());
        }
        ClientResponse response = null;
        switch (request.getMethod()) {
            case GET:
                response = builder.get(ClientResponse.class);
                break;
            case POST:
                if(request.getFormParams() != null)
                    response = builder.post(ClientResponse.class, convertHashMap(request.getFormParams()));
                else {
                    response = builder.post(ClientResponse.class);
                }
                break;
            case PUT:
                if(request.getFormParams() != null)
                    response = builder.put(ClientResponse.class, convertHashMap(request.getFormParams()));
                else {
                    response = builder.put(ClientResponse.class);
                }
                break;
            case DELETE:
                if(request.getFormParams() != null)
                    response = builder.delete(ClientResponse.class, convertHashMap(request.getFormParams()));
                else {
                    response = builder.delete(ClientResponse.class);
                }
                break;
            default:
                response = builder.get(ClientResponse.class);
                break;
        }

        return response;
    }

    private MultivaluedMap<String, String> convertHashMap(HashMap<String, List<String>> map) {
        if (map == null) return null;
        MultivaluedMap<String, String> resultMap = new MultivaluedMapImpl();
        for (Map.Entry<String, List<String>> entry : map.entrySet()) {
            for (String value : entry.getValue()) {
                resultMap.add(entry.getKey(), value);
            }
        }
        return resultMap;
    }

    private WebResource getResource(IRequest request) {
        WebResourceName resourceName = request.getClass().getAnnotation(WebResourceName.class);
        if (resourceName != null && resourceName.value().length() > 0) {
            return client.resource(String.format("%s/%s", this.serviceUrl, resourceName.value()));
        } else {
            return client.resource(this.serviceUrl);
        }
    }
}
