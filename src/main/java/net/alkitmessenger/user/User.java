package net.alkitmessenger.user;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
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
    String mail;
    String password;

    @ManyToMany
    @JoinTable(name="user_friends",
            joinColumns = @JoinColumn(name = "id"),
            inverseJoinColumns = @JoinColumn(name = "friend")
    )
    List<User> friends;

    Date creationDate;

    boolean logined;

    public User(long id) {

        this.id = id;

        displayID = IDUtil.generateRandomID(3);

        name = "";
        password = "";
        mail = "";

        friends = new ArrayList<>();

        creationDate = new Date();

        logined = false;

    }

    public User(@NonNull String name, @NonNull String mail, @NonNull String password) {

        id = ThreadLocalRandom.current().nextLong();
        displayID = IDUtil.generateRandomID(3);

        this.name = name;
        this.mail = mail;
        this.password = password;

        friends = new ArrayList<>();

        creationDate = new Date();

        logined = false;

    }

    public boolean equalsPassword(@NonNull String password) {

        return this.password.equals(password);

    }
}