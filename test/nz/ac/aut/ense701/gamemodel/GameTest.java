package nz.ac.aut.ense701.gamemodel;

import nz.ac.aut.ense701.gamemodel.occupants.Predator;
import nz.ac.aut.ense701.gamemodel.occupants.Food;
import nz.ac.aut.ense701.gamemodel.occupants.Item;
import nz.ac.aut.ense701.gamemodel.occupants.Tool;
import nz.ac.aut.ense701.gamemodel.occupants.Hazard;
import org.junit.Test;

/**
 * The test class GameTest.
 *
 * @author  AS
 * @version S2 2011
 */
public class GameTest extends junit.framework.TestCase
{
    private static final String CLIFF = "Cliff";
    private static final String SCREWDRIVER = "Screwdriver";
    private static final String GOOD_TOOL_TO_FIX_TRAP = "A good tool to fix a trap";
    private static final String SHOULD_NOT_BE_ABLE_TO_USE = "Should not be able to use";
    private static final String SHOULD_BE_ABLE_TO_USE = "Should be able to use";
    private static final String SANDWICH = "Sandwich";
    private static final String YUMMY = "Yummy";
    private static final String PREDATOR_TRAP = "A predator trap";
    private static final String WRONG_STAMINA = "Wrong stamina";
    private static final String MOVE_VALID = "Move valid";
    private static final String PLAYER_SHOULD_HAVE_TRAP = "Player should have trap";
    private static final String RAT_TRAP = "Rat trap";
    private static final String EXTRA_OCCUPANT = "An extra occupant";
    private static final String PLAYER_SHOULD_NOT_HAVE_FOOD = "Player should no longer have food";
    private static final String PLAYER_SHOULD_HAVE_FOOD = "Player should have food";
    
