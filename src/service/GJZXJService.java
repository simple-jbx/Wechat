package service;

import java.util.List;
import java.io.IOException;
import utils.DataBaseUtil;
import java.sql.SQLException;
import utils.excelUtils;
import bean.GJZXJ;

public class GJZXJService {
	/**
	 * 根据上传文件将数据存入数据库
	 * @param path
	 * @throws IOException
	 * @throws SQLException
	 */
	public void save2DB(String path) throws IOException, SQLException {
		List<GJZXJ > list = excelUtils.analysisExcel(path, GJZXJ.class);
		GJZXJ gjzxj = null;
		/**System.out.println(list.size());
		for(int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i).toJson());
		}//**/
		String sql = "insert into t_gjzxj(id,xymc,rydm,xm,nj,zymc,yhkh,je,dj,isdel)"
				+ " VALUES (?,?,?,?,?,?,?,?,?,?)";	
		
		for(int i = 0; i < list.size(); i++) {
			gjzxj = list.get(i);
			DataBaseUtil.update(sql, gjzxj.getID(), gjzxj.getXYMC(), gjzxj.getRYDM(), gjzxj.getXM(),
					gjzxj.getNJ(), gjzxj.getZYMC(), gjzxj.getYHKH(), gjzxj.getJE(), gjzxj.getDJ(), 0);
		}//**/
	}
	
	
	
	/**
	 * ��������
	 * @param GJZXJ
	 */
	public void updateData(GJZXJ gjzxj) {
		
		if(gjzxj.getISDEL() == 0) {
			String sql = "update t_gjzxj set xymc = ?, rydm = ?, xm = ?, nj = ?, zymc=?, yhkh=?,"
					+ "je = ?, dj = ? where id = ?";			
			DataBaseUtil.update(sql, gjzxj.getXYMC(), gjzxj.getRYDM(), gjzxj.getXM(),
					gjzxj.getNJ(), gjzxj.getZYMC(), gjzxj.getYHKH(), gjzxj.getJE(), gjzxj.getDJ(),
					gjzxj.getID());
		}else if(gjzxj.getISDEL() == 2){
			String sql = "insert into t_gjzxj(id,xymc,rydm,xm,nj,zymc,yhkh,je,dj,isdel)"
					+ " VALUES (?,?,?,?,?,?,?,?,?,?)";	
			DataBaseUtil.update(sql, gjzxj.getID(), gjzxj.getXYMC(), gjzxj.getRYDM(), gjzxj.getXM(),
					gjzxj.getNJ(), gjzxj.getZYMC(), gjzxj.getYHKH(), gjzxj.getJE(), gjzxj.getDJ(), 0);
		}
	}
}
