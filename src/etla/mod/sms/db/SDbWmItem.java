/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etla.mod.sms.db;

import etla.mod.SModConsts;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import sa.gui.util.SUtilConsts;
import sa.lib.db.SDbConsts;
import sa.lib.db.SDbRegistryUser;
import sa.lib.gui.SGuiSession;

/**
 *
 * @author Alfredo Pérez
 */
public class SDbWmItem extends SDbRegistryUser {

    protected int mnPkWmItemId;
    protected String msProductId;
    protected String msCode;
    protected String msName;
    protected boolean mbDeleted;
    protected boolean mbSystem;
    protected int mnFkUserInsertId;
    protected int mnFkUserUpdateId;
    protected Date mtTsUserInsert;
    protected Date mtTsUserUpdate;

    /*
     *Mail
     */

//    protected String msamn_box;
//    protected String msumn_box;
//    protected boolean mbb_amn;
//    protected boolean mbb_umn;
//    protected boolean mbb_umn_owm;

    public SDbWmItem () {
        super(SModConsts.SU_WM_ITEM);
    }

    /*
     * Public methods
     */

    public void setPkWmItemId(int n) { mnPkWmItemId = n; }
    public void setProductId(String s) { msProductId = s; }
    public void setCode(String s) { msCode = s; }
    public void setName(String s) { msName = s; }
    public void setDeleted(boolean b) { mbDeleted = b; }
    public void setSystem(boolean b) { mbSystem = b; }
    public void setFkUserInsertId(int n) { mnFkUserInsertId = n; }
    public void setFkUserUpdateId(int n) { mnFkUserUpdateId = n; }
    public void setTsUserInsert(Date t) { mtTsUserInsert = t; }
    public void setTsUserUpdate(Date t) { mtTsUserUpdate = t; }
    /*
     *Mail
     */
//    public void setMsamnBox(String s) { msamn_box = s;}
//    public void setMsumnBox(String s) { msumn_box = s;}
//    public void setMbbamn(boolean b) { mbb_amn = b; }
//    public void setMbbumn(boolean b) { mbb_umn = b; }
//    public void setMbbUmnOwn(boolean b) { mbb_umn_owm = b; }

    public int getPkWmItemId() { return mnPkWmItemId; }
    public String getProductId() { return msProductId; }
    public String getCode() { return msCode; }
    public String getName() { return msName; }
    public boolean isDeleted() { return mbDeleted; }
    public boolean isSystem() { return mbSystem; }
    public int getFkUserInsertId() { return mnFkUserInsertId; }
    public int getFkUserUpdateId() { return mnFkUserUpdateId; }
    public Date getTsUserInsert() { return mtTsUserInsert; }
    public Date getTsUserUpdate() { return mtTsUserUpdate; }
    /*
     *Mail
     */
//    public String getMsamnBox() { return msamn_box;}
//    public String getMsumnBox() { return msumn_box;}
//    public boolean getMbbamn() { return mbb_amn; }
//    public boolean getMbbumn() { return mbb_umn; }
//    public boolean getMbbUmnOwn() { return mbb_umn_owm; }

    /*
     * Overriden methods
     */

    @Override
    public void setPrimaryKey(int[] pk) {
        mnPkWmItemId = pk[0];
    }

    @Override
    public int[] getPrimaryKey() {
        return new int[] { mnPkWmItemId };
    }

    @Override
    public void initRegistry() {

        initBaseRegistry();

        mnPkWmItemId = 0;
        msProductId = "";
        msCode = "";
        msName = "";
        mbDeleted = false;
        mbSystem = false;
        mnFkUserInsertId = 0;
        mnFkUserUpdateId = 0;
        mtTsUserInsert = null;
        mtTsUserUpdate = null;
        /**
          * mail
          **/
//        msamn_box = "";
//        msumn_box = "";
//        mbb_amn = false;
//        mbb_umn = false;
//        mbb_umn_owm = false;

    }

    @Override
    public String getSqlTable() {
        return SModConsts.TablesMap.get(mnRegistryType);
    }

    @Override
    public String getSqlWhere() {
        return "WHERE id_wm_item = " + mnPkWmItemId + " ";
    }

    @Override
    public String getSqlWhere(int[] pk) {
        return "WHERE id_wm_item = " + pk[0] + " ";
    }

    @Override
    public void computePrimaryKey(SGuiSession session) throws SQLException, Exception {
        ResultSet resultSet = null;

        mnPkWmItemId = 0;

        msSql = "SELECT COALESCE(MAX(id_wm_item), 0) + 1 FROM " + getSqlTable() + " ";
        resultSet = session.getStatement().executeQuery(msSql);
        if (resultSet.next()) {
            mnPkWmItemId = resultSet.getInt(1);
        }
    }

