package no.runsafe.azuren;

import no.runsafe.framework.RunsafePlugin;
import org.bukkit.generator.ChunkGenerator;

public class Plugin extends RunsafePlugin
{
	@Override
	protected void pluginSetup()
	{
	}

	public ChunkGenerator getDefaultWorldGenerator(String worldName, String id)
	{
		return getComponent(Generator.class);
	}
}
