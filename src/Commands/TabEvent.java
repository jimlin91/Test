package Commands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

public class TabEvent implements TabCompleter{
	
	List<String> arguments = new ArrayList<String>();
	
	@Override
	public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
		
		if(arguments.isEmpty()) {
			arguments.add("angle");
			arguments.add("autoTurn");
			arguments.add("autoTurnCancel");
		}
		if(args.length ==1) {
			if(sender instanceof Player) {
				Player player = (Player)sender;
				if(player.hasPermission("ferrisweelspin.*")) {
					List<String> result = new ArrayList<String>();
					
					for(String t : arguments) {
						if(t.toLowerCase().startsWith(args[0])) {
							result.add(t);
						}
					}
					return result;
				}
			}
		}
		
		return null;
	}

}
