package FerrisWeelAuto;

import org.bukkit.scheduler.BukkitRunnable;

import FerrisWeel.FerrisWeelModel;
import myPack.Main;

public class AutoSping extends BukkitRunnable{
	public static double currentAngle = 0;
	
	Main plugin;
	
	public AutoSping(Main plugin) {
		this.plugin = plugin;
	}

	@Override
	public void run() {
		int[][] blank = new int[Main.sizeI][Main.sizeJ];
		
		int[][] glowModel1 = FerrisWeelModel.turnAngle(FerrisWeelModel.getGlowstone(),Double.valueOf(currentAngle));
		int[][] glowModel2 = FerrisWeelModel.turnAngle(FerrisWeelModel.getGlowstone(),Double.valueOf(currentAngle)+45d);
		
		blank = FerrisWeelModel.add(blank,FerrisWeelModel.getTrapDoor(glowModel1));
		blank = FerrisWeelModel.add(blank,FerrisWeelModel.getTrapDoor(glowModel2));
		
		for(int i=0;i<8;i++) {
			int[][] model= FerrisWeelModel.getFerrisCenter(i+2);				
			blank = FerrisWeelModel.add(blank,FerrisWeelModel.turnAngle(model,Double.valueOf(currentAngle)+(double)(i*45)));
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
		
		FerrisWeelModel.teleportPlayer(Double.valueOf(currentAngle), plugin);
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
			
			blank = FerrisWeelModel.add(blank,FerrisWeelModel.getBuckets(Double.valueOf(currentAngle))[i]);
			blank = FerrisWeelModel.add(blank,FerrisWeelModel.turnAngle(FerrisWeelModel.getCenterRod(), Double.valueOf(currentAngle)));
			
			FerrisWeelModel.showFerrisWeel(blank, plugin,i+1);
		}
		
		// Back Spin
		blank = new int[Main.sizeI][Main.sizeJ];
		
		blank = FerrisWeelModel.add(blank,FerrisWeelModel.getTrapDoor(glowModel1));
		blank = FerrisWeelModel.add(blank,FerrisWeelModel.getTrapDoor(glowModel2));
		
		for(int i=0;i<8;i++) {
			int[][] model= FerrisWeelModel.getFerrisCenter(i+2);				
			blank = FerrisWeelModel.add(blank,FerrisWeelModel.turnAngle(model,Double.valueOf(currentAngle)+(double)(i*45)));
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
		
		
		
		currentAngle +=plugin.getConfig().getDouble("Custom.angle");
	}
}
