package com.cnluminous.kookticketbot;

import java.util.ArrayList;

public class Config {
    public static ArrayList<String> classification = (ArrayList<String>) Main.getInstance().getConfig().getStringList("classification");
    public static ArrayList<Integer> adminRol = (ArrayList<Integer>) Main.getInstance().getConfig().getIntegerList("adminRol");
    public static String groupId = Main.getInstance().getConfig().getString("groupId");
}
