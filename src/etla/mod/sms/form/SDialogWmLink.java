/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etla.mod.sms.form;

import com.mysql.jdbc.Statement;
import etla.mod.SModConsts;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Connection;
import javax.swing.JOptionPane;
import sa.lib.SLibConsts;
import sa.lib.SLibUtils;
import sa.lib.db.SDbRegistry;
import sa.lib.gui.SGuiClient;
import sa.lib.gui.SGuiConsts;
import sa.lib.gui.SGuiUtils;
import sa.lib.gui.SGuiValidation;
import sa.lib.gui.bean.SBeanFormDialog;
import etla.mod.sms.db.SSmsUtils2;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import sa.lib.db.SDbConsts;

/**
 *
 * @author Alfredo Perez
 */
public class SDialogWmLink extends SBeanFormDialog implements ActionListener, ItemListener {

    private static Connection moConecctionDest = SSmsUtils2.openConnectionDestino();
    

    /**
     * Creates new form SDialogEtl
     *
     * @param client
     * @param title
     * @param type
     */
    public SDialogWmLink(SGuiClient client, String title, int type) {
        setFormSettings(client, SGuiConsts.BEAN_FORM_EDIT, SModConsts.AX_ETL, SLibConsts.UNDEFINED, title);
        initComponents();
        initComponentsCustom();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jlTicket = new javax.swing.JLabel();
        sBeanFieldTextTicket = new sa.lib.gui.bean.SBeanFieldText();
        jPanel11 = new javax.swing.JPanel();
        jButtonSearchTicket = new javax.swing.JButton();
        sBeanTotalTicket = new sa.lib.gui.bean.SBeanFieldText();
        sBeanAvailableTicket = new sa.lib.gui.bean.SBeanFieldText();
        jPanel4 = new javax.swing.JPanel();
        jlDocument = new javax.swing.JLabel();
        sBeanFieldTextDocument = new sa.lib.gui.bean.SBeanFieldText();
        jButtonSearchDoc = new javax.swing.JButton();
        sBeanTotalDoc = new sa.lib.gui.bean.SBeanFieldText();
        sBeanAvailableDoc = new sa.lib.gui.bean.SBeanFieldText();
        jPanel5 = new javax.swing.JPanel();
        jlWeight = new javax.swing.JLabel();
        sBeanFieldDecimalWeight = new sa.lib.gui.bean.SBeanFieldDecimal();
        jPanel6 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jlDatePeriodStart1 = new javax.swing.JLabel();
        sBeanFieldTextNote = new sa.lib.gui.bean.SBeanFieldText();
        jPanel10 = new javax.swing.JPanel();

        getContentPane().setLayout(new java.awt.BorderLayout(2, 0));

        jPanel1.setLayout(new java.awt.BorderLayout(0, 2));

        jPanel13.setLayout(new java.awt.GridLayout(2, 2, 5, 0));

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Fecha de importación:"));
        jPanel2.setLayout(new java.awt.GridLayout(5, 0, 0, 5));

        jPanel3.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 0));

        jlTicket.setText("Boleto:*");
        jlTicket.setPreferredSize(new java.awt.Dimension(100, 23));
        jPanel3.add(jlTicket);
        jPanel3.add(sBeanFieldTextTicket);

        jPanel11.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 0));
        jPanel3.add(jPanel11);

        jButtonSearchTicket.setText("Buscar Boleto");
        jButtonSearchTicket.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSearchTicketActionPerformed(evt);
            }
        });
        jPanel3.add(jButtonSearchTicket);

        sBeanTotalTicket.setText("TOTAL");
        jPanel3.add(sBeanTotalTicket);

        sBeanAvailableTicket.setText("DISPONIBLE");
        jPanel3.add(sBeanAvailableTicket);

        jPanel2.add(jPanel3);

        jPanel4.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 0));

        jlDocument.setText("Documento:*");
        jlDocument.setPreferredSize(new java.awt.Dimension(100, 23));
        jPanel4.add(jlDocument);
        jPanel4.add(sBeanFieldTextDocument);

        jButtonSearchDoc.setText("Buscar Documento");
        jButtonSearchDoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSearchDocActionPerformed(evt);
            }
        });
        jPanel4.add(jButtonSearchDoc);

        sBeanTotalDoc.setText("TOTAL");
        jPanel4.add(sBeanTotalDoc);

        sBeanAvailableDoc.setText("DISPONIBLE");
        jPanel4.add(sBeanAvailableDoc);

        jPanel2.add(jPanel4);

        jPanel5.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 0));

        jlWeight.setText("Peso:*");
        jlWeight.setPreferredSize(new java.awt.Dimension(100, 23));
        jPanel5.add(jlWeight);
        jPanel5.add(sBeanFieldDecimalWeight);

        jPanel6.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 0));
        jPanel5.add(jPanel6);

        jPanel7.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 0));

        jPanel8.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 0));
        jPanel7.add(jPanel8);

        jPanel5.add(jPanel7);

        jPanel2.add(jPanel5);

        jPanel9.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 0));

        jlDatePeriodStart1.setText("Nota");
        jlDatePeriodStart1.setPreferredSize(new java.awt.Dimension(100, 23));
        jPanel9.add(jlDatePeriodStart1);
        jPanel9.add(sBeanFieldTextNote);

        jPanel10.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 0));
        jPanel9.add(jPanel10);

        jPanel2.add(jPanel9);

        jPanel13.add(jPanel2);

        jPanel1.add(jPanel13, java.awt.BorderLayout.NORTH);

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonSearchDocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSearchDocActionPerformed
        try {
            searchDoc();
        } catch (Exception ex) {
            Logger.getLogger(SFormLink.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButtonSearchDocActionPerformed

    private void jButtonSearchTicketActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSearchTicketActionPerformed
        try {
            searchTicket();
        } catch (Exception ex) {
            Logger.getLogger(SFormLink.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButtonSearchTicketActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonSearchDoc;
    private javax.swing.JButton jButtonSearchTicket;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JLabel jlDatePeriodStart1;
    private javax.swing.JLabel jlDocument;
    private javax.swing.JLabel jlTicket;
    private javax.swing.JLabel jlWeight;
    private sa.lib.gui.bean.SBeanFieldText sBeanAvailableDoc;
    private sa.lib.gui.bean.SBeanFieldText sBeanAvailableTicket;
    private sa.lib.gui.bean.SBeanFieldDecimal sBeanFieldDecimalWeight;
    private sa.lib.gui.bean.SBeanFieldText sBeanFieldTextDocument;
    private sa.lib.gui.bean.SBeanFieldText sBeanFieldTextNote;
    private sa.lib.gui.bean.SBeanFieldText sBeanFieldTextTicket;
    private sa.lib.gui.bean.SBeanFieldText sBeanTotalDoc;
    private sa.lib.gui.bean.SBeanFieldText sBeanTotalTicket;
    // End of variables declaration//GEN-END:variables
    private boolean mbFoundDoc;
    private boolean mbFoundTicket;

    /*
     * Private methods
     */
    private void initComponentsCustom() {
        com.mysql.jdbc.Statement statementDest = null;
        try {
            statementDest = (com.mysql.jdbc.Statement) moConecctionDest.createStatement();
        } catch (Exception e) {
            SLibUtils.showException(this, e);
        }

        SGuiUtils.setWindowBounds(this, 640, 400);

        moFields.setFormButton(jbSave);

        jbSave.setText(SGuiConsts.TXT_BTN_OK);

    }

    /*
     * Public methods
     */
    
    /*
     * Overriden methods
     */
    @Override
    public void addAllListeners() {
        this.sBeanFieldDecimalWeight.addActionListener(this);
        this.sBeanFieldTextDocument.addActionListener(this);
        this.sBeanFieldTextNote.addActionListener(this);
        this.sBeanFieldTextTicket.addActionListener(this);
        this.jButtonSearchDoc.addActionListener(this);
        this.jButtonSearchTicket.addActionListener(this);
    }

    @Override
    public void removeAllListeners() {
        this.sBeanFieldDecimalWeight.removeActionListener(this);
        this.sBeanFieldTextDocument.removeActionListener(this);
        this.sBeanFieldTextNote.removeActionListener(this);
        this.sBeanFieldTextTicket.removeActionListener(this);
        this.jButtonSearchDoc.removeActionListener(this);
        this.jButtonSearchTicket.removeActionListener(this);
    }

    @Override
    public void reloadCatalogues() {

    }

    @Override
    public void setRegistry(SDbRegistry registry) throws Exception {

    }

    @Override
    public SDbRegistry getRegistry() throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public SGuiValidation validateForm() {
        SGuiValidation validation = moFields.validateFields();

        if (validation.isValid()) {
            if (mbFoundDoc && mbFoundTicket) {

            } else {
                validation.setMessage("ERROR!");
            }
        }

        return validation;
    }

    @Override
    public void initForm() {

        sBeanFieldTextTicket.setValue("");
        sBeanFieldTextDocument.setValue("");
        sBeanFieldDecimalWeight.setValue("0.0");
        sBeanFieldTextNote.setValue("");
        mbFoundDoc = false;
        mbFoundTicket = false;
        addAllListeners();

    }
    
    private int idTicket;
    @Override
    public void actionSave() {
        String msg = "¿Vincular?...";

        if (jbSave.isEnabled()) {
            if (miClient.showMsgBoxConfirm(msg) == JOptionPane.YES_OPTION) {
                try {
                    this.setCursor(new Cursor(Cursor.WAIT_CURSOR));

                    saveRegistry(sBeanFieldTextTicket.getText(), idTicket);

                    miClient.showMsgBoxInformation(SLibConsts.MSG_PROCESS_FINISHED);
                    mnFormResult = SGuiConsts.FORM_RESULT_OK;

                    dispose();
                } catch (Exception e) {
                    SLibUtils.showException(this, e);
                } finally {
                    this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                }
            }
        }
    }

    private void searchDoc() throws SQLException, Exception {
        String document, sql;
        document = sBeanFieldTextDocument.getValue();
        sql = "SELECT weight FROM s_erp_doc WHERE id_erp_doc = '" + document + "' AND NOT b_del";
        Statement statementDest = (Statement) moConecctionDest.createStatement();
        ResultSet resultSet1 = statementDest.executeQuery(sql);
        int  typeDocument = 1;

        if (resultSet1.next()) {
            mbFoundTicket = true;
            sBeanTotalDoc.setText((resultSet1.getString("weight")));

            Double pesoTotal = (resultSet1.getDouble("weight")) - Double.parseDouble(getAvailableWeight(typeDocument, document)) ;

            sBeanAvailableDoc.setText(pesoTotal.toString());
            if (Double.parseDouble(resultSet1.getString("weight")) < sBeanFieldDecimalWeight.getValue()) {
                System.out.println("EL PESO ASIGNADO POR EL USUARIO ES MAYOR QUE EL PESAJE MARCADO EN EL BOLETO"); // mostrar mensaje al usuario! XXX
            }
            System.out.println("Documento " + document + " encontrado."); // mostrar mensaje al usuario! XXX
        } else {
            System.out.println("NO FUE POSIBLE ENCONTRAR EL DOCUMENTO: " + document); // mostrar mensaje al usuario! XXX
        }
    }

    private void searchTicket() throws SQLException, Exception {
        String msTicket, sql;
        
        msTicket = sBeanFieldTextTicket.getValue();
        sql = "SELECT id_wm_ticket,ticket_id, weight FROM etla_com.s_wm_ticket WHERE ticket_id = " + msTicket + " AND NOT b_del";
        Statement statementDest = (Statement) moConecctionDest.createStatement();
        ResultSet resultSet1 = statementDest.executeQuery(sql);
        if (resultSet1.next()) {
            mbFoundTicket = true;
            sBeanTotalTicket.setText((resultSet1.getString("weight")));
            idTicket = resultSet1.getInt("id_wm_ticket");
//            Double pesoTotal = (resultSet1.getDouble("weight")) - Double.parseDouble(getAvailableWeight(SModSysConsts.SU_WM_TP_DOC, resultSet1.getString("id_wm_ticket")));
            Double pesoTotal = (resultSet1.getDouble("weight")) - Double.parseDouble(getAvailableWeight(1, resultSet1.getString("id_wm_ticket")));

            sBeanAvailableTicket.setText(pesoTotal.toString());
            
            if (Double.parseDouble(resultSet1.getString("weight")) < sBeanFieldDecimalWeight.getValue()) {
                System.out.println("EL PESO ASIGNADO POR EL USUARIO ES MAYOR QUE EL PESAJE MARCADO EN EL BOLETO"); // mostrar mensaje al usuario! XXX
            }
            System.out.println("Boleto " + msTicket + " encontrado."); // mostrar mensaje al usuario! XXX
        } else {
            System.out.println("NO FUE POSIBLE ENCONTRAR EL BOLETO: " + msTicket); // mostrar mensaje al usuario! XXX
        }
    }

    /**
     *
     * @param type DOCUMENTO(1) O BOLETO(2)
     * @param id
     * @return AvailableWeight for Documento or Ticket
     */
    private String getAvailableWeight(int type, String id) throws Exception {
        String sql = "";
        String field = "";
        switch (type) {
//            case SModSysConsts.SU_WM_TP_TIC:
            case 1:
                field = "fk_wm_ticket";
                break;
            case 2:
//            case SModSysConsts.SU_WM_TP_DOC:
                field = "fk_erp_doc";
                break;
            default:
                break;
        }
        try {
            sql = "SELECT SUM(weight_link) FROM etla_com.s_wm_ticket_link WHERE " + field + " = " + id + " AND NOT b_del";
            Statement statementDest = (Statement) moConecctionDest.createStatement();
            ResultSet resultSet1 = statementDest.executeQuery(sql);
            if (resultSet1.next()) {
                return resultSet1.getString(1);
            } else {
                throw new Exception("");
            }
        } catch (Exception e) {
            throw new Exception("");
        }
    }

    private void saveRegistry(String id_ticket, int ticket) throws SQLException, Exception {
        Statement statementDest = (Statement) moConecctionDest.createStatement();

        String sql;

        double mnPkWmTicketLinkId;
        String msNotes;
        boolean mbApproved;
        boolean mbAutoApproved;
        boolean mbDeleted;
        boolean mbSystem;
        String msFkWmTicketId;
        String mnFkErpDocId;
        int mnFkWmLinkStatusId;
        int mnFkUserLinkStatusId;
        int mnFkUserInsertId;
        int mnFkUserUpdateId;

        mnPkWmTicketLinkId = Double.parseDouble(id_ticket);

        msNotes = sBeanFieldTextNote.getValue();
        mbApproved = false;
        mbAutoApproved = false;
        mbDeleted = false;
        mbSystem = false;

        msFkWmTicketId = ticket + "";
        mnFkErpDocId = sBeanFieldTextDocument.getValue();
        mnFkWmLinkStatusId = 1;
        mnFkUserLinkStatusId = 1;
        mnFkUserInsertId = miClient.getSession().getUser().getPkUserId();
        mnFkUserUpdateId = miClient.getSession().getUser().getPkUserId();

        int nextNumber = 0;

        String sqlTemp = "SELECT (id_wm_ticket_link + 1) AS num FROM s_wm_ticket_link ORDER BY id_wm_ticket_link DESC LIMIT 1;";
        ResultSet resultSet1 = statementDest.executeQuery(sqlTemp);
        if (resultSet1.next()) {
            nextNumber = resultSet1.getInt(1);
        } else {
            throw new Exception(sa.lib.SLibConsts.ERR_MSG_UNKNOWN);
        }

        sql = "INSERT INTO S_WM_TICKET_LINK ("
                + "id_wm_ticket_link, "
                + "weight_link, "
                + "notes, "
                + "b_appd, "
                + "b_aut_appd, "
                + "b_del, "
                + "b_sys, "
                + "fk_wm_ticket, "
                + "fk_erp_doc, "
                + "fk_wm_link_st, "
                + "fk_usr_link_st,"
                + "fk_usr_ins, "
                + "fk_usr_upd, "
                + "ts_usr_link_st, "
                + "ts_usr_ins, "
                + "ts_usr_upd) "
                + "VALUES ("
                + nextNumber + ", "
                + sBeanFieldDecimalWeight.getValue() + ", "
                + "'" +msNotes + "', "
                + mbApproved + ", "
                + mbAutoApproved + ", "
                + mbDeleted + ", "
                + mbSystem + ", "
                + msFkWmTicketId + ", "
                + mnFkErpDocId + ", "
                + mnFkWmLinkStatusId + ", "
                + mnFkUserLinkStatusId + ", "
                + mnFkUserInsertId + ", "
                + mnFkUserUpdateId + ", "
                + "now(), "
                + "now(), "
                + "now()"
                + ")";

        int resultSet2;

        resultSet2 = statementDest.executeUpdate(sql);

        if (resultSet2 == 1) {
            System.out.println(sa.lib.SLibConsts.MSG_PROCESS_FINISHED); // mostrar mensaje al usuario! XXX
        } else {
            throw new Exception(sa.lib.SLibConsts.ERR_MSG_UNKNOWN + "NO FUE POSIBLE ACCTUALIZAR EL ID:" + mnPkWmTicketLinkId);
        }

    }

    /**
     * Creates connection to external system's database.
     *
     * @param type Database type, 1 for MySql AND 2 for SQLServer (int)
     * @param host Database host (String).
     * @param port Database port (int).
     * @param name Database name (String).
     * @param user User name (String).
     * @param pswd User's password (String).
     * @return Database connection (Connection).
     * @throws Exception
     */
    public static Connection createConnection(final int type, final String host, final int port, final String name, final String user, final String pswd) throws Exception {
        Connection connection = null;

        switch (type) {
            
            case SDbConsts.DBMS_MYSQL:
                Class.forName("com.mysql.jdbc.Driver").newInstance();
                connection = DriverManager.getConnection("jdbc:mysql://" + host + (port == 0 ? "" : ":" + port) + "/" + name + "?user=" + user + "&password=" + pswd);
                connection.createStatement().execute("SET AUTOCOMMIT=1");
                break;
            case SDbConsts.DBMS_SQL_SERVER_2005:
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                connection = DriverManager.getConnection("jdbc:sqlserver://" + host + (port == 0 ? "" : ":" + port) + ";databaseName=" + name + ";user=" + user + ";password=" + pswd);
                break;
            default:
                throw new Exception(SLibConsts.ERR_MSG_OPTION_UNKNOWN);
        }

        return connection;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
