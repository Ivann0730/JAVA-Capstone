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
    public Tile[] tileDungeon;
    public Tile[] tilePathway;
    public int[][][] mapTileNum;
    boolean drawPath = false;

    public TileManager(GamePanel gp) {
        this.gp = gp;
        tile = new Tile[60];// Assuming 60 types of tiles
        tileSpawn = new Tile[105];
        tileDungeon = new Tile[100];
        tilePathway = new Tile[196];
        mapTileNum = new int[gp.maxMap][gp.maxWorldCol][gp.maxWorldRow]; // Map for tile numbers
        getTileImage(); // Load tile images
        getTileImageSpawn();
        getTileDungeon();
        getTilePathway();
        loadMap("/maps/testmap.txt", 0); // Load map from file
        loadMap("/maps/interior01.txt",1);
        loadMap("/maps/Spawn", 2); //SPAWN
        loadMap("/maps/Dungeon", 3);
        loadMap("/maps/Pathway", 4);

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

    public void getTileDungeon() {
        setUpDungeon(0, "/Tiles_Dungeon/Decor-1", true);
        setUpDungeon(1, "/Tiles_Dungeon/Decor-2", true);
        setUpDungeon(2, "/Tiles_Dungeon/Decor-3", true);
        setUpDungeon(3, "/Tiles_Dungeon/Decor-4", true);
        setUpDungeon(4, "/Tiles_Dungeon/Decor-5", true);
        setUpDungeon(5, "/Tiles_Dungeon/Decor-6", true);
        setUpDungeon(6, "/Tiles_Dungeon/Decor-7", true);
        setUpDungeon(7, "/Tiles_Dungeon/Floor-1", false);
        setUpDungeon(8, "/Tiles_Dungeon/Floor-2", false);
        setUpDungeon(9, "/Tiles_Dungeon/Floor-3", false);
        setUpDungeon(10, "/Tiles_Dungeon/Floor-4", false);
        setUpDungeon(11, "/Tiles_Dungeon/FS-1", false);
        setUpDungeon(12, "/Tiles_Dungeon/FS-2", false);
        setUpDungeon(13, "/Tiles_Dungeon/FS-3", false);
        setUpDungeon(14, "/Tiles_Dungeon/FS-4", false);
        setUpDungeon(15, "/Tiles_Dungeon/FS-5", false);
        setUpDungeon(16, "/Tiles_Dungeon/Stairs-1", false);
        setUpDungeon(17, "/Tiles_Dungeon/Stairs-2", false);
        setUpDungeon(18, "/Tiles_Dungeon/tr-1", false);
        setUpDungeon(19, "/Tiles_Dungeon/Walls-1", true);
        setUpDungeon(20, "/Tiles_Dungeon/Walls-10", true);
        setUpDungeon(21, "/Tiles_Dungeon/Walls-11", true);
        setUpDungeon(22, "/Tiles_Dungeon/Walls-12.png-1.png", true);
        setUpDungeon(23, "/Tiles_Dungeon/Walls-12.png-2.png", true);
        setUpDungeon(24, "/Tiles_Dungeon/Walls-12.png-3.png", true);
        setUpDungeon(25, "/Tiles_Dungeon/Walls-12.png-4.png", true);
        setUpDungeon(26, "/Tiles_Dungeon/Walls-12.png-5.png", true);
        setUpDungeon(27, "/Tiles_Dungeon/Walls-12", true);
        setUpDungeon(28, "/Tiles_Dungeon/Walls-13", true);
        setUpDungeon(29, "/Tiles_Dungeon/Walls-14", true);
        setUpDungeon(30, "/Tiles_Dungeon/Walls-15", true);
        setUpDungeon(31, "/Tiles_Dungeon/Walls-16", true);
        setUpDungeon(32, "/Tiles_Dungeon/Walls-17", true);
        setUpDungeon(33, "/Tiles_Dungeon/Walls-18", true);
        setUpDungeon(34, "/Tiles_Dungeon/Walls-19", true);
        setUpDungeon(35, "/Tiles_Dungeon/Walls-2", true);
        setUpDungeon(36, "/Tiles_Dungeon/Walls-20", true);
        setUpDungeon(37, "/Tiles_Dungeon/Walls-21", true);
        setUpDungeon(38, "/Tiles_Dungeon/Walls-22", true);
        setUpDungeon(39, "/Tiles_Dungeon/Walls-23", true);
        setUpDungeon(40, "/Tiles_Dungeon/Walls-24", true);
        setUpDungeon(41, "/Tiles_Dungeon/Walls-25.png", true);
        setUpDungeon(42, "/Tiles_Dungeon/Walls-26.png", true);
        setUpDungeon(43, "/Tiles_Dungeon/Walls-27.png", true);
        setUpDungeon(44, "/Tiles_Dungeon/Walls-28.png", true);
        setUpDungeon(45, "/Tiles_Dungeon/Walls-29.png", true);
        setUpDungeon(46, "/Tiles_Dungeon/Walls-5", true);
        setUpDungeon(47, "/Tiles_Dungeon/Walls-30.png", true);
        setUpDungeon(48, "/Tiles_Dungeon/Walls-31.png", true);
        setUpDungeon(49, "/Tiles_Dungeon/Walls-32.png", true);
        setUpDungeon(50, "/Tiles_Dungeon/Walls-33.png", true);
        setUpDungeon(51, "/Tiles_Dungeon/Walls-34.png", true);
        setUpDungeon(52, "/Tiles_Dungeon/Walls-35.png", true);
        setUpDungeon(53, "/Tiles_Dungeon/Walls-36.png", true);
        setUpDungeon(54, "/Tiles_Dungeon/Walls-37.png", true);
        setUpDungeon(55, "/Tiles_Dungeon/Walls-38.png", true);
        setUpDungeon(56, "/Tiles_Dungeon/Walls-39.png", true);
        setUpDungeon(57, "/Tiles_Dungeon/Walls-4", true);
        setUpDungeon(58, "/Tiles_Dungeon/Walls-40.png", true);
        setUpDungeon(59, "/Tiles_Dungeon/Walls-41.png", true);
        setUpDungeon(60, "/Tiles_Dungeon/Walls-42.png", true);
        setUpDungeon(61, "/Tiles_Dungeon/Walls-43.png", true);
        setUpDungeon(62, "/Tiles_Dungeon/Walls-44.png", true);
        setUpDungeon(63, "/Tiles_Dungeon/Walls-45.png", true);
        setUpDungeon(64, "/Tiles_Dungeon/Walls-46.png", true);
        setUpDungeon(65, "/Tiles_Dungeon/Walls-47.png", true);
        setUpDungeon(66, "/Tiles_Dungeon/Walls-48.png", true);
        setUpDungeon(67, "/Tiles_Dungeon/Walls-49.png", true);
        setUpDungeon(68, "/Tiles_Dungeon/Walls-3", true);
        setUpDungeon(69, "/Tiles_Dungeon/Walls-50.png", true);
        setUpDungeon(70, "/Tiles_Dungeon/Walls-51.png", true);
        setUpDungeon(71, "/Tiles_Dungeon/Walls-52.png", true);
        setUpDungeon(72, "/Tiles_Dungeon/Walls-53.png", true);
        setUpDungeon(73, "/Tiles_Dungeon/Walls-54.png", true);
        setUpDungeon(74, "/Tiles_Dungeon/Walls-55.png", true);
        setUpDungeon(75, "/Tiles_Dungeon/Walls-58.png", true);
        setUpDungeon(76, "/Tiles_Dungeon/Walls-59", true);
        setUpDungeon(77, "/Tiles_Dungeon/Walls-59.png", true);
        setUpDungeon(78, "/Tiles_Dungeon/Walls-6", true);
        setUpDungeon(79, "/Tiles_Dungeon/Walls-60", true);
        setUpDungeon(80, "/Tiles_Dungeon/Walls-60.png", true);
        setUpDungeon(81, "/Tiles_Dungeon/Walls-61", true);
        setUpDungeon(82, "/Tiles_Dungeon/Walls-61.png", true);
        setUpDungeon(83, "/Tiles_Dungeon/Walls-62", true);
        setUpDungeon(84, "/Tiles_Dungeon/Walls-62.png", true);
        setUpDungeon(85, "/Tiles_Dungeon/Walls-63.png", true);
        setUpDungeon(86, "/Tiles_Dungeon/Walls-64.png", true);
        setUpDungeon(87, "/Tiles_Dungeon/Walls-65.png", true);
        setUpDungeon(88, "/Tiles_Dungeon/Walls-66.png", true);
        setUpDungeon(89, "/Tiles_Dungeon/Walls-65.png", true);
        setUpDungeon(90, "/Tiles_Dungeon/Walls-66.png", true);
        setUpDungeon(91, "/Tiles_Dungeon/Walls-7", true);
        setUpDungeon(92, "/Tiles_Dungeon/Walls-8", true);
        setUpDungeon(93, "/Tiles_Dungeon/Walls-9", true);
    }

    public void getTilePathway() {
        setUpPathway(0, "/Tiles_Pathway/001", false);
        setUpPathway(1, "/Tiles_Pathway/002", false);
        setUpPathway(2, "/Tiles_Pathway/003", false);
        setUpPathway(3, "/Tiles_Pathway/004", false);
        setUpPathway(4, "/Tiles_Pathway/005", false);
        setUpPathway(5, "/Tiles_Pathway/006", false);
        setUpPathway(6, "/Tiles_Pathway/007", false);
        setUpPathway(7, "/Tiles_Pathway/008", false);
        setUpPathway(8, "/Tiles_Pathway/009", false);
        setUpPathway(9, "/Tiles_Pathway/010", false);
        setUpPathway(10, "/Tiles_Pathway/011", false);
        setUpPathway(11, "/Tiles_Pathway/012", false);
        setUpPathway(12, "/Tiles_Pathway/013", false);
        setUpPathway(13, "/Tiles_Pathway/014", false);
        setUpPathway(14, "/Tiles_Pathway/015", false);
        setUpPathway(15, "/Tiles_Pathway/018", false);
        setUpPathway(16, "/Tiles_Pathway/019", true);
        setUpPathway(17, "/Tiles_Pathway/02", false);
        setUpPathway(18, "/Tiles_Pathway/020", true);
        setUpPathway(19, "/Tiles_Pathway/021", true);
        setUpPathway(20, "/Tiles_Pathway/022", true);
        setUpPathway(21, "/Tiles_Pathway/023", true);
        setUpPathway(22, "/Tiles_Pathway/024", true);
        setUpPathway(23, "/Tiles_Pathway/025", true);
        setUpPathway(24, "/Tiles_Pathway/026", true);
        setUpPathway(25, "/Tiles_Pathway/027", true);
        setUpPathway(26, "/Tiles_Pathway/028", true);
        setUpPathway(27, "/Tiles_Pathway/029", true);
        setUpPathway(28, "/Tiles_Pathway/030", true);
        setUpPathway(29, "/Tiles_Pathway/031", false);
        setUpPathway(30, "/Tiles_Pathway/04", false);
        setUpPathway(31, "/Tiles_Pathway/05", false);
        setUpPathway(32, "/Tiles_Pathway/09", false);
        setUpPathway(33, "/Tiles_Pathway/1", false);
        setUpPathway(34, "/Tiles_Pathway/10", false);
        setUpPathway(35, "/Tiles_Pathway/14", false);
        setUpPathway(36, "/Tiles_Pathway/15", false);
        setUpPathway(37, "/Tiles_Pathway/16", false);
        setUpPathway(38, "/Tiles_Pathway/17", false);
        setUpPathway(39, "/Tiles_Pathway/18", false);
        setUpPathway(40, "/Tiles_Pathway/19", false);
        setUpPathway(41, "/Tiles_Pathway/20", false);
        setUpPathway(42, "/Tiles_Pathway/21", false);
        setUpPathway(43, "/Tiles_Pathway/22", false);
        setUpPathway(44, "/Tiles_Pathway/B", true);
        setUpPathway(45, "/Tiles_Pathway/B1", true);
        setUpPathway(46, "/Tiles_Pathway/B2", true);
        setUpPathway(47, "/Tiles_Pathway/B3", true);
        setUpPathway(48, "/Tiles_Pathway/Decor-1", true);
        setUpPathway(49, "/Tiles_Pathway/Decor-2", true);
        setUpPathway(50, "/Tiles_Pathway/Decor-3", true);
        setUpPathway(51, "/Tiles_Pathway/Decor-4", true);
        setUpPathway(52, "/Tiles_Pathway/Decor-5", true);
        setUpPathway(53, "/Tiles_Pathway/Decor-6", true);
        setUpPathway(54, "/Tiles_Pathway/Decor-7", true);
        setUpPathway(55, "/Tiles_Pathway/Floor-1", false);
        setUpPathway(56, "/Tiles_Pathway/Floor-2", false);
        setUpPathway(57, "/Tiles_Pathway/Floor-3", false);
        setUpPathway(58, "/Tiles_Pathway/Floor-4", false);
        setUpPathway(59, "/Tiles_Pathway/FS-1", false);
        setUpPathway(60, "/Tiles_Pathway/FS-2", false);
        setUpPathway(61, "/Tiles_Pathway/FS-3", false);
        setUpPathway(62, "/Tiles_Pathway/FS-4", false);
        setUpPathway(63, "/Tiles_Pathway/FS-5", false);
        setUpPathway(64, "/Tiles_Pathway/h1", true);
        setUpPathway(65, "/Tiles_Pathway/h10", true);
        setUpPathway(66, "/Tiles_Pathway/h11", true);
        setUpPathway(67, "/Tiles_Pathway/h12", true);
        setUpPathway(68, "/Tiles_Pathway/h13", true);
        setUpPathway(69, "/Tiles_Pathway/h14", true);
        setUpPathway(70, "/Tiles_Pathway/h15", true);
        setUpPathway(71, "/Tiles_Pathway/h16", true);
        setUpPathway(72, "/Tiles_Pathway/h17", true);
        setUpPathway(73, "/Tiles_Pathway/h18", true);
        setUpPathway(74, "/Tiles_Pathway/h19", true);
        setUpPathway(75, "/Tiles_Pathway/h2", true);
        setUpPathway(76, "/Tiles_Pathway/h20", true);
        setUpPathway(77, "/Tiles_Pathway/h21", true);
        setUpPathway(78, "/Tiles_Pathway/h22", true);
        setUpPathway(79, "/Tiles_Pathway/h23", true);
        setUpPathway(80, "/Tiles_Pathway/h24", true);
        setUpPathway(81, "/Tiles_Pathway/h25", true);
        setUpPathway(82, "/Tiles_Pathway/h26", true);
        setUpPathway(83, "/Tiles_Pathway/h27", true);
        setUpPathway(84, "/Tiles_Pathway/h28", true);
        setUpPathway(85, "/Tiles_Pathway/h29", true);
        setUpPathway(86, "/Tiles_Pathway/h3", true);
        setUpPathway(87, "/Tiles_Pathway/h30", true);
        setUpPathway(88, "/Tiles_Pathway/h4", true);
        setUpPathway(89, "/Tiles_Pathway/h5", true);
        setUpPathway(90, "/Tiles_Pathway/h6", true);
        setUpPathway(91, "/Tiles_Pathway/h7", true);
        setUpPathway(92, "/Tiles_Pathway/h8", true);
        setUpPathway(93, "/Tiles_Pathway/h9", true);
        setUpPathway(94, "/Tiles_Pathway/Rock", true);
        setUpPathway(95, "/Tiles_Pathway/Sign", true);
        setUpPathway(96, "/Tiles_Pathway/st1", false);
        setUpPathway(97, "/Tiles_Pathway/st2", true);
        setUpPathway(98, "/Tiles_Pathway/st3", true);
        setUpPathway(99, "/Tiles_Pathway/st4", true);
        setUpPathway(100, "/Tiles_Pathway/st5", true);
        setUpPathway(101, "/Tiles_Pathway/st6", true);
        setUpPathway(102, "/Tiles_Pathway/Stairs-1", false);
        setUpPathway(103, "/Tiles_Pathway/Stairs-2", false);
        setUpPathway(104, "/Tiles_Pathway/T", true);
        setUpPathway(105, "/Tiles_Pathway/T1", true);
        setUpPathway(106, "/Tiles_Pathway/t10", true);
        setUpPathway(107, "/Tiles_Pathway/T11", true);
        setUpPathway(108, "/Tiles_Pathway/t2", true);
        setUpPathway(109, "/Tiles_Pathway/T3", true);
        setUpPathway(110, "/Tiles_Pathway/T4", true);
        setUpPathway(111, "/Tiles_Pathway/T5", true);
        setUpPathway(112, "/Tiles_Pathway/T6", true);
        setUpPathway(113, "/Tiles_Pathway/T7", true);
        setUpPathway(114, "/Tiles_Pathway/T8", true);
        setUpPathway(115, "/Tiles_Pathway/T9", true);
        setUpPathway(116, "/Tiles_Pathway/tr-1", true);
        setUpPathway(117, "/Tiles_Pathway/tr1", true);
        setUpPathway(118, "/Tiles_Pathway/tr2", true);
        setUpPathway(119, "/Tiles_Pathway/tr3", true);
        setUpPathway(120, "/Tiles_Pathway/tr4", true);
        setUpPathway(121, "/Tiles_Pathway/Walls-1", true);
        setUpPathway(122, "/Tiles_Pathway/Walls-10", true);
        setUpPathway(123, "/Tiles_Pathway/Walls-11", true);
        setUpPathway(124, "/Tiles_Pathway/Walls-12", true);
        setUpPathway(125, "/Tiles_Pathway/Walls-12.png-1.png", true);
        setUpPathway(126, "/Tiles_Pathway/Walls-12.png-2.png", true);
        setUpPathway(127, "/Tiles_Pathway/Walls-12.png-3.png", true);
        setUpPathway(128, "/Tiles_Pathway/Walls-12.png-4.png", true);
        setUpPathway(129, "/Tiles_Pathway/Walls-12.png-5.png", true);
        setUpPathway(130, "/Tiles_Pathway/Walls-13", true);
        setUpPathway(131, "/Tiles_Pathway/Walls-14", true);
        setUpPathway(132, "/Tiles_Pathway/Walls-15", true);
        setUpPathway(133, "/Tiles_Pathway/Walls-16", true);
        setUpPathway(134, "/Tiles_Pathway/Walls-17", true);
        setUpPathway(135, "/Tiles_Pathway/Walls-18", true);
        setUpPathway(136, "/Tiles_Pathway/Walls-19", true);
        setUpPathway(137, "/Tiles_Pathway/Walls-6", true);
        setUpPathway(138, "/Tiles_Pathway/Walls-20", true);
        setUpPathway(139, "/Tiles_Pathway/Walls-21", true);
        setUpPathway(140, "/Tiles_Pathway/Walls-22", true);
        setUpPathway(141, "/Tiles_Pathway/Walls-23", true);
        setUpPathway(142, "/Tiles_Pathway/Walls-24", true);
        setUpPathway(143, "/Tiles_Pathway/Walls-25.png", true);
        setUpPathway(144, "/Tiles_Pathway/Walls-26.png", true);
        setUpPathway(145, "/Tiles_Pathway/Walls-27.png", true);
        setUpPathway(146, "/Tiles_Pathway/Walls-28.png", true);
        setUpPathway(147, "/Tiles_Pathway/Walls-29.png", true);
        setUpPathway(148, "/Tiles_Pathway/Walls-3", true);
        setUpPathway(149, "/Tiles_Pathway/Walls-30.png", true);
        setUpPathway(150, "/Tiles_Pathway/Walls-31.png", true);
        setUpPathway(151, "/Tiles_Pathway/Walls-32.png", true);
        setUpPathway(152, "/Tiles_Pathway/Walls-33.png", true);
        setUpPathway(153, "/Tiles_Pathway/Walls-34.png", true);
        setUpPathway(154, "/Tiles_Pathway/Walls-35.png", true);
        setUpPathway(155, "/Tiles_Pathway/Walls-36.png", true);
        setUpPathway(156, "/Tiles_Pathway/Walls-37.png", true);
        setUpPathway(157, "/Tiles_Pathway/Walls-38.png", true);
        setUpPathway(158, "/Tiles_Pathway/Walls-39.png", true);
        setUpPathway(159, "/Tiles_Pathway/Walls-2", true);
        setUpPathway(160, "/Tiles_Pathway/Walls-40.png", true);
        setUpPathway(161, "/Tiles_Pathway/Walls-41.png", true);
        setUpPathway(162, "/Tiles_Pathway/Walls-42.png", true);
        setUpPathway(163, "/Tiles_Pathway/Walls-43.png", true);
        setUpPathway(164, "/Tiles_Pathway/Walls-44.png", true);
        setUpPathway(165, "/Tiles_Pathway/Walls-45.png", true);
        setUpPathway(166, "/Tiles_Pathway/Walls-46.png", true);
        setUpPathway(167, "/Tiles_Pathway/Walls-47.png", true);
        setUpPathway(168, "/Tiles_Pathway/Walls-48.png", true);
        setUpPathway(169, "/Tiles_Pathway/Walls-49.png", true);
        setUpPathway(170, "/Tiles_Pathway/Walls-3", true);
        setUpPathway(171, "/Tiles_Pathway/Walls-50.png", true);
        setUpPathway(172, "/Tiles_Pathway/Walls-51.png", true);
        setUpPathway(173, "/Tiles_Pathway/Walls-52.png", true);
        setUpPathway(174, "/Tiles_Pathway/Walls-53.png", true);
        setUpPathway(175, "/Tiles_Pathway/Walls-54.png", true);
        setUpPathway(176, "/Tiles_Pathway/Walls-55.png", true);
        setUpPathway(177, "/Tiles_Pathway/Walls-58.png", true);
        setUpPathway(178, "/Tiles_Pathway/Walls-59", true);
        setUpPathway(179, "/Tiles_Pathway/Walls-59.png", true);
        setUpPathway(180, "/Tiles_Pathway/Walls-4", true);
        setUpPathway(181, "/Tiles_Pathway/Walls-60", true);
        setUpPathway(182, "/Tiles_Pathway/Walls-60.png", true);
        setUpPathway(183, "/Tiles_Pathway/Walls-61", true);
        setUpPathway(184, "/Tiles_Pathway/Walls-61.png", true);
        setUpPathway(185, "/Tiles_Pathway/Walls-62", true);
        setUpPathway(186, "/Tiles_Pathway/Walls-62.png", true);
        setUpPathway(187, "/Tiles_Pathway/Walls-63.png", true);
        setUpPathway(188, "/Tiles_Pathway/Walls-64.png", true);
        setUpPathway(189, "/Tiles_Pathway/Walls-65.png", true);
        setUpPathway(190, "/Tiles_Pathway/Walls-66.png", true);
        setUpPathway(191, "/Tiles_Pathway/Walls-67.png", true);
        setUpPathway(192, "/Tiles_Pathway/Walls-68.png", true);
        setUpPathway(193, "/Tiles_Pathway/Walls-7", true);
        setUpPathway(194, "/Tiles_Pathway/Walls-8", true);
        setUpPathway(195, "/Tiles_Pathway/Walls-9", true);


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
    public void setUpDungeon(int index, String imageName, boolean collision){

        UtilityTool uTool = new UtilityTool();
        try {
            tileDungeon[index] = new Tile();
            tileDungeon[index].image = ImageIO.read(getClass().getResourceAsStream(imageName + ".png"));
            tileDungeon[index].image = uTool.scaleImage(tileDungeon[index].image, gp.tileSize, gp.tileSize);
            tileDungeon[index].collision = collision;
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    public void setUpPathway(int index, String imageName, boolean collision){

        UtilityTool uTool = new UtilityTool();
        try {
            tilePathway[index] = new Tile();
            tilePathway[index].image = ImageIO.read(getClass().getResourceAsStream(imageName + ".png"));
            tilePathway[index].image = uTool.scaleImage(tilePathway[index].image, gp.tileSize, gp.tileSize);
            tilePathway[index].collision = collision;
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

                switch(gp.currentMap){

                    case 0 ,1: g2.drawImage(tile[tileNum].image, screenX, screenY, null); break;
                    case 2: g2.drawImage(tileSpawn[tileNum].image, screenX, screenY, null); break;
                    case 3: g2.drawImage(tileDungeon[tileNum].image, screenX, screenY, null);
                    case 4: g2.drawImage(tilePathway[tileNum].image, screenX, screenY, null); break;

                }
            }
            worldCol++;

            if (worldCol == gp.maxWorldCol) {
                worldCol = 0;
                worldRow++;
            }
        }
        if(drawPath){
            g2.setColor(new Color(255,0,0,70));
            for (int i = 0; i < gp.pFinder.pathList.size(); i++){
                int worldX = gp.pFinder.pathList.get(i).col * gp.tileSize;
                int worldY = gp.pFinder.pathList.get(i).row * gp.tileSize;
                int screenX = worldX - gp.player.worldX + gp.player.screenX;
                int screenY = worldY - gp.player.worldY + gp.player.screenY;
                g2.fillRect(screenX,screenY,gp.tileSize,gp.tileSize);
            }
        }
    }
}

