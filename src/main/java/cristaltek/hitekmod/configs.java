package cristaltek.hitekmod;

import net.minecraftforge.common.config.Configuration;

public class configs 
{
	
	private static Configuration config;
	
//	public static int TDTool_chargeRate;
//	public static int TDTool_maxEnergy;    
    
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
//    	TDTool_chargeRate = config.getInt("chargeRate", "TDTool", 1000, 100, 10000, "How fast the TDTool can charge");
//    	TDTool_maxEnergy = config.getInt("maxEnergy", "TDTool", 1000, 100, 10000, "The maximum amount of energy that the TDTool can hold");
    	    	
    	magnet_strength = config.getInt("magnetStrength", "misc", 10, 5, 15, "How far the magnet can reach");
    }

}
