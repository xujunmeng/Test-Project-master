package com.aihuishou.service.client.spring;

import com.aihuishou.service.client.impl.JerseyClient;
import com.aihuishou.service.client.provider.FastJsonProvider;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import org.springframework.beans.factory.FactoryBean;

/**
 * Created by james on 2017/5/31.
 */
public class JerseyClientFactory implements FactoryBean<JerseyClient> {

    private String serviceUrl;

    private String dateFormate;

    private Integer threadPoolSize = 20;

    private Integer readTimeout = 10000;  //Read timeout interval property, in milliseconds

    @Override
    public JerseyClient getObject() throws Exception {
        ClientConfig config = new DefaultClientConfig();
        config.getSingletons().add(new FastJsonProvider());
        config.getProperties().put(ClientConfig.PROPERTY_THREADPOOL_SIZE, threadPoolSize);
        config.getProperties().put(ClientConfig.PROPERTY_READ_TIMEOUT, readTimeout);
        return new JerseyClient(Client.create(config), serviceUrl);
    }

    @Override
    public Class<?> getObjectType() {
        return JerseyClient.class;
    }

    @Override
    public boolean isSingleton() {
        return Boolean.TRUE;
    }

    public String getServiceUrl() {
        return serviceUrl;
    }

    public void setServiceUrl(String serviceUrl) {
        this.serviceUrl = serviceUrl;
    }

    public String getDateFormate() {
        return dateFormate;
    }

    public void setDateFormate(String dateFormate) {
        this.dateFormate = dateFormate;
    }

    public Integer getThreadPoolSize() {
        return threadPoolSize;
    }

    public void setThreadPoolSize(Integer threadPoolSize) {
        this.threadPoolSize = threadPoolSize;
    }

    public Integer getReadTimeout() {
        return readTimeout;
    }

    public void setReadTimeout(Integer readTimeout) {
        this.readTimeout = readTimeout;
    }
}
