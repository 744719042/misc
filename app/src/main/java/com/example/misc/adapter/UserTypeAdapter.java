package com.example.misc.adapter;

import com.example.misc.model.User;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

/**
 * Created by Administrator on 2018/3/13.
 */

public class UserTypeAdapter extends TypeAdapter<User> {
    @Override
    public void write(JsonWriter out, User value) throws IOException {
        out.beginObject();
        out.name("id").value(value.getId());
        out.name("name").value(value.getName());
        out.name("age").value(value.getAge());
        out.endObject();
    }

    @Override
    public User read(JsonReader in) throws IOException {
        User user = new User();
        in.beginObject();
        while (in.hasNext()) {
            switch (in.nextName()) {
                case "id":
                    user.setId(in.nextString());
                    break;
                case "name":
                    user.setName(in.nextString());
                    break;
                case "age":
                    int age = in.nextInt();
                    user.setAge(age);
                    user.setAdult(age == 18 ? 0 : age > 18 ? 1 : 2);
                    break;
            }
        }
        in.endObject();
        return user;
    }
}
