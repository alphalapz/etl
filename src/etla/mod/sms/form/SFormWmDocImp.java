/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etla.mod.sms.form;

import com.mysql.jdbc.Statement;
import etla.mod.SModConsts;
import etla.mod.cfg.db.SDbConfig;
import etla.mod.etl.db.SEtlConsts;
import etla.mod.etl.db.SEtlProcess;
import etla.mod.sms.db.SDbErpDoc;
import etla.mod.sms.db.SSmsConsts;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Connection;
import sa.lib.SLibConsts;
import sa.lib.SLibUtils;
import sa.lib.db.SDbRegistry;
import sa.lib.gui.SGuiClient;
import sa.lib.gui.SGuiConsts;
import sa.lib.gui.SGuiUtils;
import sa.lib.gui.SGuiValidation;
import sa.lib.gui.bean.SBeanFormDialog;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import sa.lib.gui.SGuiSession;

/**
 *
 * @author Alfredo Perez
 */
public class SFormWmDocImp extends SBeanFormDialog implements ActionListener, ItemListener {

    private static Connection moConecctionOrigen = null;//SSmsUtils.openConnectionOrigen();
    private static Connection moConecctionDest = null;//SSmsUtils.openConnectionOrigen();
    private SDbRegistry moRegistry;
    /**
     * Creates new form SDialogEtl
     * @param client
     * @param title
     */
    public SFormWmDocImp(final SGuiClient client, final String title) {
        client.getSession().getDatabase().getConnection();
        SGuiSession session = client.getSession();
        SDbConfig config = (SDbConfig) session.getConfigSystem();
        session.getDatabase().getDbName();
        try {
            moConecctionOrigen = SEtlProcess.createConnection(SEtlConsts.DB_MYSQL,config.getSiieHost(), 
                config.getSiiePort(), 
                config.getSiieName(), 
                config.getSiieUser(), 
                config.getSiiePassword());
            moConecctionDest = client.getSession().getDatabase().getConnection();
        } catch (Exception ex) {
            Logger.getLogger(SFormWmDocImp.class.getName()).log(Level.SEVERE, null, ex);
        }
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

        bgExportMode = new javax.swing.ButtonGroup();
        bgUpdateMode = new javax.swing.ButtonGroup();
        bgInvoiceBatch = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();

        jPanel1.setLayout(new java.awt.BorderLayout(0, 5));

        jPanel13.setLayout(new java.awt.GridLayout(1, 2, 5, 0));

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Importar documentos:"));
        jPanel2.setLayout(new java.awt.GridLayout(5, 0, 0, 5));

        jLabel2.setText("Aceptar para importar documentos.");
        jPanel2.add(jLabel2);

        jPanel13.add(jPanel2);

        jPanel1.add(jPanel13, java.awt.BorderLayout.NORTH);

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup bgExportMode;
    private javax.swing.ButtonGroup bgInvoiceBatch;
    private javax.swing.ButtonGroup bgUpdateMode;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel2;
    // End of variables declaration//GEN-END:variables

    /*
     * Private methods
     */
    private void initComponentsCustom() {
        SGuiUtils.setWindowBounds(this, 240, 150);
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

    }

    @Override
    public void removeAllListeners() {

    }

    @Override
    public void reloadCatalogues() {

    }

    @Override
    public void setRegistry(SDbRegistry registry) throws Exception {
        moRegistry = (SDbErpDoc) registry;
        
        moRegistry.setCode(msTitle);
    }

    @Override
    public SDbRegistry getRegistry() throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public SGuiValidation validateForm() {
        SGuiValidation validation = moFields.validateFields();

        if (validation.isValid()) {}
        
        return validation;
    }

    @Override
    public void initForm() {
        try {

        } catch (Exception e) {
            SLibUtils.showException(this, e);
        } finally {
            addAllListeners();
        }
    }

    @Override
    public void actionSave() {

        if (jbSave.isEnabled()) {
            try {
                this.setCursor(new Cursor(Cursor.WAIT_CURSOR));

                miClient.showMsgBoxInformation(SLibConsts.MSG_PROCESS_FINISHED + " Se modificaron " + run()+ " registros");
                mnFormResult = SGuiConsts.FORM_RESULT_OK;

                dispose();
            } catch (Exception e) {
                SLibUtils.showException(this, e);
            } finally {
                this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
    }

    private static int run() throws SQLException {
        int cont = 0;
        Statement statementDest = null;
        Statement statementOri = null;
        statementDest = (Statement) moConecctionDest.createStatement();
        statementOri = (Statement) moConecctionOrigen.createStatement();
        ResultSet resulSetOri = null;
        ResultSet resulSetDest = null;
        String sqlOri;
        String sqlDes;
        String sqlTemp;
        String initial_date = "";
        String final_date = "";

        moConecctionDest.setAutoCommit(false);
        moConecctionOrigen.setAutoCommit(false);

        String erpCompany = "erp_universal";

        ArrayList<Object> listOfIds = new ArrayList<>();

        sqlTemp = "SELECT ts_siie_rep_1 FROM etla_com.c_cfg;";
        resulSetDest = statementDest.executeQuery(sqlTemp);
        if (resulSetDest.next()) {
            initial_date = (resulSetDest.getTimestamp("ts_siie_rep_1")).toString();
        }
        sqlTemp = "UPDATE etla_com.c_cfg SET ts_siie_rep_1 = ts_siie_rep_2;";
        statementDest.executeUpdate(sqlTemp);
        sqlTemp = "SELECT now() AS now FROM etla_com.c_cfg;";
        resulSetDest = statementDest.executeQuery(sqlTemp);
        if (resulSetDest.next()) {
            final_date = (resulSetDest.getTimestamp("now")).toString();
        }
        else{
            final_date = "2018/06/01 00:00:0";
        }

        sqlOri = "SELECT trn_dps.id_year, trn_dps.id_doc, trn_dps.ts_edit FROM " + erpCompany + ".trn_dps "
               + "WHERE NOT b_del AND fid_st_dps = " + SSmsConsts.ST_DPS_EMITTED + " AND trn_dps.dt >= '" + initial_date + "' AND (fid_cl_dps = " + SSmsConsts.DOC_TYPE_CN_NUM +" OR fid_cl_dps = "+ SSmsConsts.DOC_TYPE_INV_NUM + ");";

        String sqlDesTemp;
        resulSetOri = statementOri.executeQuery(sqlOri);

        // Recorrer TODOS los registros del ORIGEN
        while (resulSetOri.next()) {
            listOfIds.add(resulSetOri.getInt("id_year") + "-" + resulSetOri.getInt("id_doc"));

            // Buscar cada uno de los IDS del ORIGEN en el DESTINO.
            sqlDesTemp = "SELECT s_erp_doc.erp_year_id, s_erp_doc.erp_doc_id, s_erp_doc.doc_upd FROM etla_com.s_erp_doc "
                    + "WHERE s_erp_doc.erp_year_id = " + resulSetOri.getString("id_year") + " AND  s_erp_doc.erp_doc_id = " + resulSetOri.getString("id_doc");
            resulSetDest = statementDest.executeQuery(sqlDesTemp);
            if (resulSetDest.next()) {
//                 Si los valores DATE son iguales, todo OK
                if (resulSetDest.getTimestamp("doc_upd").toString().equals(resulSetOri.getTimestamp("ts_edit").toString())) {
                    //System.out.println("TODO OK");
                } else {// sino, si el DATE en el DESTINO es anterior al DATE del ORIGEN 
//                    if (resulSetDest.getTimestamp("doc_upd").before(resulSetOri.getTimestamp("ts_edit"))) {
                    if (sa.lib.SLibTimeUtils.isSameDatetime(resulSetDest.getTimestamp("doc_upd"), resulSetOri.getTimestamp("ts_edit"))) {
                        needUpdate(resulSetOri.getString("id_year"), resulSetOri.getString("id_doc"));
                        cont++;
                    } 
//                    else {
//                        If the row is afected by the destiny and the origin es OK
//                        System.out.println((char) 27 + "[31m" + "El registro con el ID :" + resulSetOri.getString("id_year") + " " + resulSetOri.getString("id_doc") + " fue modificado en el Destino, y no coincide con el origen.");
//                        needUpdate(resulSetOri.getString("id_year"), resulSetOri.getString("id_doc"));
//                    }
                }
            } else {
                // If the origin row need to add in destiny.
                // Requiere ser agrado el registro ya que no se encuentra en el ORIGEN mas no en el DESTINO
                needInsert(resulSetOri.getString("id_year"), resulSetOri.getString("id_doc"));
                cont++;
            }
        }
        
        sqlDes = "SELECT s_erp_doc.id_erp_doc as id, s_erp_doc.erp_year_id, s_erp_doc.erp_doc_id FROM etla_com.s_erp_doc WHERE NOT B_DEL AND s_erp_doc.doc_date >= '" + initial_date + "';";

        resulSetDest = statementDest.executeQuery(sqlDes);
        ArrayList<Object> id_list_des = new ArrayList<>();

        while (resulSetDest.next()) {
            id_list_des.add(resulSetDest.getInt("erp_year_id") + "-" + resulSetDest.getInt("erp_doc_id"));
        }
        resulSetDest.first();
        // Split id_yer - id_doc
        String[] idSplit = null;
        for (int i = 0; i < id_list_des.size(); i++) {
            if (listOfIds.contains(id_list_des.get(i))) {

            } else {
//                System.out.println("Sobra este id: " + id_list_des.get(i));
                idSplit = id_list_des.get(i).toString().split("-");
                needBDel(resulSetDest.getString("id"),idSplit[0], idSplit[1]);
                cont++;
            }
        }
        sqlTemp = "UPDATE etla_com.c_cfg SET ts_siie_rep_2 = '" + final_date + "';";
        statementDest.executeUpdate(sqlTemp);
        moConecctionOrigen.commit();
        moConecctionDest.commit();
        return cont;
    }

    private static void needUpdate(String id_year, String id_doc) throws SQLException {
        Statement statementDest = (Statement) moConecctionDest.createStatement();
        Statement statementOri = (Statement) moConecctionOrigen.createStatement();
        
        moConecctionDest.setAutoCommit(false);
        moConecctionOrigen.setAutoCommit(false);
        
        String newQuery, sql;
        ResultSet resultSetValoresOrigen = null;
        
        newQuery = "SELECT "
                + "dps.id_year, "
                + "dps.id_doc, "
                + "dps.dt, "
                + "dps.num_ser, "
                + "dps.num, "
                + "dps.fid_ct_dps, "
                + "dps.fid_cl_dps, "
                + "dps.ts_edit, "
                + "bp.id_bp, "
                + "bp.bp, "
                + "sum(ety.weight_gross) AS weight_gross, "
                + "dps.b_del, "
                + "dps.b_sys "
                + "FROM TRN_DPS_ETY AS ety "
                + "INNER JOIN TRN_DPS AS dps on ety.id_doc = dps.id_doc "
                + "INNER JOIN ERP.BPSU_BP AS BP ON dps.fid_bp_r = bp.id_bp "
                + "WHERE dps.id_year = " + id_year + " AND dps.id_doc = " + id_doc + " AND NOT dps.b_del AND NOT ety.b_del group by id_year;";

        resultSetValoresOrigen = statementOri.executeQuery(newQuery);

        // Variables for save the fields of the resultset
        int mnId_year = 0, mnId_doc = 0, mnFid_ct_dps = 0, mnFid_cl_dps = 0, mn_id_bp = 0;
        String msNum = "", msNum_Ser = "", msBp = "";
        double mdWeight = 0.0;
        boolean mbDel = false, mbSys = false;
        Date mdDt = null;
        Timestamp mtTsEdit = null;

        if (resultSetValoresOrigen.next()) {
            /**
             *
             */

            mnId_year = resultSetValoresOrigen.getInt("id_year");
            mnId_doc = resultSetValoresOrigen.getInt("id_doc");
            mdDt = resultSetValoresOrigen.getDate("dt");
            msNum_Ser = resultSetValoresOrigen.getString("num_ser");
            msNum = resultSetValoresOrigen.getString("num");
            mnFid_ct_dps = resultSetValoresOrigen.getInt("fid_cl_dps");
            mnFid_cl_dps = resultSetValoresOrigen.getInt("fid_ct_dps");
            mtTsEdit = resultSetValoresOrigen.getTimestamp("ts_edit");
            mn_id_bp = resultSetValoresOrigen.getInt("id_bp");
            msBp = resultSetValoresOrigen.getString("bp");
            mdWeight = resultSetValoresOrigen.getDouble("weight_gross");
            mbDel = resultSetValoresOrigen.getBoolean("b_del");
            mbSys = resultSetValoresOrigen.getBoolean("b_sys");
        }

         sql = "UPDATE etla_com.s_erp_doc SET "
                + "erp_year_id = " + mnId_year + ", "
                + "erp_doc_id = " + mnId_doc + ", "
                + "doc_date = '" + mdDt + "', "
                + "doc_ser = '" + msNum_Ser + "', "
                + "doc_num = " + msNum + ", "
                + "doc_type = '" + mnFid_cl_dps + "', "
                + "doc_class = '" + mnFid_ct_dps + "', "
                + "doc_upd = '" + mtTsEdit + "', "
                + "biz_partner_id = '" + mn_id_bp + "', "
                + "biz_partner = '" + msBp + "', "
                + "weight = " + mdWeight + ", "
                + "b_del = " + (mbDel ? "1" : "0") + ", "
                + "b_sys = " + (mbSys ? "1" : "0") + ", "
                // + "fk_usr_ins = " + 1 + ", "
                + "fk_usr_upd = " + 1 + ", "
                // + "ts_usr_ins = NOW(), "
                + "ts_usr_upd = NOW() "
                + "WHERE erp_year_id = " + id_year + " AND erp_doc_id = " + id_doc;

        int resultSet1 = statementDest.executeUpdate(sql);
        moConecctionOrigen.commit();
        moConecctionDest.commit();
        if (resultSet1 == 1) {
//            System.out.println("SE HA ACTUALIZADO DE MANERA CORRECTA EL ID: " + mnId_year + "" + mnId_doc);
        } else {
            System.out.println((char) 27 + "[31m" + "NO FUE POSIBLE ACCTUALIZAR EL ID:" + mnId_year + "" + mnId_doc); // mostrar mensaje al usuario! XXX
        }
    }

    private static void needBDel(String id, String id_year, String id_doc) throws SQLException {
        Statement statementDest = (Statement) moConecctionDest.createStatement();
        moConecctionDest.setAutoCommit(false);

        String sql = "";
        sql = "UPDATE etla_com.s_erp_doc SET b_del = 1 WHERE id_erp_doc = " + id + " AND erp_year_id = " + id_year + " AND erp_doc_id" + " = " + id_doc;
        boolean resultSet1 = statementDest.execute(sql);

        if (!resultSet1) {
//            System.out.println("SE HA CAMBIADO B_DEL DE MANERA CORRECTA PARA ID: " + id);
        } else {
            System.out.println((char) 27 + "[31m" + "NO SE HA CAMBIADO B_DEL DEL ID: " + id + " EN LA TABLA DE DESTINO");
        }

        // validacion las relaciones posiblemente creadas en la base de destino que hagan referencia al ID del registro elimanado //

        sql = "UPDATE etla_com.s_wm_ticket_link SET b_del = 1 WHERE fk_erp_doc = " + id + ";";

        resultSet1 = statementDest.execute(sql);
        if (!resultSet1) {
//            System.out.println("SE HA CAMBIADO B_DEL DE MANERA CORRECTA PARA ID: " + id + " para los documentos vinculados donde este participa");
        } else {
            System.out.println((char) 27 + "[31m" + "NO SE HA CAMBIADO B_DEL DEL ID: " + id + " EN LA TABLA DE DESTINO");        
        }
        moConecctionDest.commit();
    }

    private static void needInsert(String id_year, String id_doc) throws SQLException {
        Statement statementDest = (Statement) moConecctionDest.createStatement();
        Statement statementOri= (Statement) moConecctionOrigen.createStatement();
        moConecctionDest.setAutoCommit(false);
        moConecctionOrigen.setAutoCommit(false);
        String newQuery, sql;
        int nextId = 0;

        ResultSet rstNextId = statementDest.executeQuery("SELECT COALESCE(MAX(id_erp_doc), 0) + 1 FROM etla_com.s_erp_doc;");
        if (rstNextId.next()){
            nextId = rstNextId.getInt(1);
        }

        newQuery = "SELECT "
                + "dps.id_year, "
                + "dps.id_doc, "
                + "dps.dt, "
                + "dps.num_ser, "
                + "dps.num, "
                + "dps.fid_ct_dps, "
                + "dps.fid_cl_dps, "
                + "dps.ts_edit, "
                + "bp.id_bp, "
                + "bp.bp, "
                + "sum(ety.weight_gross) AS weight_gross, "
                + "dps.b_del, "
                + "dps.b_sys "
                + "FROM TRN_DPS_ETY AS ety "
                + "INNER JOIN TRN_DPS AS dps on ety.id_doc = dps.id_doc "
                + "INNER JOIN ERP.BPSU_BP AS BP ON fid_bp_r = id_bp "
                + "WHERE dps.id_year = " + id_year + " AND dps.id_doc = " + id_doc + " AND NOT dps.b_del AND NOT ety.b_del group by id_year;";

        ResultSet resultSetValoresOrigen = statementOri.executeQuery(newQuery);
        // Variables for save the fields of the resultset
        int mnId_year = 0, mnId_doc = 0, mn_id_bp = 0;
        String msNum = "", msNum_Ser = "", msBp = "", mnFid_cl_dps = "", mnFid_ct_dps = "";
        double mdWeight = 0.0;
        boolean mbDel = false, mbSys = false;
        Date mdDt = null;
        Timestamp mtTsEdit = null;

        if (resultSetValoresOrigen.next()) {
//            registry.setId_year(resultSetValoresOrigen.getInt("id_year"));
//            registry.setId_doc(resultSetValoresOrigen.getInt("id_doc"));
//            registry.setDt(resultSetValoresOrigen.getDate("dt"));
//            registry.setNum_Ser(resultSetValoresOrigen.getString("num_ser"));
//            registry.setNum(resultSetValoresOrigen.getString("num"));
//            registry.setFid_ct_dps(resultSetValoresOrigen.getInt("fid_ct_dps"));
//            registry.setFid_cl_dps(resultSetValoresOrigen.getInt("fid_cl_dps"));
//            registry.setTsEdit(resultSetValoresOrigen.getTimestamp("ts_edit"));
//            registry.setWeight(resultSetValoresOrigen.getDouble("weight_gross"));
//            registry.setDel(resultSetValoresOrigen.getBoolean("b_del"));
//            registry.setSys(resultSetValoresOrigen.getBoolean("b_sys"));
             
             
            mnId_year = resultSetValoresOrigen.getInt("id_year");
            mnId_doc = resultSetValoresOrigen.getInt("id_doc");
            mdDt = resultSetValoresOrigen.getDate("dt");
            msNum_Ser = resultSetValoresOrigen.getString("num_ser");
            msNum = resultSetValoresOrigen.getString("num");
            mnFid_ct_dps = resultSetValoresOrigen.getString("fid_ct_dps");
            mnFid_cl_dps = resultSetValoresOrigen.getString("fid_cl_dps");
            mtTsEdit = resultSetValoresOrigen.getTimestamp("ts_edit");
            mn_id_bp = resultSetValoresOrigen.getInt("id_bp");
            msBp = resultSetValoresOrigen.getString("bp");
            mdWeight = resultSetValoresOrigen.getDouble("weight_gross");
            mbDel = resultSetValoresOrigen.getBoolean("b_del");
            mbSys = resultSetValoresOrigen.getBoolean("b_sys");

            switch (mnFid_ct_dps){
                case "1"://INC
                    mnFid_ct_dps = "'INC'";
                    break;
                case "2": //EXP
                    mnFid_ct_dps = "'EXP'";
                    break;
                default:
                    break;
            }

            switch (mnFid_cl_dps){
                case "3": //INV
                    mnFid_cl_dps = "'INV'";
                    break;
                case "5": //NC
                    mnFid_cl_dps = "'NC'";
                    break;
                default:
                    break;
            }
        }

        sql = "INSERT INTO etla_com.s_erp_doc( "
                + "id_erp_doc, "
                + "erp_year_id, "
                + "erp_doc_id, "
                + "doc_date, "
                + "doc_ser, "
                + "doc_num, "
                + "doc_type, "
                + "doc_class, "
                + "doc_upd, "
                + "biz_partner_id, "
                + "biz_partner, "
                + "weight, "
                + "b_del, "
                + "b_sys, "
                + "fk_usr_ins, "
                + "fk_usr_upd, "
                + "ts_usr_ins, "
                + "ts_usr_upd"
                + ") "
                + "VALUES( "
                + nextId + ", "
                + "'" + mnId_year + "', "
                + mnId_doc + ", "
                + "'" + mdDt + "', "
                + "'" + msNum_Ser + "', "
                + "'" + msNum + "', "
                + mnFid_cl_dps  + ", "
                + mnFid_ct_dps+ ", "
                + "'" + mtTsEdit + "', "
                + "'" + mn_id_bp + "', "
                + "'" + msBp + "', "
                + "" + mdWeight + ", "
                + (mbDel ? "1" : "0") + ", "
                + (mbSys ? "1" : "0") + ", 1, 1, now(), now()"
                + ")";

        boolean resultSet1 = statementDest.execute(sql);
        if (!resultSet1) {
//            System.out.println("SE HA INSERTADO DE MANERA CORRECTA EL ID: " + nextId);
        } else {
            System.out.println((char) 27 + "[31m" + "NO FUE POSIBLE INSERTAR EL ID:" + nextId);
        }
        moConecctionOrigen.commit();
        moConecctionDest.commit();
    }

    private static Connection conectarDB2(String DB) {
        String url = "jdbc:mysql://localhost:3306/" + DB;
        String username = "root";
        String password = "msroot";
//        System.out.println("Connecting database...");
        Connection connection;
        try {
            connection = DriverManager.getConnection(url, username, password);
//            System.out.println("Database connected!");
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }
        return connection;
    }

    /**
     * Creates connection to external system's database.
     *
     * @param type Database type, 1 for MySql AND 2 for SQLServer (int)
     * @param host Database host (String).
     * @param port Database port (int).
     * @param dbName Database name (String).
     * @param user User name (String).
     * @param pswd User's password (String).
     * @return Database connection (Connection).
     * @throws Exception
     */
    public static Connection createConnection(final int type, final String host, final int port, final String dbName, final String user, final String pswd) throws Exception {
        Connection connection = null;

        switch (type) {
            case 1:
                Class.forName("com.mysql.jdbc.Driver").newInstance();
                connection = DriverManager.getConnection("jdbc:mysql://" + host + (port == 0 ? "" : ":" + port) + "/" + dbName + "?user=" + user + "&password=" + pswd);
                connection.createStatement().execute("SET AUTOCOMMIT=1");
                break;
            case 2:
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                connection = DriverManager.getConnection("jdbc:sqlserver://" + host + (port == 0 ? "" : ":" + port) + ";databaseName=" + dbName + ";user=" + user + ";password=" + pswd);
                break;
            default:
                throw new Exception(SLibConsts.ERR_MSG_OPTION_UNKNOWN);
        }

        return connection;
    }

    /*
     USE THIS EACH TIME NEED TO DELETE A REGISTRY
     */
    private void needPhysicalDelete(String destino, String id_column, int id) throws SQLException {
        Statement statementDest = null;
        boolean resultSet1 = statementDest.execute("DELETE FROM " + destino + " WHERE " + id_column + " = " + id);
        if (!resultSet1) {
//            System.out.println("SE HA ELIMINADO DE MANERA CORRECTA EL ID: " + id);
        } else {
            System.out.println((char) 27 + "[31m" + "NO SE HA ELIMINADO EL ID: " + id + " DE LA TABLA DE DESTINO");
        }
        //Pendiente validar las relaciones posiblemente creadas en la base de destino que hagan referencia al ID del registro elimanado
    }

    private SGuiSession getSession() {
        return miClient.getSession();
    }

}