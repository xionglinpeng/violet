package com.violet.collection.updater;

import java.util.Collection;

/**
 * <p></p>
 *
 * @author xlp
 * @date 2020/5/4 上午11:02
 * @since 1.0.0
 */
public class UserCollectionUpdater extends CollectionUpdater<User,String> {

    public UserCollectionUpdater(Collection<User> elements) {
        super(elements);
    }

    @Override
    public String obtainElementIdentifier(User user) {
        return user.getId();
    }

}
