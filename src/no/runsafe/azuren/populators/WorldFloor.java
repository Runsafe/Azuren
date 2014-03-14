package no.runsafe.azuren.populators;

import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.World;

import java.util.Random;

public class WorldFloor extends SheetGenerator
{
	@Override
	public void populate(World world, Random random, Chunk chunk)
	{
		generateSheet(chunk, 0, Material.OBSIDIAN.getId(), (byte) 0);
	}
}
