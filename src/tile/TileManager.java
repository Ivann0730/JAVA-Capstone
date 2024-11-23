package tile;

import DannyGermanSimulator.GamePanel;
import DannyGermanSimulator.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;

public class TileManager {
    GamePanel gp;
    public Tile[] tile;
    public int[][] mapTileNum;

    public TileManager(GamePanel gp) {
        this.gp = gp;
        tile = new Tile[60]; // Assuming 10 types of tiles
        mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow]; // Map for tile numbers
        getTileImage(); // Load tile images
        loadMap("/maps/testmap.txt"); // Load map from file
    }
    // Method to load tile images
    public void getTileImage() {
            setUp(0,"000" , false);
            setUp(1,"001" , false);
            setUp(2,"002" , false);
            setUp(3,"003" , false);
            setUp(4,"004" , false);
            setUp(5,"005" , false);
            setUp(6,"006" , false);
            setUp(7,"007" , false);
            setUp(8,"008" , false);
            setUp(9,"009" , false);
            setUp(10,"010" , false);
            setUp(11,"011" , false);
            setUp(12,"012" , false);
            setUp(13,"013" , false);
            setUp(14,"014" , false);
            setUp(15,"015" , false);
            setUp(16,"016" , true);
            setUp(17,"017" , false);
            setUp(18,"018" , true);
            setUp(19,"019" , true);
            setUp(20,"02" , false);
            setUp(21,"020" , true);
            setUp(22,"021" , true);
            setUp(23,"022" , true);
            setUp(24,"023", true);
            setUp(25,"024" , true);
            setUp(26,"025" , true);
            setUp(27,"026" , true);
            setUp(28,"027" , true);
            setUp(29,"028" , true);
            setUp(30,"029" , true);
            setUp(31,"030" , true);
            setUp(32,"031" , true);
            setUp(33,"032" , true);
            setUp(34,"033" , true);
            setUp(35,"034" , false);
            setUp(36,"035" , true);
            setUp(37,"036" , false);
            setUp(38,"037" , false);
            setUp(39,"04" , false);
            setUp(40,"05" , false);
            setUp(41,"08" , true);
            setUp(42,"09" , false);
            setUp(43,"1" , false);
            setUp(44,"10" , false);
            setUp(45,"11" , true);
            setUp(46,"12" , true);
            setUp(47,"13" , false);
            setUp(48,"14" , false);
            setUp(49,"15" , false);
            setUp(50,"16" , false);
            setUp(51,"17" , false);
            setUp(52,"18" , false);
            setUp(53,"19" , false);
            setUp(54,"20" , false);
            setUp(55,"21" , false);
            setUp(56,"22" , false);
    }
    public void setUp(int index, String imageName, boolean collision){

        UtilityTool uTool = new UtilityTool();
        try {
            tile[index] = new Tile();
            tile[index].image = ImageIO.read(getClass().getResourceAsStream("/Tiles_Ren/" + imageName + ".png"));
            tile[index].image = uTool.scaleImage(tile[index].image, gp.tileSize, gp.tileSize);
            tile[index].collision = collision;
        } catch(IOException e){
            e.printStackTrace();
        }
    }
    // Load map data from text file
    public void loadMap(String filePath) {

        try {

            // import and read the map matrix
            InputStream is = getClass().getResourceAsStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(Objects.requireNonNull(is)));

            int col = 0;
            int row = 0;

            // read one row line of the map matrix data
            while (col < gp.maxWorldCol && row < gp.maxWorldRow) {

                String line = br.readLine();

                while (col < gp.maxWorldCol) {

                    // split the line of data matrix into solo digits
                    String[] numbers = line.split(" ");

                    // parse String to int
                    int num = Integer.parseInt(numbers[col]);

                    mapTileNum[col][row] = num;
                    col++;
                }

                if (col == gp.maxWorldCol) {
                    col = 0;
                    row++;
                }
            }
            br.close();

        }
        catch (Exception e) {


        }
    }
    // Draw method to render the map
    public void draw(Graphics2D g2) {
        int worldCol = 0;
        int worldRow = 0;

        while (worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow) {
            int tileNum = mapTileNum[worldCol][worldRow];

            int worldX = worldCol * gp.tileSize;
            int worldY = worldRow * gp.tileSize;
            int screenX = worldX - gp.player.worldX + gp.player.screenX;
            int screenY = worldY - gp.player.worldY + gp.player.screenY;

            // Only draw the tiles that are visible on the screen
            if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
                    worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
                    worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
                    worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {

                g2.drawImage(tile[tileNum].image, screenX, screenY, null);
            }
            worldCol++;

            if (worldCol == gp.maxWorldCol) {
                worldCol = 0;
                worldRow++;
            }
        }
    }
}

