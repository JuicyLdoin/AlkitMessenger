package net.alkitmessenger.user;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import net.alkitmessenger.util.IDUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Data
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)

@Entity
@Table(name = "users")
public class User {

    @Id
    long id;
    String displayID;

    String name;
    String password;

    @ManyToMany
    List<User> friends;

    Date creationDate;

    boolean logined;

    public User(String name) {

        id = ThreadLocalRandom.current().nextLong();
        displayID = IDUtil.generateRandomID(3);

        this.name = name;

        friends = new ArrayList<>();

        creationDate = new Date();

        logined = false;

    }
}