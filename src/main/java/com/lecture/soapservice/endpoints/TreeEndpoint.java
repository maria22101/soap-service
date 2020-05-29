package com.lecture.soapservice.endpoints;

import com.lecture.soapservice.entity.Tree;
import com.lecture.soapservice.exception.NoElementByThisIdException;
import com.lecture.soapservice.repository.TreeRepository;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import tutorial.soapservice.*;

import javax.annotation.Resource;

@Endpoint
public class TreeEndpoint {
    private static final String NAMESPACE_URI = "http://soapService.tutorial";

    @Resource
    private TreeRepository treeRepository;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getTreeRequest")
    @ResponsePayload
    public GetTreeResponse getTree(@RequestPayload GetTreeRequest request) {
        ObjectFactory objectFactory = new ObjectFactory();
        GetTreeResponse response = objectFactory.createGetTreeResponse();
        tutorial.soapservice.Tree responseTree = objectFactory.createTree();

        Tree foundTree = treeRepository
                .findById(request.getId())
                .orElseThrow(NoElementByThisIdException::new);

        responseTree.setId(foundTree.getId());
        responseTree.setFruit(foundTree.getFruit());
        responseTree.setGeoLocation(foundTree.getGeoLocation());

        response.setTree(responseTree);

        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "createTreeRequest")
    @ResponsePayload
    public CreateTreeResponse createTree(@RequestPayload CreateTreeRequest request) {
        ObjectFactory objectFactory = new ObjectFactory();
        CreateTreeResponse response = objectFactory.createCreateTreeResponse();
        Tree treeToCreate = new Tree();
        tutorial.soapservice.Tree requestTree = request.getTree();

        treeToCreate.setId(requestTree.getId());
        treeToCreate.setFruit(requestTree.getFruit());
        treeToCreate.setGeoLocation(requestTree.getGeoLocation());

        treeRepository.create(treeToCreate);
        response.setTree(requestTree);

        return response;
    }
}
