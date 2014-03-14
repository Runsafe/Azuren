package no.runsafe.azuren.populators;

import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.generator.BlockPopulator;

import java.util.Random;

public class DungeonGen extends BlockPopulator
{
	@Override
	public void populate(World world, Random random, Chunk chunk)
	{
		if (random.nextFloat() <= 0.01)
		{
			// Render dungeon
			int startX = chunk.getX();
			int startZ = chunk.getZ();

			int startY = random.nextInt(50) + 80; // Testing numbers.

			int currentLayer = 0;
			for (int[][] layer : dungeonLayout)
			{
				int y = startY + currentLayer;

				int currentRow = 0;
				for (int[] row : layer)
				{
					int x = startX + currentRow;

					int currentCol = 0;
					for (int col : row)
					{
						int z = startZ + currentCol;
						BlockInfo block = palette[col]; // Get the block info.
						world.getBlockAt(x, y, z).setTypeIdAndData(block.getBlockID(), block.getBlockData(), false);
						currentCol++;
					}
					currentRow++;
				}
				currentLayer++;
			}
		}
	}

	private final BlockInfo[] palette = new BlockInfo[] {
			new BlockInfo(Material.STAINED_CLAY, (byte) 3), // 0
			new BlockInfo(Material.STAINED_CLAY, (byte) 11), // 1
			new BlockInfo(Material.AIR), // 2
			new BlockInfo(Material.ENDER_PORTAL_FRAME) // 3
	};

	private final int[][][] dungeonLayout = new int[][][] {
			{ // Bottom layer
					{0, 0, 0, 0, 0, 0, 0, 0, 0},
					{0, 1, 0, 0, 1, 0, 0, 1, 0},
					{0, 0, 1, 0, 1, 0, 1, 0, 0},
					{0, 0, 0, 1, 1, 1, 0, 0, 0},
					{0, 0, 0, 1, 1, 1, 0, 0, 0},
					{0, 0, 0, 1, 1, 1, 0, 0, 0},
					{0, 0, 1, 0, 1, 0, 1, 0, 0},
					{0, 1, 0, 0, 1, 0, 0, 1, 0},
					{0, 0, 0, 0, 0, 0, 0, 0, 0},
			},
			{
					{1, 1, 1, 1, 1, 1, 1, 1, 1},
					{1, 2, 2, 2, 2, 2, 2, 2, 1},
					{1, 2, 2, 2, 2, 2, 2, 2, 1},
					{1, 2, 2, 2, 2, 2, 2, 2, 1},
					{1, 2, 2, 2, 3, 2, 2, 2, 1},
					{1, 2, 2, 2, 2, 2, 2, 2, 1},
					{1, 2, 2, 2, 2, 2, 2, 2, 1},
					{1, 2, 2, 2, 2, 2, 2, 2, 1},
					{1, 1, 1, 1, 1, 1, 1, 1, 1},
			},
			{
					{1, 1, 1, 1, 1, 1, 1, 1, 1},
					{1, 2, 2, 2, 2, 2, 2, 2, 1},
					{1, 2, 2, 2, 2, 2, 2, 2, 1},
					{1, 2, 2, 2, 2, 2, 2, 2, 1},
					{1, 2, 2, 2, 2, 2, 2, 2, 1},
					{1, 2, 2, 2, 2, 2, 2, 2, 1},
					{1, 2, 2, 2, 2, 2, 2, 2, 1},
					{1, 2, 2, 2, 2, 2, 2, 2, 1},
					{1, 1, 1, 1, 1, 1, 1, 1, 1},
			},
			{
					{1, 1, 1, 1, 1, 1, 1, 1, 1},
					{1, 2, 2, 2, 2, 2, 2, 2, 1},
					{1, 2, 2, 2, 2, 2, 2, 2, 1},
					{1, 2, 2, 2, 2, 2, 2, 2, 1},
					{1, 2, 2, 2, 2, 2, 2, 2, 1},
					{1, 2, 2, 2, 2, 2, 2, 2, 1},
					{1, 2, 2, 2, 2, 2, 2, 2, 1},
					{1, 2, 2, 2, 2, 2, 2, 2, 1},
					{1, 1, 1, 1, 1, 1, 1, 1, 1},
			},
			{
					{1, 1, 1, 1, 1, 1, 1, 1, 1},
					{1, 2, 2, 2, 2, 2, 2, 2, 1},
					{1, 2, 2, 2, 2, 2, 2, 2, 1},
					{1, 2, 2, 2, 2, 2, 2, 2, 1},
					{1, 2, 2, 2, 2, 2, 2, 2, 1},
					{1, 2, 2, 2, 2, 2, 2, 2, 1},
					{1, 2, 2, 2, 2, 2, 2, 2, 1},
					{1, 2, 2, 2, 2, 2, 2, 2, 1},
					{1, 1, 1, 1, 1, 1, 1, 1, 1},
			},
			{
					{1, 1, 1, 1, 1, 1, 1, 1, 1},
					{1, 2, 2, 2, 2, 2, 2, 2, 1},
					{1, 2, 2, 2, 2, 2, 2, 2, 1},
					{1, 2, 2, 2, 2, 2, 2, 2, 1},
					{1, 2, 2, 2, 2, 2, 2, 2, 1},
					{1, 2, 2, 2, 2, 2, 2, 2, 1},
					{1, 2, 2, 2, 2, 2, 2, 2, 1},
					{1, 2, 2, 2, 2, 2, 2, 2, 1},
					{1, 1, 1, 1, 1, 1, 1, 1, 1},
			}
	};
}
