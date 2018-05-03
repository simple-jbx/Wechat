package service;

import java.util.List;
import java.io.IOException;
import utils.DataBaseUtil;
import java.sql.SQLException;
import utils.excelUtils;
import bean.QGZX;

public class QGZXService {
	/**
	 * 鏍规嵁涓婁紶鏂囦欢灏嗘暟鎹瓨鍏ユ暟鎹簱
	 * @param path
	 * @throws IOException
	 * @throws SQLException
	 */
	public void save2DB(String path) throws IOException, SQLException {
		List<QGZX > list = excelUtils.analysisExcel(path, QGZX.class);
		QGZX qgzx = null;
		/**
		System.out.println(list.size());
		for(int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i).toJson());
		}//**/
		String sql = "insert into t_qgzx(id,xymc,rydm,xm,ffny,yrdw,yhkh,je,gwmc,isdel)"
				+ " VALUES (?,?,?,?,?,?,?,?,?,?)";	
		///**
		for(int i = 0; i < list.size(); i++) {
			qgzx = list.get(i);
			DataBaseUtil.update(sql, qgzx.getID(), qgzx.getXYMC(), qgzx.getRYDM(), qgzx.getXM(),
					qgzx.getFFNY(), qgzx.getYRDW(), qgzx.getYHKH(), qgzx.getJE(),
					qgzx.getGWMC(), 0);
		}//**/
	}
	
	
	
	/**
	 * 单条更新
	 * @param QGZX
	 */
	public void updateData(QGZX qgzx) {
		
		if(qgzx.getISDEL() == 0) {
			String sql = "update t_qgzx set xymc = ?, rydm = ?, xm = ?, ffny = ?, yrdw = ?,"
					+ "yhkh = ?, je = ?, gwmc = ? where id = ?";			
			DataBaseUtil.update(sql, qgzx.getXYMC(), qgzx.getRYDM(), qgzx.getXM(),
					qgzx.getFFNY(), qgzx.getYRDW(), qgzx.getYHKH(), qgzx.getJE(),
					qgzx.getGWMC(), qgzx.getID());
		}else if(qgzx.getISDEL() == 2){
			String sql = "insert into t_qgzx(id,xymc,rydm,xm,ffny,yrdw,yhkh,je,gwmc,isdel)"
					+ " VALUES (?,?,?,?,?,?,?,?,?,?)";	
			DataBaseUtil.update(sql, qgzx.getID(), qgzx.getXYMC(), qgzx.getRYDM(), qgzx.getXM(),
					qgzx.getFFNY(), qgzx.getYRDW(), qgzx.getYHKH(), qgzx.getJE(),
					qgzx.getGWMC(), 0);
		}
	}
}
