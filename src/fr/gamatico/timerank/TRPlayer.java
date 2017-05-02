package fr.gamatico.timerank;

import fr.gamatico.timerank.utils.Config;
import fr.gamatico.timerank.utils.ConnectTime;
import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissionAttachment;

import java.util.HashMap;

/**
 * @Author Gamatico
 * @Date 09/04/2017
 */
public class TRPlayer {

    private static HashMap<String, TRPlayer> players = new HashMap<>();

    private Player player;
    private long loginTime;
    private int beforeJoinTime;
    private Rank rank;
    private PermissionAttachment pa;

    public TRPlayer(Player player){
        this.player = player;
        this.loginTime = System.currentTimeMillis();
        this.pa = player.addAttachment(TimeRank.getInstance());
    }

    public Player getPlayer(){
        return player;
    }

    public void sayAccount(){
        msg("§d§m-----[§6§lAccount§d§m]-----\n" +
                "§aTotal time : §6" + getBeautyTime() + "\n" +
                "§aCurrent rank : §6" + (hasRank() ? rank.getName() : "§cnull"));
    }

    public String getBeautyTime(){
        int seconds = getTotalTime() % 60;
        int minutes = ((getTotalTime() - seconds) / 60) % 60;
        int hours = (getTotalTime() - (seconds + minutes)) / 3600;

        StringBuilder sb = new StringBuilder();
        sb.append((hours > 0 ? (hours < 10 ? "0" + hours + ":" : hours + ":") : "00:"));
        sb.append((minutes > 0 ? (minutes < 10 ? "0" + minutes + ":" : minutes + ":") : "00:"));
        sb.append((seconds < 10 ? "0" + seconds : seconds));

        return sb.toString();
    }

    public void msg(String msg){
        getPlayer().sendMessage(TimeRank.getInstance().prefix + msg);
    }

    public ConnectTime getConnectTime(){
        return new ConnectTime(System.currentTimeMillis() - loginTime);
    }

    public int getTotalTime(){
        return beforeJoinTime + getConnectTime().getSecond();
    }

    public boolean hasRank(){
        return rank != null;
    }

    public void removeRank(){
        for(String perm : rank.getPermissions()){
            pa.unsetPermission(perm);
        }
        getPlayer().recalculatePermissions();
        rank = null;
    }

    public void setRank(Rank r){
        rank = r;
        for(String perm : r.getPermissions()){
            pa.setPermission(perm, true);
        }
        getPlayer().recalculatePermissions();
    }

    public Rank getRank(){
        return rank;
    }

    public void register(){
        players.put(getPlayer().getName(), this);
        beforeJoinTime = Config.createPlayerConfig(getPlayer().getUniqueId().toString());
    }

    public void unregister(){
        players.remove(getPlayer().getName());
        Config.updateConfig(getPlayer().getUniqueId().toString(), getConnectTime());
    }

    public static TRPlayer getPlayer(Player p){
        return players.get(p.getName());
    }

    public static HashMap<String, TRPlayer> getPlayers(){
        return players;
    }

}
