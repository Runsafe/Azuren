package no.runsafe.azuren.populators;

import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.World;

import java.util.Random;

@SuppressWarnings("deprecation")
public class Surface extends SheetGenerator
{
	@Override
	public void populate(World world, Random random, Chunk chunk)
	{
		generateSheet(chunk, 64, Material.STAINED_CLAY.getId(), (byte) 11);
	}
}
