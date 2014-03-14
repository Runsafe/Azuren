package no.runsafe.azuren.events;

import no.runsafe.framework.api.player.IPlayer;
import no.runsafe.framework.minecraft.event.player.RunsafeCustomEvent;

public class DungeonEvent extends RunsafeCustomEvent
{
	public DungeonEvent(IPlayer player)
	{
		super(player, "azuren.dungeon.event");
	}

	@Override
	public Object getData()
	{
		return null;
	}
}
