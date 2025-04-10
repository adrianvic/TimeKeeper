package gd.rf.adrianvictor.realtime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.plugin.java.JavaPlugin;
import gd.rf.adrianvictor.lib.Log;
import gd.rf.adrianvictor.lib.ConfigurationEx;

public class Main extends JavaPlugin {
	Log log;
	ConfigurationEx config;
	
	@Override
	public void onDisable() {
		log.info("Disabling!");
	}

	@Override
	public void onEnable() {
		log = new Log(this);
		log.info("Starting!");
		config = new ConfigurationEx(this, "config.yml", log);
		config.loadConfig();
		int ticksBetweenUpdate = config.getInt("ticksBetweenUpdate", 20);
		List<String> worlds = config.getStringList("worlds", null);
		Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
			@Override
			public void run() {
				for (String worldName : worlds) {
					World world = Bukkit.getServer().getWorld(worldName);
					if (world == null) {
						log.severe("World " + worldName + " specified in the config was not found, removing it.");
						worlds.remove(worldName);
						return;
					}
					ZonedDateTime time = ZonedDateTime.now(ZoneId.of(config.getString("timezone", ZoneId.systemDefault().toString())));
					int hour = time.getHour();
					int minute = time.getMinute();
					long minecraftTime = (hour * 1000 + minute * 100 / 6 - 6000) % 24000;
					if (minecraftTime < 0) {
						minecraftTime = minecraftTime * 2;
					}
					world.setTime(minecraftTime);
				}
			}
		}, 0L, ticksBetweenUpdate);
	}
}
