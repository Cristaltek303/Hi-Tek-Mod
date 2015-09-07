package cristaltek.hitekmod;

import net.minecraftforge.common.config.Configuration;

public class Configs
{

	private static Configuration config;
	
	
	public static int TDTool_maxEnergy;
	public static int TDTool_energyPerUse;
	
	public static int OPSword_maxEnergy;
	public static int OPSword_energyPerHit;
	
	public static int htArmor_maxEnergy;
	public static int htArmor_energyPerTick;
	
	public static int energyCube_maxEnergy;
	
	public static int magnet_strength;
	
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
		TDTool_energyPerUse = config.getInt("energyPerUse", "TDTool", 100, 10, 1000, "The amount of energy the TDTool consumes per use");
		
		OPSword_maxEnergy = config.getInt("maxEnergy", "OPSword", 1000000, 10000, 10000000, "The maximum amount of energy that the OPSword can hold");
		OPSword_energyPerHit = config.getInt("energyPerHit", "OPSword", 100, 10, 1000, "The amount of energy the OPSword consumes per hit");
		
		htArmor_maxEnergy = config.getInt("maxEnergy", "htArmor", 1000000, 1000, 10000000, "The maximum amount of energy that the XLF-1 armor can hold");
		htArmor_energyPerTick = config.getInt("energyPerTick", "htArmor", 10, 1, 1000, "The amount of energy the XLF-1 armor consumes per tick");
		
		energyCube_maxEnergy = config.getInt("maxEnergy", "energyCube", 10000000, 10000, 100000000, "The maximum amount of energy that the energy cube can store");
		
		magnet_strength = config.getInt("magnetStrength", "misc", 10, 5, 15, "How far the magnet can reach");
	}
}
