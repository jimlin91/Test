package myPack;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.logging.Level;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class DataManager {
	Main plugin;
	private File configFile = null;
	private FileConfiguration dataConfig = null;
	
	public DataManager(Main plugin) {
		this.plugin = plugin;
		saveDefaultConfig();
	}
	
	public void reloadConfig() {
		if(this.configFile == null) {
			configFile = new File(plugin.getDataFolder(), "config.yml");
		}
		dataConfig = YamlConfiguration.loadConfiguration(configFile);
		InputStream defaultStream = plugin.getResource("config.yml"); 
		
		if(defaultStream != null) {
			YamlConfiguration defaultConfig = YamlConfiguration.loadConfiguration(new InputStreamReader(defaultStream));
			dataConfig.setDefaults(defaultConfig);
		}
		
	}
	
	public FileConfiguration getConfig() {
		if(dataConfig == null) {
			reloadConfig();
		}
		return dataConfig;
	}
	
	public void saveConfig() {
		if(dataConfig == null || configFile == null) {
			return;
		}
		
		try {
			getConfig().save(configFile);
		} catch (IOException e) {
			plugin.getLogger().log(Level.SEVERE, "Could not save config to"+configFile,e);
		}
	}
	
	public void saveDefaultConfig() {
		if(configFile == null) {
			configFile  = new File(plugin.getDataFolder(),"config.yml");
		}
		if(!configFile.exists()) {
			plugin.saveResource("config.yml", false);
		}
		
	}
	
	
	
}
