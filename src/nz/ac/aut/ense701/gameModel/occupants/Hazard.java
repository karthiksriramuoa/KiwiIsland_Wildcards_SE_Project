
package nz.ac.aut.ense701.gameModel.occupants;

import nz.ac.aut.ense701.gameModel.Position;
import nz.ac.aut.ense701.gameModel.Occupants;

/**
 * This class represents a hazard that can be found on the island
 *  
 * @author AS
 * @version July 2011
 */

public class Hazard extends Occupant {
    
    private final double impact;
    private final double FATAL_IMPACT = 1.0;

    /**
     * Create a hazard on the island
     * @param position where hazard is.
     * @param name of hazard.
     * @param description a more detailed description of hazard
     * @param impact on player
     */
    public Hazard(Position position, String name, String description, double impact) {
        
        super(position, name, description);
        this.impact = impact;
        
    }
    
    /**
     * What is impact of hazard
     * @return impact
     */
    public double getImpact() {
        return this.impact;
    }
    
    /**
     * Checks if fatal impact
     * @return true if fatal
     */
    public boolean isFatal()
    {
        return impact == FATAL_IMPACT;
    }
    
    /**
     * Checks if this is broken trap hazard
     * @return true if this is broken trap hazard
     */
    public boolean isBreakTrap() 
    {
        String name = this.getName();
        return "Broken trap".equalsIgnoreCase(name);
    }
    
    @Override
    public String getStringRepresentation() {
        return Occupants.HAZARD.toString();
    }
}
