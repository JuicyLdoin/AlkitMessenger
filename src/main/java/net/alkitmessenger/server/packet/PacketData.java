package net.alkitmessenger.server.packet;

import com.google.gson.Gson;
import lombok.Value;

@Value
public class PacketData<T> {

    String classPath;
    T data;

    public PacketData(T data) {

        classPath = data.getClass().getName();
        this.data = data;

    }

    public T deserialize(String string) {

        return (T) new Gson().fromJson(string, data.getClass());

    }
}