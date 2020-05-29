package com.lecture.soapservice.repository;

import com.lecture.soapservice.entity.Tree;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component(value = "treeRepository")
public class TreeRepository {
    private List<Tree> storage = new ArrayList<Tree>() {{
        add(new Tree(1, "Apple", "2.43456, 4.56486"));
        add(new Tree(2, "Pear", "3.45678, 4.34567"));
        add(new Tree(3, "Plum", "3.45688, 4.35555"));
    }};

    public Tree create(Tree tree) {
        tree.setId(storage.size() + 1);
        storage.add(tree);

        return tree;
    }

    public Optional<Tree> findById(int id) {
        return storage.stream()
                .filter(t -> t.getId() == id)
                .findFirst();
    }
}
