package com.mygdx.game;

import com.badlogic.gdx.math.Rectangle;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rikard Eide on 08/09/14.
 * Description:
 */
public class Quadtree {

    private int MAX_OBJECTS = 10;
    private int MAX_DEPTH = 5;

    private int depth;
    private ArrayList<Entity> entities;
    private Rectangle bounds;
    private Quadtree[] children;

    public Quadtree(int level, Rectangle bounds) {
        this.depth = level;
        entities = new ArrayList<Entity>();
        this.bounds = bounds;
        children = new Quadtree[4];
    }

    public void clear(){
        entities.clear();

        for (int i = 0; i < children.length; i++) {
            if(children[i] != null){
                children[i].clear();
                children[i] = null;
            }
        }
    }

    public void split(){
        int subWidth =  (int)(bounds.getWidth()/2);
        int subHeight = (int)(bounds.getHeight()/2);

        int x = (int)bounds.getX();
        int y = (int)bounds.getY();

/*
        Overview of children:
        2 3
        0 1
*/
        children[0] = new Quadtree(depth + 1, new Rectangle(x, y, subWidth,subHeight)); // First Quadrant
        children[1] = new Quadtree(depth + 1, new Rectangle(x+subWidth, y, subWidth,subHeight)); // Second
        children[2] = new Quadtree(depth + 1, new Rectangle(x, y+subHeight, subWidth,subHeight)); // Third
        children[3] = new Quadtree(depth + 1, new Rectangle(x+subWidth, y+subHeight, subWidth,subHeight)); // Forth
    }


    /**
     * Returns correct quadrant or -1 if it wont fit in any quadrants.
     */
    public int getIndex(Entity e){
        int index = -1;
        float verticalMidpoint = bounds.getX() + (bounds.getWidth()/2);
        float horizontalMidpoint = bounds.getY() + (bounds.getHeight()/2);

        boolean isInBottomTwoQuadrants = (e.y < horizontalMidpoint && (e.y + e.height) < horizontalMidpoint);
        boolean isInTopTwoQuadrants = (e.y > horizontalMidpoint);

        if(e.x < verticalMidpoint && (e.x + e.width) < verticalMidpoint) {
            if (isInBottomTwoQuadrants)
                index = 0;
            else if (isInTopTwoQuadrants)
                index = 2;
        }
        else if(e.x > verticalMidpoint){
            if(isInBottomTwoQuadrants)
                index = 1;
            else if(isInTopTwoQuadrants)
                index = 3;
        }
        return index;
    }

    public void insert(Entity entity){
        if(children[0] != null){
            int index = getIndex(entity);

            if(index != -1){
                children[index].insert(entity);
                return;
            }
        }

        entities.add(entity);

        if(entities.size() > MAX_OBJECTS && depth < MAX_DEPTH);{
            if(children[0] == null){
                split();
            }

            int i = 0;
            while(i < entities.size()){
                int index = getIndex(entities.get(i));
                if(index != -1){
                    children[index].insert(entities.remove(i));
                }
                else {
                    i++;
                }
            }
        }
    }

    public List retrive(ArrayList<Entity> returnEntities, Entity currentEntity){
        int index = getIndex(currentEntity);
        if(index != -1 && children[0] != null){
            children[index].retrive(returnEntities, currentEntity);
        }
        returnEntities.addAll(entities);
        return returnEntities;
    }
}