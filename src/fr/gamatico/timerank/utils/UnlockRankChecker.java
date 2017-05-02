package fr.gamatico.timerank.utils;

import fr.gamatico.timerank.Rank;
import fr.gamatico.timerank.TRPlayer;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * @Author Gamatico
 * @Date 09/04/2017
 */
public class UnlockRankChecker extends BukkitRunnable {

    @Override
    public void run() {
        for(TRPlayer tp : TRPlayer.getPlayers().values()){
            Rank u = null;
            for(Rank r : Rank.getRanks()){
                if(u == null && tp.getTotalTime() >= r.getNeededTime()){
                    u = r;
                }else if(u != null && tp.getTotalTime() >= r.getNeededTime() && r.getNeededTime() >= u.getNeededTime()){
                    u = r;
                }
            }
            if(u == null){
                if(tp.hasRank()) tp.removeRank();
            }else{
                if(tp.hasRank() && tp.getRank() != u){
                    tp.removeRank();
                }

                if(!tp.hasRank()){
                    tp.setRank(u);
                    tp.msg("§aYou have unlock the rank : " + u.getName() + " §a!");
                }
            }

        }
    }
}
