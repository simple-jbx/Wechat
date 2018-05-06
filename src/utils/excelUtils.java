package utils;

import java.io.File;
import java.io.FileInputStream;  
import java.io.IOException;  
import java.io.InputStream;  
import java.util.ArrayList;  
import java.util.List;
import java.util.Map;
import org.apache.poi.hssf.usermodel.HSSFRow;  
import org.apache.poi.hssf.usermodel.HSSFSheet;  
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class excelUtils {
	
	/**public List<GQRY > readXlsOfGQRE(String path) throws IOException {  
         InputStream is = new FileInputStream(path);  
         HSSFWorkbook hssfWorkbook = new HSSFWorkbook(is);  
         GQRY gqry = null;  
         List<GQRY > list = new ArrayList<GQRY >();  
         // ѭ��������Sheet  
         for (int numSheet = 0; numSheet < hssfWorkbook.getNumberOfSheets(); numSheet++) {  
             HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(numSheet);  
             if (hssfSheet == null) {  
                 continue;  
             }  
             // ѭ����Row  
             for (int rowNum = 1; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {  
                 HSSFRow hssfRow = hssfSheet.getRow(rowNum);  
                 if (hssfRow != null) {  
                	 //System.out.println(hssfRow.toString());
                	 gqry = new GQRY();
                     HSSFCell gH = hssfRow.getCell(0);  
                     HSSFCell xM = hssfRow.getCell(1);  
                     HSSFCell zB = hssfRow.getCell(2);  
                     HSSFCell gW = hssfRow.getCell(3);  
                     HSSFCell zGSJXS = hssfRow.getCell(4);
                     HSSFCell zCXS = hssfRow.getCell(5);
                     HSSFCell kQXS = hssfRow.getCell(6);
                     HSSFCell fPXS = hssfRow.getCell(7);
                     HSSFCell nZBZJJ = hssfRow.getCell(8);
                     HSSFCell jTHDCS = hssfRow.getCell(9);
                     HSSFCell sFPJJL = hssfRow.getCell(10);
                     HSSFCell xYPYJL = hssfRow.getCell(11);
                     HSSFCell jTHDBT = hssfRow.getCell(12);
                     HSSFCell xYHBT = hssfRow.getCell(13);
                     HSSFCell kHJC = hssfRow.getCell(14);
                     HSSFCell dYCFPJCJHJ = hssfRow.getCell(15);
                     HSSFCell dECFPJCJHJ = hssfRow.getCell(16);
                     HSSFCell hJJE = hssfRow.getCell(17);
                     gH.setCellType(Cell.CELL_TYPE_STRING);
                     gqry.setGH(gH.getStringCellValue());
                     xM.setCellType(Cell.CELL_TYPE_STRING);
                     gqry.setXM(xM.getStringCellValue());
                     gW.setCellType(Cell.CELL_TYPE_STRING);
                     gqry.setGW(gW.getStringCellValue());
                     zB.setCellType(Cell.CELL_TYPE_STRING);
                     gqry.setZB(zB.getStringCellValue());
                     gqry.setZGSJXS(zGSJXS.getNumericCellValue());
                     gqry.setZCXS(zCXS.getNumericCellValue());
                     gqry.setKQXS(kQXS.getNumericCellValue());
                     gqry.setFPXS(fPXS.getNumericCellValue());
                     BigDecimal bd = BigDecimal.valueOf(nZBZJJ.getNumericCellValue());
                     gqry.setNZBZJJ(bd);
                     gqry.setJTHDCS((int)jTHDCS.getNumericCellValue());
                     bd = BigDecimal.valueOf(sFPJJL.getNumericCellValue());
                     gqry.setSFPJJL(bd);
                     bd = BigDecimal.valueOf(xYPYJL.getNumericCellValue());
                     gqry.setXYPYJL(bd);
                     bd = BigDecimal.valueOf(jTHDBT.getNumericCellValue());
                     gqry.setJTHDBT(bd);
                     bd = BigDecimal.valueOf(xYHBT.getNumericCellValue());
                     gqry.setXYHBT(bd);
                     bd = BigDecimal.valueOf(kHJC.getNumericCellValue());
                     gqry.setKHJC(bd);
                     bd = BigDecimal.valueOf(dYCFPJCJHJ.getNumericCellValue());
                     gqry.setDYCFPJCJHJ(bd);
                     bd = BigDecimal.valueOf(dECFPJCJHJ.getNumericCellValue());
                     gqry.setDECFPJCJHJ(bd);
                     bd = BigDecimal.valueOf(hJJE.getNumericCellValue());
                     gqry.setHJJE(bd);
                     list.add(gqry);  
                 }  
             }  
         }  
         return list;  
     } 
	**/
    public static <T> List<T> analysisExcel(String path, Class<T> c) {
    	File file = new File(path);
        List<T> list = new ArrayList<T>();
        InputStream inputStream = null;
        String fileName = null;
        Workbook wb = null;
        try{
            inputStream = new FileInputStream(file);
            fileName = file.getName();
            //System.out.println(fileName);
            if(fileName.endsWith(".xls") || fileName.endsWith(".xlsx")){
                //�����2003�汾
                if(fileName.endsWith(".xls")){
                    //1.�Ƚ����ļ�
                    POIFSFileSystem fs = new POIFSFileSystem(inputStream);
                    wb = new HSSFWorkbook(fs);
                    inputStream.close();
                }else if( fileName.endsWith(".xlsx")){//�����2007���ϰ汾
                	//inputStream = new FileInputStream(file); 
                    wb = new XSSFWorkbook(inputStream);
                    inputStream.close();
                }else{
                	inputStream.close();
                    return null;
                }
            }
        }catch(IOException e){
            e.printStackTrace();
        }
        
       // System.out.println( wb.getNumberOfSheets());
        
        for (int numSheet = 0; numSheet < wb.getNumberOfSheets(); numSheet++) {  
            if(fileName.endsWith(".xls") || fileName.endsWith(".xlsx")){
            	if(fileName.endsWith(".xls")){
            		 HSSFSheet hssfSheet = (HSSFSheet) wb.getSheetAt(numSheet);  
            		 //System.out.println(fileName);
                     if (hssfSheet == null) {  
                         continue;  
                     }
                     
                     HSSFRow hssfTitleRow = hssfSheet.getRow(0);
                     int colNum = hssfTitleRow.getPhysicalNumberOfCells();//������
                     //��������һһ��������
                     String[] titles = new String[colNum];
                     for(int i = 0 ; i < colNum ; i++){
                    	 hssfTitleRow.getCell(i).setCellType(Cell.CELL_TYPE_STRING);
                         titles[i] = hssfTitleRow.getCell(i).getStringCellValue();
                         //System.out.println(titles[i]);
                     }
                     int rowNum = hssfSheet.getLastRowNum();//������
                     //int rowNum = hssfSheet.getPhysicalNumberOfRows();
                     //System.out.println(rowNum);
                     Field fields[] = c.getDeclaredFields();
                     Map<String,Field> fieldMap = new HashMap<String, Field>();
                     //for (int rownum = 1; rownum <= rowNum; rownum++) {                  
                         //��ȡָ�����������Լ��������е��ֶ� ���� private int bean.GQRY.ID                      
                         for (int i = 0; i < fields.length; i++) {
                             fieldMap.put(fields[i].getName(), fields[i]);
                           //System.out.println(fields[i].getName() );
                          // System.out.println(fields[i]);
                         }
                     //}
                        // System.out.println(fields.length);
                         //ʹ�÷�����ƣ���ֵ�����Ӧ������
                     String methodName[] = new String[fields.length];
                     Class<?> cc[] = new Class<?>[fields.length-1];
                     for(int j = 1; j < fields.length-1; j++) {
                         String fieldName = fields[j].getName();
                         methodName[j] = "set" + fieldName.substring(0,1).toUpperCase()+fieldName.substring(1);
                         cc[j] = fieldMap.get(fields[j].getName()).getType();
                         //method[j] = c.getMethod(methodName[j], cc);
                     }
                     try {
                    	 T t = null;
                         for (int i = 1; i < rowNum; i++) {
                         HSSFRow hssfRow = hssfSheet.getRow(i);	    
                         t =c.newInstance();
                         if(StringUtils.isEmpty(String.valueOf(hssfSheet.getRow(i).getCell(0)))) {
                             //System.out.println("continue");
                        	 continue;
                         }
                         for (int j = 1; j < fields.length-1; j++) {
	                          //��excel��������ֶ�
	                          //if(fieldMap.containsKey(titles[j])){ 
	                          //String fieldName = fields[j].getName();
	                          //System.out.println(fieldName);
	                          //String methodName = "set" + fieldName.substring(0,1).toUpperCase()+fieldName.substring(1);
	                          //���ø��ֶζ�Ӧ��set����
	                          //System.out.println(methodName);
	                          //Class<?> cc = fieldMap.get(fields[j].getName()).getType();                                  
	                          //System.out.println(fieldMap.get(fields[j].getName()).getType());
	                          Method method = c.getMethod(methodName[j], cc[j]);
	                          //System.out.println(methodName);
	                          //��Ϊexcel���û������idҲû���Ƿ�ɾ����� ���Ӧ���ӵ�0�п�ʼ��ȡ
	                          hssfRow.getCell(j-1).setCellType(Cell.CELL_TYPE_STRING);
	                          String value = hssfRow.getCell(j-1).getStringCellValue();
	                          if(StringUtils.isEmpty(value)) {
	                        	  //System.out.println("continue");
	                        	  continue;
	                          }
	                          method.invoke(t, parseValue(value, cc[j]));
                          }
                        list.add(t);
                     }
                       //list.add(t);
                     } catch (Exception e) {
                    	 e.printStackTrace();
                     } 
                }
            }else {
            		XSSFSheet xssffSheet =   (XSSFSheet) wb.getSheetAt(numSheet);
            		XSSFRow xssfTitleRow = xssffSheet.getRow(1);
            		int colNum = xssfTitleRow.getPhysicalNumberOfCells();//������
            		//��������һһ��������
            		String[] titles = new String[colNum];
            		for(int i = 0 ; i < colNum ; i++){
            			xssfTitleRow.getCell(i).setCellType(Cell.CELL_TYPE_STRING);
            			titles[i] = xssfTitleRow.getCell(i).getStringCellValue();
            			//System.out.println(titles[i]);
            		}
            		int rowNum = xssffSheet.getLastRowNum();//������
            		for (int rownum = 1; rownum <= rowNum; rownum++) {  
            			//��ȡָ�����������Լ��������е��ֶ� ���� private int bean.GQRY.ID
            			Field fields[] = c.getDeclaredFields();
            			Map<String,Field> fieldMap = new HashMap<String, Field>();
            			for (int i = 0; i < fields.length; i++) {
            				fieldMap.put(fields[i].getName(), fields[i]);
                        }
                        //ʹ�÷�����ƣ���ֵ�����Ӧ������
            			try {
                           for (int i = 1; i <= rowNum; i++) {
                               T t =c.newInstance();
                               for (int j = 1; j < fields.length-1; j++) {
                                 //if(fieldMap.containsKey(titles[j])){ 
                                 String fieldName = fields[j].getName();
                                 //System.out.println(fieldName);
                                 String methodName = "set" + fieldName.substring(0,1).toUpperCase()+fieldName.substring(1);
                                 //���ø��ֶζ�Ӧ��set����
                                 //System.out.println(methodName);
                                 Class<?> cc = fieldMap.get(fields[j].getName()).getType();                                  
                                 Method method = c.getMethod(methodName, cc);
                                 
                                 //��Ϊexcel���û������idҲû���Ƿ�ɾ����� ���Ӧ���ӵ�0�п�ʼ��ȡ
                                 String value = String.valueOf(xssffSheet.getRow(i).getCell(j-1));
                                 //System.out.println(value);
                                 method.invoke(t, parseValue(value, cc));
                               }
                               list.add(t);
                           }
                        } catch (Exception e) {
                             e.printStackTrace();
                        }
            		}
            }
        }
        return list;    
    }

    
    /**
     * ���ַ���ת��Ϊָ�����͵Ķ���
     * @param s----Ҫת�����ַ���
     * @param c----Ŀ���������
     * @return
     */
    private static Object parseValue(String s,Class<?> c){
        Object obj = null;
        String className = c.getName();
        //System.out.println(c);
        //excel�е����ֽ���֮�����ĩβ����.0����Ҫȥ��
        if(s.endsWith(".0")) s = s.substring(0, s.length()-2);

        if(className.equals("java.lang.Integer")){ //Integer
        	if(!s.equals("") && s != null)
        		obj =  new Integer(s);
        	else
        		obj = new Integer("0");
        }else if(className.equals("int")){ //int
        	if(!s.equals("") && s != null)
        		obj = (int)Integer.parseInt(s);
        	else
        		obj = (int)Integer.parseInt("0");
        }else if(className.equals("java.lang.String")){ //String
            obj = s;
        }else if(className.equals("java.lang.Double")){ //Double
        	if(!s.equals("") && s != null)
        		obj = new Double(s);
        	else
        		obj = new Double(s);
        }else if(className.equals("double")){ //double
        	if(!s.equals("") && s != null)
        		obj = (double)new Double(s);
        	else
        		obj = (double)new Double(s);
        }else if(className.equals("java.lang.Float")){ //Float
        	if(!s.equals("") && s != null)
        		obj = new Float(s);
        	else
        		obj = new Float("0.00");
        }else if(className.equals("float")){ //float
        	if(!s.equals("") && s != null)
        		obj = (float)new Float(s);
        	else 
        		obj = (float)new Float("0.00");
        }else if(className.equals("java.util.Date")){ //Date
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            try {
                obj = sdf.parse(s);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }else if(className.equals("long")){ //long
        	if(!s.equals("") && s != null)
        		obj = Long.parseLong(s);
        	else 
        		obj = Long.parseLong("0");
        }else if(className.equals("java.util.Long")){ //Long
        	if(!s.equals("") && s != null)
        		obj = new Long(s);
        	else
        		obj = new Long("0");
        }else if(className.equals("java.math.BigDecimal")){	
        	try{
        		obj = new BigDecimal(s);        		
        	}catch(NumberFormatException e) {
        		s = "0.00";
        		obj = new BigDecimal(s);
        	}	
        }
        return obj;
    }
    
    public static void main(String[] args) {
		// TODO Auto-generated method stub
   /**
    * List<GQRY> gqry = excelUtils.analysisExcel("C:/Users/Administrator/Desktop/test.xls", GQRY.class);
    
    if(gqry.size() > 0)
    	System.out.println(gqry.get(0).getGH());
    else
    	System.out.println("==0");
	}
	*/
    }
}
