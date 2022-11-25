package net.alkitmessenger.user;

import lombok.NonNull;
import lombok.Value;
import net.alkitmessenger.util.HibernateUtil;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

@Value
public class UserManager {

    Map<Long, User> users = new HashMap<>();

    public UserManager() {

    }

    public void createUser(long id) {

        if (users.containsKey(id))
            return;

        User user = new User(id);
        users.put(id, user);

        saveUser(user);

    }

    public User getUserByID(long id) {

        if (!users.containsKey(id))
            createUser(id);

        return users.get(id);

    }

    public User getUserByID(@NonNull String id) {

        return getUserByID(Long.parseLong(id));

    }

    public User getUserByNameAndDisplayID(@NonNull String name, @NonNull String displayID) {

        return users.values()
                .stream()
                .filter(user -> user.getDisplayID().equals(displayID))
                .filter(user -> user.getName().equals(name))
                .toList()
                .get(0);

    }

    public User getByMail(@NotNull String mail) {

        return users.values()
                .stream()
                .filter(user -> user.getMail().equals(mail))
                .toList()
                .get(0);

    }

    public void saveUser(@NonNull User user) {

        System.out.println("save " + user.getId());
        HibernateUtil.saveOrUpdate(user);

    }

    public void saveAll() {

        users.values().forEach(this::saveUser);

    }
}