package net.alkitmessenger.user.message;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import net.alkitmessenger.user.User;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Predicate;
import java.util.stream.Stream;

@Data
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)

@Entity
@Table(name = "private_messages")
public class PrivateMessages {

    @Id
    long id;

    long user1;
    long user2;

    @ManyToOne(targetEntity = Message.class)
    List<Message> messages;

    public PrivateMessages(long user1, long user2) {

        id = ThreadLocalRandom.current().nextLong();

        this.user1 = user1;
        this.user2 = user2;

        messages = new ArrayList<>();

    }

    public PrivateMessages(User user1, User user2) {

        this(user1.getId(), user2.getId());

    }

    public Stream<Message> filter(Predicate<Message> predicate) {

        return messages.stream().filter(predicate);

    }
}