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
    public Tile[] tileSpawn;
    public int[][][] mapTileNum;

    public TileManager(GamePanel gp) {
        this.gp = gp;
        tile = new Tile[60];// Assuming 60 types of tiles
        tileSpawn = new Tile[105];
        mapTileNum = new int[gp.maxMap][gp.maxWorldCol][gp.maxWorldRow]; // Map for tile numbers
        getTileImage(); // Load tile images
        getTileImageSpawn();
        loadMap("/maps/testmap.txt", 0); // Load map from file
        loadMap("/maps/interior01.txt",1);
        loadMap("/maps/Spawn.txt", 2); //SPAWN
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
        setUp(34,"/Tiles_Ren/033" , false);
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
    public void getTileImageSpawn() {
        setUpSpawn(0,"/Spawn/001" , false);
        setUpSpawn(1,"/Spawn/002" , false);
        setUpSpawn(2,"/Spawn/003" , false);
        setUpSpawn(3,"/Spawn/004" , false);
        setUpSpawn(4,"/Spawn/005" , false);
        setUpSpawn(5,"/Spawn/006" , false);
        setUpSpawn(6,"/Spawn/007" , false);
        setUpSpawn(7,"/Spawn/008" , false);
        setUpSpawn(8,"/Spawn/009" , false);
        setUpSpawn(9,"/Spawn/010" , false);
        setUpSpawn(10,"/Spawn/011" , false);
        setUpSpawn(11,"/Spawn/012" , false);
        setUpSpawn(12,"/Spawn/013" , false);
        setUpSpawn(13,"/Spawn/014" , false);
        setUpSpawn(14,"/Spawn/015" , false);
        setUpSpawn(15,"/Spawn/018" , true);
        setUpSpawn(16,"/Spawn/019" , true);
        setUpSpawn(17,"/Spawn/02" , false);
        setUpSpawn(18,"/Spawn/020" , true);
        setUpSpawn(19,"/Spawn/021" , true);
        setUpSpawn(20,"/Spawn/022" , true);
        setUpSpawn(21,"/Spawn/023" , true);
        setUpSpawn(22,"/Spawn/024" , true);
        setUpSpawn(23,"/Spawn/025" , true);
        setUpSpawn(24,"/Spawn/026" , true);
        setUpSpawn(25,"/Spawn/027" , true);
        setUpSpawn(26,"/Spawn/028" , true);
        setUpSpawn(27,"/Spawn/029" , true);
        setUpSpawn(28,"/Spawn/030" , true);
        setUpSpawn(29,"/Spawn/031" , true);
        setUpSpawn(30,"/Spawn/04" , false);
        setUpSpawn(31,"/Spawn/05" , false);
        setUpSpawn(32,"/Spawn/09" , false);
        setUpSpawn(33,"/Spawn/1" , false);
        setUpSpawn(34,"/Spawn/10" , false);
        setUpSpawn(35,"/Spawn/14" , false);
        setUpSpawn(36,"/Spawn/15" , false);
        setUpSpawn(37,"/Spawn/16" , false);
        setUpSpawn(38,"/Spawn/17" , false);
        setUpSpawn(39,"/Spawn/18" , false);
        setUpSpawn(40,"/Spawn/19" , false);
        setUpSpawn(41,"/Spawn/20" , false);
        setUpSpawn(42,"/Spawn/21" , false);
        setUpSpawn(43,"/Spawn/22" , false);
        setUpSpawn(44,"/Spawn/B" , true);
        setUpSpawn(45,"/Spawn/B1" , true);
        setUpSpawn(46,"/Spawn/B2" , true);
        setUpSpawn(47,"/Spawn/b3" , true);
        setUpSpawn(48,"/Spawn/h1" , true);
        setUpSpawn(49,"/Spawn/h10" , true);
        setUpSpawn(50,"/Spawn/h11" , true);
        setUpSpawn(51,"/Spawn/h12" , true);
        setUpSpawn(52,"/Spawn/h13" , true);
        setUpSpawn(53,"/Spawn/h14" , true);
        setUpSpawn(54,"/Spawn/h15" , true);
        setUpSpawn(55,"/Spawn/h16" , true);
        setUpSpawn(56,"/Spawn/h17" , true);
        setUpSpawn(57,"/Spawn/h18" , true);
        setUpSpawn(58,"/Spawn/h19" , true);
        setUpSpawn(59,"/Spawn/h2" , true);
        setUpSpawn(60,"/Spawn/h20" , true);
        setUpSpawn(61,"/Spawn/h21" , true);
        setUpSpawn(62,"/Spawn/h22" , true);
        setUpSpawn(63,"/Spawn/h23" , true);
        setUpSpawn(64,"/Spawn/h24" , true);
        setUpSpawn(65,"/Spawn/h25" , true);
        setUpSpawn(66,"/Spawn/h26" , true);
        setUpSpawn(67,"/Spawn/h27" , true);
        setUpSpawn(68,"/Spawn/h28" , true);
        setUpSpawn(69,"/Spawn/h29" , true);
        setUpSpawn(70,"/Spawn/h3" , true);
        setUpSpawn(71,"/Spawn/h30" , true);
        setUpSpawn(72,"/Spawn/h4" , true);
        setUpSpawn(73,"/Spawn/h5" , true);
        setUpSpawn(74,"/Spawn/h6" , true);
        setUpSpawn(75,"/Spawn/h7" , true);
        setUpSpawn(76,"/Spawn/h8" , true);
        setUpSpawn(77,"/Spawn/h9" , true);
        setUpSpawn(78,"/Spawn/Rock" , true);
        setUpSpawn(79,"/Spawn/Sign" , true);
        setUpSpawn(80,"/Spawn/st1" , true);
        setUpSpawn(81,"/Spawn/st2" , true);
        setUpSpawn(82,"/Spawn/st3" , true);
        setUpSpawn(83,"/Spawn/st4" , true);
        setUpSpawn(84,"/Spawn/st5" , true);
        setUpSpawn(85,"/Spawn/st6" , true);
        setUpSpawn(86,"/Spawn/T" , true);
        setUpSpawn(87,"/Spawn/T1" , true);
        setUpSpawn(88,"/Spawn/t10" , true);
        setUpSpawn(89,"/Spawn/T11" , true);
        setUpSpawn(90,"/Spawn/t2" , true);
        setUpSpawn(91,"/Spawn/T3" , true);
        setUpSpawn(92,"/Spawn/t4" , true);
        setUpSpawn(93,"/Spawn/T5" , true);
        setUpSpawn(94,"/Spawn/T6" , true);
        setUpSpawn(95,"/Spawn/T7" , true);
        setUpSpawn(96,"/Spawn/T8" , true);
        setUpSpawn(97,"/Spawn/T9" , true);
        setUpSpawn(98,"/Spawn/tr1" , true);
        setUpSpawn(99,"/Spawn/tr2" , true);
        setUpSpawn(100,"/Spawn/tr3" , true);
        setUpSpawn(101,"/Spawn/tr4" , true);
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
    public void setUpSpawn(int index, String imageName, boolean collision){

        UtilityTool uTool = new UtilityTool();
        try {
            tileSpawn[index] = new Tile();
            tileSpawn[index].image = ImageIO.read(getClass().getResourceAsStream(imageName + ".png"));
            tileSpawn[index].image = uTool.scaleImage(tileSpawn[index].image, gp.tileSize, gp.tileSize);
            tileSpawn[index].collision = collision;
        } catch(IOException e){
            e.printStackTrace();
        }
    }
    // Load map data from text file
    public void loadMap(String filePath, int map) {

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

                    mapTileNum[map][col][row] = num;
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
            int tileNum = mapTileNum[gp.currentMap][worldCol][worldRow];

            int worldX = worldCol * gp.tileSize;
            int worldY = worldRow * gp.tileSize;
            int screenX = worldX - gp.player.worldX + gp.player.screenX;
            int screenY = worldY - gp.player.worldY + gp.player.screenY;

            // Only draw the tiles that are visible on the screen
            if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
                    worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
                    worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
                    worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {

                if(gp.currentMap == 2){
                    g2.drawImage(tileSpawn[tileNum].image, screenX, screenY, null);
                } else {
                    g2.drawImage(tile[tileNum].image, screenX, screenY, null);
                }

            }
            worldCol++;

            if (worldCol == gp.maxWorldCol) {
                worldCol = 0;
                worldRow++;
            }
        }
    }
}

