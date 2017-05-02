package fr.gamatico.timerank.events;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.beans.EventHandler;

/**
 * @Author Gamatico
 * @Date 09/04/2017
 */
public class EventManager {

    private JavaPlugin instance;

    public EventManager(JavaPlugin instance){
        this.instance = instance;
    }

    public void registerEvents(PluginManager pm){
        pm.registerEvents(new PlayerJoin(), instance);
        pm.registerEvents(new PlayerKick(), instance);
        pm.registerEvents(new PlayerQuit(), instance);
    }
}
