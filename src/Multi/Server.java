/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Multi;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;

/**
 *
 * @author ferdio
 */
public class Server extends javax.swing.JFrame {

    /**
     * Creates new form Server
     */
    public Server() {
        initComponents();
    }

    int port;
    String sport;
    ArrayList clientOutputStreams;
    ArrayList<String> utilisateurs;
    boolean estActif = true;
    
    public class RunServer implements Runnable {

        @Override
        public void run() {
            clientOutputStreams = new ArrayList();
            utilisateurs = new ArrayList();
            sport = portserveur.getText();
            port = Integer.parseInt(sport);
            try {
                ServerSocket serverSock = new ServerSocket(port);
                while (estActif) {
                    Socket clientSock = serverSock.accept();
                    PrintWriter writer = new PrintWriter(clientSock.getOutputStream());
                    clientOutputStreams.add(writer);
                    new Thread(new TraitementClient(clientSock, writer)).start();
                    Affichage.append("Connection etablis. \n");
                }
            } catch (Exception ex) {}
        }
    }

    
 
    // Client 
    public class TraitementClient implements Runnable {
        BufferedReader reader;
        Socket sock;
        PrintWriter client;
        public TraitementClient(Socket clientSocket, PrintWriter user) {
            client = user;
            try {
                sock = clientSocket;
                InputStreamReader isReader = new InputStreamReader(sock.getInputStream());
                reader = new BufferedReader(isReader);
                
            } catch (Exception ex) {
                Affichage.append("Unexpected error... \n");
            }
         }
        
        @Override
        public void run() {
            String message, connect = "Connecter", chat = "Chat", file = "Fichier";
            String[] data;
            
            try {
                while ((message = reader.readLine()) != null){
                    Affichage.append("Reception d'un message: " + message + "\n");
                    data = message.split(":");
                    for (String token : data) {
                        Affichage.append(token + "\n");
                    }
                    if (data[2].equals(connect)){
                        parler((data[0] + ":" + data[1] + ":" + chat));
                        ajouter(data[0]);
                    } else if (data[2].equals(chat)) {
                        parler(message);
                    } else if (data[2].equals(file)){
                        parler(message);
                    } else {
                        Affichage.append("Pas de condition rencontrer. \n");
                    }
                }

            } catch (Exception ex) {
                ex.printStackTrace();
                clientOutputStreams.remove(client);
            }   
        }
        
    }


    
    public void ajouter(String data) {
        String message, add = ": :Connection", done = "Serveur: :ok", name = data;
        utilisateurs.add(name);
        list.append(name+"\n");
        String[] tempList = new String[(utilisateurs.size())];
        utilisateurs.toArray(tempList);

        for (String token : tempList) {
            message = (token + add);
            parler(message);
        }
        parler(done);
    }
    

    public void parler(String message) {
        Iterator it = clientOutputStreams.iterator();
        while (it.hasNext()) {
            try {
                PrintWriter writer = (PrintWriter) it.next();
                writer.println(message);
                Affichage.append("Envoyé: " + message + "\n");
                writer.flush();
                Affichage.setCaretPosition(Affichage.getDocument().getLength());
            } catch (Exception ex) {
                Affichage.append("Erreur. \n");
            }
        }
    }
    

    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        Affichage = new javax.swing.JTextArea();
        portserveur = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        list = new javax.swing.JTextArea();
        RunS = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(44, 65, 80));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "SERVER", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Linux Biolinum Keyboard O", 3, 18), new java.awt.Color(255, 255, 255))); // NOI18N

        Affichage.setEditable(false);
        Affichage.setColumns(20);
        Affichage.setRows(5);
        jScrollPane1.setViewportView(Affichage);

        portserveur.setText("2222");
        portserveur.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                portserveurActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Liberation Serif", 3, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Port :");

        list.setColumns(20);
        list.setRows(5);
        list.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Liste des utilisateurs", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP));
        jScrollPane3.setViewportView(list);

        RunS.setText("DEMARRER");
        RunS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RunSActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(81, 81, 81)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(RunS, javax.swing.GroupLayout.PREFERRED_SIZE, 355, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(29, 29, 29)
                                .addComponent(portserveur, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 99, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 509, Short.MAX_VALUE)
                    .addComponent(jScrollPane3))
                .addGap(35, 35, 35)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(portserveur, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addComponent(RunS)
                .addContainerGap(35, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void portserveurActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_portserveurActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_portserveurActionPerformed

    private void RunSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RunSActionPerformed
         new Thread(new RunServer()).start();
         Affichage.append("Serveur démarré \n");
         Affichage.append("En attente d'un client \n");
    }//GEN-LAST:event_RunSActionPerformed

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Server().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JTextArea Affichage;
    private javax.swing.JButton RunS;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextArea list;
    private javax.swing.JTextField portserveur;
    // End of variables declaration//GEN-END:variables
}