    @Override
    public void read(SGuiSession session, int[] pk) throws SQLException, Exception {
        ResultSet resultSet = null;

        initRegistry();
        initQueryMembers();
        mnQueryResultId = SDbConsts.READ_ERROR;

        msSql = "SELECT * " + getSqlFromWhere(pk);
        resultSet = session.getStatement().executeQuery(msSql);
        if (!resultSet.next()) {
            throw new Exception(SDbConsts.ERR_MSG_REG_NOT_FOUND);
        }
        else {
            mnPkWmItemId = resultSet.getInt("id_wm_item");
            msProductId = resultSet.getString("prod_id");
            msCode = resultSet.getString("code");
            msName = resultSet.getString("name");
            mbDeleted = resultSet.getBoolean("b_del");
            mbSystem = resultSet.getBoolean("b_sys");
            mnFkUserInsertId = resultSet.getInt("fk_usr_ins");
            mnFkUserUpdateId = resultSet.getInt("fk_usr_upd");
            mtTsUserInsert = resultSet.getTimestamp("ts_usr_ins");
            mtTsUserUpdate = resultSet.getTimestamp("ts_usr_upd");
            /**
             * mail
             */
//            msamn_box = resultSet.getString("amn_box");
//            msumn_box = resultSet.getString("umn_box");
//            mbb_amn = resultSet.getBoolean("b_amn");
//            mbb_umn = resultSet.getBoolean("b_umn");
//            mbb_umn_owm = resultSet.getBoolean("b_umn_owm");

            mbRegistryNew = false;
        }

        mnQueryResultId = SDbConsts.READ_OK;
    }

    @Override
    public void save(SGuiSession session) throws SQLException, Exception {       
        initQueryMembers();
        mnQueryResultId = SDbConsts.READ_ERROR;

        if (mbRegistryNew) {
            computePrimaryKey(session);
            mbDeleted = false;
            mbSystem = false;
            mnFkUserInsertId = session.getUser().getPkUserId();
            mnFkUserUpdateId = SUtilConsts.USR_NA_ID;

            msSql = "INSERT INTO " + getSqlTable() + " VALUES (" +
                    mnPkWmItemId + ", " + 
                    "'" + msProductId + "', " + 
                    "'" + msCode + "', " + 
                    "'" + msName + "', " + 
                    "'N', " +
                    ""+(mbDeleted ? 1 : 0) + ", " + 
                    ""+(mbSystem ? 1 : 0) + ", " + 
                    ""+mnFkUserInsertId + ", " + 
                    ""+mnFkUserUpdateId + ", " + 
                    "NOW()" + ", " + 
                    "NOW()" + " " +
//                    "amn_box = '" + msamn_box + "', " +
//                    "umn_box = '" + msumn_box + "', " +
//                    "b_amn = " + (mbb_amn ? 1 : 0) + ", " +
//                    "b_umn = " + (mbb_umn ? 1 : 0) + ", " +
//                    "b_umn_owm = " + (mbb_umn_owm ? 1 : 0) + " " +
                     ")";
        }
        else {
            mnFkUserUpdateId = session.getUser().getPkUserId();

            msSql = "UPDATE " + getSqlTable() + " SET " +
                    //"id_wm_item = " + mnPkWmItemId + ", " +
                    "prod_id = '" + msProductId + "', " +
                    "code = '" + msCode + "', " +
                    "name = '" + msName + "', " +
                    "b_del = " + (mbDeleted ? 1 : 0) + ", " +
                    "b_sys = " + (mbSystem ? 1 : 0) + ", " +
                    //"fk_usr_ins = " + mnFkUserInsertId + ", " +
                    "fk_usr_upd = " + mnFkUserUpdateId + ", " +
                    //"ts_usr_ins = NOW()" + ", " +
                    "ts_usr_upd = NOW()" + " " +
//                    "'" + msamn_box + "', " + 
//                    "'" + msumn_box + "', " + 
//                    (mbb_amn ? 1 : 0) + ", " + 
//                    (mbb_umn ? 1 : 0) + ", " + 
//                    (mbb_umn_owm ? 1 : 0) + " " + 

                    getSqlWhere();
        }

        session.getStatement().execute(msSql);

        mbRegistryNew = false;
        mnQueryResultId = SDbConsts.SAVE_OK;
    }

    @Override
    public SDbWmItem clone() throws CloneNotSupportedException {
        SDbWmItem  registry = new SDbWmItem();

        registry.setPkWmItemId(this.getPkWmItemId());
        registry.setProductId(this.getProductId());
        registry.setCode(this.getCode());
        registry.setName(this.getName());
        registry.setDeleted(this.isDeleted());
        registry.setSystem(this.isSystem());
        registry.setFkUserInsertId(this.getFkUserInsertId());
        registry.setFkUserUpdateId(this.getFkUserUpdateId());
        registry.setTsUserInsert(this.getTsUserInsert());
        registry.setTsUserUpdate(this.getTsUserUpdate());

        /*
         * mail
         */
//        registry.setMsamnBox(this.getMsamnBox());
//        registry.setMsumnBox(this.getMsumnBox());
//        registry.setMbbamn(this.getMbbamn());
//        registry.setMbbumn(this.getMbbumn());
//        registry.setMbbUmnOwn(this.getMbbUmnOwn());

        registry.setRegistryNew(this.isRegistryNew());
        return registry;
    }
}
