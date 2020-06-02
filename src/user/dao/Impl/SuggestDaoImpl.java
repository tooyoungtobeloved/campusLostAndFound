package user.dao.Impl;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;

import utils.DBUtil;

public class SuggestDaoImpl implements user.dao.ISuggestDao{
	QueryRunner qr = new QueryRunner(DBUtil.dataSource);
	
	@Override
	public int insertSuggest(String type, String name, String contact, String info) {
		if(name == null || name == "") {
			name = "未填写";
		}
		String sql = "INSERT INTO `suggest`(`sugID`, `sugType`, `sugName`, `sugContact`, `sugContent`) VALUES (null,?,?,?,?)";
		int count = 0;
		try {
			count = qr.update(sql, type, name, contact, info);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}

}
