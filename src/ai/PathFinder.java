package ai;

import DannyGermanSimulator.GamePanel;
import Entity.Entity;

import java.util.ArrayList;

public class PathFinder {
    GamePanel gp;
    Node[][] node;
    ArrayList<Node> openList = new ArrayList<>();
    public ArrayList<Node> pathList = new ArrayList<>();
    Node startNode, goalNode, currentNode;
    boolean goalReached = false;
    int step = 0;

    public PathFinder(GamePanel gp){
        this.gp = gp;
        instantiateNode();
    }

    public void instantiateNode(){
        node = new Node[gp.maxWorldCol][gp.maxWorldRow];
        int col = 0;
        int row = 0;

        while(col < gp.maxWorldCol && row < gp.maxWorldRow){
            node[col][row] = new Node(col,row);
            col++;
            if(col == gp.maxWorldCol){
                col = 0;
                row++;
            }
        }
    }
    public void resetNode(){
        int col = 0;
        int row = 0;

        while(col < gp.maxWorldCol && row < gp.maxWorldRow){
            //Reset node data
            node[col][row].open = false;
            node[col][row].checked = false;
            node[col][row].solid = false;
            col++;
            if(col == gp.maxWorldCol){
                col = 0;
                row++;
            }
        }
        //reset other settings
        openList.clear();
        pathList.clear();
        goalReached = false;
        step = 0;
    }
    public void setNodes(int startCol, int startRow, int goalCol, int goalRow, Entity entity){
        resetNode();
        startNode = node[startCol][startRow];
        currentNode = startNode;
        goalNode = node[goalCol][goalRow];
        openList.add(currentNode);

        int col = 0;
        int row = 0;

        while(col < gp.maxWorldCol && row < gp.maxWorldRow){
            //set solid node
            //check tiles
            int tileNum = gp.tileM.mapTileNum[gp.currentMap][col][row];

            switch(gp.currentMap){
                case 0 ,1:
                    if(gp.tileM.tile[tileNum].collision){
                        node[col][row].solid = true;
                    }
                    break;
                case 2:
                    if(gp.tileM.tileSpawn[tileNum].collision){
                        node[col][row].solid = true;
                    }
                    break;
                case 3:
                    if(gp.tileM.tileDungeon[tileNum].collision){
                        node[col][row].solid = true;
                    }
                    break;
            }
            //check Interactive tile
            for(int i = 0; i < gp.iTile[1].length; i++){
                if(gp.iTile[gp.currentMap][i] != null && gp.iTile[gp.currentMap][i].destructible){
                    int itCol = gp.iTile[gp.currentMap][i].worldX/gp.tileSize;
                    int itRow = gp.iTile[gp.currentMap][i].worldY/gp.tileSize;
                    node[itCol][itRow].solid = true;
                }
            }
            //SET COST
            getCost(node[col][row]);

            col++;
            if(col == gp.maxWorldCol){
                col = 0;
                row++;
            }
        }
    }
    public void getCost(Node node){
        //G cost
        int xDistance = Math.abs(node.col - startNode.col);
        int yDistance = Math.abs(node.row - startNode.row);
        node.gCost = xDistance + yDistance;
        //H cost
        xDistance = Math.abs(node.col - goalNode.col);
        yDistance = Math.abs(node.row - goalNode.row);
        node.hCost = xDistance + yDistance;
        //F cost
        node.fCost = node.gCost + node.hCost;
    }
    public boolean search(){
        while(!goalReached && step < 700){
            int col = currentNode.col;
            int row = currentNode.row;

            //check the current node
            currentNode.checked = true;
            openList.remove(currentNode);

            //open the up node
            if(row - 1 >= 0){
                openNode(node[col][row-1]);
            }
            //open the left node
            if(col - 1 >= 0){
                openNode(node[col-1][row]);
            }
            //open the down node
            if(row + 1 < gp.maxWorldRow){
                openNode(node[col][row+1]);
            }
            //open the right node
            if(col + 1 < gp.maxWorldCol){
                openNode(node[col+1][row]);
            }

            //find the best node
            int bestNodeIndex = 0;
            int bestNodefCost = 999;

            for(int i = 0; i < openList.size(); i++){
                //check if this node's f cost is better
                if(openList.get(i).fCost < bestNodefCost){
                    bestNodeIndex = i;
                    bestNodefCost = openList.get(i).fCost;
                }
                //if f cost is equal check g cost
                else if(openList.get(i).fCost == bestNodefCost){
                    if(openList.get(i).gCost < openList.get(bestNodeIndex).gCost){
                        bestNodeIndex = i;
                    }
                }
            }
            //if no node in open list
            if(openList.size()==0){
                break;
            }
            //after loop
            currentNode = openList.get(bestNodeIndex);
            if(currentNode == goalNode){
                goalReached = true;
                trackThePath();
            }
            step++;
        }
        return goalReached;
    }
    public void openNode(Node node){
        if(!node.open && !node.checked && !node.solid){
            node.open = true;
            node.Parent = currentNode;
            openList.add(node);
        }
    }
    public void trackThePath(){
        Node current = goalNode;
        while(current != startNode){
            pathList.add(0,current);
            current = current.Parent;

        }
    }
}
