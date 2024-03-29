package no.runsafe.azuren;

import no.runsafe.framework.api.IConfiguration;
import no.runsafe.framework.api.IWorld;
import no.runsafe.framework.api.event.plugin.IConfigurationChanged;
import no.runsafe.framework.api.player.IPlayer;

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

	public boolean isAzurenWorld(IWorld world)
	{
		if (world == null)
			return false;
		return worlds.contains(world.getName());
	}

	public boolean playerIsInAzurenWorld(IPlayer player)
	{
		return isAzurenWorld(player.getWorld());
	}

	private final List<String> worlds = new ArrayList<>(0);
}
