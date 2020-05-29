package com.violet.collection.updater;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p></p>
 *
 * @author xlp
 * @date 2020/5/4 上午11:01
 * @since 1.0.0
 */
public class CollectionUpdaterTest {

    @Test
    public void foo(){
        List<User> elements = new ArrayList<>();
        elements.add(new User("1","小明"));
        elements.add(new User("2","小张"));
        elements.add(new User("3","小王"));
        elements.add(new User("4","小李"));
//        CollectionUpdater<User, String> instance = CollectionUpdater.getInstance(elements, UserCollectionUpdater.class);
        CollectionUpdater<User, String> instance = CollectionUpdater.getInstance(elements, User::getId);

        List<String> identifier = new ArrayList<>();
        Collections.addAll(identifier, "3", "4", "5");

        System.out.println("新增元素："+instance.calculateNewElementIdentifier(identifier));
        System.out.println("移除元素："+instance.calculateRemoveElementIdentifier(identifier));
        System.out.println("新增元素："+instance.modifyCollections(identifier));
        System.out.println("剩余元素："+elements);

        instance.addElements(identifier,identifiers->
            identifiers.stream().map(ident->new User(ident,"小-"+ident)).collect(Collectors.toList()));

        System.out.println("新元素集："+elements);
    }
}
