package com.example.myapplication.Room.Converters;

import androidx.room.TypeConverter;

import com.example.myapplication.Room.Model.Voter;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class Converter {
    @TypeConverter
    public static ArrayList<Voter> fromString(String value) {
        Type listType = new TypeToken<ArrayList<Voter>>() {}.getType();
        return new Gson().fromJson(value, listType);
    }
    @TypeConverter
    public static String fromArrayList(ArrayList<Voter> list) {
        Gson gson = new Gson();
        String json = gson.toJson(list);
        return json;
    }
}
