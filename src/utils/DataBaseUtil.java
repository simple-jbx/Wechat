package utils;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.lang.Integer;
import java.util.Properties;

public class DataBaseUtil {
		
	private static String username; //閻€劍鍩涢崥锟�
	private static String password; //鐎靛棛鐖�?
	private static String dataBaseName; //閺佺増宓佹惔鎾虫�?
	static{
		config("config/jdbc.properties");
	}
	
	/**
	 * 闁板秶鐤嗛弫鐗堝祦鎼存挸鐔�閺堫兛淇婇幁锟�
	 * @author simple
	 * @param path
	 */
	public static void config(String path){
		InputStream inputStream = DataBaseUtil.class.getClassLoader().getResourceAsStream(path);
		Properties p = new Properties();
		try{
			p.load(inputStream);
			username = p.getProperty("db.username");
			password = p.getProperty("db.password");
			dataBaseName = p.getProperty("db.dataBaseName");
			//System.out.println(username);
		}catch (IOException e){
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 閼惧嘲褰囬弫鐗堝祦鎼存捁绻涢幒锟�?
	 * @return Connection
	 */
	public static Connection getConnection(){
		Connection connection = null;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+dataBaseName+"?useUnicode=true&characterEncoding=utf8&useSSL=false",username,password);
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}
	
	
	/**
	 * 閸忔娊妫撮弫鐗堝祦鎼存捁绻涢幒锟�?
	 * @param connection
	 * @param statement
	 * @param rs
	 */
	public static void closeConnection(Connection connection, PreparedStatement statement, ResultSet rs) {
		try{
			if(rs != null) {
				rs.close();
			}
			if(statement != null) {
				statement.close();
			}
			if(connection != null) {
				connection.close();
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 婢э拷 閸掞�?  閺�锟�
	 *@param sql
	 *@param objects
	 *@return
	 */
	public static void update(String sql, Object...objects) {
		Connection connection  = getConnection();
		PreparedStatement statement = null;
		try{
			statement = (PreparedStatement) connection.prepareStatement(sql);
			for(int i = 0; i < objects.length; i++) {
				statement.setObject(i+1, objects[i]);
			}
			statement.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			closeConnection(connection, statement, null);
		}
	}
	
	
	/**
	 * 閺屻儴顕楅弫鐗堝祦閿涘奔浜抣ist瑜般垹绱℃潻鏂挎�?
	 * @parm sql
	 * @parm objects
	 * @return
	 * @throws SQLException
	 */
	public static List<Map<String, Object> > queryForList(String sql, Object...objects) {
		List<Map<String, Object> > resultList = new ArrayList<Map<String, Object> >();
		Connection connection = getConnection();
		PreparedStatement statement= null;
		ResultSet rs = null;
		try{
			statement = connection.prepareStatement(sql);
			for(int i = 0; i < objects.length; i++) {
				//System.out.println(i);
				statement.setObject(i+1, objects[i]);
			}
			rs = statement.executeQuery();
			while(rs.next()) {
				ResultSetMetaData resultSetMetaData = rs.getMetaData();//閸忓厖绨琑esultSet娑擃厼鍨崥宥囆為崪灞间繆閹垳娈戠猾璇茬��?
				int count = resultSetMetaData.getColumnCount();//閼惧嘲褰囬崚妤佹�?
				Map<String, Object> map = new HashMap<String, Object>();
				for(int i = 0; i < count; i++) {
					//System.out.println(resultSetMetaData.getColumnName(i+1)); 閸氬秶袨
					//System.out.println(rs.getObject(resultSetMetaData.getColumnName(i+1))); 閸婏�?
					map.put(resultSetMetaData.getColumnName(i+1), rs.getObject(resultSetMetaData.getColumnName(i+1)));
				}
				resultList.add(map);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			closeConnection(connection, statement, rs);
		}
		return resultList;
	}
	
	
	/**
	 * 閺屻儴顕楅弫鐗堝祦閿涘苯鑻熸稉鏂句簰map鏉╂柨娲�?
	 * @param sql
	 * @return
	 * @throws SQLException
	 */
	public static Map<String, Object> queryForMap(String sql, Object...objects) {
		Map<String, Object> result = new HashMap<String, Object>();
		List<Map<String, Object> >list = queryForList(sql, objects);
		if(list.size() != 1) {
			return null;
		}
		result = list.get(0);
		return result;
	}
	
	
	/**
	 * 閺屻儴顕楅弫鐗堝祦閿涘矁绻戦崶鐐扮娑撶嫬avaBean
	 * @param sql
	 * @param clazz
	 * @param objects
	 * @return
	 * @throws NOSuchFileException
	 * @throws SecurityException
	 */
	
	public static <T>T queryForBean(String sql, Class<?> clazz, Object...objects) {
		T obj = null;
		Map<String, Object> map = null;
		Field field = null;
		try{
			obj = (T) clazz.newInstance();//閸掓稑缂撴稉锟芥稉鐙焑an鐎圭偘绶�?
			map = queryForMap(sql, objects);//鐏忓棛绮ㄩ弸婊堟肠閸忓牊鏂侀崷銊ょ娑撶尠ap娑擄�?
		}catch(InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
		
		if(map == null)
			return null;
		//闁秴宸籱ap
		for(String columnName : map.keySet()) {
			Method method = null;
			//鐎涙顔岄崥宥呯摟缁楋箒娴嗛幑锟�?
			String propertyName = StringUtils.columnToUpperCase(columnName);
			try {
				field = clazz.getDeclaredField(propertyName);
				//System.out.println(field);
			}catch(NoSuchFieldException e) {
				e.printStackTrace();
			}catch(SecurityException e) {
				e.printStackTrace();
			}
			
			//鏉╁洤骞揓avaBean娑擃厾娈戠�涙顔�
			String fieldType = field.toString().split(" ")[1];
			Object value = map.get(columnName);
			if(value == null)
				continue;
			//JavaBean set閺傝纭堕崥锟�
			String setMethodName = "set" + propertyName;
			try{
					if(fieldType.equals("java.lang.Integer")){ //Integer
			        		value = new Integer(String.valueOf(value));
			        }else if(fieldType.equalsIgnoreCase("int")) {
		        		value = new Integer(String.valueOf(value));
			        }else if(fieldType.equalsIgnoreCase("java.util.String")) {
						value = String.valueOf(value);
					}else if(fieldType.equalsIgnoreCase("java.util.Date")) {
						//閺佺増宓佹惔鎾诲櫡閻ㄥ嫭妞傞梻缈犵瑢java鏉烆剚宕�?
						String dateStr = String.valueOf(value);
						Timestamp ts = Timestamp.valueOf(dateStr);
						Date date = new Date(ts.getTime());
						value = date;
					}else if(fieldType.equalsIgnoreCase("java.math.BigDecimal")) {
						value = new BigDecimal(String.valueOf(value));
					}else if(fieldType.equalsIgnoreCase("java.lang.Double")) {
						value = new Double(String.valueOf(value));
					}else if(fieldType.equalsIgnoreCase("double")) {
						value = new Double(String.valueOf(value));
					}else if(fieldType.equalsIgnoreCase("java.lang.Float")) {
						value = new Float(String.valueOf(value));
					}else if(fieldType.equalsIgnoreCase("float")) {
						value = new Float(String.valueOf(value));
					}else if(fieldType.equalsIgnoreCase("long")) {
						value = new Long(String.valueOf(value));
					}else if(fieldType.equalsIgnoreCase("java.util.Long")) {
						value = new Long(String.valueOf(value));
					}
			   		     
				//鐎圭偟骞囬崝銊︼拷浣界殶閻�?
				method = clazz.getMethod(setMethodName, field.getType());
				method.invoke(obj, value);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return obj;
	}
}
