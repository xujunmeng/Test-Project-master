package com.aihuishou.service.client;

import com.sun.jersey.api.client.GenericType;

/**
 * Created by james on 2017/5/31.
 */
public interface IClient {

    <R> R invoke(IRequest request, Class<R> returnType);

    <R> R invoke(IRequest request, GenericType<R> returnType);

}
