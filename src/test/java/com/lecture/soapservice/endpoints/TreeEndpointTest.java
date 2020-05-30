package com.lecture.soapservice.endpoints;

import com.lecture.soapservice.entity.Tree;
import com.lecture.soapservice.exception.NoElementByThisIdException;
import com.lecture.soapservice.repository.TreeRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import tutorial.soapservice.*;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.ExpectedCount.times;

@RunWith(MockitoJUnitRunner.class)
public class TreeEndpointTest {
    private static final int ID = 1;
    private static final String FRUIT = "Cherry";
    private static final String GEO_LOCATION = "1.11111, 2.22222";

    private Tree tree;
    private ObjectFactory objectFactory;

    @InjectMocks
    private TreeEndpoint treeEndpoint;
    @Mock
    private TreeRepository repository;

    @Before
    public void setUp() throws Exception {
        tree = new Tree(ID, FRUIT, GEO_LOCATION);
        objectFactory = new ObjectFactory();
    }

    @Test
    public void getTreeShouldReturnTreeIfTreeWithGivenIdExists() {
        when(repository.findById(ID)).thenReturn(Optional.of(tree));

        GetTreeRequest request = objectFactory.createGetTreeRequest();
        request.setId(ID);
        GetTreeResponse response = treeEndpoint.getTree(request);

        assertEquals(tree.getId(), response.getTree().getId());
        assertEquals(tree.getFruit(), response.getTree().getFruit());
        assertEquals(tree.getGeoLocation(), response.getTree().getGeoLocation());
    }

    @Test (expected = NoElementByThisIdException.class)
    public void getTreeShouldReturnNoElementByThisIdExceptionIfIdNotPresent() {
        when(repository.findById(ID)).thenReturn(Optional.empty());

        GetTreeRequest request = objectFactory.createGetTreeRequest();
        request.setId(ID);
        treeEndpoint.getTree(request);
    }

    @Test
    public void createTreeShouldCreateTreePassedAsArgument() {
        CreateTreeRequest request = objectFactory.createCreateTreeRequest();
        tutorial.soapservice.Tree requestTree = objectFactory.createTree();
        requestTree.setId(tree.getId());
        requestTree.setFruit(tree.getFruit());
        requestTree.setGeoLocation(tree.getGeoLocation());
        request.setTree(requestTree);

        CreateTreeResponse response = treeEndpoint.createTree(request);

        assertEquals(tree.getId(), response.getTree().getId());
        assertEquals(tree.getFruit(), response.getTree().getFruit());
        assertEquals(tree.getGeoLocation(), response.getTree().getGeoLocation());
        verify(repository).create(any(Tree.class));
    }
}
