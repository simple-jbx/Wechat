package service;

import java.util.List;
import java.io.IOException;
import utils.DataBaseUtil;
import java.sql.SQLException;
import utils.excelUtils;
import bean.MFSFSSHBZ;

public class MFSFSSHBZService {
	/**
	 * 鏍规嵁涓婁紶鏂囦欢灏嗘暟鎹瓨鍏ユ暟鎹簱
	 * @param path
	 * @throws IOException
	 * @throws SQLException
	 */
	public void save2DB(String path) throws IOException, SQLException {
		List<MFSFSSHBZ > list = excelUtils.analysisExcel(path, MFSFSSHBZ.class);
		MFSFSSHBZ mfsfsshbz = null;
		/**
		System.out.println(list.size());
		for(int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i).toJson());
		}//**/
		String sql = "insert into t_mfsfsshbz(id,xymc,rydm,xm,zymc,nj,yhkh,je,isdel)"
				+ " VALUES (?,?,?,?,?,?,?,?,?)";	
		///**
		for(int i = 0; i < list.size(); i++) {
			mfsfsshbz = list.get(i);
			DataBaseUtil.update(sql, mfsfsshbz.getID(), mfsfsshbz.getXYMC(), mfsfsshbz.getRYDM(), mfsfsshbz.getXM(),
					mfsfsshbz.getZYMC(), mfsfsshbz.getNJ(), mfsfsshbz.getYHKH(), mfsfsshbz.getJE(), 0);
		}//**/
	}
	
	
	
	/**
	 * 单条更新
	 * @param MFSFSSHBZ
	 */
	public void updateData(MFSFSSHBZ mfsfsshbz) {
		
		if(mfsfsshbz.getISDEL() == 0) {
			String sql = "update t_mfsfsshbz set xymc = ?, rydm = ?, xm = ?, zymc = ?, nj = ?,"
					+ "yhkh = ?, je = ? where id = ?";			
			DataBaseUtil.update(sql, mfsfsshbz.getXYMC(), mfsfsshbz.getRYDM(), mfsfsshbz.getXM(),
					mfsfsshbz.getZYMC(), mfsfsshbz.getNJ(), mfsfsshbz.getYHKH(), mfsfsshbz.getJE(), mfsfsshbz.getID());
		}else if(mfsfsshbz.getISDEL() == 2){
			String sql = "insert into t_mfsfsshbz(id,xymc,rydm,xm,zymc,nj,yhkh,je,isdel)"
					+ " VALUES (?,?,?,?,?,?,?,?,?)";	
			DataBaseUtil.update(sql, mfsfsshbz.getID(), mfsfsshbz.getXYMC(), mfsfsshbz.getRYDM(), mfsfsshbz.getXM(),
					mfsfsshbz.getZYMC(), mfsfsshbz.getNJ(), mfsfsshbz.getYHKH(), mfsfsshbz.getJE(), 0);
		}
	}
}
