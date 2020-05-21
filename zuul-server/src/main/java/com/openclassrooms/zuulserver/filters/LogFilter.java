package com.openclassrooms.zuulserver.filters;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.exception.ZuulException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class LogFilter extends ZuulFilter {

    Logger log = LoggerFactory.getLogger(this.getClass());

    /*
    ** Allow you to manipulate request, tho I have yet not idea how to use this.
    ** refresher : https://openclassrooms.com/fr/courses/4668216-optimisez-votre-architecture-microservices/5176838-creez-une-api-gateway-pour-votre-application-zuul
    */
    @Override
    public Object run() throws ZuulException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean shouldFilter() {
        // TODO Auto-generated method stub
        return false;
    }


    // filtrer order (from 1 to int_limit)
    @Override
    public int filterOrder() {
        // TODO Auto-generated method stub
        return 0;
    }
    // if return : "pre" the gateway will filter before reaching the microservice
    // if return : "post" will filter on the way back from the microservice to the client throught the gateway
    @Override
    public String filterType() {
        // TODO Auto-generated method stub
        return null;
    }
    
}