    private Game       game;
    private Player     player;
    private Position   playerPosition;
    private Island island ;
   
    
    /**
     * Default constructor for test class GameTest
     */
    public GameTest()
    {
        //Default constructor for the test class
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Override
    protected void setUp()
    {
        // Create a new game from the data file.
        // Player is in position 2,0 & has 100 units of stamina
        game           = new Game();
        playerPosition = game.getPlayer().getPosition();
        player         = game.getPlayer();
        island = game.getIsland();
    }
    
    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @Override
    protected void tearDown()
    {
        game = null;
        player = null;
        playerPosition = null;
    }

    /*********************************************************************************************************
     * Game under test
      
---------------------------------------------------
|    |    |@   | F  | T  |    |    | PK |    |    |
|~~~~|~~~~|....|....|....|~~~~|^^^^|^^^^|^^^^|^^^^|
---------------------------------------------------
|    |    |    |    | H  |    |    |    |    |    |
|~~~~|####|^^^^|^^^^|^^^^|^^^^|^^^^|^^^^|^^^^|^^^^|
---------------------------------------------------
|    |    | H  |    | E  |    | P  |    | K  |    |
|####|####|####|####|^^^^|^^^^|^^^^|^^^^|^^^^|~~~~|
---------------------------------------------------
| T  |    |    |    | P  | H  |    |    |    |    |
|....|####|####|####|****|****|^^^^|....|~~~~|~~~~|
---------------------------------------------------
| F  | P  |    |    |    |    | F  |    |    |    |
|....|^^^^|^^^^|^^^^|****|****|^^^^|....|~~~~|~~~~|
---------------------------------------------------
| H  |    | P  | T  | E  |    |    |    |    |    |
|....|****|****|****|****|****|####|####|####|~~~~|
---------------------------------------------------
|    |    | K  |    | P  | H  | K  | E  | F  |    |
|....|****|****|****|****|****|****|####|####|####|
---------------------------------------------------
| K  |    |    | F  | H  |    | H  | K  | T  |    |
|****|****|****|****|****|~~~~|****|****|~~~~|~~~~|
---------------------------------------------------
|    |    | E  | K  |    |    |    |    | F  |    |
|....|....|****|****|~~~~|~~~~|~~~~|****|****|....|
---------------------------------------------------
|    |    |    | K  | K  |    | K  | P  |    |    |
|~~~~|....|****|****|****|~~~~|****|****|****|....|
---------------------------------------------------
 *********************************************************************************************************/
    /**
     * Tests for Accessor methods of Game, excluding those which are wrappers for accessors in other classes.
     * Other class accessors are tested in their test classes.
     */
    
    @Test
    public void testGetNumRows(){
        assertEquals("Check row number", game.getNumRows(), 10);
    }
    
    @Test
    public void testGetNumColumns(){
        assertEquals("Check column number", game.getNumRows(), 10);
    }
    
    @Test
    public void testGetPlayer(){
        String name = player.getName();
        String checkName = "RiverSong";
        assertTrue("Check player name", name.equals(checkName) );
    } 

    @Test
    public void testGetInitialState(){
        assertEquals("Wrong initial state", game.getState(), GameState.PLAYING);
    }
    
    @Test
    public void testGetPlayerValues(){
        int[] values = game.getPlayerValues();
        assertEquals("Check Max backpack size.", values[Game.MAXSIZE_INDEX], 5);    
        assertEquals("Check max stamina.", values[Game.MAXSTAMINA_INDEX], 100);
        assertEquals("Check max backpack weight.", values[Game.MAXWEIGHT_INDEX], 10);
        assertEquals("Check initialstamina", values[Game.STAMINA_INDEX], 100);
        assertEquals("Check initial backpack weight.", values[Game.WEIGHT_INDEX], 0);
        assertEquals("Check initial backp[ack size.", values[Game.SIZE_INDEX], 0);
    }
    
    @Test
    public void testIsPlayerMovePossibleValidMove(){
        //At start of game player has valid moves EAST, West & South
        assertTrue("Move should be valid", game.isPlayerMovePossible(MoveDirection.SOUTH));
    }
    
    @Test
    public void testIsPlayerMovePossibleInvalidMove(){
        //At start of game player has valid moves EAST, West & South
        assertFalse("Move should not be valid", game.isPlayerMovePossible(MoveDirection.NORTH));
    }
    
    @Test
    public void testCanCollectCollectable(){
        //Items that are collectable and fit in backpack
        Item valid = new Food(playerPosition,SANDWICH, YUMMY,1.0, 1.0,1.0);
        assertTrue("Should be able to collect", game.canCollect(valid));
    }
    
    @Test    
    public void testCanCollectNotCollectable(){
        //Items with size of '0' cannot be carried
        Item notCollectable = new Food(playerPosition,SANDWICH, "Very Heavy Sandwich",10.0, 0.0,1.0);
        assertFalse("Should not be able to collect", game.canCollect(notCollectable));
    }
    
    @Test
    public void testCanUseFoodValid(){
        //Food can always be used
        Item valid = new Food(playerPosition,SANDWICH, YUMMY,1.0, 1.0,1.0);
        assertTrue(SHOULD_BE_ABLE_TO_USE, game.canUse(valid));
    }
    
    @Test
    public void testCanUseTrapValid(){
        //Trap can be used if there is a predator here
        Item valid = new Tool(playerPosition,"Trap", PREDATOR_TRAP,1.0, 1.0);
        //Add predator
        Predator rat = new Predator(playerPosition,"Rat", "A norway rat");
        island.addOccupant(playerPosition, rat);
        assertTrue(SHOULD_BE_ABLE_TO_USE, game.canUse(valid));
    }
    
    @Test
    public void testCanUseTrapNoPredator(){
        //Trap can be used if there is a predator here
        Item tool = new Tool(playerPosition,"Trap", PREDATOR_TRAP,1.0, 1.0);

        assertFalse(SHOULD_NOT_BE_ABLE_TO_USE, game.canUse(tool));
    }
    
    @Test
    public void testCanUseTool(){
        //Screwdriver can be used if player has a broken trap
        Item tool = new Tool(playerPosition,SCREWDRIVER, GOOD_TOOL_TO_FIX_TRAP,1.0, 1.0);
        Tool trap = new Tool(playerPosition,"Trap", PREDATOR_TRAP,1.0, 1.0);
        trap.setBroken();
        player.collect(trap);

        assertTrue(SHOULD_BE_ABLE_TO_USE, game.canUse(tool));
    }
    
    @Test
    public void testCanUseToolNoTrap(){
        //Screwdriver can be used if player has a broken trap
        Item tool = new Tool(playerPosition,SCREWDRIVER, GOOD_TOOL_TO_FIX_TRAP,1.0, 1.0);
        Tool trap = new Tool(playerPosition,"Trap", PREDATOR_TRAP,1.0, 1.0);
        trap.setBroken();

        assertFalse(SHOULD_NOT_BE_ABLE_TO_USE, game.canUse(tool));
    }
    
    @Test
    public void testCanUseToolTrapNotBroken(){
        //Screwdriver can be used if player has a broken trap
        Item tool = new Tool(playerPosition,SCREWDRIVER, GOOD_TOOL_TO_FIX_TRAP,1.0, 1.0);
        Tool trap = new Tool(playerPosition,"Trap", PREDATOR_TRAP,1.0, 1.0);
        player.collect(trap);

        assertFalse(SHOULD_NOT_BE_ABLE_TO_USE, game.canUse(tool));
    }
    
    @Test
    public void testGetKiwiCountInitial()
    {
       assertEquals("Shouldn't have counted any kiwis yet",game.getKiwiCount(),0); 
    }
    /**
     * Test for mutator methods
     */
    
    @Test
    public void testCollectValid(){
        Item food = new Food(playerPosition,SANDWICH, YUMMY,1.0, 1.0,1.0);
        island.addOccupant(playerPosition, food);
        assertTrue("Food now on island", island.hasOccupant(playerPosition, food));
        game.collectItem(food);
        
        assertTrue(PLAYER_SHOULD_HAVE_FOOD,player.hasItem(food));
        assertFalse("Food should no longer be on island", island.hasOccupant(playerPosition, food));
    }
    
    @Test
    public void testCollectNotCollectable(){
        Item notCollectable = new Food(playerPosition,"House", "Can't collect",1.0, 0.0,1.0);
        island.addOccupant(playerPosition, notCollectable);
        assertTrue("House now on island", island.hasOccupant(playerPosition, notCollectable));
        game.collectItem(notCollectable);
        
        assertFalse("Player should not have house",player.hasItem(notCollectable));
        assertTrue("House should be on island", island.hasOccupant(playerPosition, notCollectable));
    }
    
    @Test    
    public void testDropValid(){
        Item food = new Food(playerPosition,SANDWICH, YUMMY,1.0, 1.0,1.0);
        island.addOccupant(playerPosition, food);
        game.collectItem(food);
        assertTrue(PLAYER_SHOULD_HAVE_FOOD,player.hasItem(food));
        
        game.dropItem(food);
        assertFalse(PLAYER_SHOULD_NOT_HAVE_FOOD,player.hasItem(food));
        assertTrue("Food should be on island", island.hasOccupant(playerPosition, food));
    }
    
    @Test
    public void testDropNotValidPositionFull(){
        Item food = new Food(playerPosition,SANDWICH, YUMMY,1.0, 1.0,1.0);
        island.addOccupant(playerPosition, food);
        game.collectItem(food);
        assertTrue(PLAYER_SHOULD_HAVE_FOOD,player.hasItem(food));
        
        //Positions can have at most three occupants
        Item dummy = new Tool(playerPosition,"Trap", EXTRA_OCCUPANT, 1.0, 1.0);
        Item dummy2 = new Tool(playerPosition,"Trap", EXTRA_OCCUPANT, 1.0, 1.0);
        Item dummy3 = new Tool(playerPosition,"Trap", EXTRA_OCCUPANT, 1.0, 1.0);
        island.addOccupant(playerPosition, dummy);
        island.addOccupant(playerPosition, dummy2);
        island.addOccupant(playerPosition, dummy3);
        
        game.dropItem(food);
        assertTrue(PLAYER_SHOULD_HAVE_FOOD,player.hasItem(food));
        assertFalse("Food should not be on island", island.hasOccupant(playerPosition, food));
    }
    
    @Test
    public void testUseItemFoodCausesIncrease(){
        Item food = new Food(playerPosition,SANDWICH, YUMMY,1.0, 1.0,1.3);
        player.collect(food);
        assertTrue(PLAYER_SHOULD_HAVE_FOOD,player.hasItem(food));
        
        // Will only get a stamina increase if player has less than max stamina
        player.reduceStamina(5.0);
        game.useItem(food);
        assertFalse(PLAYER_SHOULD_NOT_HAVE_FOOD,player.hasItem(food));
        assertEquals("Wrong stamina level", player.getStaminaLevel(), 96.3);
    }
 
    public void testUseItemFoodNoIncrease(){
        Item food = new Food(playerPosition,SANDWICH, "Yummy",1.0, 1.0,1.3);
        player.collect(food);
        assertTrue(PLAYER_SHOULD_HAVE_FOOD,player.hasItem(food));
        
        // Will only get a stamina increase if player has less than max stamina
        game.useItem(food);
        assertFalse(PLAYER_SHOULD_NOT_HAVE_FOOD,player.hasItem(food));
        assertEquals("Wrong stamina level", player.getStaminaLevel(), 100.0);
    }  
    
    @Test
    public void testUseItemTrap(){
        Item trap = new Tool(playerPosition,"Trap", RAT_TRAP,1.0, 1.0);
        player.collect(trap);
        assertTrue(PLAYER_SHOULD_HAVE_TRAP,player.hasItem(trap));
        
        // Can only use trap if there is a predator.
        Predator predator = new Predator(playerPosition,"Rat", "Norway rat");
        island.addOccupant(playerPosition, predator);
        game.useItem(trap);
        assertTrue("Player should still have trap",player.hasItem(trap));
        assertFalse("Predator should be gone.", island.hasPredator(playerPosition));
    }
    
    @Test
    public void testUseItemTrapFinalPredator(){
        assertTrue("Check player moves", trapAllPredators());
        assertTrue("Game should be won", game.getState()== GameState.WON);    
    }
    
    @Test
    public void testUseItemBrokenTrap(){
        Tool trap = new Tool(playerPosition,"Trap", RAT_TRAP,1.0, 1.0);
        player.collect(trap);
        assertTrue(PLAYER_SHOULD_HAVE_TRAP,player.hasItem(trap));
        
        // Can only use trap if there is a predator.
        Predator predator = new Predator(playerPosition,"Rat", "Norway rat");
        island.addOccupant(playerPosition, predator);
        trap.setBroken();
        game.useItem(trap);
        assertTrue("Player should still have trap",player.hasItem(trap));
        assertTrue("Predator should still be there as trap broken.", island.hasPredator(playerPosition));
    }
    
    @Test
    public void testUseItemToolFixTrap(){
        Tool trap = new Tool(playerPosition,"Trap", RAT_TRAP,1.0, 1.0);
        trap.setBroken();
        player.collect(trap);
        assertTrue(PLAYER_SHOULD_HAVE_TRAP,player.hasItem(trap));
        Tool screwdriver = new Tool(playerPosition,SCREWDRIVER, "Useful screwdriver",1.0, 1.0);
        player.collect(screwdriver);
        assertTrue("Player should have screwdriver",player.hasItem(screwdriver));
        
        game.useItem(screwdriver);
        assertFalse("Trap should be fixed", trap.isBroken());
    }
   
    @Test
    public void testPlayerMoveToInvalidPosition(){
        //A move NORTH would be invalid from player's start position
        assertFalse("Move not valid", game.playerMove(MoveDirection.NORTH));
    }
 
    @Test
    public void testPlayerMoveValidNoHazards(){
        double stamina = player.getStaminaLevel();  

        assertTrue(MOVE_VALID, game.playerMove(MoveDirection.SOUTH));
        //Stamina reduced by move
        assertEquals(WRONG_STAMINA, stamina - 3, player.getStaminaLevel());
        Position newPos = game.getPlayer().getPosition();
        assertEquals("Wrong position", newPos.getRow(), 1);
        assertFalse("Player should not be here", game.hasPlayer(playerPosition.getRow(), playerPosition.getColumn()));
    }
    
    @Test
    public void testPlayerMoveFatalHazard(){ 
        Position hazardPosition = new Position(island, playerPosition.getRow()+1, playerPosition.getColumn());
        Hazard fatal = new Hazard(hazardPosition, CLIFF, "Steep cliff", 1.0);
        island.addOccupant(hazardPosition, fatal);
        
        assertTrue(MOVE_VALID, game.playerMove(MoveDirection.SOUTH));
        //Fatal Hazard should kill player
        assertTrue("Player should be dead.", !player.isAlive());
        assertTrue("Game should be over", game.getState()== GameState.LOST);
    }
    
    @Test
    public void testPlayerMoveDeadPlayer(){
        player.kill();
        assertFalse(game.playerMove(MoveDirection.SOUTH));
    }
    
    @Test
    public void testPlayerMoveNonFatalHazardNotDead(){
        double stamina = player.getStaminaLevel(); 
        Position hazardPosition = new Position(island, playerPosition.getRow()+1, playerPosition.getColumn());
        Hazard fatal = new Hazard(hazardPosition, CLIFF, "Not so steep cliff", 0.5);
        island.addOccupant(hazardPosition, fatal);
        
        assertTrue(MOVE_VALID, game.playerMove(MoveDirection.SOUTH));
        //Non-fatal Hazard should reduce player stamina
        assertTrue("Player should be alive.", player.isAlive());
        assertTrue("Game should not be over", game.getState()== GameState.PLAYING);
        assertEquals(WRONG_STAMINA, stamina-53, player.getStaminaLevel());
    }
    
    @Test
    public void testPlayerMoveNonFatalHazardDead(){
        Position hazardPosition = new Position(island, playerPosition.getRow()+1, playerPosition.getColumn());
        Hazard fatal = new Hazard(hazardPosition, CLIFF, "Not so steep cliff", 0.5);
        island.addOccupant(hazardPosition, fatal);
        player.reduceStamina(47.0);
        
        assertTrue(MOVE_VALID, game.playerMove(MoveDirection.SOUTH));
        //Non-fatal Hazard should reduce player stamina to less than zero
        assertFalse("Player should not be alive.", player.isAlive());
        assertTrue("Game should be over", game.getState()== GameState.LOST);
        assertEquals(WRONG_STAMINA, 0.0, player.getStaminaLevel());
    }
    
    @Test
    public void testPlayerMoveNotEnoughStamina(){
        
        player.moveToPosition(new Position(island, 3, 7), Terrain.WETLAND);
        // Reduce player's stamina to less than is needed for the most challenging move
        //Most challenging move is WEST as Terrain is water
        player.reduceStamina(97.0);
        assertFalse("Player should not have required stamina", game.playerMove(MoveDirection.WEST));
        //Game not over as there other moves player has enough stamina for
        assertTrue("Game should not be over", game.getState()== GameState.PLAYING);
    }
    
    @Test
    public void testCountKiwi()
    {
        //Need to move to a place where there is a kiwi
        assertTrue (" This move valid", playerMoveEast(5));
        game.countKiwi();
        assertEquals("Wrong count", game.getKiwiCount(), 1);
    }
    
    

    @Test
    public void testGetPlayerMessages()
    {
        game.addFact("Test Message 1");
        assertEquals("Test Message 1", game.getPlayerMessages().get(0));
        
    }
    
    /**
     * Makes the player take ten steps without counting a kiwi
     * and asserts that the kiwi population on the island is not
     * changed
     */
    @Test
    public void testChangeKiwiPopulationNoChangeIfKiwiHasNotBeenCounted(){
        int currentKiwiPopulation = island.getCurrentKiwiPopulationOnIsland();
        for(int i = 0; i < 10; i++){
            game.getPlayer().incrementSteps();
        }
        assertEquals(0, game.changeKiwiPopulation());
        assertEquals(currentKiwiPopulation, island.getCurrentKiwiPopulationOnIsland());
        
    }
    
    /**
     * Makes the player take ten steps (given the user has counted a kiwi)
     * and asserts that the kiwi population on the island is reduced by one
     */
    @Test
    public void testChangeKiwiPopulationIncreaseKiwiPopukationByOne(){
        player.moveToPosition(new Position(island, 0, 7), Terrain.SCRUB);
        game.countKiwi();
        int currentKiwiPopulation = island.getCurrentKiwiPopulationOnIsland();
        for(int i = 0; i < 10; i++){
            game.getPlayer().incrementSteps();
        }
        assertEquals(-1, game.changeKiwiPopulation());
        assertEquals(--currentKiwiPopulation, island.getCurrentKiwiPopulationOnIsland());
        
    }
    
    /**
     * Makes the player take twelve steps (given the user has counted a kiwi)
     * and asserts that the kiwi population on the island is increased by one
     */
    @Test
    public void testChangeKiwiPopulationDecreaseKiwiPopukationByOne(){
        player.moveToPosition(new Position(island, 0, 7), Terrain.SCRUB);
        game.countKiwi();
        int currentKiwiPopulation = island.getCurrentKiwiPopulationOnIsland();
        for(int i = 0; i < 12; i++){
            game.getPlayer().incrementSteps();
        }
        assertEquals(1, game.changeKiwiPopulation());
        assertEquals(++currentKiwiPopulation, island.getCurrentKiwiPopulationOnIsland());
        
    }
    
    
    /**
     * Private helper methods
     */
    private boolean trapAllPredators()
    {
        //Firstly player needs a trap
        Tool trap = new Tool(playerPosition,"Trap", PREDATOR_TRAP,1.0, 1.0);
        game.collectItem(trap);
        
        //Now player needs to trap all predators
        //Predator 1
        player.moveToPosition(new Position(island, 0, 7), Terrain.WETLAND);
        game.useItem(trap);
        //Predator 2
        player.moveToPosition(new Position(island, 2, 6), Terrain.WETLAND);
        game.useItem(trap);
        //Predator 3
        player.moveToPosition(new Position(island, 3, 4), Terrain.WETLAND);
        game.useItem(trap);
        //Predator 4
        player.moveToPosition(new Position(island, 4, 1), Terrain.WETLAND);
        game.useItem(trap);
        //Predator 5
        player.moveToPosition(new Position(island, 5, 2), Terrain.WETLAND);
        game.useItem(trap);
        //Predator 6
        player.moveToPosition(new Position(island, 6, 4), Terrain.WETLAND);
        game.useItem(trap);
        //Predator 7
        boolean moveOK = playerMoveNorth(1);
        if(moveOK){
            moveOK = playerMoveEast(3);
        }
        if(moveOK){
            playerMoveSouth(4);
            game.useItem(trap);
        }
        return true;
    }
    
    private boolean playerMoveNorth(int numberOfMoves)
    {
        boolean success = false;
        for (int i = 0; i < numberOfMoves; i++) {
            success = game.playerMove(MoveDirection.NORTH);
            if(!success)
                break;
            
        }
        return success;
    }
    
    private boolean playerMoveSouth(int numberOfMoves)
    {
        boolean success = false;
        for (int i = 0; i < numberOfMoves; i++) {
            success = game.playerMove(MoveDirection.SOUTH);
            if(!success)
                break;
            
        }
        return success;
    }
    
    private boolean playerMoveEast(int numberOfMoves)
    {
        boolean success = false;
        for (int i = 0; i < numberOfMoves; i++) {
            success = game.playerMove(MoveDirection.EAST);
            if(!success)
                break;
            
        }
        return success;
    }
    
    private boolean playerMoveWest(int numberOfMoves)
    {
        boolean success = false;
        for (int i = 0; i < numberOfMoves; i++) {
            success = game.playerMove(MoveDirection.WEST);
            if(!success)
                break;
            
        }
        return success;
    }
}
