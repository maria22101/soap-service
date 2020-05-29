package com.lecture.soapservice.endpoints;

import com.lecture.soapservice.repository.TreeRepository;
import org.springframework.ws.server.endpoint.annotation.Endpoint;

import javax.annotation.Resource;

@Endpoint
public class TreeEndpoint {
    private static final String NAMESPACE_URI = "http://soapService.tutorial";

    @Resource
    private TreeRepository treeRepository;
}
