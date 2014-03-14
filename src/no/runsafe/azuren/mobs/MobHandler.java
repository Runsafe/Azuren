package no.runsafe.azuren.mobs;

import no.runsafe.azuren.WorldHandler;
import no.runsafe.framework.api.event.player.IPlayerInteractEvent;
import no.runsafe.framework.api.event.plugin.IPluginEnabled;
import no.runsafe.framework.api.log.IConsole;
import no.runsafe.framework.api.player.IPlayer;
import no.runsafe.framework.internal.wrapper.ObjectUnwrapper;
import no.runsafe.framework.minecraft.event.player.RunsafePlayerInteractEvent;
import no.runsafe.framework.tools.nms.EntityRegister;

public class MobHandler implements IPluginEnabled, IPlayerInteractEvent
{
	public MobHandler(IConsole console, WorldHandler handler)
	{
		this.console = console;
		this.handler = handler;
	}

	@Override
	public void OnPluginEnabled()
	{
		EntityRegister.registerEntity(Nightstalker.class, "nightstalker", 51);
	}

	@Override
	public void OnPlayerInteractEvent(RunsafePlayerInteractEvent event)
	{
		console.logInformation("Event detected");
		IPlayer player = event.getPlayer();
		if (handler.playerIsInAzurenWorld(player))
		{
			console.logInformation("Player is in correct world.");
			new Nightstalker(ObjectUnwrapper.getMinecraft(player.getWorld())).spawn(player.getLocation());
		}
	}

	private final IConsole console;
	private final WorldHandler handler;
}
