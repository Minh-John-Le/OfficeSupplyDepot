package Utilities;

import java.util.List;

public class MySQLQueryBuilder {
	
	public String insertIntoTable(String tableName, List<String> columns, List<String> values) {
		StringBuilder sb = new StringBuilder();
		sb.append("INSERT INTO");
		sb.append(" ");
		sb.append(tableName);
		sb.append(" (");
		
		for(int i=0; i<columns.size()-1;i++) {
			sb.append(columns.get(i));
			sb.append(", ");
		}
		sb.append(columns.get(columns.size()-1));
		sb.append(")");
		
		sb.append("VALUES (");
		for(int i=0; i<values.size()-1;i++) {
			sb.append(values.get(i));
			sb.append(", ");
		}
		sb.append(values.get(columns.size()-1));
		sb.append(")");
		
		return sb.toString();
	}
	
	public String deleteFromTable(String tableName, String column, int value) {
	    StringBuilder sb = new StringBuilder();
	    sb.append("DELETE FROM ");
	    sb.append(tableName);
	    sb.append(" WHERE ");
	    sb.append(column);
	    sb.append(" = ");
	    sb.append(value);

	    return sb.toString();
	}

	
	public String updateTable(String tableName, List<String> columns, List<String> values, String primaryKey, String primaryKeyValue) {
	    StringBuilder sb = new StringBuilder();
	    sb.append("UPDATE ");
	    sb.append(tableName);
	    sb.append(" SET ");
	    
	    for(int i=0; i<columns.size()-1;i++) {
	        sb.append(columns.get(i));
	        sb.append(" = ");
	        sb.append(values.get(i));
	        sb.append(", ");
	    }
	    
	    sb.append(columns.get(columns.size()-1));
	    sb.append(" = ");
	    sb.append(values.get(columns.size()-1));
	    
	    sb.append(" WHERE ");
	    sb.append(primaryKey);
	    sb.append(" = ");
	    sb.append(primaryKeyValue);

	    return sb.toString();
	}

}
