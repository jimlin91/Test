package Commands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;

import FerrisWeel.FerrisWeelModel;
import FerrisWeelAuto.AutoSping;
import myPack.Main;

public class Spin implements CommandExecutor{
	Main plugin;
	static BukkitTask autoSpinTask;
	
	public Spin(Main plugin) {
		this.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		
		if(sender instanceof Player) {
			Player player = (Player) sender;
			if(!player.hasPermission("ferrisweelspin.*")) {
				player.sendMessage(ChatColor.RED+"你沒有權限");
				return true;
			}
			if(args.length==1) {
				if(args[0].equals("set")) {
					List<Integer> pos = new ArrayList<Integer>();
					Block block = player.getTargetBlockExact(15);
					if(block == null) {
						player.sendMessage(ChatColor.RED+"No block found!");
						return true;
					}
					float direction = player.getLocation().getYaw();
					int facing = 0;
					if((direction>=135 && direction<=180) || ( direction>=-180 && direction<=-135)) {
						facing = 1;
					}
					else if(direction>=-135 && direction<-45) {
						facing = 2;
					}
					else if((direction>=-45 && direction<=0) ||( direction>=0 && direction<=45)) {
						facing = 3;
					}
					else if(direction>=45 && direction<135) {
						facing = 4;
					}
					pos.add(block.getLocation().getBlockX());
					pos.add(block.getLocation().getBlockY());
					pos.add(block.getLocation().getBlockZ());
					plugin.data.getConfig().set("Center.pos", pos);
					plugin.data.getConfig().set("Center.world", player.getWorld().getName());
					plugin.data.getConfig().set("Center.facing", facing);
					plugin.data.saveConfig();
					
					player.sendMessage(ChatColor.GREEN+"Block saved!");
				}
				else if(args[0].equalsIgnoreCase("autoTurn")) {
					if(autoSpinTask == null) {
						autoSpinTask = new AutoSping(plugin).runTaskTimer(plugin, 0, plugin.data.getConfig().getInt("Custom.time"));
					}
					else {
						player.sendMessage(ChatColor.RED+"You have set areadly");
					}
				}
				else if(args[0].equalsIgnoreCase("autoTurnCancel")) {
					if(autoSpinTask != null) {
						autoSpinTask.cancel();
						autoSpinTask = null;
					}
					else {
						player.sendMessage(ChatColor.RED+"No task execute!");
					}
				}
			}
			else if(args.length==2 && args[0].equalsIgnoreCase("angle")) {
				int[][] blank = new int[Main.sizeI][Main.sizeJ];
				
				int[][] glowModel1 = FerrisWeelModel.turnAngle(FerrisWeelModel.getGlowstone(),Double.valueOf(args[1]));
				int[][] glowModel2 = FerrisWeelModel.turnAngle(FerrisWeelModel.getGlowstone(),Double.valueOf(args[1])+45d);
				
				blank = FerrisWeelModel.add(blank,FerrisWeelModel.getTrapDoor(glowModel1));
				blank = FerrisWeelModel.add(blank,FerrisWeelModel.getTrapDoor(glowModel2));
				
				for(int i=0;i<8;i++) {
					int[][] model= FerrisWeelModel.getFerrisCenter(i+2);				
					blank = FerrisWeelModel.add(blank,FerrisWeelModel.turnAngle(model,Double.valueOf(args[1])+(double)(i*45)));
				}
				blank = FerrisWeelModel.add(blank,FerrisWeelModel.getOutSideFrame());
				blank = FerrisWeelModel.add(blank,FerrisWeelModel.getMiddleFrame());
				
				blank = FerrisWeelModel.add(blank,glowModel1);
				blank = FerrisWeelModel.add(blank,glowModel2);
				
				FerrisWeelModel.showFerrisWeel(blank, plugin,0);
				
				blank = FerrisWeelModel.getFrontTrapDoor(glowModel1);
				blank = FerrisWeelModel.add(blank,FerrisWeelModel.getFrontTrapDoor(glowModel2));
				blank = FerrisWeelModel.add(blank,FerrisWeelModel.getOutsideCover());
				
				FerrisWeelModel.showFerrisWeel(blank, plugin,-1);
				
				// Middle spin
				for(int i=0;i<7;i++) {
					blank = new int[Main.sizeI][Main.sizeJ];
					if(i == 0) {
						blank = FerrisWeelModel.getBackTrapDoor(glowModel1);
						blank = FerrisWeelModel.add(blank,FerrisWeelModel.getBackTrapDoor(glowModel2));
					}
					else if(i==6) {
						blank = FerrisWeelModel.getFrontTrapDoor(glowModel1);
						blank = FerrisWeelModel.add(blank,FerrisWeelModel.getFrontTrapDoor(glowModel2));
					}
					
					blank = FerrisWeelModel.add(blank,FerrisWeelModel.getBuckets(Double.valueOf(args[1]))[i]);
					blank = FerrisWeelModel.add(blank,FerrisWeelModel.turnAngle(FerrisWeelModel.getCenterRod(), Double.valueOf(args[1])));
					
					FerrisWeelModel.showFerrisWeel(blank, plugin,i+1);
				}
				
				// Back Spin
				blank = new int[Main.sizeI][Main.sizeJ];
				
				blank = FerrisWeelModel.add(blank,FerrisWeelModel.getTrapDoor(glowModel1));
				blank = FerrisWeelModel.add(blank,FerrisWeelModel.getTrapDoor(glowModel2));
				
				for(int i=0;i<8;i++) {
					int[][] model= FerrisWeelModel.getFerrisCenter(i+2);				
					blank = FerrisWeelModel.add(blank,FerrisWeelModel.turnAngle(model,Double.valueOf(args[1])+(double)(i*45)));
				}
				blank = FerrisWeelModel.add(blank,FerrisWeelModel.getOutSideFrame());
				blank = FerrisWeelModel.add(blank,FerrisWeelModel.getMiddleFrame());
				
				blank = FerrisWeelModel.add(blank,glowModel1);
				blank = FerrisWeelModel.add(blank,glowModel2);
				
				FerrisWeelModel.showFerrisWeel(blank, plugin,8);
				
				blank = FerrisWeelModel.getBackTrapDoor(glowModel1);
				blank = FerrisWeelModel.add(blank,FerrisWeelModel.getBackTrapDoor(glowModel2));
				blank = FerrisWeelModel.add(blank,FerrisWeelModel.getOutsideCover());
				
				FerrisWeelModel.showFerrisWeel(blank, plugin,9);
				//FerrisWeelModel.teleportPlayer(Double.valueOf(args[1]), plugin);
			}		
			else {
				player.sendMessage(ChatColor.RED+"usage:/spin [set|angle]");
			}
		}
		else {
			System.out.println("Please execute as a player!");
		}
		
		
		
		return true;
	}
	
	
	
}
