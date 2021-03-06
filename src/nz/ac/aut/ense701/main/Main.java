package nz.ac.aut.ense701.main;

import nz.ac.aut.ense701.gui.WelcomePage;

/**
 * Kiwi Count Project
 * 
 * @author AS
 * @version 2011
 */
public class Main 
{
    /**
     * Main method of Kiwi Count.
     * 
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        final WelcomePage welcomePage = new WelcomePage();
        java.awt.EventQueue.invokeLater(() -> 
            welcomePage.setVisible(true)
        );
        
    }

}
