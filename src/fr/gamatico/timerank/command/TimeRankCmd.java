package fr.gamatico.timerank.command;

import fr.gamatico.timerank.TRPlayer;
import fr.gamatico.timerank.TimeRank;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;

import java.util.Arrays;

/**
 * @Author Gamatico
 * @Date 09/04/2017
 */
public class TimeRankCmd extends BukkitCommand {

    public TimeRankCmd() {
        super("timerank");

        super.usageMessage = "/timerank help";
        super.setAliases(Arrays.asList("tr"));
    }

    @Override
    public boolean execute(CommandSender sender, String s, String[] strings) {
        if(strings.length < 1){
            sender.sendMessage(TimeRank.getInstance().author + " §c: Use " + super.usageMessage);
            return true;
        }else if(strings[0].equalsIgnoreCase("noPermission")){
            sender.sendMessage(TimeRank.getInstance().author);
            return true;
        }

        switch(strings[0].toLowerCase()){
            case "?":
            case "h":
            case "help":
                sender.sendMessage(TimeRank.getInstance().prefix + "§d§m-----[§r§6§lHelp§d§m]-----§r\n" +
                        "§6- Command help : /timerank help \n" +
                        "§6- Get account : /timerank account \n" +
                        "§6- Reload config : /timerank reload");
                break;
            case "reload":
            case "rl":
                if(sender instanceof Player && !((Player) sender).hasPermission("timerank.rl")){
                    String[] a = {"noPermission"};
                    execute(((Player) sender), s, a);
                    return true;
                }
                sender.sendMessage(TimeRank.getInstance().prefix + "§aThe plugin configuration reload !");
                TimeRank.getInstance().reloadConfig();
                TimeRank.getInstance().loadRank();
                break;
            case "account":
                if(sender instanceof Player){
                    TRPlayer tp = TRPlayer.getPlayer(((Player) sender));
                    tp.sayAccount();
                }else{
                    sender.sendMessage(TimeRank.getInstance().prefix + "§cYou need be a player !");
                }
                break;
            default:
                execute(sender, s, new String[0]);
                break;
        }
        return true;
    }
}
