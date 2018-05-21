/*
 * Here comes the text of your license
 * Each line should be prefixed with  * 
 */

package View.GUI;
import java.util.ArrayList;

import deepspace.SpaceStationToUI;
import deepspace.WeaponToUI;
import deepspace.ShieldToUI;
import deepspace.HangarToUI;

/**
 *
 * @author yabir
 */
public class SpaceStationView extends javax.swing.JPanel {

    /**
     * Creates new form SpaceStation
     */
    
    private HangarView hangarView;
    private DamageView damageView;
    boolean hangarVisible = true;
   
    public SpaceStationView() {
        initComponents();
        
        hangarView = new HangarView();
        
        noHangar.setVisible(false);
        hangarPanel.add(hangarView);
        
        damageView = new DamageView();
        currentDamage.add(damageView);
        currentDamage.setVisible(false);
        
        repaint();
        revalidate();
    }
    
    public void setSpaceStation(SpaceStationToUI station){
        
        //Cambiar las props de la nave
        name.setText(station.getName());
        ammoPower.setText(Float.toString(station.getAmmoPower()));
        shieldPower.setText(Float.toString(station.getShieldPower()));
        fuelUnits.setText(Float.toString(station.getFuelUnits()));
        
        //Limpiar los menus
        weaponsPanel.removeAll();
        shieldsPanel.removeAll();
        hangarPanel.removeAll();
        
        //Inicializar las armas
        WeaponView weaponView;
        for(WeaponToUI w: station.getWeapons()){
            weaponView = new WeaponView();
            weaponView.setWeapon(w);
            weaponsPanel.add(weaponView);
        }
        
        //Inicializar los escudos
        ShieldView shieldView;
        for(ShieldToUI w: station.getShieldBoosters()){
            shieldView = new ShieldView();
            shieldView.setShield(w);
            shieldsPanel.add(shieldView);
        }
        
        
        if (station.getHangar() == null){
            hangarVisible = false;
        }else{
            hangarVisible = true;
            hangarView.setHangar(station.getHangar());
        }
        
        noHangar.setVisible(!hangarVisible);
        hangarPanel.setVisible(hangarVisible);
        hangarPanel.add(hangarView);
                
        repaint();
        revalidate();
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        ammoPower = new javax.swing.JLabel();
        shieldPower = new javax.swing.JLabel();
        fuelUnits = new javax.swing.JLabel();
        Labelname = new javax.swing.JLabel();
        name = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        shieldsPanel = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        weaponsPanel = new javax.swing.JPanel();
        hangarPanel = new javax.swing.JPanel();
        equipButton = new javax.swing.JButton();
        discard = new javax.swing.JButton();
        discardHangar = new javax.swing.JButton();
        noHangar = new javax.swing.JLabel();
        currentDamage = new javax.swing.JPanel();

        setBorder(null);

        jLabel1.setText("Potencia de fuego: ");

        jLabel2.setText("Potencia de disparo:");

        jLabel3.setText("Combustible:");

        ammoPower.setText("jLabel4");

        shieldPower.setText("jLabel5");

        fuelUnits.setText("jLabel6");

        Labelname.setText("Nombre:");

        name.setText("jLabel4");

        jScrollPane1.setBorder(javax.swing.BorderFactory.createTitledBorder("Escudos"));
        jScrollPane1.setViewportView(shieldsPanel);

        jScrollPane2.setBorder(javax.swing.BorderFactory.createTitledBorder("Weapons"));
        jScrollPane2.setViewportView(weaponsPanel);

        hangarPanel.setBorder(null);

        equipButton.setText("Equipar");
        equipButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                equipButtonActionPerformed(evt);
            }
        });

        discard.setText("Decartar");
        discard.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                discardActionPerformed(evt);
            }
        });

        discardHangar.setText("Descartar Hangar");
        discardHangar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                discardHangarActionPerformed(evt);
            }
        });

        noHangar.setFont(new java.awt.Font("DejaVu Sans", 0, 24)); // NOI18N
        noHangar.setText("No tienes ningun Hangar");

        currentDamage.setBorder(javax.swing.BorderFactory.createTitledBorder("Daño recibido"));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(currentDamage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(hangarPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1)
                    .addComponent(jScrollPane2)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(equipButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(discard)
                                .addGap(18, 18, 18)
                                .addComponent(discardHangar))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel1)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(ammoPower)
                                        .addGap(41, 41, 41))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(jLabel2)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(shieldPower)
                                        .addGap(35, 35, 35)))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(Labelname)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(name))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel3)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(fuelUnits))))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(38, 38, 38)
                                .addComponent(noHangar)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel3)
                    .addComponent(ammoPower)
                    .addComponent(fuelUnits))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(shieldPower)
                    .addComponent(Labelname)
                    .addComponent(name))
                .addGap(18, 18, 18)
                .addComponent(currentDamage, javax.swing.GroupLayout.DEFAULT_SIZE, 106, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(noHangar, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(hangarPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(equipButton)
                    .addComponent(discard)
                    .addComponent(discardHangar)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void equipButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_equipButtonActionPerformed
        // TODO add your handling code here:
        MainWindow.controller.mountFromHangar(hangarView.getSelected());
    }//GEN-LAST:event_equipButtonActionPerformed

    private void discardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_discardActionPerformed
        // TODO add your handling code here:
        MainWindow.controller.discardMountedElements(hangarView.getSelected());
        //MainWindow.controller.updateView();
    }//GEN-LAST:event_discardActionPerformed

    private void discardHangarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_discardHangarActionPerformed
        // TODO add your handling code here:
        MainWindow.controller.discardHangar();
    }//GEN-LAST:event_discardHangarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Labelname;
    private javax.swing.JLabel ammoPower;
    private javax.swing.JPanel currentDamage;
    private javax.swing.JButton discard;
    private javax.swing.JButton discardHangar;
    private javax.swing.JButton equipButton;
    private javax.swing.JLabel fuelUnits;
    private javax.swing.JPanel hangarPanel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel name;
    private javax.swing.JLabel noHangar;
    private javax.swing.JLabel shieldPower;
    private javax.swing.JPanel shieldsPanel;
    private javax.swing.JPanel weaponsPanel;
    // End of variables declaration//GEN-END:variables
}