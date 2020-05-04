package me.etorresh.tagminigame;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TagCommandExecutor implements CommandExecutor {
    Tag main = Tag.getPlugin(Tag.class);
    @Override
    public boolean onCommand(CommandSender sender, Command tag, String playerToTag, String[] args) {
        if (sender.isOp() && args.length > 0){
            if (args[0].equals("start")){
                Player target = Bukkit.getPlayerExact(args[1]);
                if (target instanceof Player) {
                    main.onTag(Bukkit.getPlayerExact(sender.getName()), target);
                    return true;
                }
            }
            else if (args[0].equals("cooldown")){
                try {
                    Integer.parseInt(args[1]);
                }
                catch(NumberFormatException ex){
                    return false;
                }
                Tag.getPlugin(Tag.class);
                main.changeCooldown(Integer.parseInt(args[1]));
                return true;
            }
            else if (args[0].equals("clear")){
                main.clear(Bukkit.getPlayerExact(args[1]));
                return true;
            }
        }
        return false;
    }
}
