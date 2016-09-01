package cristaltek.hitekmod;

import net.minecraftforge.common.config.Configuration;

public class Configs {
	private static Configuration config;
	
	public static int TDTool_maxEnergy;
	public static int TDTool_energyPerUse;
	
	public static void init(Configuration configuration) {
		config = configuration;
		config.load();
		hydrateConfig();
		config.save();
	}
	
	public static void hydrateConfig() {
		TDTool_maxEnergy = config.getInt("maxEnergy", "TDTool", 1000000, 10000, 10000000, "The maximum amount of energy that the TDTool can hold");
		TDTool_energyPerUse = config.getInt("energyPerUse", "TDTool", 128, 10, 1000, "The amount of energy the TDTool consumes per use");
	}
}
