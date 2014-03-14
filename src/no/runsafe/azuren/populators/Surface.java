package no.runsafe.azuren.populators;

import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.generator.BlockPopulator;

import java.util.Random;

@SuppressWarnings("deprecation")
public class Surface extends BlockPopulator
{
	@Override
	public void populate(World world, Random random, Chunk chunk)
	{
		for (int x = 0; x < 16; x++)
		{
			for (int z = 0; z < 16; z++)
			{
				Block block = chunk.getBlock(x, 64, z);
				block.setTypeIdAndData(Material.STAINED_CLAY.getId(), (byte) 11, false);
			}
		}
	}
}
