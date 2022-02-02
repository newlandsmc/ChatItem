package com.semivanilla.chatitem;

import com.semivanilla.chatitem.config.Config;
import com.semivanilla.chatitem.listener.ChatListener;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class ChatItem extends JavaPlugin {

    private static ChatItem instance;

    public void onEnable() {
        instance = this;
        Config.init();

        registerListener(new ChatListener());
    }

    @Override
    public void onDisable() {
        instance = null;
    }

    public void registerListener(Listener... listeners) {
        for (Listener listener : listeners) {
            getServer().getPluginManager().registerEvents(listener, this);
        }
    }

    public static ChatItem getInstance() {
        return instance;
    }
}
