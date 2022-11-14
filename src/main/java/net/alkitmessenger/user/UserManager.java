package net.alkitmessenger.user;

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

    public User getUserByID(long id) {

        return users.get(id);

    }

    public User getUserByID(String id) {

        return users.get(Long.parseLong(id));

    }

    public User getUserByNameAndDisplayID(String name, String displayID) {

        return users.values()
                .stream()
                .filter(user -> user.getDisplayID().equals(displayID))
                .filter(user -> user.getName().equals(name))
                .toList()
                .get(0);

    }
}