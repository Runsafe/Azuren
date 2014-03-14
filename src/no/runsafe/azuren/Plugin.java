package no.runsafe.azuren;

import no.runsafe.azuren.dungeons.DungeonHandler;
import no.runsafe.framework.RunsafePlugin;
import org.bukkit.generator.ChunkGenerator;

public class Plugin extends RunsafePlugin
{
	@Override
	protected void pluginSetup()
	{
		addComponent(Generator.class);
		addComponent(DungeonHandler.class);
	}

	public ChunkGenerator getDefaultWorldGenerator(String worldName, String id)
	{
		return getComponent(Generator.class);
	}
}
