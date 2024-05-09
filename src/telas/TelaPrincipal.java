package telas;
import javax.swing.JOptionPane;
import java.text.*;

public class TelaPrincipal extends javax.swing.JFrame {
    

    public TelaPrincipal() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Desktop = new javax.swing.JDesktopPane();
        Menu = new javax.swing.JMenuBar();
        usuario = new javax.swing.JMenu();
        MenuCad = new javax.swing.JMenuItem();
        MenuSobre = new javax.swing.JMenu();
        SaibaMais = new javax.swing.JMenuItem();
        MenuOpcao = new javax.swing.JMenu();
        MenuSair = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("TELA PRINCIPAL");

        Desktop.setPreferredSize(new java.awt.Dimension(400, 400));

        javax.swing.GroupLayout DesktopLayout = new javax.swing.GroupLayout(Desktop);
        Desktop.setLayout(DesktopLayout);
        DesktopLayout.setHorizontalGroup(
            DesktopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        DesktopLayout.setVerticalGroup(
            DesktopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );

        usuario.setText("Cadastrar");
        usuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                usuarioActionPerformed(evt);
            }
        });

        MenuCad.setText("Contato");
        MenuCad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuCadActionPerformed(evt);
            }
        });
        usuario.add(MenuCad);

        Menu.add(usuario);

        MenuSobre.setText("Sobre");
        MenuSobre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuSobreActionPerformed(evt);
            }
        });

        SaibaMais.setText("Saiba Mais");
        SaibaMais.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SaibaMaisActionPerformed(evt);
            }
        });
        MenuSobre.add(SaibaMais);

        Menu.add(MenuSobre);

        MenuOpcao.setText("Opção");

        MenuSair.setText("Sair");
        MenuSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuSairActionPerformed(evt);
            }
        });
        MenuOpcao.add(MenuSair);

        Menu.add(MenuOpcao);

        setJMenuBar(Menu);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Desktop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Desktop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void MenuSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuSairActionPerformed

        int sair = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja sair? ", "Atenção",JOptionPane.YES_NO_OPTION);

        if(sair == JOptionPane.YES_OPTION){
            System.exit(0);
        }
        
    }//GEN-LAST:event_MenuSairActionPerformed

    private void usuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_usuarioActionPerformed

    }//GEN-LAST:event_usuarioActionPerformed

    private void MenuCadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuCadActionPerformed
        // TODO add your handling code here:
        TelaUsuarios usuario = new TelaUsuarios();
        usuario.setVisible(true);
        Desktop.add(usuario);
    }//GEN-LAST:event_MenuCadActionPerformed

    private void MenuSobreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuSobreActionPerformed
        // TODO add your handling code here:
         TelaSobre Sobre = new TelaSobre();
         Sobre.setVisible(true);
    }//GEN-LAST:event_MenuSobreActionPerformed

    private void SaibaMaisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SaibaMaisActionPerformed
        // TODO add your handling code here:
        TelaSobre saibamais = new TelaSobre();
        saibamais.setVisible(true);
    }//GEN-LAST:event_SaibaMaisActionPerformed

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(() -> {
            new TelaPrincipal().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDesktopPane Desktop;
    private javax.swing.JMenuBar Menu;
    private javax.swing.JMenuItem MenuCad;
    private javax.swing.JMenu MenuOpcao;
    private javax.swing.JMenuItem MenuSair;
    private javax.swing.JMenu MenuSobre;
    private javax.swing.JMenuItem SaibaMais;
    private javax.swing.JMenu usuario;
    // End of variables declaration//GEN-END:variables
}
