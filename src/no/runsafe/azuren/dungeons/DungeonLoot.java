package no.runsafe.azuren.dungeons;

import no.runsafe.framework.api.ILocation;
import no.runsafe.framework.minecraft.Item;
import no.runsafe.framework.minecraft.item.meta.RunsafeMeta;

public class DungeonLoot
{
	public DungeonLoot(Item item, int min, int max)
	{
		this.item = item;
		this.min = min;
		this.max = max;
	}

	public DungeonLoot(Item item, int max)
	{
		this(item, 1, max);
	}

	public DungeonLoot(Item item)
	{
		this(item, 1, 1);
	}

	public void drop(ILocation location)
	{
		RunsafeMeta itemStack = item.getItem();
		itemStack.setAmount(DungeonHandler.random.nextInt((max - min) + 1) + min);
		location.getWorld().dropItem(location, itemStack);
	}

	private final Item item;
	private final int min;
	private final int max;
}
