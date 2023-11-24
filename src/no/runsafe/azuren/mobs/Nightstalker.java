package no.runsafe.azuren.mobs;

import net.minecraft.server.v1_12_R1.*;
import no.runsafe.azuren.Plugin;
import no.runsafe.framework.api.ILocation;
import no.runsafe.framework.api.IWorld;
import no.runsafe.framework.minecraft.Sound;

public class Nightstalker extends EntityBat
{
	public Nightstalker(World world)
	{
		super(world);
		rworld = Plugin.server.getWorld(world.worldData.getName());
		//effect = new MobEffect(MobEffects.INVISIBILITY, 86400 * 20, 1, true, true);
	}

	public void spawn(ILocation location, String headName)
	{
		//addEffect(effect);

		//RunsafeSkull skull = (RunsafeSkull) no.runsafe.framework.minecraft.Item.Decoration.Head.Human.getItem();
		//skull.setOwner(headName);
		//setEquipment(EnumItemSlot.HEAD, ObjectUnwrapper.getMinecraft(skull));
		//dropChanceArmor[3] = 0.0F; // Prevent head dropping
		setPosition(location.getX(), 75, location.getZ());
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

	/*
	* Plays Idle Sound.
	* Make it return null instead.
	* Names of this function in various spigot versions:
	* v1_12_R1: F
	 */
	@Override
	public SoundEffect F()
	{
		return null;
	}

	/*
	* Involved in playing the bat death sound.
	* Make it return null instead.
	* Names of this function in various spigot versions:
	* v1_12_R1: cf
	 */
	@Override
	protected SoundEffect cf()
	{
		return null;
	}

	/*
	* Plays bat hurt sound.
	* Make it return null instead.
	* Names of this function in various spigot versions:
	* v1_12_R1: d(DamageSource), returns SoundEffect
	 */
	@Override
	protected SoundEffect d(DamageSource damageSource)
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
	protected void dropDeathLoot(boolean flag, int i)
	{
		a(Items.COOKIE, 1); // Always drop a cookie.
	}

	private final IWorld rworld;
	//private final MobEffect effect;
}
