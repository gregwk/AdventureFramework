/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adventure;

/**
 *
 * @author alex
 */
public class AdventureGUI extends javax.swing.JFrame {

    private GameEngine engine;
    
    /**
     * Creates new form AdventureGUI
     */
    public AdventureGUI(GameEngine engineArg) {
        engine = engineArg;
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        inputField = new javax.swing.JTextField();
        moveButton = new javax.swing.JButton();
        scrollPane = new javax.swing.JScrollPane();
        outputPane = new javax.swing.JTextPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        inputField.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        inputField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inputFieldActionPerformed(evt);
            }
        });

        moveButton.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        moveButton.setText("Move");
        moveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                moveButtonActionPerformed(evt);
            }
        });

        scrollPane.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        outputPane.setEditable(false);
        outputPane.setContentType("text/html"); // NOI18N
        outputPane.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        scrollPane.setViewportView(outputPane);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(scrollPane)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(inputField, javax.swing.GroupLayout.DEFAULT_SIZE, 571, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(moveButton)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 434, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(inputField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(moveButton))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void moveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_moveButtonActionPerformed
        generalActionPerformed(evt);
    }//GEN-LAST:event_moveButtonActionPerformed

    private void inputFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inputFieldActionPerformed
        generalActionPerformed(evt);
    }//GEN-LAST:event_inputFieldActionPerformed

    private void generalActionPerformed(java.awt.event.ActionEvent evt) {
        String input = inputField.getText();
        String output = engine.processInput(input);
        // outputPane.setContentType("text/html");
        outputPane.setText(output);
        inputField.setText("");
        inputField.requestFocus();
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

        GameEngine mockEngine = new GameEngine () {
            
            @Override
            public void initializeGame() {
                // do nothing
            }

            @Override
            public String processInput(String input) {
                // echo input in HTML
                
                StringBuilder sb = new StringBuilder();
                sb.append("<b>" + input + "</b>");
                return sb.toString();
            }       
        };
        
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AdventureGUI(mockEngine).setVisible(true);
            }
        });
    }
      
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField inputField;
    private javax.swing.JButton moveButton;
    private javax.swing.JTextPane outputPane;
    private javax.swing.JScrollPane scrollPane;
    // End of variables declaration//GEN-END:variables
}
