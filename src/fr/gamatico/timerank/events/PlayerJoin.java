package fr.gamatico.timerank.events;

import fr.gamatico.timerank.TRPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

/**
 * @Author Gamatico
 * @Date 09/04/2017
 */
public class PlayerJoin implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        Player p = e.getPlayer();
        TRPlayer tp = new TRPlayer(p);
        tp.register();
    }
}
