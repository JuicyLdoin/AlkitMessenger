package net.alkitmessenger.user.message;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import net.alkitmessenger.user.User;

import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;

@Data
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)

@Entity
@Table(name = "messages")
public class Message {

    @Id
    long id;

    String text;
    long author;

    Date date;

    public Message(String text, long author) {

        id = ThreadLocalRandom.current().nextLong();

        this.text = text;
        this.author = author;

        date = new Date();

    }

    public Message(String text, User author) {

        this(text, author.getId());

    }
}