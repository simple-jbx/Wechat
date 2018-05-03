package service;

import java.util.List;
import java.io.IOException;
import utils.DataBaseUtil;
import java.sql.SQLException;
import utils.excelUtils;
import bean.YH;

public class YHService {
	/**
	 * 鏍规嵁涓婁紶鏂囦欢灏嗘暟鎹瓨鍏ユ暟鎹簱
	 * @param path
	 * @throws IOException
	 * @throws SQLException
	 */
	public void save2DB(String path) throws IOException, SQLException {
		YH yh = null;
		YH yhTest = new YH();
		List<YH > list = excelUtils.analysisExcel(path, YH.class);
		//System.out.println(list.size());
		//for(int i = 0; i < list.size(); i++) {
			//System.out.println(list.get(i).toJson());
		//}
		String sql = "insert into t_yh(id,openid,rydm,xm,mm,xymc,zymc,nj,yhkh,isdel)"
				+ " VALUES (?,?,?,?,?,?,?,?,?,?)";	
		
		//由于某种原因，导入信息 中默认密码为null，因此这里使用yhTest.getMM(); 同理openid也是
		for(int i = 0; i < list.size(); i++) {
			yh = list.get(i);
			DataBaseUtil.update(sql, yh.getID(), yhTest.getOPENID(), yh.getRYDM(), yh.getXM(),
					yhTest.getMM(), yh.getXYMC(), yh.getZYMC(), yh.getNJ(), yh.getYHKH(), 0);
		}
	}
	
	
	
	/**
	 * 鏇存柊鑾峰鏁版嵁
	 * @param yh
	 */
	public void updateData(YH yh) {
		
		if(yh.getISDEL() == 0) {
			String sql = "update t_yh set openid = ?, rydm = ?, xm = ?, mm = ?, xymc=?, zymc=?,"
					+ "nj = ?, yhkh = ? where id = ?";			
			DataBaseUtil.update(sql, yh.getOPENID(), yh.getRYDM(), yh.getXM(),
					yh.getMM(), yh.getXYMC(), yh.getZYMC(), yh.getNJ(), yh.getYHKH(), yh.getID());
		}else if(yh.getISDEL() == 2){
			String sql = "insert into t_yh(id,openid,rydm,xm,mm,xymc,zymc,nj,yhkh,isdel)"
					+ " VALUES (?,?,?,?,?,?,?,?,?,?)";		
			DataBaseUtil.update(sql, yh.getID(), yh.getOPENID(), yh.getRYDM(), yh.getXM(),
					yh.getMM(), yh.getXYMC(), yh.getZYMC(), yh.getNJ(), yh.getYHKH(), 0);
		}
	}
	
	
	public void resetPassword(YH yh) {
		String sql = "update t_yh set mm = ? where id = ? and rydm = ?";
		YH yhTest = new YH();
		DataBaseUtil.update(sql, yhTest.getMM(), yh.getID(), yh.getRYDM());
	} 
}
