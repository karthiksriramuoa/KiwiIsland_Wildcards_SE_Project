/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nz.ac.aut.ense701.gui;

import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import nz.ac.aut.ense701.gameModel.Game;
import nz.ac.aut.ense701.gameModel.Score;
import nz.ac.aut.ense701.gameModel.ScoreBoard;

/**
 *
 * @author aashi
 */
public class WelcomePage extends javax.swing.JFrame {
    /**
     * Creates new form WelcomePage
     */
    public WelcomePage() {
        initComponents();
        initializeScoreBoard();
        pnlBackground.repaint();
    }

    /**
     * Starts the KiwiIsland game
     */
    public void startNewGame(){
        // create the game object
        final Game game = new Game();
        // create the GUI for the game
        final KiwiCountUI  gui  = new KiwiCountUI(game, this); 
        
        // make the GUI visible
        java.awt.EventQueue.invokeLater(new Runnable() 
        {
            @Override
            public void run() 
            {
                gui.setVisible(true);
                gui.setFocusable(true);
                gui.requestFocus();
                WelcomePage.this.setVisible(false);
            }
        });
    }
  
    /**
     * Initializes the JList with player score
     */
    @SuppressWarnings("unchecked")
    public final void initializeScoreBoard(){
        try {
            List<Score> playerScores = ScoreBoard.getScoreBoard();
            if(playerScores != null){
                Collections.sort(playerScores, new Comparator<Score>(){
                    @Override
                    public int compare(Score o1, Score o2) {
                        return o2.getScore() - o1.getScore();
                    }
                });
                DefaultListModel<String> listModel = new DefaultListModel<String>();
                for(int i = 0; i < playerScores.size(); i++){
                    listModel.addElement((i + 1) + ". " + playerScores.get(i).toString());
                }
                scoreList.setModel(listModel);
            }
            else{
                scoreList.setModel(new DefaultListModel());
            }
        } catch (IOException ex) {
            Logger.getLogger(WelcomePage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSplitPane1 = new javax.swing.JSplitPane();
        jScrollPane2 = new javax.swing.JScrollPane();
        scoreList = new javax.swing.JList<String>();
        jPanel3 = new javax.swing.JPanel();
        pnlButtons = new javax.swing.JPanel();
        btnNewGame = new javax.swing.JButton();
        pnlBackground = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(235, 500));
        setPreferredSize(new java.awt.Dimension(833, 612));

        jSplitPane1.setDividerLocation(0.5);

        scoreList.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane2.setViewportView(scoreList);

        jSplitPane1.setLeftComponent(jScrollPane2);

        jPanel3.setLayout(new java.awt.BorderLayout());

        pnlButtons.setLayout(new java.awt.BorderLayout());

        btnNewGame.setText("New Game");
        btnNewGame.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewGameActionPerformed(evt);
            }
        });
        pnlButtons.add(btnNewGame, java.awt.BorderLayout.WEST);

        jPanel3.add(pnlButtons, java.awt.BorderLayout.PAGE_END);

        javax.swing.GroupLayout pnlBackgroundLayout = new javax.swing.GroupLayout(pnlBackground);
        pnlBackground.setLayout(pnlBackgroundLayout);
        pnlBackgroundLayout.setHorizontalGroup(
            pnlBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 616, Short.MAX_VALUE)
        );
        pnlBackgroundLayout.setVerticalGroup(
            pnlBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 349, Short.MAX_VALUE)
        );

        jPanel3.add(pnlBackground, java.awt.BorderLayout.CENTER);

        jSplitPane1.setRightComponent(jPanel3);

        getContentPane().add(jSplitPane1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnNewGameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewGameActionPerformed
        startNewGame();
    }//GEN-LAST:event_btnNewGameActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(WelcomePage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(WelcomePage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(WelcomePage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(WelcomePage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new WelcomePage().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnNewGame;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JPanel pnlBackground;
    private javax.swing.JPanel pnlButtons;
    private javax.swing.JList<String> scoreList;
    // End of variables declaration//GEN-END:variables
}
