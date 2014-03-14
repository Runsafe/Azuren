package no.runsafe.azuren.mobs;

import no.runsafe.azuren.WorldHandler;
import no.runsafe.framework.api.event.player.IPlayerLeftClickAirEvent;
import no.runsafe.framework.api.event.plugin.IPluginEnabled;
import no.runsafe.framework.api.player.IPlayer;
import no.runsafe.framework.internal.wrapper.ObjectUnwrapper;
import no.runsafe.framework.minecraft.event.player.RunsafePlayerClickEvent;
import no.runsafe.framework.tools.nms.EntityRegister;

public class MobHandler implements IPluginEnabled, IPlayerLeftClickAirEvent
{
	@Override
	public void OnPluginEnabled()
	{
		EntityRegister.registerEntity(Nightstalker.class, "nightstalker", 51);
	}

	@Override
	public void OnPlayerLeftClick(RunsafePlayerClickEvent event)
	{
		IPlayer player = event.getPlayer();
		if (WorldHandler.playerIsInAzurenWorld(player))
			new Nightstalker(ObjectUnwrapper.getMinecraft(player.getWorld())).spawn(player.getLocation());
	}
}
