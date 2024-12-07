package Entity;

import DannyGermanSimulator.GamePanel;
import DannyGermanSimulator.KeyHandler;
import object.*;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Player extends Entity {
    KeyHandler keyH;
    public final int screenX;
    public final int screenY;
    int tempSpeed = 6;
    int tempSpriteSpeedMultiplier = spriteSpeedMultiplier;
    public boolean attackCanceled = false;
    public boolean lightUpdated = false;

    public Player(GamePanel gp, KeyHandler keyH) {
        super(gp);
        this.keyH = keyH;
        this.screenX = gp.screenWidth/2 - (gp.tileSize/2);
        this.screenY = gp.screenHeight/2 - (gp.tileSize/2);
        solidArea = new Rectangle();

        //TO FIX Optimize
        solidArea.x=34;
        solidArea.y=48;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width=48;
        solidArea.height=48;

        setDefaultValues();
    }
    public void setDefaultValues() {
        //TEST MAP
//        worldX = gp.tileSize * 23;
//        worldY = gp.tileSize * 21;

        //INTERIOR
//        worldX = gp.tileSize * 12;
//        worldY = gp.tileSize * 12;
        defaultSpeed = 6;
        speed = defaultSpeed; //also change tempSpeed
        direction = "down";

        //PLAYER STATUS
        level = 1;
        maxLife = 6;
        life = maxLife;
        maxMana = 8;
        mana = maxMana;
        //more damage
        strength = 1;
        //more defence
        dexterity = 1;
        exp = 0;
        nextLevelExp = 5;
        coins = 500;
        gems = 0;
        currentWeapon = new OBJ_Sword_Normal(gp);
        currentShield = new OBJ_Shield_Wood(gp);
        currentLight = null;
        projectile = new OBJ_Fireball(gp);
        attack = getAttack();
        defence = getDefence();

        setDefaultPosition();
        getImage();
        getAttackImage();
        getGuardImage();
        setItems();
    }
    public void setDefaultPosition(){
        switch(gp.currentMap){
            case 0,1:
                break;
            case 2://SPAWN
                worldX = gp.tileSize * 27;
                worldY = gp.tileSize * 123;
                break;
            case 3:
                //DUNGEON
                worldX = gp.tileSize * 22;
                worldY = gp.tileSize * 230;
//                worldX = gp.tileSize * 39;
//                worldY = gp.tileSize * 165;
                break;
            case 4:
                break;
        }
        direction = "down";
    }
    public void restoreStatus(){
        life = maxLife;
        mana = maxMana;
        speed = defaultSpeed;
        invincible = false;
        transparent = false;
        attacking = false;
        guarding = false;
        knockBack = false;
        lightUpdated = true;
    }
    public void setItems(){

        inventory.clear();
        inventory.add(currentWeapon);
        inventory.add(currentShield);
        inventory.add(new OBJ_Key(gp));
        inventory.add(new OBJ_Tent(gp));
    }
    public int getAttack(){
        attackArea =currentWeapon.attackArea;
        motion1_duration = currentWeapon.motion1_duration;
        motion2_duration = currentWeapon.motion2_duration;
        return attack = strength * currentWeapon.attackValue;
    }
    public int getDefence(){
        return defence = dexterity * currentShield.defenseValue;
    }
    public int getCurrentWeaponSlot(){
        int currentWeaponSlot = 0;
        for(int i = 0; i < inventory.size(); i++){
            if(inventory.get(i) == currentWeapon){
                currentWeaponSlot = i;
            }
        }
        return currentWeaponSlot;
    }
    public int getCurrentShieldSlot(){
        int currentSheildSlot = 0;
        for(int i = 0; i < inventory.size(); i++){
            if(inventory.get(i) == currentShield){
                currentSheildSlot = i;
            }
        }
        return currentSheildSlot;
    }
    public void getImage() {
        //Diri ibutang nato atoang mga pictures for characters
        idle = setUp("/player/heil",gp.tileSize,gp.tileSize);
        idleleft = setUp("/maxLevelArmor/Left1",gp.tileSize,gp.tileSize);
        idleright = setUp("/maxLevelArmor/Right1",gp.tileSize,gp.tileSize);
        idledown = setUp("/maxLevelArmor/Back1",gp.tileSize,gp.tileSize);
        idleup = setUp("/maxLevelArmor/Front1",gp.tileSize,gp.tileSize);
        up1 = setUp("/maxLevelArmor/Front2",gp.tileSize,gp.tileSize);
        up2 = setUp("/maxLevelArmor/Front3",gp.tileSize,gp.tileSize);
        up3 = setUp("/maxLevelArmor/Front2",gp.tileSize,gp.tileSize);
        up4 = setUp("/maxLevelArmor/Front4",gp.tileSize,gp.tileSize);
        down1 = setUp("/maxLevelArmor/Back1",gp.tileSize,gp.tileSize);
        down2 = setUp("/maxLevelArmor/Back2",gp.tileSize,gp.tileSize);
        down3 = setUp("/maxLevelArmor/Back3",gp.tileSize,gp.tileSize);
        down4 = setUp("/maxLevelArmor/Back4",gp.tileSize,gp.tileSize);
        left1 = setUp("/maxLevelArmor/Left1",gp.tileSize,gp.tileSize);
        left2 = setUp("/maxLevelArmor/Left2",gp.tileSize,gp.tileSize);
        left3 = setUp("/maxLevelArmor/Left3",gp.tileSize,gp.tileSize);
        left4 = setUp("/maxLevelArmor/Left4",gp.tileSize,gp.tileSize);
        right1 = setUp("/maxLevelArmor/Right1",gp.tileSize,gp.tileSize);
        right2 = setUp("/maxLevelArmor/Right2",gp.tileSize,gp.tileSize);
        right3 = setUp("/maxLevelArmor/Right3",gp.tileSize,gp.tileSize);
        right4 = setUp("/maxLevelArmor/Right4",gp.tileSize,gp.tileSize);
    }
    public void getSleepingImage(BufferedImage image){
        idle = image;
        idleleft = image;
        idleright = image;
        idledown = image;
        idleup = image;
        up1 = image;
        up2 = image;
        up3 = image;
        up4 = image;
        down1 = image;
        down2 = image;
        down3 = image;
        down4 = image;
        left1 = image;
        left2 = image;
        left3 = image;
        left4 = image;
        right1 = image;
        right2 = image;
        right3 = image;
        right4 = image;
    }
    public void getAttackImage(){
        if(currentWeapon.type == type_sword){
            attackUp1 = setUp("/player/atkb1",gp.tileSize*2,gp.tileSize*2);
            attackUp2 = setUp("/player/atkb2",gp.tileSize*2,gp.tileSize*2);
            attackDown1 = setUp("/player/atk1",gp.tileSize*2,gp.tileSize*2);
            attackDown2 = setUp("/player/atk2",gp.tileSize*2,gp.tileSize*2);
            attackLeft1 = setUp("/player/atkl1",gp.tileSize*2,gp.tileSize*2);
            attackLeft2 = setUp("/player/atkl2",gp.tileSize*2,gp.tileSize*2);
            attackRight1 = setUp("/player/atkr1",gp.tileSize*2,gp.tileSize*2);
            attackRight2 = setUp("/player/atkr2",gp.tileSize*2,gp.tileSize*2);
        }
        if(currentWeapon.type == type_axe){
            attackUp1 = setUp("/player/AXE/AatkB",gp.tileSize*2,gp.tileSize*2);
            attackUp2 = setUp("/player/AXE/AatkB",gp.tileSize*2,gp.tileSize*2);
            attackDown1 = setUp("/player/AXE/AatkF",gp.tileSize*2,gp.tileSize*2);
            attackDown2 = setUp("/player/AXE/AatkF",gp.tileSize*2,gp.tileSize*2);
            attackLeft1 = setUp("/player/AXE/AatkL",gp.tileSize*2,gp.tileSize*2);
            attackLeft2 = setUp("/player/AXE/AatkL",gp.tileSize*2,gp.tileSize*2);
            attackRight1 = setUp("/player/AXE/AatkR",gp.tileSize*2,gp.tileSize*2);
            attackRight2 = setUp("/player/AXE/AatkR",gp.tileSize*2,gp.tileSize*2);
        }

    }
    public void getGuardImage(){
        guardUp = setUp("/NPC/NOOB MASTER-1.png",gp.tileSize,gp.tileSize);
        guardDown = setUp("/NPC/NOOB MASTER-1.png",gp.tileSize,gp.tileSize);
        guardLeft= setUp("/NPC/NOOB MASTER-1.png",gp.tileSize,gp.tileSize);
        guardRight = setUp("/NPC/NOOB MASTER-1.png",gp.tileSize,gp.tileSize);
    }
    public void update() {

        if(knockBack){
            //check tile collision
            collisionOn = false;
            gp.colCheck.checkTile(this);
            gp.colCheck.checkObject(this, true);
            gp.colCheck.checkEntity(this, gp.npc);
            gp.colCheck.checkEntity(this, gp.monster);
            gp.colCheck.checkEntity(this, gp.iTile);

            if(collisionOn){
                knockBackCounter = 0;
                knockBack = false;
                speed = defaultSpeed;
            }
            else if(!collisionOn){
                switch (knockBackDirection){
                    case "up":
                        worldY -= speed;
                        break;
                    case "down":
                        worldY += speed;
                        break;
                    case "left":
                        worldX -= speed;
                        break;
                    case "right":
                        worldX += speed;
                        break;
                }
            }
            knockBackCounter++;
            if(knockBackCounter == 10){
                knockBackCounter = 0;
                knockBack = false;
                speed = defaultSpeed;
            }
        }
        else if(attacking){
            attacking();
        } else if(keyH.spacePressed){
            guarding = true;
            guardCounter++;
        }
        else if(keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed || keyH.enterPressed) {
            if (keyH.upPressed) {
                direction = "up";
            }
            if (keyH.downPressed) {
                direction = "down";
            }
            if (keyH.leftPressed) {
                direction = "left";
            }
            if (keyH.rightPressed) {
                direction = "right";
            }

            //check tile collision
            collisionOn = false;
            gp.colCheck.checkTile(this);

            //check object collision
            int objIndex = gp.colCheck.checkObject(this, true);
            pickUpObject(objIndex);

            //Check NPC collision
            int npcIndex = gp.colCheck.checkEntity(this, gp.npc);
            interactNPC(npcIndex);

            //Check Monster Collision
            int monsterIndex = gp.colCheck.checkEntity(this, gp.monster);
            contactMonster(monsterIndex);

            //Check Interactive Tile Collision
            int iTileIndex = gp.colCheck.checkEntity(this,gp.iTile);

            //Check Event
            gp.eHandler.checkEvent();

            //if collision is false, player can move
            if(!collisionOn && !keyH.enterPressed){
                if(keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed) {
                    if (keyH.upPressed) {
                        worldY -= speed;
                    }
                    if (keyH.downPressed) {
                        worldY += speed;
                    }
                    if (keyH.leftPressed) {
                        worldX -= speed;
                    }
                    if (keyH.rightPressed) {
                        worldX += speed;
                    }
                }
            }

            if(keyH.enterPressed &&  !attackCanceled){
                gp.playSE(7);
                attacking = true;
                spriteCounter = 0;
            }
            attackCanceled = false;
            gp.keyH.enterPressed = false;
            guarding = false;
            guardCounter = 0;

            spriteCounter++;
            if(spriteCounter > spriteSpeedMultiplier) {
                switch (spriteNum){
                    case 1:
                        spriteNum = 2;
                        break;
                    case 2:
                        spriteNum = 3;
                        break;
                    case 3:
                        spriteNum = 4;
                        break;
                    case 4:
                        spriteNum = 1;
                        break;
                }
                guardCounter = 0;
                spriteCounter = 0;
            }
        }

        //projectile
        if(gp.keyH.shotKeyPressed && !projectile.alive && shotAvailableCounter == 30 && projectile.haveResource(this)){
            //SET DEFAULT COORDINATES, DIRECTION
            projectile.set(worldX,worldY,direction,true,this);

            //SUBTRACT MANA COST
            projectile.subtractResource(this);

            //ADD TO THE LIST
            //check vacancy
            for(int i = 0; i < gp.projectile[1].length; i++){
                if(gp.projectile[gp.currentMap][i] == null){
                    gp.projectile[gp.currentMap][i] = projectile;
                    break;
                }
            }
            shotAvailableCounter = 0;
            gp.playSE(2);
        }
        if(keyH.mountPressed){
            speed = 15;
            spriteSpeedMultiplier = 4;
        } else{
            speed = tempSpeed;
            spriteSpeedMultiplier = tempSpriteSpeedMultiplier;
        }

        if(invincible){
            invincibleCounter++;
            if(invincibleCounter > 60){
                invincible = false;
                transparent = false ;
                invincibleCounter = 0;
            }
        }

        if(shotAvailableCounter < 30){
            shotAvailableCounter++;
        }
        if(life > maxLife){
            life = maxLife;
        }
        if(mana > maxMana){
            mana = maxMana;
        }
        if(life <= 0){
            gp.gameState = gp.gameOverState;
            gp.ui.commandNum = -1;
            gp.stopMusic();
            gp.playSE(9);
        }
    }
    public void pickUpObject(int index){
        if (index != 999){
            //PICKUP ONLY ITEMS
            if(gp.obj[gp.currentMap][index].type == type_pickupOnly){
                gp.obj[gp.currentMap][index].use(this);
                gp.obj[gp.currentMap][index] = null;
            }
            //OBSTACLE
            else if(gp.obj[gp.currentMap][index].type == type_obstacle){
                if(keyH.enterPressed){
                    attackCanceled = true;
                    gp.obj[gp.currentMap][index].interact();
                }
            }
            //INVENTORY ITEMS
            else {
                String text;
                if(canObtainItem(gp.obj[gp.currentMap][index])){
                    gp.playSE(0);
                    text = "Got a " + gp.obj[gp.currentMap][index].name + "!";
                } else {
                    text = "Inventory Full";
                }
                gp.ui.addMessage(text);
                gp.obj[gp.currentMap][index] = null;
            }
        }
    }
    public void interactNPC(int index){

        if (gp.keyH.enterPressed) {
            if(index!=999){
                attackCanceled = true;
                gp.gameState = gp.dialogueState;
                gp.npc[gp.currentMap][index].speak();
            }
        }
    }
    public void contactMonster(int index){
        if (index != 999){
            if(!invincible && !gp.monster[gp.currentMap][index].dying){
                gp.playSE(6);
                int damage = gp.monster[gp.currentMap][index].attack - defence;
                if(damage < 1){
                    damage = 1;
                }
                life-=damage;
                invincible = true;
                transparent = true;
            }
        }
    }
    public void damageMonster(int index, Entity attacker, int attack, int knockBackPower){
        if(index != 999) {
            if(!gp.monster[gp.currentMap][index].invincible){
                gp.playSE(5);
                if(knockBackPower > 0){
                    setKnockBack(gp.monster[gp.currentMap][index], attacker, knockBackPower);
                }
                //critical hit
                if(gp.monster[gp.currentMap][index].offBalance){
                    attack *= 5;
                }
                int damage = attack - gp.monster[gp.currentMap][index].defence;
                if(damage < 0){
                    damage = 0;
                }
                gp.monster[gp.currentMap][index].life -= damage;
                gp.ui.addMessage(damage + " Damage!");

                gp.monster[gp.currentMap][index].invincible = true;
                gp.monster[gp.currentMap][index].damageReaction();
                if(gp.monster[gp.currentMap][index].life <= 0){
                    gp.monster[gp.currentMap][index].dying = true;
                    gp.ui.addMessage("Killed an " + gp.monster[gp.currentMap][index].name + "!");
                    gp.ui.addMessage("Gained +" + gp.monster[gp.currentMap][index].exp + " exp");
                    exp += gp.monster[gp.currentMap][index].exp;
                    checkLevelUp();
                }
            }
        }
    }
    public void damageInteractiveTile(int i){

        if(i != 999 && gp.iTile[gp.currentMap][i].destructible && gp.iTile[gp.currentMap][i].isCorrectItem(this) && !gp.iTile[gp.currentMap][i].invincible){

            gp.iTile[gp.currentMap][i].playSE();
            gp.iTile[gp.currentMap][i].life--;
            gp.iTile[gp.currentMap][i].invincible = true;

            generateParticle(gp.iTile[gp.currentMap][i],gp.iTile[gp.currentMap][i]);

            if(gp.iTile[gp.currentMap][i].life-- == 0){
                gp.iTile[gp.currentMap][i] = gp.iTile[gp.currentMap][i].getDestroyForm();
            }
        }
    }
    public void damageProjectile(int i){
        if(i != 999){
            Entity projectile = gp.projectile[gp.currentMap][i];
            projectile.alive = false;
            generateParticle(projectile, projectile);
        }
    }
    public void checkLevelUp(){
        if(exp >= nextLevelExp) {
            level++;
            nextLevelExp = (int) (nextLevelExp * 1.5);
            maxLife += 2;
            strength++;
            dexterity++;
            attack = getAttack();
            defence = getDefence();
            gp.playSE(4);
            gp.gameState = gp.dialogueState;
            if(level%2 != 0){
                projectile.attack++;
            }
            gp.ui.currentDialogue = "You are now level " + level + " now!\n";
        }
    }
    public void selectItem(){

        int itemIndex = gp.ui.getItemIndexOnSlot(gp.ui.playerSlotCol, gp.ui.playerSlotRow);
        if(itemIndex < inventory.size()){
            Entity selectedItem = inventory.get(itemIndex);
            if(selectedItem.type == type_sword || selectedItem.type == type_axe){
                currentWeapon = selectedItem;
                attack = getAttack();
                getAttackImage();
            }
            if(selectedItem.type == type_shield){
                currentShield = selectedItem;
                defence = getDefence();
            }
            if(selectedItem.type == type_light){
                if(currentLight == selectedItem){
                    currentLight = null;
                }
                else {
                    currentLight = selectedItem;
                }
                lightUpdated = true;
            }
            if(selectedItem.type == type_consumable){
                if(selectedItem.use(this)){
                    if(selectedItem.amount > 1){
                        selectedItem.amount--;
                    }
                    else {
                        inventory.remove(itemIndex);
                    }
                }
            }
        }
    }
    public int searchItemInInventory(String itemName){

        int itemIndex = 999;
        for(int i = 0; i < inventory.size(); i++){
            if(inventory.get(i).name == itemName){
                itemIndex = i;
                break;
            }
        }
        return itemIndex;
    }
    public boolean canObtainItem(Entity item){
        //to check if item obtained is stackable
        boolean canObtain = false;
        //check if item is stackable
        if(item.stackable){
            int index = searchItemInInventory(item.name);
            if(index != 999){
                inventory.get(index).amount++;
                canObtain = true;
            }
            else { //new item to check vacancy of inventory
                if(inventory.size() != maxInventorySize){
                    inventory.add(item);
                    canObtain = true;
                }
            }
        }
        else {
            //NOT STACKABLE
            if(inventory.size() != maxInventorySize){
                inventory.add(item);
                canObtain = true;
            }
        }
        return canObtain;
    }
    public void draw(Graphics2D g2) {
        BufferedImage image = null;

        int tempScreenX = screenX;
        int tempScreenY = screenY;

        // Array Sprites
        BufferedImage[] upSprites = {up1, up2, up3, up4};
        BufferedImage[] downSprites = {down1, down2, down3, down4};
        BufferedImage[] leftSprites = {left1, left2, left3, left4};
        BufferedImage[] rightSprites = {right1, right2, right3, right4};

        switch (direction) {
            case "up":
                if(!attacking){
                    image = upSprites[spriteNum - 1]; // Cycle through the up sprites
                    if (!keyH.upPressed) {
                        image = idleup;
                        spriteCounter = 8;
                    }
                }
                if(attacking){
                    tempScreenX = screenX - 64;
                    tempScreenY = screenY - 64;
                    if(spriteNum==1) image = attackUp1;
                    if(spriteNum==2) image = attackUp2;
                }
                if(guarding){
                    image = guardUp;
                }
                break;
            case "down":
                if(!attacking){
                    image = downSprites[spriteNum - 1]; // Cycle through the down sprites
                    if (!keyH.downPressed) {
                        image = idledown;
                        spriteCounter = 8;
                    }
                }
                if(attacking){
                    tempScreenX = screenX - 64;
                    tempScreenY = screenY - 64;
                    if(spriteNum==1) image = attackDown1;
                    if(spriteNum==2) image = attackDown2;
                }
                if(guarding){
                    image = guardDown;
                }
                break;

            case "left":
                if(!attacking){
                    image = leftSprites[spriteNum - 1]; // Cycle through the left sprites
                    if (!keyH.leftPressed) {
                        image = idleleft;
                        spriteCounter = 8;
                    }
                }
                if(attacking){
                    tempScreenX = screenX - 64;
                    tempScreenY = screenY - 64;
                    if(spriteNum==1) image = attackLeft1;
                    if(spriteNum==2) image = attackLeft2;
                }
                if(guarding){
                    image = guardLeft;
                }
                break;

            case "right":
                if(!attacking){
                    image = rightSprites[spriteNum - 1]; // Cycle through the right sprites
                    if (!keyH.rightPressed) {
                        image = idleright;
                        spriteCounter = 8;
                    }
                }
                if(attacking){
                    tempScreenX = screenX - 64;
                    tempScreenY = screenY - 64;
                    if(spriteNum==1) image = attackRight1;
                    if(spriteNum==2) image = attackRight2;
                }
                if(guarding){
                    image = guardRight;
                }
                break;
        }

        //for immunity effect
        if(transparent){
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3F));
        }
        g2.drawImage(image, tempScreenX, tempScreenY,null);
        //reset
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1F));

        //to see collision box or hit box
        g2.setColor(Color.red);
        g2.drawRect(screenX + solidArea.x, screenY + solidArea.y, solidArea.width, solidArea.height);

        //debug
//        g2.setFont(new Font("Arial", Font.PLAIN, 26));
//        g2.setColor(Color.white);
//        g2.drawString("Invincible:"+invincibleCounter,10, 400);

        // DEBUG FOR ATTACK
        // AttackArea
        tempScreenX = screenX + solidArea.x;
        tempScreenY = screenY + solidArea.y;
        switch(direction) {
            case "up": tempScreenY = screenY - attackArea.height; break;
            case "down": tempScreenY = screenY + gp.tileSize; break;
            case "left": tempScreenX = screenX - attackArea.width; break;
            case "right": tempScreenX = screenX + gp.tileSize; break;
        }
        g2.setColor(Color.blue);
        g2.setStroke(new BasicStroke(1));
        g2.drawRect(tempScreenX, tempScreenY, attackArea.width, attackArea.height);
    }
}