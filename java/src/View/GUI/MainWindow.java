/*
 * Here comes the text of your license
 * Each line should be prefixed with  * 
 */
package View.GUI;
import View.View;
import deepspace.GameUniverse;
import java.util.ArrayList;
import controller.Controller;
import javax.swing.JOptionPane;
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

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(1024, 720));

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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(stationPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 574, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(enemyPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(fight, javax.swing.GroupLayout.DEFAULT_SIZE, 143, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(stationPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 505, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(enemyPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 354, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(fight, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void fightActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fightActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fightActionPerformed

    
    public String getAppName(){
        return appName;
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
        StationView.setSpaceStation(controller.getUIversion().getCurrentStation());
        EnemyView.setEnemy(controller.getUIversion().getCurrentEnemy());
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
    private javax.swing.JPanel stationPanel;
    // End of variables declaration//GEN-END:variables
}
