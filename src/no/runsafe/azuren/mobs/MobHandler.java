package no.runsafe.azuren.mobs;

import net.minecraft.server.v1_12_R1.World;
import no.runsafe.azuren.WorldHandler;
import no.runsafe.framework.api.*;
import no.runsafe.framework.api.event.plugin.IConfigurationChanged;
import no.runsafe.framework.api.event.plugin.IPluginDisabled;
import no.runsafe.framework.api.event.plugin.IPluginEnabled;
import no.runsafe.framework.api.player.IPlayer;
import no.runsafe.framework.internal.wrapper.ObjectUnwrapper;
import no.runsafe.framework.tools.nms.EntityRegister;

import java.util.Objects;
import java.util.Random;

public class MobHandler implements IPluginEnabled, IPluginDisabled, IConfigurationChanged
{
	public MobHandler(IScheduler scheduler, WorldHandler handler, IServer server)
	{
		this.scheduler = scheduler;
		this.handler = handler;
		this.server = server;
	}

	@Override
	public void OnPluginEnabled()
	{
		EntityRegister.registerEntity(Nightstalker.class, "nightstalker", 35);
		cycle = scheduler.startAsyncRepeatingTask(this::runCycle, 10, 10);
	}

	@Override
	public void OnPluginDisabled()
	{
		scheduler.cancelTask(cycle);
	}

	@Override
	public void OnConfigurationChanged(IConfiguration config)
	{
		this.nightstalkerHeadName = config.getConfigValueAsString("nightstalkerHeadName");
		this.nightstalkerSpawnRate = config.getConfigValueAsFloat("nightstalkerSpawnRate");
	}

	private void runCycle()
	{
		for (final IPlayer player : server.getOnlinePlayers())
		{
			ILocation location = player.getLocation();
			if (location == null || !handler.isAzurenWorld(location.getWorld()) || !(random.nextFloat() <= nightstalkerSpawnRate))
				continue;

			World world = ObjectUnwrapper.getMinecraft(location.getWorld());
			scheduler.runNow(() -> new Nightstalker(world).spawn(location, nightstalkerHeadName));
		}
	}

	private String nightstalkerHeadName;
	private float nightstalkerSpawnRate = 0.001f;
	private int cycle;
	private final IScheduler scheduler;
	private final WorldHandler handler;
	private final IServer server;
	private final Random random = new Random();
}
