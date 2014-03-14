package no.runsafe.azuren.populators;

import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.generator.BlockPopulator;

import java.util.Random;

public class Spires extends BlockPopulator
{
	@Override
	public void populate(World world, Random random, Chunk chunk)
	{
		for (int x = 0; x < 16; x++)
		{
			for (int z = 0; z < 16; z++)
			{
				if (random.nextFloat() <= 0.5F)
				{
					int treeTop = 65 + (3 + random.nextInt(4));
					for (int y = 65; y < treeTop; y++)
					{
						Block block = chunk.getBlock(x, y, z);
						block.setType(Material.OBSIDIAN);
					}
				}
			}
		}
	}
}
