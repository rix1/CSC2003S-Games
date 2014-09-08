package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.MyGdxGame.Direction;

public class Entity {
	public MyGdxGame game;
	public float x;
	public float y;
	public float dx;
	public float dy;
	public int width;
	public int height;
	public float speed;
	public Texture texture;
    public String[] bitMask;
    public int sampleRate = 3;
    public String name;
    public Integer tempRow;

	public Entity(MyGdxGame game, float x, float y, int width, int height, float speed, Texture texture, String name) {
		this.game = game;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.speed = speed;
		this.texture = texture;
        this.name = name;
        createBitMask();
        printBitMask();
	}

    public void createBitMask(){

        if(sampleRate > 1){
        bitMask = new String[(height/sampleRate) + 1];
        }else bitMask = new String[height];

        Pixmap pixmap = new Pixmap(Gdx.files.internal(getTexureName()));

        int colorValue;
        int alphaValue;
        int counter = 0;

        for (int y = 0; y < height; y+= sampleRate) {
            for (int x = 0; x < width; x+= sampleRate) {
                colorValue = pixmap.getPixel(x,y);
                alphaValue = (colorValue & 0x000000ff);

                if(x == 0) {
                    bitMask[counter] = "";
                    bitMask[counter] += (alphaValue == 255) ? "1" : "0";
                }

                if(alphaValue == 255)
                    bitMask[counter] += 1;
                else bitMask[counter] += 0;
            }
            counter++;
        }
    }
    public void printBitMask(){
        for (int y = 0; y < bitMask.length; y++) {
            System.out.println(bitMask[y]);
        }
        System.out.println();
    }

    public String getTexureName(){
        return "enemy.png";
    }

	public void update(float delta) {

	}
	
	public void move(float newX, float newY) {
		x = newX;
		y = newY;		
	}
	
	public void render() {
		
	}

	public void tileCollision(int tile, int tileX, int tileY, float newX, float newY, Direction direction) {
		System.out.println("tile collision at: " + tileX + " " + tileY);
		
		if(direction == Direction.U) {
			y = tileY * game.tileSize + game.tileSize;
		}
		else if(direction == Direction.D) {
			y = tileY * game.tileSize - height;
		}
		else if(direction == Direction.L) {
			x = tileX * game.tileSize + game.tileSize;
		}
		else if(direction == Direction.D) {
			x = tileX * game.tileSize - width;
		}
	}

	public boolean entityCollision(Entity e2, float newX, float newY) {
        return bitCheck(newX, newY, e2);
	}

    public boolean bitCheck(float newX, float newY, Entity e2){
        Entity left, right, top, bottom;
        int shift;

        if(Math.max(newX, e2.x) == newX){ // NewX is on the right
            shift = (int)(newX-e2.x)/sampleRate;
            right = this;
            left = e2;
        }else{
            shift = (int)(e2.x-newX)/sampleRate;
            right = e2;
            left = this;
        }

        if(Math.max(newY, e2.y) == newY){ // NewY is bottom
            top = this;
            bottom = e2;
        }else {
            top = e2;
            bottom = this;
        }

        int startPos = (int)(Math.abs((newY-e2.y)))/sampleRate;
        int length = (int) (((top.height/sampleRate)+1) - (startPos));

         int currentPos = startPos;

//        reportLog(left, right, top, bottom, newX, newY, startPos, length, shift);

        for (int y = 0; y < length; y++) {

            if(left.equals(top)){
                left.tempRow = Integer.parseInt(left.bitMask[currentPos], 2);
                right.tempRow = Integer.parseInt(right.bitMask[y], 2);
            }else {
                left.tempRow = Integer.parseInt(left.bitMask[y], 2);
                right.tempRow = Integer.parseInt(right.bitMask[currentPos], 2);
            }

//            System.out.println("Before shift");
//            System.out.println("Left " + Integer.toBinaryString(left.tempRow));
//            System.out.println("Right " + Integer.toBinaryString(right.tempRow));

            left.tempRow <<= shift;
//            System.out.println("------\nAfter shift");
//            System.out.println("Left " + Integer.toBinaryString(left.tempRow));
//            System.out.println("Right " + Integer.toBinaryString(right.tempRow));

            String res = Integer.toBinaryString((left.tempRow & right.tempRow));

//            System.out.println("AND: " + res);

            if(res.contains("1")){
                currentPos++;
                return true;
            }
            currentPos++;
        }
        return false;
    }

    public void reportLog(Entity left, Entity right, Entity up, Entity down, float newX, float newY, int startPos, int length, int shift){
        System.out.println("\n---- Report Start----");

        System.out.println("Entities:\nLeft:\t" + left.name + " - X: " + left.x + " Y: " + left.y + ", bitmask: height " + bitMask.length + ", length: " + bitMask[0].length());
        System.out.println("Right:\t" + right.name + " - X: " + right.x + " Y: " + right.y + ", bitmask: height " + bitMask.length + ", length: " + bitMask[0].length());
        System.out.println("Up:\t\t" + up.name + " - X: " + up.x + " Y: " + up.y + ", bitmask: height " + bitMask.length + ", length: " + bitMask[0].length());
        System.out.println("Below:\t" + down.name + " - X: " + down.x + " Y: " + down.y + ", bitmask: height " + bitMask.length + ", length: " + bitMask[0].length());
        System.out.println();
        System.out.println("Properties of for-loop:");
        System.out.println("Starting at row " + startPos + " in " + up.name + ", ending after " + length + " iterations");
        System.out.println("Shifting bits in " + left.name + " " + shift + " to the left");


        System.out.println("---- Report End----\n");
    }
}