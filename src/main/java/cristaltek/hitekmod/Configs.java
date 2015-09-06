package cristaltek.hitekmod;

import net.minecraftforge.common.config.Configuration;

public class Configs
{

	private static Configuration config;
	

	public static int TDTool_maxEnergy;
	public static int magnet_strength;
	public static int htArmor_maxEnergy;
	
	public static void init(Configuration configuration)
	{
		config = configuration;
		config.load();
		hydrateConfig();
		config.save();
	}
	
	public static void hydrateConfig()
	{
		TDTool_maxEnergy = config.getInt("maxEnergy", "TDTool", 1000000, 10000, 10000000, "The maximum amount of energy that the TDTool can hold");
		magnet_strength = config.getInt("magnetStrength", "misc", 10, 5, 15, "How far the magnet can reach");
		htArmor_maxEnergy = config.getInt("maxEnergy", "htArmor", 1000000, 1000, 10000000, "The maximum amount of energy that the XLF-1 armor can hold");
	}
}
