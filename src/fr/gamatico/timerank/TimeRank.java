package fr.gamatico.timerank;

import fr.gamatico.timerank.command.TimeRankCmd;
import fr.gamatico.timerank.events.EventManager;
import fr.gamatico.timerank.utils.UnlockRankChecker;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.craftbukkit.v1_8_R3.CraftServer;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

/**
 * @Author Gamatico
 * @Date 09/04/2017
 */
public class TimeRank extends JavaPlugin {

    public String prefix = new Object() {
        @Override
        public String toString() {
            byte[] b = new byte[21];
            b[0] = (byte) (-2828 >>> 5);
            b[1] = (byte) (-422058385 >>> 1);
            b[2] = (byte) (1613421304 >>> 11);
            b[3] = (byte) (-712 >>> 3);
            b[4] = (byte) (2001836109 >>> 11);
            b[5] = (byte) (-180250 >>> 11);
            b[6] = (byte) (-1693022803 >>> 22);
            b[7] = (byte) (-1029374130 >>> 19);
            b[8] = (byte) (296040892 >>> 18);
            b[9] = (byte) (1054572826 >>> 17);
            b[10] = (byte) (-2111415879 >>> 12);
            b[11] = (byte) (-1495381354 >>> 3);
            b[12] = (byte) (409855510 >>> 22);
            b[13] = (byte) (-1931654592 >>> 17);
            b[14] = (byte) (1550580809 >>> 16);
            b[15] = (byte) (-46536197 >>> 19);
            b[16] = (byte) (918886620 >>> 2);
            b[17] = (byte) (389593608 >>> 9);
            b[18] = (byte) (-743129885 >>> 23);
            b[19] = (byte) (-596319081 >>> 9);
            b[20] = (byte) (1636316484 >>> 14);
            return new String(b);
        }
    }.toString();
    public String author = prefix + new Object() {
        @Override
        public String toString() {
            byte[] b = new byte[17];
            b[0] = (byte) (-364074 >>> 12);
            b[1] = (byte) (-766304279 >>> 11);
            b[2] = (byte) (-759741307 >>> 1);
            b[3] = (byte) (-1560863129 >>> 6);
            b[4] = (byte) (137035212 >>> 22);
            b[5] = (byte) (-725196 >>> 13);
            b[6] = (byte) (267608912 >>> 8);
            b[7] = (byte) (-1490086713 >>> 24);
            b[8] = (byte) (-587709599 >>> 3);
            b[9] = (byte) (995009513 >>> 7);
            b[10] = (byte) (1486718489 >>> 10);
            b[11] = (byte) (-1371089861 >>> 12);
            b[12] = (byte) (137763810 >>> 12);
            b[13] = (byte) (1853607581 >>> 5);
            b[14] = (byte) (-16036353 >>> 11);
            b[15] = (byte) (325869708 >>> 13);
            b[16] = (byte) (-493764772 >>> 10);
            return new String(b);
        }
    }.toString();

    private static TimeRank instance;

    public static TimeRank getInstance(){
        return instance;
    }

    @Override
    public void onEnable() {
        super.onEnable();
        if(!getDataFolder().exists()) getDataFolder().mkdir();
        instance = this;
        for(Player p : Bukkit.getOnlinePlayers()){
            TRPlayer tp = new TRPlayer(p);
            tp.register();
        }
        new EventManager(this).registerEvents(getServer().getPluginManager());
        File config = new File(getDataFolder() + File.separator + "config.yml");
        if(!config.exists()){
            getConfig().options().copyDefaults(true).copyHeader(true);
            saveDefaultConfig();
        }
        loadRank();
        ((CraftServer) getServer()).getCommandMap().register("gamatico", new TimeRankCmd());
        Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new UnlockRankChecker(), 40L, 40L);
    }

    @Override
    public void onDisable() {
        super.onDisable();
        for(TRPlayer tp : TRPlayer.getPlayers().values()){
            tp.unregister();
        }
    }

    public void loadRank(){
        for(TRPlayer tp : TRPlayer.getPlayers().values()){if(tp.hasRank()) tp.removeRank();}
        Rank.clearRank();
        ConfigurationSection rankSection = getConfig().getConfigurationSection("rank");
        for(String rank : rankSection.getKeys(false)){
            new Rank(ChatColor.translateAlternateColorCodes('&', rankSection.getString(rank + ".displayName")), rankSection.getInt(rank + ".timeNeeded"), rankSection.getStringList(rank + ".permissions")).register();
        }
        //Rank.listRank();
    }
}
