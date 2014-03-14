package no.runsafe.azuren.populators;

import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.World;

import java.util.Random;

public class Ground extends SheetGenerator
{
	@Override
	public void populate(World world, Random random, Chunk chunk)
	{
		for (int y = 1; y < 62; y++)
			generateSheet(chunk, y, Material.STAINED_CLAY.getId(), (byte) 3);
	}
}
