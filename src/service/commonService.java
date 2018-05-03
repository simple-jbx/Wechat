package service;

import java.util.List;
import java.util.Map;
import annotation.Table;
import utils.DataBaseUtil;

/**
 * Service涓殑鍏叡鏂规硶
 * @author simple
 */
public class commonService {

	/**
	 * 鑾峰彇鏌愯〃涓殑鎵�鏈夋暟鎹�
	 * @param clazz
	 * @return
	 */
	public static List<Map<String, Object> > getAllData(Class<?> clazz) {
		
		Table table = (Table) clazz.getAnnotation(Table.class);
		String tableName = table.tableName();
		if(tableName == null)
			tableName = "";
		String sql = "select * from " + tableName + " where isdel = ?";
		return DataBaseUtil.queryForList(sql, 0);
	}
	
	
	/**
	 * 杩斿洖鏌愯〃涓殑鍗曟潯鏁版嵁
	 * @param clazz
	 * @param Rydm
	 * @return
	 */
	public static <T> T getDataByRydm(Class<?> clazz, String Rydm) {
		Table table = (Table) clazz.getAnnotation(Table.class);
		String tableName = table.tableName();
		if(tableName == null)
			tableName = "";
		String sql = "select * from " + tableName + " where rydm = ? and isdel = ?";
		//System.out.println(sql + " " + Rydm);
		return DataBaseUtil.queryForBean(sql, clazz, Rydm, 0);
	}
	
	
	/**
	 * 鏍规嵁id鍒犻櫎鏌愯〃瀵瑰簲鏁版嵁
	 * @param id
	 */
	public static void deleteByID(Class<?> clazz, String iD) {
		Table table = (Table) clazz.getAnnotation(Table.class);
		String tableName = table.tableName();
		if(tableName == null)
			tableName = "";
		String sql = "update " + tableName + " set isdel = ? where id  = ?";
		DataBaseUtil.update(sql, 1, iD);
	}
	
	
	public static void deleteAll(Class<?> clazz) {
		Table table = (Table) clazz.getAnnotation(Table.class);
		String tableName = table.tableName();
		if(tableName == null)
			tableName = "";
		String sql = "update " + tableName + " set isdel = ? where isdel  = ?";
		DataBaseUtil.update(sql, 1, 0);
	}
}
