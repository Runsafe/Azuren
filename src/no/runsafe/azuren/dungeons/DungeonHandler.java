package no.runsafe.azuren.dungeons;

import no.runsafe.framework.api.ILocation;
import no.runsafe.framework.api.IScheduler;
import no.runsafe.framework.api.block.IBlock;
import no.runsafe.framework.api.event.player.IPlayerRightClickBlock;
import no.runsafe.framework.api.player.IPlayer;
import no.runsafe.framework.minecraft.Item;
import no.runsafe.framework.minecraft.Sound;
import no.runsafe.framework.minecraft.WorldBlockEffect;
import no.runsafe.framework.minecraft.WorldBlockEffectType;
import no.runsafe.framework.minecraft.item.meta.RunsafeMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DungeonHandler implements IPlayerRightClickBlock
{
	public DungeonHandler(IScheduler scheduler)
	{
		this.scheduler = scheduler;
		sparkEffect = new WorldBlockEffect(WorldBlockEffectType.BLOCK_DUST, Item.BuildingBlock.StainedClay.Green);
	}

	@Override
	public boolean OnPlayerRightClick(IPlayer player, RunsafeMeta usingItem, final IBlock targetBlock)
	{
		if (targetBlock.is(Item.Decoration.EnderPortalFrame) && usingItem.is(Item.Miscellaneous.EyeOfEnder))
		{
			final ILocation effectLocation = targetBlock.getLocation();

			effectLocation.offset(0.5, 0.5, 0.5); // Offset to middle of the block.
			effectLocation.playSound(Sound.Portal.Travel, 1F, 0F);

			for (int i = 0; i < 10; i++)
				playSpark(effectLocation, i);

			scheduler.startSyncTask(new Runnable()
			{
				@Override
				public void run()
				{
					targetBlock.set(Item.Unavailable.Air); // Remove the end frame block
					effectLocation.getWorld().createExplosion(effectLocation, 3, false, false);
				}
			}, 11);
		}
		return true;
	}

	private void playSpark(final ILocation location, int seconds)
	{
		scheduler.startSyncTask(new Runnable()
		{
			@Override
			public void run()
			{
				location.playEffect(sparkEffect, 1F, 100, 30);
				location.playSound(Sound.Environment.Fizz, 1F, 0F);

				int lootAmount = random.nextInt(3) + 1;
				for (int c = 0; c < lootAmount; c++)
					loot.get(random.nextInt(loot.size())).drop(location);
			}
		}, seconds);
	}

	private final WorldBlockEffect sparkEffect;
	private final IScheduler scheduler;
	public static final Random random = new Random();

	private static final List<DungeonLoot> loot = new ArrayList<DungeonLoot>(0);
	static
	{
		loot.add(new DungeonLoot(Item.Materials.Diamond, 3));
		loot.add(new DungeonLoot(Item.Materials.Emerald, 5, 10));
		loot.add(new DungeonLoot(Item.Tool.Pickaxe.Diamond));
		loot.add(new DungeonLoot(Item.Tool.Shovel.Diamond));
		loot.add(new DungeonLoot(Item.Combat.Boots.Diamond));
		loot.add(new DungeonLoot(Item.Combat.Chestplate.Diamond));
		loot.add(new DungeonLoot(Item.Combat.Leggings.Diamond));
		loot.add(new DungeonLoot(Item.Combat.Helmet.Diamond));
		loot.add(new DungeonLoot(Item.Transportation.Saddle));
		loot.add(new DungeonLoot(Item.Miscellaneous.EnderPearl, 10, 15));
		loot.add(new DungeonLoot(Item.Food.Golden.Apple));
		loot.add(new DungeonLoot(Item.Food.Plant.Apple, 2));
		loot.add(new DungeonLoot(Item.Materials.GoldIngot, 4, 6));
		loot.add(new DungeonLoot(Item.Transportation.Minecart.Explosive));
		loot.add(new DungeonLoot(Item.Combat.Sword.Diamond));
		loot.add(new DungeonLoot(Item.Miscellaneous.Beacon));
		loot.add(new DungeonLoot(Item.Brewing.BrewingStand));
	};
}
