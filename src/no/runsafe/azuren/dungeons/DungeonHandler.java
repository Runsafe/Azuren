package no.runsafe.azuren.dungeons;

import no.runsafe.azuren.WorldHandler;
import no.runsafe.azuren.events.DungeonEvent;
import no.runsafe.framework.api.ILocation;
import no.runsafe.framework.api.IScheduler;
import no.runsafe.framework.api.block.IBlock;
import no.runsafe.framework.api.entity.ILivingEntity;
import no.runsafe.framework.api.event.player.IPlayerRightClickBlock;
import no.runsafe.framework.api.player.IPlayer;
import no.runsafe.framework.minecraft.*;
import no.runsafe.framework.minecraft.entity.LivingEntity;
import no.runsafe.framework.minecraft.item.meta.RunsafeMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DungeonHandler implements IPlayerRightClickBlock
{
	public DungeonHandler(IScheduler scheduler, WorldHandler handler)
	{
		this.scheduler = scheduler;
		this.handler = handler;
		sparkEffect = new WorldBlockEffect(WorldBlockEffectType.BLOCK_DUST, Item.BuildingBlock.StainedClay.Green);
	}

	@Override
	public boolean OnPlayerRightClick(IPlayer player, RunsafeMeta usingItem, final IBlock targetBlock)
	{
		if (usingItem == null || targetBlock == null)
			return true;

		if (targetBlock.is(Item.Decoration.EnderPortalFrame) && usingItem.is(Item.Miscellaneous.EyeOfEnder) && handler.playerIsInAzurenWorld(player))
		{
			final ILocation effectLocation = targetBlock.getLocation();

			effectLocation.offset(0.5, 0.5, 0.5); // Offset to middle of the block.
			effectLocation.playSound(Sound.Portal.Travel, 1F, 0F);

			for (int i = 0; i < 10; i++)
				playSpark(effectLocation, i);

			scheduler.startSyncTask(() ->
			{
				targetBlock.set(Item.Unavailable.Air); // Remove the end frame block
				int lootAmount = random.nextInt(3) + 1;
				for (int c = 0; c < lootAmount; c++)
					loot.get(random.nextInt(loot.size())).drop(effectLocation);
			}, 11);

			new DungeonEvent(player).Fire();
		}
		return true;
	}

	private void playSpark(final ILocation location, int seconds)
	{
		scheduler.startSyncTask(() ->
		{
			location.playEffect(sparkEffect, 1F, 100, 30);
			location.playSound(Sound.Environment.Fizz, 1F, 0F);

			int mobAmount = random.nextInt(2) + 1;
			Buff jumpBuff = Buff.Utility.Movement.JumpHeight.ambient(true).amplification(5).duration(36000);
			Buff powerBuff = Buff.Combat.Damage.Increase.ambient(true).amplification(2).duration(36000);
			Buff speedBuff = Buff.Utility.Movement.IncreaseSpeed.ambient(true).amplification(2).duration(36000);
			Buff fireResist = Buff.Resistance.Fire.ambient(true).amplification(2).duration(36000);

			for (int m = 0; m < mobAmount; m++)
			{
				ILivingEntity entity = (ILivingEntity) LivingEntity.Silverfish.spawn(location);
				jumpBuff.applyTo(entity);
				powerBuff.applyTo(entity);
				speedBuff.applyTo(entity);
				fireResist.applyTo(entity);
			}

		}, seconds);
	}

	private final WorldBlockEffect sparkEffect;
	private final IScheduler scheduler;
	public static final Random random = new Random();
	private final WorldHandler handler;

	private static final List<DungeonLoot> loot = new ArrayList<>(0);
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
		loot.add(new DungeonLoot(Item.BuildingBlock.EndStone, 10, 30));
		loot.add(new DungeonLoot(Item.Food.Golden.Apple));
		loot.add(new DungeonLoot(Item.Food.Plant.Apple, 2));
		loot.add(new DungeonLoot(Item.BuildingBlock.EndStone, 10, 30));
		loot.add(new DungeonLoot(Item.Materials.GoldIngot, 4, 6));
		loot.add(new DungeonLoot(Item.BuildingBlock.EndStone, 10, 30));
		loot.add(new DungeonLoot(Item.Transportation.Minecart.Explosive));
		loot.add(new DungeonLoot(Item.Combat.Sword.Diamond));
		loot.add(new DungeonLoot(Item.BuildingBlock.EndStone, 10, 30));
		loot.add(new DungeonLoot(Item.Miscellaneous.Beacon));
		loot.add(new DungeonLoot(Item.Brewing.BrewingStand));
	};
}
