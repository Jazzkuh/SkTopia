package com.jazzkuh.sktopia.utils;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.jazzkuh.sktopia.Main;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import javax.net.ssl.HttpsURLConnection;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Utils {

    Main plugin;

    public Utils(Main plugin) {
        this.plugin = plugin;
    }

    public static String color(String input) {
        return ChatColor.translateAlternateColorCodes('&', input);
    }
    public static void sendMessage(Player player, String input) {
        player.sendMessage(Utils.color(input));
    }
    public static void sendMessage(CommandSender sender, String input) {
        sender.sendMessage(Utils.color(input));
    }
}

