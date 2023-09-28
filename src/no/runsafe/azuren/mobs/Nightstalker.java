package no.runsafe.azuren.mobs;

import net.minecraft.server.v1_8_R3.*;
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
		effect = new MobEffect(MobEffectList.INVISIBILITY.id, 86400 * 20, 1, true, true);
	}

	public void spawn(ILocation location, String headName)
	{
		addEffect(effect);

		RunsafeSkull skull = (RunsafeSkull) no.runsafe.framework.minecraft.Item.Decoration.Head.Human.getItem();
		skull.setOwner(headName);
		setEquipment(4, ObjectUnwrapper.getMinecraft(skull));
		dropChances[4] = 0.0F; // Prevent head dropping

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
	* v1_8_R3: z
	* v1_12_R1: F returns SoundEffect
	 */
	@Override
	protected String z()
	{
		return null;
	}

	/*
	* Involved in playing the bat death sound.
	* Make it return null instead.
	* Names of this function in various spigot versions:
	* v1_8_R3: bp
	* v1_12_R1: cf, returns SoundEffect
	 */
	@Override
	protected String bp()
	{
		return null;
	}

	/*
	* Plays bat hurt sound.
	* Make it return null instead.
	* Names of this function in various spigot versions:
	* v1_8_R3: bo
	* v1_12_R1: d(DamageSource), returns SoundEffect
	 */
	@Override
	protected String bo()
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
	private boolean died;
	private final MobEffect effect;
}
