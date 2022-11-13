package net.alkitmessenger.user;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.function.Predicate;
import java.util.stream.Stream;

@Data
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)

@Entity
@Table(name = "private_messages")
public class PrivateMessages {

    @OneToMany
    User user1;

    @OneToMany
    User user2;

    @ManyToOne
    ObservableList<Message> messages;

    public PrivateMessages(User user1, User user2) {

        this.user1 = user1;
        this.user2 = user2;

        messages = FXCollections.observableArrayList();

    }

    public Stream<Message> filter(Predicate<Message> predicate) {

        return messages.stream().filter(predicate);

    }
}