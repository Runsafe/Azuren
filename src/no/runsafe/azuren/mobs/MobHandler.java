package no.runsafe.azuren.mobs;

import no.runsafe.azuren.WorldHandler;
import no.runsafe.framework.api.IScheduler;
import no.runsafe.framework.api.IServer;
import no.runsafe.framework.api.event.plugin.IPluginDisabled;
import no.runsafe.framework.api.event.plugin.IPluginEnabled;
import no.runsafe.framework.api.player.IPlayer;
import no.runsafe.framework.internal.wrapper.ObjectUnwrapper;
import no.runsafe.framework.tools.nms.EntityRegister;

import java.util.Random;

public class MobHandler implements IPluginEnabled, IPluginDisabled
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
		EntityRegister.registerEntity(Nightstalker.class, "nightstalker", 51);
		cycle = scheduler.startAsyncRepeatingTask(new Runnable()
		{
			@Override
			public void run()
			{
				runCycle();
			}
		}, 10, 10);
	}

	@Override
	public void OnPluginDisabled()
	{
		scheduler.cancelTask(cycle);
	}

	private void runCycle()
	{
		for (final IPlayer player : server.getOnlinePlayers())
		{
			if (handler.playerIsInAzurenWorld(player) && random.nextFloat() <= 0.001)
			{
				scheduler.runNow(new Runnable()
				{
					@Override
					public void run()
					{
						new Nightstalker(ObjectUnwrapper.getMinecraft(player.getWorld())).spawn(player.getLocation());
					}
				});
			}
		}
	}

	private int cycle;
	private final IScheduler scheduler;
	private final WorldHandler handler;
	private final IServer server;
	private final Random random = new Random();
}
