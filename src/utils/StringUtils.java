package utils;

public class StringUtils {
	public static boolean isEmpty(String str){
		return null == str || str.equals("") || str.matches("\\s*");
	}
	
	public static String defaultValue(String content, String defaultValue){
		if(isEmpty(content))
			return defaultValue;
		return content;
	}
	
	/**
	 * 閹跺﹥鏆熼幑顔肩氨鐎涙顔岄崥宥囆為弨瑙勫灇妞圭厧鍢查弬鐟扮�?
	 * @param column
	 * @return String
	 */
	public static String columnToProperty(String column) {
		//婵�?��?�鐏夌�涙顔岄崥宥勮礋缁岀尨绱濈亸杈箲閸ョ偟鈹栫�涙顑佹稉锟�
		if(isEmpty(column))
			return "";
		//閼惧嘲褰囩�涙顔岄梹鍨  娑擄拷閼割剙鐡у▓鐢告毐鎼达箒绶濈亸锟�?
		Byte length = (byte) column.length();
		
		StringBuilder sb = new StringBuilder(length);
		//闁秴宸荤�涙顔屽В蹇庣娑擃亜鐡х粭锟�
		for(int j = 0; j < length; j++) {
			//閸栧綊鍘ら崚鎵儑娑擄拷娑撶�?
			if(column.charAt(j) == '_') {
				while(column.charAt(++j) == '_');
				sb.append(("" + column.charAt(j)).toUpperCase());
			}else {
				//婵�?��?�鐏夌�涙顑佹稉宥嗘Ц_娣囨繂鐡�?
				sb.append(column.charAt(j));
			}
		}
		return sb.toString();
	}
	
	
	/**
	 * 閹跺﹥鏆熼幑顔肩氨鐎涙顔岄崥宥囆為弨瑙勫灇妞圭厧鍢查弬鐟扮�?
	 * @param column
	 * @return String
	 */
	public static String columnToUpperCase(String column) {
		//婵�?��?�鐏夌�涙顔岄崥宥勮礋缁岀尨绱濈亸杈箲閸ョ偟鈹栫�涙顑佹稉锟�
		if(isEmpty(column))
			return "";
		//閻╁瓨甯寸亸鍡楃摟濞堥潧鎮曟潪顒佸床娑撳搫銇囬崘娆掔箲閸ョ偛宓嗛崣锟�?
		return column.toUpperCase();
	}
	
	
	/**
	 * 鐠佽弓绔存稉顏勭摟缁楋缚瑕嗛惃鍕浕鐎涙鐦濋弨瑙勫灇婢堆冨�?
	 * @param str
	 * @return
	 */
	public static String upperCaseFirstCharacter(String str) {
		StringBuilder sb = new StringBuilder();
		char[] arr = str.toCharArray();
		for(int i = 0; i < arr.length; i++) {
			if(i == 0) {
				sb.append((arr[i] + "").toUpperCase());
			}else {
				sb.append((arr[i] + ""));
			}
		}
		return sb.toString();
	}
}
