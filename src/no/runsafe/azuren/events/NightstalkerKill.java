package no.runsafe.azuren.events;

import no.runsafe.framework.api.player.IPlayer;
import no.runsafe.framework.minecraft.event.player.RunsafeCustomEvent;

public class NightstalkerKill extends RunsafeCustomEvent
{
	public NightstalkerKill(IPlayer player)
	{
		super(player, "nightstalker.kill");
	}

	@Override
	public Object getData()
	{
		return null;
	}
}
