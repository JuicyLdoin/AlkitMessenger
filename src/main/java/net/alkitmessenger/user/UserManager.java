package net.alkitmessenger.user;

import lombok.NonNull;
import lombok.Value;
import net.alkitmessenger.util.HibernateUtil;

import java.util.HashMap;
import java.util.Map;

@Value
public class UserManager {

    Map<Long, User> users = new HashMap<>();

    public UserManager() {

        new Thread(() -> HibernateUtil.createQueryAndCallActionForEach("From User", User.class, user -> users.put(user.getId(), user))).start();

    }

    public User getUserByID(@NonNull long id) {

        return users.get(id);

    }

    public User getUserByID(@NonNull String id) {

        return users.get(Long.parseLong(id));

    }

    public User getUserByNameAndDisplayID(@NonNull String name, @NonNull String displayID) {

        return users.values()
                .stream()
                .filter(user -> user.getDisplayID().equals(displayID))
                .filter(user -> user.getName().equals(name))
                .toList()
                .get(0);

    }

    public void saveUser(@NonNull User user) {

        HibernateUtil.saveOrUpdate(user);

    }
}