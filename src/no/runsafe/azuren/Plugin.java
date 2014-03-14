package no.runsafe.azuren;

import no.runsafe.azuren.dungeons.DungeonHandler;
import no.runsafe.framework.RunsafeConfigurablePlugin;
import no.runsafe.framework.features.Configuration;
import no.runsafe.framework.features.Events;
import org.bukkit.generator.ChunkGenerator;

public class Plugin extends RunsafeConfigurablePlugin
{
	@Override
	protected void pluginSetup()
	{
		addComponent(Events.class);
		addComponent(Configuration.class);

		addComponent(Generator.class);
		addComponent(WorldHandler.class);
		addComponent(DungeonHandler.class);
	}

	public ChunkGenerator getDefaultWorldGenerator(String worldName, String id)
	{
		return getComponent(Generator.class);
	}
}
