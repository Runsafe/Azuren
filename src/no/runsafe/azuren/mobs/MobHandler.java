package no.runsafe.azuren.mobs;

import no.runsafe.azuren.WorldHandler;
import no.runsafe.framework.api.ILocation;
import no.runsafe.framework.api.event.player.IPlayerMove;
import no.runsafe.framework.api.event.plugin.IPluginEnabled;
import no.runsafe.framework.api.log.IConsole;
import no.runsafe.framework.api.player.IPlayer;
import no.runsafe.framework.internal.wrapper.ObjectUnwrapper;
import no.runsafe.framework.tools.nms.EntityRegister;

public class MobHandler implements IPluginEnabled, IPlayerMove
{
	public MobHandler(IConsole console)
	{
		this.console = console;
	}

	@Override
	public void OnPluginEnabled()
	{
		EntityRegister.registerEntity(Nightstalker.class, "nightstalker", 51);
	}

	@Override
	public boolean OnPlayerMove(IPlayer player, ILocation from, ILocation to)
	{
		console.logInformation("Event detected");
		if (WorldHandler.playerIsInAzurenWorld(player))
		{
			console.logInformation("Player is in correct world.");
			new Nightstalker(ObjectUnwrapper.getMinecraft(player.getWorld())).spawn(player.getLocation());
		}
		return true;
	}

	private final IConsole console;
}
