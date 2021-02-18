/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Multi;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import jdk.jfr.DataAmount;

/**
 *
 * @author ferdio
 */
public class OneClient extends javax.swing.JFrame {

    public OneClient() {
        initComponents();
    }

    String username;
    ArrayList<String> users = new ArrayList();
    int port;
    String pt;
    Boolean estConnecté = false;
    Data mesDonne;
    Socket sock;
    BufferedReader reader;
    PrintWriter writer;

    private DefaultListModel mod = new DefaultListModel();
    
    
    public void ecoute(){
         Thread TravailClient = new Thread(new TravailClient());
         TravailClient.start();
    }
    
    
    public void ajout(String data){
         users.add(data);
    }
    
    
    private void open() throws FileNotFoundException, IOException {
        Data data = (Data) mod.getElementAt(list.getSelectedIndex());
        //if (data.getStatus().equals("image")){
            ShowImage obj = new ShowImage(this, true);
            ImageIcon icon = new ImageIcon(data.getFile());
            obj.set(icon);
            obj.setVisible(true);
        //}
    }
   
    private void save() {
        String data = (String) mod.getElementAt(list.getSelectedIndex());
        JFileChooser ch = new JFileChooser();
        int c = ch.showSaveDialog(this);
        if (c == JFileChooser.APPROVE_OPTION) {
            try {
                byte[] b = new byte[5000];
                FileOutputStream out = new FileOutputStream(ch.getSelectedFile());
                out.write(b);
                out.close();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, e, "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    public class TravailClient implements Runnable{
        
        @Override
        public void run() {
            list.setModel(mod);
            String[] data;
            String stream, connect = "Connecter", chat = "Chat",file = "Fichier";
            try{
                while ((stream = reader.readLine()) != null){
                     data = stream.split(":");
                     if (data[2].equals(chat)) {
                        AffichageClient.append(data[0] + ":" + data[1] + "\n");
                        AffichageClient.setCaretPosition(AffichageClient.getDocument().getLength());
                     } 
                     else if (data[2].equals(file)){
                        AffichageClient.append(data[0] + ":" + data[1] + "\n");
                        mod.addElement(data[1]);
                        AffichageClient.setCaretPosition(AffichageClient.getDocument().getLength());
                     } 
                     else if (data[2].equals(connect)){
                        AffichageClient.removeAll();
                        ajout(data[0]);
                     } 
                }
                
                
                
           }catch(Exception ex) { }
        }
        
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        AffichageClient = new javax.swing.JTextArea();
        jLabel3 = new javax.swing.JLabel();
        adresse = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        nomC = new javax.swing.JTextField();
        envoyer = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        message = new javax.swing.JTextPane();
        connexion = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        portclient1 = new javax.swing.JTextField();
        SendFichier = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        list = new javax.swing.JList<>();
        jLabel1 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        filename = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel2.setBackground(new java.awt.Color(44, 65, 80));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "CLIENT", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Linux Biolinum Keyboard O", 3, 18), new java.awt.Color(255, 255, 255))); // NOI18N

        AffichageClient.setColumns(20);
        AffichageClient.setRows(5);
        jScrollPane1.setViewportView(AffichageClient);

        jLabel3.setFont(new java.awt.Font("Liberation Serif", 3, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Adresse :");

        adresse.setText("127.0.0.1");
        adresse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                adresseActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Liberation Serif", 3, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Port :");

        nomC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nomCActionPerformed(evt);
            }
        });

        envoyer.setText("SEND");
        envoyer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                envoyerActionPerformed(evt);
            }
        });

        jScrollPane2.setViewportView(message);

        connexion.setText("CONNEXION");
        connexion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                connexionActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Liberation Serif", 3, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Nom :");

        portclient1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                portclient1ActionPerformed(evt);
            }
        });

        SendFichier.setText("FICHIER");
        SendFichier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SendFichierActionPerformed(evt);
            }
        });

        jScrollPane3.setViewportView(list);

        jLabel1.setFont(new java.awt.Font("Liberation Sans", 3, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Envoyer un Message");

        jButton3.setText("ENREGISTRER");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        filename.setText("nom du fichier");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel3)
                                .addComponent(jLabel2))
                            .addGap(12, 12, 12)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(connexion, javax.swing.GroupLayout.DEFAULT_SIZE, 298, Short.MAX_VALUE)
                                .addComponent(adresse)
                                .addComponent(portclient1)
                                .addComponent(nomC)))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(envoyer, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE))
                                    .addGap(28, 28, 28))
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addGap(33, 33, 33)
                                    .addComponent(jLabel1)
                                    .addGap(66, 66, 66)))
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(filename, javax.swing.GroupLayout.DEFAULT_SIZE, 223, Short.MAX_VALUE)
                                    .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(42, 42, 42))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(SendFichier, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(98, 98, 98)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 461, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(adresse, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(portclient1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(nomC, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addComponent(connexion)
                .addGap(25, 25, 25)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(filename, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(SendFichier)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 74, Short.MAX_VALUE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(12, 12, 12)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(envoyer)
                    .addComponent(jButton3))
                .addGap(20, 20, 20))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 514, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void adresseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_adresseActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_adresseActionPerformed

    private void nomCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nomCActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nomCActionPerformed

    private void envoyerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_envoyerActionPerformed
        String nothing = "";
        if ((message.getText()).equals(nothing)) {
             message.setText("");
             message.requestFocus();
        } else {
            try {
               writer.println(username + ":" + message.getText() + ":" + "Chat");
               writer.flush(); 
            } catch (Exception ex){
                AffichageClient.append("Le message  n'est pas envoyé. \n");
            }
            message.setText("");
            message.requestFocus();
        }
        message.setText("");
        message.requestFocus();
    }//GEN-LAST:event_envoyerActionPerformed

    private void connexionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_connexionActionPerformed

       if (estConnecté == false){
            username = nomC.getText();
            nomC.setEditable(false);
            try{
                pt = portclient1.getText();
                port = Integer.parseInt(pt);
                sock = new Socket(adresse.getText().trim(), port);
                InputStreamReader streamreader = new InputStreamReader(sock.getInputStream());
                reader = new BufferedReader(streamreader);
                writer = new PrintWriter(sock.getOutputStream());
                writer.println(username + ":est connecté.:Connecter");
                writer.flush();  
                estConnecté = true;
                JOptionPane.showMessageDialog(this, "Vous êtes maintenant connecté");
            } 
            catch (Exception ex){
                JOptionPane.showMessageDialog(this, ex, "Error", JOptionPane.ERROR_MESSAGE);
                AffichageClient.append("Connexion impossible ! Essayer a nouveau. \n");
                nomC.setEditable(true);
            }
            
            ecoute();
            
        } else if (estConnecté == true) {
            AffichageClient.append("Vous etes déja connecté. \n");
        }
       
    }//GEN-LAST:event_connexionActionPerformed

    private void portclient1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_portclient1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_portclient1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        if (!list.isSelectionEmpty()) {
            save();
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void SendFichierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SendFichierActionPerformed
        try {
            JFileChooser ch = new JFileChooser();
            int c = ch.showOpenDialog(this);
            if (c == JFileChooser.APPROVE_OPTION) {
                File f = ch.getSelectedFile();
                FileInputStream in = new FileInputStream(f);
                byte b[] = new byte[in.available()];
                in.read(b);
                Data data = new Data();
                data.setName(filename.getText().trim());
                data.setFile(b);
                writer.println(username + ":" + data.getName() + ":" + "Fichier"); 
                writer.println(username + ":" + data.getFile() + ":" + "Fichier"); 
                writer.flush();
                AffichageClient.append("Envoi du fichier effectué \n");
             }
        
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_SendFichierActionPerformed

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
            java.util.logging.Logger.getLogger(OneClient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(OneClient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(OneClient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(OneClient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new OneClient().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea AffichageClient;
    private javax.swing.JButton SendFichier;
    private javax.swing.JTextField adresse;
    private javax.swing.JButton connexion;
    private javax.swing.JButton envoyer;
    private javax.swing.JTextField filename;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JList<String> list;
    private javax.swing.JTextPane message;
    private javax.swing.JTextField nomC;
    private javax.swing.JTextField portclient1;
    // End of variables declaration//GEN-END:variables
}
