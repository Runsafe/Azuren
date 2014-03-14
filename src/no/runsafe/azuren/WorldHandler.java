package no.runsafe.azuren;

import no.runsafe.framework.api.IConfiguration;
import no.runsafe.framework.api.IWorld;
import no.runsafe.framework.api.event.plugin.IConfigurationChanged;
import no.runsafe.framework.api.log.IConsole;
import no.runsafe.framework.api.player.IPlayer;

import java.util.ArrayList;
import java.util.List;

public class WorldHandler implements IConfigurationChanged
{
	public WorldHandler(IConsole console)
	{
		this.console = console;
	}

	@Override
	public void OnConfigurationChanged(IConfiguration config)
	{
		worlds.clear();
		worlds.addAll(config.getConfigValueAsList("worlds"));
	}

	public boolean isAzurenWorld(IWorld world)
	{
		return worlds.contains(world.getName());
	}

	public boolean playerIsInAzurenWorld(IPlayer player)
	{
		console.logInformation("Checking worlds!");
		console.logInformation("World amount: " + worlds.size());
		for (String worldName : worlds)
			console.logInformation("World: " + worldName);

		return isAzurenWorld(player.getWorld());
	}

	private final List<String> worlds = new ArrayList<String>(0);
	private final IConsole console;
}
