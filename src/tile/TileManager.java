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
        tile = new Tile[60]; // Assuming 60 types of tiles
        mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow]; // Map for tile numbers
        getTileImage(); // Load tile images
        loadMap("/maps/testmap.txt"); // Load map from file
    }
    // Method to load tile images
    public void getTileImage() {
            setUp(0,"/Tiles_Ren/000" , false);
            setUp(1,"/Tiles_Ren/001" , false);
            setUp(2,"/Tiles_Ren/002" , false);
            setUp(3,"/Tiles_Ren/003" , false);
            setUp(4,"/Tiles_Ren/004" , false);
            setUp(5,"/Tiles_Ren/005" , false);
            setUp(6,"/Tiles_Ren/006" , false);
            setUp(7,"/Tiles_Ren/007" , false);
            setUp(8,"/Tiles_Ren/008" , false);
            setUp(9,"/Tiles_Ren/009" , false);
            setUp(10,"/Tiles_Ren/010" , false);
            setUp(11,"/Tiles_Ren/011" , false);
            setUp(12,"/Tiles_Ren/012" , false);
            setUp(13,"/Tiles_Ren/013" , false);
            setUp(14,"/Tiles_Ren/014" , false);
            setUp(15,"/Tiles_Ren/015" , false);
            setUp(16,"/Tiles_Ren/016" , true);
            setUp(17,"/Tiles_Ren/017" , false);
            setUp(18,"/Tiles_Ren/018" , true);
            setUp(19,"/Tiles_Ren/019" , true);
            setUp(20,"/Tiles_Ren/02" , false);
            setUp(21,"/Tiles_Ren/020" , true);
            setUp(22,"/Tiles_Ren/021" , true);
            setUp(23,"/Tiles_Ren/022" , true);
            setUp(24,"/Tiles_Ren/023", true);
            setUp(25,"/Tiles_Ren/024" , true);
            setUp(26,"/Tiles_Ren/025" , true);
            setUp(27,"/Tiles_Ren/026" , true);
            setUp(28,"/Tiles_Ren/027" , true);
            setUp(29,"/Tiles_Ren/028" , true);
            setUp(30,"/Tiles_Ren/029" , true);
            setUp(31,"/Tiles_Ren/030" , true);
            setUp(32,"/Tiles_Ren/031" , true);
            setUp(33,"/Tiles_Ren/032" , true);
            setUp(34,"/Tiles_Ren/033" , true);
            setUp(35,"/Tiles_Ren/034" , false);
            setUp(36,"/Tiles_Ren/035" , true);
            setUp(37,"/Tiles_Ren/036" , false);
            setUp(38,"/Tiles_Ren/037" , false);
            setUp(39,"/Tiles_Ren/04" , false);
            setUp(40,"/Tiles_Ren/05" , false);
            setUp(41,"/Tiles_Ren/08" , true);
            setUp(42,"/Tiles_Ren/09" , false);
            setUp(43,"/Tiles_Ren/1" , false);
            setUp(44,"/Tiles_Ren/10" , false);
            setUp(45,"/Tiles_Ren/11" , true);
            setUp(46,"/Tiles_Ren/12" , true);
            setUp(47,"/Tiles_Ren/13" , false);
            setUp(48,"/Tiles_Ren/14" , false);
            setUp(49,"/Tiles_Ren/15" , false);
            setUp(50,"/Tiles_Ren/16" , false);
            setUp(51,"/Tiles_Ren/17" , false);
            setUp(52,"/Tiles_Ren/18" , false);
            setUp(53,"/Tiles_Ren/19" , false);
            setUp(54,"/Tiles_Ren/20" , false);
            setUp(55,"/Tiles_Ren/21" , false);
            setUp(56,"/Tiles_Ren/22" , false);
            setUp(57,"/tiles_interactive/drytree" , false);
            setUp(58,"/tiles_interactive/trunk" , false);
    }
    public void setUp(int index, String imageName, boolean collision){

        UtilityTool uTool = new UtilityTool();
        try {
            tile[index] = new Tile();
            tile[index].image = ImageIO.read(getClass().getResourceAsStream(imageName + ".png"));
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

