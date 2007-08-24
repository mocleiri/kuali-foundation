package org.apache.ojb.tools.mapping.reversedb.gui;

/* Copyright 2002-2005 The Apache Software Foundation
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/**
 *
 * @author <a href="mailto:bfl@florianbruckner.com">Florian Bruckner</a> 
 * @version $Id: JDlgDBConnection.java,v 1.1 2007-08-24 22:17:32 ewestfal Exp $
 */
public class JDlgDBConnection extends javax.swing.JDialog {

    JFrmMainFrame mainFrame;
    /** Creates new form JDlgDBConnection */
    public JDlgDBConnection(java.awt.Frame parent, boolean modal, 
      JFrmMainFrame aMain) 
    {
        super(parent, modal);
        initComponents();
        mainFrame = aMain;
        this.tfJDBCDriver.setText(mainFrame.getProperty(JFrmMainFrame.JDBC_DRIVER, ""));
        this.tfJDBCURL.setText(mainFrame.getProperty(JFrmMainFrame.JDBC_URL, ""));
        this.tfUsername.setText(mainFrame.getProperty(JFrmMainFrame.JDBC_USER, ""));
        this.tfPassword.setText(mainFrame.getProperty(JFrmMainFrame.JDBC_PASSWORD, ""));
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    private void initComponents()//GEN-BEGIN:initComponents
    {
        java.awt.GridBagConstraints gridBagConstraints;

        lblJDBCDriver = new javax.swing.JLabel();
        tfJDBCDriver = new javax.swing.JTextField();
        lblJDBCURL = new javax.swing.JLabel();
        tfJDBCURL = new javax.swing.JTextField();
        lblUsername = new javax.swing.JLabel();
        tfUsername = new javax.swing.JTextField();
        lblPassword = new javax.swing.JLabel();
        tfPassword = new javax.swing.JPasswordField();
        jPanel1 = new javax.swing.JPanel();
        pbCancel = new javax.swing.JButton();
        pbConnect = new javax.swing.JButton();

        getContentPane().setLayout(new java.awt.GridBagLayout());

        addWindowListener(new java.awt.event.WindowAdapter()
        {
            public void windowClosing(java.awt.event.WindowEvent evt)
            {
                closeDialog(evt);
            }
        });

        lblJDBCDriver.setText("JDBC Driver Class:");
        lblJDBCDriver.setLabelFor(tfJDBCDriver);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        getContentPane().add(lblJDBCDriver, gridBagConstraints);

        tfJDBCDriver.setText("jTextField1");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        getContentPane().add(tfJDBCDriver, gridBagConstraints);

        lblJDBCURL.setText("JDBC URL:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        getContentPane().add(lblJDBCURL, gridBagConstraints);

        tfJDBCURL.setColumns(40);
        tfJDBCURL.setText("jTextField2");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        getContentPane().add(tfJDBCURL, gridBagConstraints);

        lblUsername.setText("Username:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        getContentPane().add(lblUsername, gridBagConstraints);

        tfUsername.setText("jTextField3");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        getContentPane().add(tfUsername, gridBagConstraints);

        lblPassword.setText("Password:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        getContentPane().add(lblPassword, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        getContentPane().add(tfPassword, gridBagConstraints);

        pbCancel.setText("Cancel");
        pbCancel.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                pbCancelActionPerformed(evt);
            }
        });

        jPanel1.add(pbCancel);

        pbConnect.setText("Connect");
        pbConnect.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                pbConnectActionPerformed(evt);
            }
        });

        jPanel1.add(pbConnect);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        getContentPane().add(jPanel1, gridBagConstraints);

        pack();
    }//GEN-END:initComponents

  private void pbCancelActionPerformed (java.awt.event.ActionEvent evt)//GEN-FIRST:event_pbCancelActionPerformed
  {//GEN-HEADEREND:event_pbCancelActionPerformed
    // Add your handling code here:
    dispose();
  }//GEN-LAST:event_pbCancelActionPerformed

    private void pbConnectActionPerformed (java.awt.event.ActionEvent evt)//GEN-FIRST:event_pbConnectActionPerformed
    {//GEN-HEADEREND:event_pbConnectActionPerformed
      // Add your handling code here:
      mainFrame.setProperty(JFrmMainFrame.JDBC_DRIVER, tfJDBCDriver.getText());
      mainFrame.setProperty(JFrmMainFrame.JDBC_URL, tfJDBCURL.getText());
      mainFrame.setProperty(JFrmMainFrame.JDBC_USER, tfUsername.getText());
      mainFrame.setProperty(JFrmMainFrame.JDBC_PASSWORD, new String(this.tfPassword.getPassword()));      
      new org.apache.ojb.tools.mapping.reversedb.gui.actions.DBConnectAction(mainFrame).actionPerformed(null);
      dispose();
    }//GEN-LAST:event_pbConnectActionPerformed
    
    /** Closes the dialog */
    private void closeDialog(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_closeDialog
        setVisible(false);
        dispose();
    }//GEN-LAST:event_closeDialog
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        // new JDlgDBConnection(new javax.swing.JFrame(), true).show();
    }
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lblJDBCURL;
    private javax.swing.JLabel lblJDBCDriver;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField tfJDBCDriver;
    private javax.swing.JLabel lblPassword;
    private javax.swing.JPasswordField tfPassword;
    private javax.swing.JLabel lblUsername;
    private javax.swing.JTextField tfUsername;
    private javax.swing.JButton pbConnect;
    private javax.swing.JButton pbCancel;
    private javax.swing.JTextField tfJDBCURL;
    // End of variables declaration//GEN-END:variables
    
}

/***************************** Changelog *****************************
// $Log: not supported by cvs2svn $
// Revision 1.1.2.1  2005/12/21 22:32:02  tomdz
// Updated license
//
// Revision 1.1  2004/05/05 16:38:49  arminw
// fix fault
// wrong package structure used:
// org.apache.ojb.tools.reversdb
// org.apache.ojb.tools.reversdb2
//
// instead of
// org.apache.ojb.tools.mapping.reversdb
// org.apache.ojb.tools.mapping.reversdb2
//
// Revision 1.1  2004/05/04 13:44:59  arminw
// move reverseDB stuff
//
// Revision 1.6  2004/04/05 12:16:23  tomdz
// Fixed/updated license in files leftover from automatic license transition
//
// Revision 1.5  2004/04/04 23:53:42  brianm
// Fixed initial copyright dates to match cvs repository
//
// Revision 1.4  2004/03/11 18:16:22  brianm
// ASL 2.0
//
// Revision 1.3  2002/06/18 12:26:41  florianbruckner
// changes in Netbeans Form definitions after move to jakarta.
//
// Revision 1.2  2002/06/17 19:34:34  jvanzyl
// Correcting all the package references.
// PR:
// Obtained from:
// Submitted by:
// Reviewed by:
//
// Revision 1.1.1.1  2002/06/17 18:16:54  jvanzyl
// Initial OJB import
//
// Revision 1.2  2002/05/16 11:47:09  florianbruckner
// fix CR/LF issue, change license to ASL
//
// Revision 1.1  2002/04/18 11:44:16  mpoeschl
//
// move files to new location
//
// Revision 1.3  2002/04/07 09:05:17  thma
// *** empty log message ***
//
// Revision 1.2  2002/03/11 17:36:26  florianbruckner
// fix line break issue for these files that were previously checked in with -kb
//
// Revision 1.1  2002/03/04 17:19:32  thma
// initial checking for Florians Reverse engineering tool
//
// Revision 1.1.1.1  2002/02/20 13:35:25  Administrator
// initial import
//
/***************************** Changelog *****************************/
