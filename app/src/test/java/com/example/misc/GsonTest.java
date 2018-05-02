package com.example.misc;

import com.example.misc.adapter.UserDeserializer;
import com.example.misc.adapter.UserSerializer;
import com.example.misc.adapter.UserTypeAdapter;
import com.example.misc.model.Group;
import com.example.misc.model.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import org.junit.Test;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Administrator on 2018/3/13.
 */

public class GsonTest {
    private Gson gson = new Gson();
    private String userJson = "{\"id\":\"1000\",\"name\":\"zhangshan\",\"age\":20}";
    private String userListJson = "[{\"id\":\"1000\",\"name\":\"zhangshan\",\"age\":20},{\"id\":\"2000\",\"name\":\"lisi\",\"age\":30},{\"id\":\"3000\",\"name\":\"wangwu\",\"age\":25}]";
    private String groupJson = "{\"id\":\"200000\",\"name\":\"Android开发群\",\"userList\":[{\"id\":\"2000\",\"name\":\"lisi\",\"age\":30},{\"id\":\"3000\",\"name\":\"wangwu\",\"age\":25},{\"id\":\"1000\",\"name\":\"zhangshan\",\"age\":20}],\"count\":3}";

    @Test
    public void convertString() {
        User user = new User("1000", "zhangshan", 20);
        String jsonStr = gson.toJson(user);
        System.out.println(jsonStr);
    }

    @Test
    public void convertListString() {
        User user = new User("1000", "zhangshan", 20);
        User user2 = new User("2000", "lisi", 30);
        User user3 = new User("3000", "wangwu", 25);
        List<User> list = new ArrayList<>();
        list.add(user);
        list.add(user2);
        list.add(user3);
        String jsonStr = gson.toJson(list);
        System.out.println(jsonStr);
    }

    @Test
    public void convertObj() {
       User user = gson.fromJson(userJson, User.class);
        System.out.println(user);
    }

    @Test
    public void convertObjList() {
        List<User> userList = gson.fromJson(userListJson, new TypeToken<List<User>>(){}.getType());
        System.out.println(userList);
    }

    @Test
    public void convertComplexObj() {
        Group group = new Group();
        group.setId("200000");
        group.setName("Android开发群");
        User user = new User("1000", "zhangshan", 20);
        User user2 = new User("2000", "lisi", 30);
        User user3 = new User("3000", "wangwu", 25);
        Set<User> set = new HashSet<>();
        set.add(user);
        set.add(user2);
        set.add(user3);
        group.setUserList(set);
        group.setCount(set.size());

        String json = gson.toJson(group);
        System.out.println(json);
    }

    @Test
    public void convertComplexObjString() {
        Group group = gson.fromJson(groupJson, Group.class);
        System.out.println(group);
    }

    @Test
    public void writeToFile() throws IOException {
        FileWriter fileWriter = new FileWriter("group.json");
        JsonWriter writer = new JsonWriter(fileWriter);
        writer.beginObject();
        writer.name("id").value("20000");
        writer.name("name").value("Android开发群");
        writer.name("count").value(3);

        writer.name("userList");
        writer.beginArray();

        writer.beginObject();
        writer.name("id").value("1000");
        writer.name("name").value("zhangsan");
        writer.name("age").value(30);
        writer.endObject();

        writer.beginObject();
        writer.name("id").value("2000");
        writer.name("name").value("lisi");
        writer.name("age").value(23);
        writer.endObject();

        writer.beginObject();
        writer.name("id").value("3000");
        writer.name("name").value("wangwu");
        writer.name("age").value(38);
        writer.endObject();

        writer.endArray();

        writer.endObject();
        writer.close();
    }

    @Test
    public void readFromFile() throws IOException {
        JsonReader jsonReader = new JsonReader(new FileReader("group.json"));
        Group group = new Group();
        Set<User> userSet = new HashSet<>();
        group.setUserList(userSet);
        User user = null;
        boolean startUserList = false;
        jsonReader.beginObject();
        while (jsonReader.hasNext()) {
            switch (jsonReader.nextName()) {
                case "id":
                    if (startUserList) {
                        user = new User();
                        user.setId(jsonReader.nextString());
                    } else {
                        group.setId(jsonReader.nextString());
                    }
                    break;
                case "name":
                    if (startUserList) {
                        user.setName(jsonReader.nextString());
                    } else {
                        group.setName(jsonReader.nextString());
                    }
                    break;
                case "age":
                    user.setAge(jsonReader.nextInt());
                    userSet.add(user);
                    jsonReader.endObject();

                    if (userSet.size() == 3) {
                        jsonReader.endArray();
                    } else {
                        jsonReader.beginObject();
                    }
                    break;
                case "count":
                    group.setCount(jsonReader.nextInt());
                    break;
                case "userList":
                    jsonReader.beginArray();
                    jsonReader.beginObject();
                    startUserList = true;
                    break;
            }
        }
        jsonReader.endObject();
        System.out.println(group);
    }

    @Test
    public void testAnnotation() {
        Gson gson = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .create();
        Group group = new Group();
        group.setId("200000");
        group.setName("Android开发群");
        User user = new User("1000", "zhangshan", 20);
        User user2 = new User("2000", "lisi", 10);
        User user3 = new User("3000", "wangwu", 15);
        Set<User> set = new HashSet<>();
        set.add(user);
        set.add(user2);
        set.add(user3);
        group.setUserList(set);
        group.setCount(set.size());

        String json = gson.toJson(group);
        System.out.println(json);
    }

    @Test
    public void testTypeAdapter() {
        Gson gson = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .registerTypeAdapter(User.class, new UserTypeAdapter())
                .create();

        User user = gson.fromJson(userJson, User.class);
        System.out.println(user);
    }

    @Test
    public void testSerDeSer() {
        Gson gson = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .registerTypeAdapter(User.class, new UserDeserializer())
                .registerTypeAdapter(User.class, new UserSerializer())
                .create();

        User user = gson.fromJson(userJson, User.class);
        System.out.println(user);
    }
}
