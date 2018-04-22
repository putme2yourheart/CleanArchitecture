package io.github.putme2yourheart.cleanarchitecture.data.cache.serializer;

import com.google.gson.Gson;

public class Serializer {

    private final Gson gson;

    public Serializer() {
        gson = new Gson();
    }

    /**
     * 将object序列化成为json数据
     *
     * @param object 要序列化的对象
     * @param clazz  类
     * @return json
     */
    public String serialize(Object object, Class clazz) {
        return gson.toJson(object, clazz);
    }

    /**
     * 将json数据反序列化成为类对象
     *
     * @param string json数据
     * @param <T>    类对象
     */
    public <T> T deserialize(String string, Class<T> clazz) {
        return gson.fromJson(string, clazz);
    }
}
