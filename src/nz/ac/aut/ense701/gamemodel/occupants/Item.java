package nz.ac.aut.ense701.gamemodel.occupants;

import nz.ac.aut.ense701.gamemodel.Position;


/**
 * This class represents an item that can be found on the island.
 * 
 * @author AS
 * @version July 2011
 */
public  abstract class Item extends Occupant
{
    /**
     * author: aashish
     * There are no checks on the "set" methods
     */
    private final double weight;
    private final double size;

    /**
     * Construct an item with known attributes.
     * @param pos the position of the item
     * @param name the name of the item
     * @param description a longer description of the item
     * @param weight
     * @param size
     */
    public Item(Position pos, String name, String description, double weight, double size) 
    {
        super(pos, name, description);
        this.weight = weight;
        this.size = size;
    }
    
     /**
     * Gets the weight of the item
     * @return the weight of the item
     */
    public double getWeight() {
        return this.weight;
    }

    /**
     * Gets the size of the item
     * @return the size of the item
     */
    public double getSize() {
        return this.size;
    }    

    
    /**
     * Is it OK to pick up and carry this item?
     * Items with size > 0 can be carried.
     * 
     * @return true if item can be carried, false if not
     */
    public boolean isOkToCarry()
    {
        return size > 0;
    }
}
