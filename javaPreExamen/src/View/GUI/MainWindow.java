/*
 * Here comes the text of your license
 * Each line should be prefixed with  * 
 */
package View.GUI;

import java.util.ArrayList;
import javax.swing.JOptionPane;

import deepspace.GameUniverse;
import deepspace.GameState;

import controller.Controller;

import View.View;
import deepspace.CombatResult;

/**
 *
 * @author yabir
 */
public class MainWindow extends javax.swing.JFrame implements View{
    
    static Controller controller;
    private String appName = "Deepspace";
    private SpaceStationView StationView;
    private EnemyStarshipView EnemyView;
    
    /**
     * Creates new form MainWindow
     */
    public MainWindow() {
        initComponents();
        
        StationView = new SpaceStationView();
        EnemyView = new EnemyStarshipView();
        
        stationPanel.add(StationView);
        enemyPanel.add(EnemyView);
        
        message.setVisible(false);
        
        setTitle(appName);
        
        repaint();
        setLocationRelativeTo(null);
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        stationPanel = new javax.swing.JPanel();
        enemyPanel = new javax.swing.JPanel();
        fight = new javax.swing.JButton();
        nextTurn = new javax.swing.JButton();
        message = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(1100, 900));

        stationPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        stationPanel.setPreferredSize(new java.awt.Dimension(600, 480));
        stationPanel.setRequestFocusEnabled(false);

        enemyPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        fight.setText("Combatir");
        fight.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fightActionPerformed(evt);
            }
        });

        nextTurn.setText("Siguiente Turno");
        nextTurn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nextTurnActionPerformed(evt);
            }
        });

        message.setText("fdf");

        jLabel1.setFont(new java.awt.Font("Bitstream Vera Sans", 1, 14)); // NOI18N
        jLabel1.setText("Información de la partida:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(stationPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 574, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(message)
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(enemyPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(fight, javax.swing.GroupLayout.DEFAULT_SIZE, 143, Short.MAX_VALUE))
                    .addComponent(nextTurn))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(enemyPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 430, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(nextTurn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(fight, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(stationPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 512, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(message)
                        .addGap(22, 22, 22))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void fightActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fightActionPerformed
        // TODO add your handling code here:
        controller.combat();
    }//GEN-LAST:event_fightActionPerformed

    private void nextTurnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nextTurnActionPerformed
        // TODO add your handling code here:
        message.setVisible(false);
        controller.nextTurn();
    }//GEN-LAST:event_nextTurnActionPerformed

    
    public String getAppName(){
        return appName;
    }
    
    
    @Override
    public void displayMessage(CombatResult result){
        message.setVisible(true);
        
        switch(result){
            case STATIONESCAPES:
                message.setText("Has logrado escapar... pero tranquilo, no eres una Gallina Espacial.");
                break;
            case STATIONWINS :
                message.setText("Has GANADO el combate. Disfruta de tu botín.");
                if (controller.haveAWinner()) {
                    JOptionPane.showMessageDialog(this,"HAS GANADO LA PARTIDA!! <3",getAppName(),JOptionPane.INFORMATION_MESSAGE);
                    System.exit (0);
                }
                break;
            case STATIONWINSANDCONVERTS:
                message.setText("Has GANADO el combate. Disfruta de tu botín. WOLOLOLO! TE HAS TRANSFORMADO!");
                if (controller.haveAWinner()) {
                    JOptionPane.showMessageDialog(this,"HAS GANADO LA PARTIDA!! <3",getAppName(),JOptionPane.INFORMATION_MESSAGE);
                    System.exit (0);
                }
                break;
            case ENEMYWINS:
                message.setText("Has PERDIDO el combate. Cumple tu castigo.");
                break;
        }
    
    }
    
    @Override
    public void displayCleanDamage(){
        JOptionPane.showConfirmDialog(this, "No puedes continuar! Tienes daño pendiente\n", getAppName(), JOptionPane.YES_NO_OPTION);
    }
    
    @Override 
    public ArrayList<String> getNames() {
        Names namesCapture = new Names (this);
        return namesCapture.getNames();
    }
    
    @Override
    public void showView() {
        setVisible(true);
    }
    
    @Override
    public void updateView() {
        GameState currentState = controller.getState();
        fight.setEnabled(false);
        nextTurn.setEnabled(true);
        if( (currentState== GameState.BEFORECOMBAT) || (currentState == GameState.INIT)){
            fight.setEnabled(true);
            nextTurn.setEnabled(false);
        }
        StationView.setSpaceStation(controller.getUIversion().getCurrentStation());
        EnemyView.setEnemy(controller.getUIversion().getCurrentEnemy());
        
        //EnemyView.setVisible(false);
        //if(currentState == GameState.AFTERCOMBAT)
        //    EnemyView.setVisible(true);
    }
    
    @Override
    public void setController (Controller c) {
        controller = c;
    }
    
    @Override
    public boolean confirmExitMessage() {
        return (JOptionPane.showConfirmDialog(this, "¿Estás segur@ que deseas salir?", getAppName(), JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION);
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel enemyPanel;
    private javax.swing.JButton fight;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel message;
    private javax.swing.JButton nextTurn;
    private javax.swing.JPanel stationPanel;
    // End of variables declaration//GEN-END:variables
}
