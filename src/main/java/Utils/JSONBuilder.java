package Utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JSONBuilder {

    public static Gson getGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();

        return gsonBuilder.create();
    }

    public static Gson getGsonArray() {
        GsonBuilder gsonBuilder = new GsonBuilder();

        return gsonBuilder.create();
    }
}
