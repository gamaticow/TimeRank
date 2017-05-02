package fr.gamatico.timerank.events;

import fr.gamatico.timerank.TRPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerKickEvent;

/**
 * @Author Gamatico
 * @Date 09/04/2017
 */
public class PlayerKick implements Listener {

    @EventHandler
    public void playerKick(PlayerKickEvent e){
        Player p = e.getPlayer();
        TRPlayer tp = TRPlayer.getPlayer(p);
        tp.unregister();
    }
}
