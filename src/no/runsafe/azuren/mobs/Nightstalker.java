package no.runsafe.azuren.mobs;

import net.minecraft.server.v1_7_R1.*;
import no.runsafe.azuren.Plugin;
import no.runsafe.framework.api.ILocation;
import no.runsafe.framework.api.IWorld;
import no.runsafe.framework.internal.wrapper.ObjectUnwrapper;
import no.runsafe.framework.minecraft.Sound;
import no.runsafe.framework.minecraft.item.meta.RunsafeSkull;

public class Nightstalker extends EntityBat
{
	public Nightstalker(World world)
	{
		super(world);
		rworld = Plugin.server.getWorld(world.worldData.getName());
	}

	public void spawn(ILocation location)
	{
		addEffect(new MobEffect(MobEffectList.INVISIBILITY.id, 86400 * 20, 1));

		RunsafeSkull skull = (RunsafeSkull) no.runsafe.framework.minecraft.Item.Decoration.Head.Human.getItem();
		skull.setOwner("JettKuso");
		setEquipment(4, ObjectUnwrapper.getMinecraft(skull));
		dropChances[4] = 0.0F; // Prevent head dropping

		setPosition(location.getX(), location.getY(), location.getZ());
		world.addEntity(this);
		location.playSound(Sound.Creature.Wolf.Growl, 2, 0);
	}

	private boolean hasWorld()
	{
		return world != null;
	}

	private ILocation getLocation()
	{
		if (!hasWorld())
			return null;

		return rworld.getLocation(locX, locY, locZ);
	}

	@Override
	protected String t()
	{
		return null;
	}

	@Override
	protected String aT()
	{
		return null;
	}

	@Override
	protected String aU()
	{
		return null;
	}

	@Override
	public boolean damageEntity(DamageSource damageSource, float v)
	{
		getLocation().playSound(Sound.Creature.PigZombie.Hurt, 2, 2);
		return super.damageEntity(damageSource, v);
	}

	@Override
	public void die(DamageSource damagesource)
	{
		getLocation().playSound(Sound.Creature.Zombie.Death, 2, 2);
		super.die(damagesource);
	}

	@Override
	protected void dropDeathLoot(boolean flag, int i)
	{
		a(Items.COOKIE, 1); // Always drop a cookie.
	}

	private final IWorld rworld;
}
