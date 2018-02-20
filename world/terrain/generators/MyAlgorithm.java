package world.terrain.generators;

import java.util.Random;
import world.World;
import world.terrain.Generator;
import Advanced-Terrain-Generation-Tool.resources.PerlinNoise;

public class MyAlgorithm extends Generator {
    
    public MyAlgorithm() { }
    
    
    /**
     * Create terrain world. Different tiles for different elevation, where 0 is the lowest value
     * and 1 is the highest value.
     * @param elevation Noise array to be analysed.
     * @param w World to generate terrain for.
     */
    private void terrainCreation(float[][] elevation, World w) {
        
        for (int i = 0; i < elevation.length; i++) {
            for (int j = 0; j < elevation[0].length; j++) {
                
                if (elevation[i][j] < 0.1) w.setTile(0, i, j);
                else if (elevation[i][j] > 0.9) w.setTile(2, i, j); 
                else w.setTile(1, i, j);
            }
        }
        
    }
    
    @Override
    public void generate(World w) {
        
                // Use PerlinNoise algorithm in other location
                // 6 is a random value, I don't know what the best value would be
                float[][] noise = GenerateWhiteNoise(w.columns(), w.rows());
                float[][] perlinNoise = GeneratePerlinNoise(noise, 6);
                
                // Create terrain
                terrainCreation(perlinNoise, w);
                
            }
        }
    }

}
