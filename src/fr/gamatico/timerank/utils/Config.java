package fr.gamatico.timerank.utils;

import fr.gamatico.timerank.TimeRank;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

/**
 * @Author Gamatico
 * @Date 09/04/2017
 */
public class Config {
    public static int createPlayerConfig(String uuid){
        if(!(new File(TimeRank.getInstance().getDataFolder() + File.separator + "Players").exists())) new File(TimeRank.getInstance().getDataFolder() + File.separator + "Players").mkdir();
        File config = new File(TimeRank.getInstance().getDataFolder() + File.separator + "Players" + File.separator + uuid + ".yml");
        if(!config.exists()){
            try {
                config.createNewFile();
            } catch (IOException e) {
                System.err.println("[ERROR] Create config " + uuid + ".yml");
            }
        }
        return insertTime(uuid);
    }

    private static int insertTime(String uuid){
        File config = new File(TimeRank.getInstance().getDataFolder() + File.separator + "Players" + File.separator + uuid + ".yml");
        FileConfiguration fc = YamlConfiguration.loadConfiguration(config);
        if(fc.get("time") == null || fc.getInt("time") == 0){
            fc.set("time", 0);
            return 0;
        }

        try{
            fc.save(config);
        }catch (IOException e) {
            System.err.println("[ERROR] Save config " + uuid + ".yml");
            return 0;
        }

        return fc.getInt("time");
    }

    public static void updateConfig(String uuid, ConnectTime time){
        File config = new File(TimeRank.getInstance().getDataFolder() + File.separator + "Players" + File.separator + uuid + ".yml");
        FileConfiguration fc = YamlConfiguration.loadConfiguration(config);
        if(fc.get("time") == null){
            insertTime(uuid);
        }
        int oldTime = fc.getInt("time");
        fc.set("time", (time.getSecond() + oldTime));

        try {
            fc.save(config);
        }catch (IOException e){
            System.err.println("[ERROR] Save config " + uuid + ".yml");
        }
    }
}
