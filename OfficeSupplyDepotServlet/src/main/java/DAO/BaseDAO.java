package DAO;
import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;

public abstract class BaseDAO {
	protected DataSource dataSource;
//	URL, user, and password can't be edited after creation. A new DAO must be created in order to change url, username, or password.
	private String url;
	private String mySQLUser;
	private String mySQLPass;

    public BaseDAO(String url, String user, String password) {
    	this.url = url;
    	this.mySQLUser = user;
    	this.mySQLPass = password;
    	dataSource = getDataSource();
    }
    
    private DataSource getDataSource() {
    	if (dataSource == null) {
            BasicDataSource ds = new BasicDataSource();
            ds.setUrl(url);
            ds.setUsername(mySQLUser);
            ds.setPassword(mySQLPass);
            ds.setMinIdle(1);
            ds.setMaxIdle(1);
            ds.setMaxTotal(1);
            dataSource = ds;
        }
        return dataSource;
    }

}
