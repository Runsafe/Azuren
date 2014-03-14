package no.runsafe.azuren;

import no.runsafe.framework.api.IConfiguration;
import no.runsafe.framework.api.IWorld;
import no.runsafe.framework.api.event.plugin.IConfigurationChanged;

import java.util.ArrayList;
import java.util.List;

public class WorldHandler implements IConfigurationChanged
{
	@Override
	public void OnConfigurationChanged(IConfiguration config)
	{
		worlds.clear();
		worlds.addAll(config.getConfigValueAsList("worlds"));
	}

	public static boolean isAzurenWorld(IWorld world)
	{
		return worlds.contains(world.getName());
	}

	private static final List<String> worlds = new ArrayList<String>(0);
}
