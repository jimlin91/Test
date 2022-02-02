package myPack;

import org.bukkit.plugin.java.JavaPlugin;

import Commands.Spin;
import Commands.TabEvent;

public class Main extends JavaPlugin{
	public DataManager data = new DataManager(this);
	public static int sizeI = 43;
	public static int sizeJ = 45;
	
	@Override
	public void onEnable() {
		if(data.getConfig().get("Custom.time") == null || data.getConfig().get("Custom.angle") == null) {
			data.getConfig().set("Custom.time", 25);
			data.getConfig().set("Custom.angle", 1);
			data.saveConfig();
		}
		getCommand("spin").setExecutor(new Spin(this));
		getCommand("spin").setTabCompleter(new TabEvent());
	}
	

}
