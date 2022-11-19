package net.alkitmessenger.user;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;
import net.alkitmessenger.util.IDUtil;
import org.jetbrains.annotations.NotNull;

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
    String mail;

    List<Long> friends;

    Date creationDate;

    boolean logined;

    public User(@NotNull long id) {

        this.id = id;

        displayID = IDUtil.generateRandomID(3);

        name = "";
        password = "";
        mail = "";

        friends = new ArrayList<>();

        creationDate = new Date();

        logined = false;

    }

    public User(@NonNull String name, @NonNull String password, @NonNull String mail) {

        id = ThreadLocalRandom.current().nextLong();
        displayID = IDUtil.generateRandomID(3);

        this.name = name;
        this.password = password;
        this.mail = mail;

        friends = new ArrayList<>();

        creationDate = new Date();

        logined = false;

    }

    public boolean equalsPassword(@NonNull String password) {

        return this.password.equals(password);

    }
}