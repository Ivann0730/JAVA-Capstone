package tile;

import DannyGermanSimulator.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
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
        try {
//            tile[0] = new Tile();
//            tile[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/grass.png"));
//
//            tile[1] = new Tile();
//            tile[1].image = ImageIO.read(getClass().getResourceAsStream("/tiles/sand.png"));
//
//            tile[2] = new Tile();
//            tile[2].image = ImageIO.read(getClass().getResourceAsStream("/tiles/tree.png"));
//            tile[2].collision = true;
//
//            tile[3] = new Tile();
//            tile[3].image = ImageIO.read(getClass().getResourceAsStream("/tiles/water.png"));
//            tile[3].collision = true;

            tile[0] = new Tile();
            tile[0].image = ImageIO.read(getClass().getResourceAsStream("/Tiles_Ren/000.png"));

            tile[1] = new Tile();
            tile[1].image = ImageIO.read(getClass().getResourceAsStream("/Tiles_Ren/001.png"));

            tile[2] = new Tile();
            tile[2].image = ImageIO.read(getClass().getResourceAsStream("/Tiles_Ren/002.png"));

            tile[3] = new Tile();
            tile[3].image = ImageIO.read(getClass().getResourceAsStream("/Tiles_Ren/003.png"));

            tile[4] = new Tile();
            tile[4].image = ImageIO.read(getClass().getResourceAsStream("/Tiles_Ren/004.png"));

            tile[5] = new Tile();
            tile[5].image = ImageIO.read(getClass().getResourceAsStream("/Tiles_Ren/005.png"));

            tile[6] = new Tile();
            tile[6].image = ImageIO.read(getClass().getResourceAsStream("/Tiles_Ren/006.png"));

            tile[7] = new Tile();
            tile[7].image = ImageIO.read(getClass().getResourceAsStream("/Tiles_Ren/007.png"));

            tile[8] = new Tile();
            tile[8].image = ImageIO.read(getClass().getResourceAsStream("/Tiles_Ren/008.png"));

            tile[9] = new Tile();
            tile[9].image = ImageIO.read(getClass().getResourceAsStream("/Tiles_Ren/009.png"));

            tile[10] = new Tile();
            tile[10].image = ImageIO.read(getClass().getResourceAsStream("/Tiles_Ren/010.png"));

            tile[11] = new Tile();
            tile[11].image = ImageIO.read(getClass().getResourceAsStream("/Tiles_Ren/011.png"));

            tile[12] = new Tile();
            tile[12].image = ImageIO.read(getClass().getResourceAsStream("/Tiles_Ren/012.png"));

            tile[13] = new Tile();
            tile[13].image = ImageIO.read(getClass().getResourceAsStream("/Tiles_Ren/013.png"));

            tile[14] = new Tile();
            tile[14].image = ImageIO.read(getClass().getResourceAsStream("/Tiles_Ren/014.png"));

            tile[15] = new Tile();
            tile[15].image = ImageIO.read(getClass().getResourceAsStream("/Tiles_Ren/015.png"));

            tile[16] = new Tile();
            tile[16].image = ImageIO.read(getClass().getResourceAsStream("/Tiles_Ren/016.png"));
            tile[16].collision = true;

            tile[17] = new Tile();
            tile[17].image = ImageIO.read(getClass().getResourceAsStream("/Tiles_Ren/017.png"));

            tile[18] = new Tile();
            tile[18].image = ImageIO.read(getClass().getResourceAsStream("/Tiles_Ren/018.png"));
            tile[18].collision = true;

            tile[19] = new Tile();
            tile[19].image = ImageIO.read(getClass().getResourceAsStream("/Tiles_Ren/019.png"));
            tile[19].collision = true;

            tile[20] = new Tile();
            tile[20].image = ImageIO.read(getClass().getResourceAsStream("/Tiles_Ren/02.png"));

            tile[21] = new Tile();
            tile[21].image = ImageIO.read(getClass().getResourceAsStream("/Tiles_Ren/020.png"));
            tile[21].collision = true;

            tile[22] = new Tile();
            tile[22].image = ImageIO.read(getClass().getResourceAsStream("/Tiles_Ren/021.png"));
            tile[22].collision = true;

            tile[23] = new Tile();
            tile[23].image = ImageIO.read(getClass().getResourceAsStream("/Tiles_Ren/022.png"));
            tile[23].collision = true;

            tile[24] = new Tile();
            tile[24].image = ImageIO.read(getClass().getResourceAsStream("/Tiles_Ren/023.png"));
            tile[24].collision = true;

            tile[25] = new Tile();
            tile[25].image = ImageIO.read(getClass().getResourceAsStream("/Tiles_Ren/024.png"));
            tile[25].collision = true;

            tile[26] = new Tile();
            tile[26].image = ImageIO.read(getClass().getResourceAsStream("/Tiles_Ren/025.png"));
            tile[26].collision = true;

            tile[27] = new Tile();
            tile[27].image = ImageIO.read(getClass().getResourceAsStream("/Tiles_Ren/026.png"));
            tile[27].collision = true;

            tile[28] = new Tile();
            tile[28].image = ImageIO.read(getClass().getResourceAsStream("/Tiles_Ren/027.png"));
            tile[28].collision = true;

            tile[29] = new Tile();
            tile[29].image = ImageIO.read(getClass().getResourceAsStream("/Tiles_Ren/028.png"));
            tile[29].collision = true;

            tile[30] = new Tile();
            tile[30].image = ImageIO.read(getClass().getResourceAsStream("/Tiles_Ren/029.png"));
            tile[30].collision = true;

            tile[31] = new Tile();
            tile[31].image = ImageIO.read(getClass().getResourceAsStream("/Tiles_Ren/030.png"));
            tile[31].collision = true;

            tile[32] = new Tile();
            tile[32].image = ImageIO.read(getClass().getResourceAsStream("/Tiles_Ren/031.png"));
            tile[32].collision = true;

            tile[33] = new Tile();
            tile[33].image = ImageIO.read(getClass().getResourceAsStream("/Tiles_Ren/032.png"));
            tile[33].collision = true;

            tile[34] = new Tile();
            tile[34].image = ImageIO.read(getClass().getResourceAsStream("/Tiles_Ren/033.png"));
            tile[34].collision = true;

            tile[35] = new Tile();
            tile[35].image = ImageIO.read(getClass().getResourceAsStream("/Tiles_Ren/034.png"));

            tile[36] = new Tile();
            tile[36].image = ImageIO.read(getClass().getResourceAsStream("/Tiles_Ren/035.png"));
            tile[36].collision = true;

            tile[37] = new Tile();
            tile[37].image = ImageIO.read(getClass().getResourceAsStream("/Tiles_Ren/036.png"));

            tile[38] = new Tile();
            tile[38].image = ImageIO.read(getClass().getResourceAsStream("/Tiles_Ren/037.png"));

            tile[39] = new Tile();
            tile[39].image = ImageIO.read(getClass().getResourceAsStream("/Tiles_Ren/04.png"));

            tile[40] = new Tile();
            tile[40].image = ImageIO.read(getClass().getResourceAsStream("/Tiles_Ren/05.png"));

            tile[41] = new Tile();
            tile[41].image = ImageIO.read(getClass().getResourceAsStream("/Tiles_Ren/08.png"));
            tile[41].collision = true;

            tile[42] = new Tile();
            tile[42].image = ImageIO.read(getClass().getResourceAsStream("/Tiles_Ren/09.png"));

            tile[43] = new Tile();
            tile[43].image = ImageIO.read(getClass().getResourceAsStream("/Tiles_Ren/1.png"));

            tile[44] = new Tile();
            tile[44].image = ImageIO.read(getClass().getResourceAsStream("/Tiles_Ren/10.png"));

            tile[45] = new Tile();
            tile[45].image = ImageIO.read(getClass().getResourceAsStream("/Tiles_Ren/11.png"));
            tile[45].collision = true;

            tile[46] = new Tile();
            tile[46].image = ImageIO.read(getClass().getResourceAsStream("/Tiles_Ren/12.png"));
            tile[46].collision = true;

            tile[47] = new Tile();
            tile[47].image = ImageIO.read(getClass().getResourceAsStream("/Tiles_Ren/13.png"));

            tile[48] = new Tile();
            tile[48].image = ImageIO.read(getClass().getResourceAsStream("/Tiles_Ren/14.png"));

            tile[49] = new Tile();
            tile[49].image = ImageIO.read(getClass().getResourceAsStream("/Tiles_Ren/15.png"));

            tile[50] = new Tile();
            tile[50].image = ImageIO.read(getClass().getResourceAsStream("/Tiles_Ren/16.png"));

            tile[51] = new Tile();
            tile[51].image = ImageIO.read(getClass().getResourceAsStream("/Tiles_Ren/17.png"));

            tile[52] = new Tile();
            tile[52].image = ImageIO.read(getClass().getResourceAsStream("/Tiles_Ren/18.png"));

            tile[53] = new Tile();
            tile[53].image = ImageIO.read(getClass().getResourceAsStream("/Tiles_Ren/19.png"));

            tile[54] = new Tile();
            tile[54].image = ImageIO.read(getClass().getResourceAsStream("/Tiles_Ren/20.png"));

            tile[55] = new Tile();
            tile[55].image = ImageIO.read(getClass().getResourceAsStream("/Tiles_Ren/21.png"));

            tile[56] = new Tile();
            tile[56].image = ImageIO.read(getClass().getResourceAsStream("/Tiles_Ren/22.png"));


            // Add more tiles if needed...
        } catch (Exception e) {
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

                g2.drawImage(tile[tileNum].image, screenX, screenY, gp.tileSize, gp.tileSize, null);
            }
            worldCol++;

            if (worldCol == gp.maxWorldCol) {
                worldCol = 0;
                worldRow++;
            }
        }
    }
}

