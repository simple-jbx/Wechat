package bean;

import java.math.BigDecimal;
import java.util.UUID;

import annotation.Column;
import annotation.Table;

@Table(tableName="t_gjzxj")
public class GJZXJ {

	@Column(field="id", type="varchar(50)", primaryKey=true, defaultNull=false)
	private String ID;
	
	@Column(field="xymc", type="varchar(50)")
	private String XYMC;//学院名称
	
	@Column(field="rydm", type="varchar(20)")
	private String RYDM;//学号、人员代码
	
	@Column(field="xm", type="varchar(50)")
	private String XM;//姓名
	
	@Column(field="nj", type="varchar(10)")
	private String NJ;//年级
	
	@Column(field="zymc", type="varchar(50)")
	private String ZYMC;//专业名称
	
	@Column(field="yhkh", type="varchar(50)")
	private String YHKH;//银行卡号
	
	@Column(field="je", type="decimal(10,2)")
	private BigDecimal JE;//金额
	
	@Column(field="dj", type="varchar(10)")
	private String DJ;//等级
	
	@Column(field="isdel", type="int(1)")
	private int ISDEL;
	
	public String toJson() {
		return "[{\"id\":\"" + "\", \"xymc\":\"" + XYMC + "\", \"rydm\":\"" + RYDM + "\", \"xm\":\"" 
				+ XM + "\", \"nj\":\"" + NJ + "\", \"zymc\":\"" + ZYMC + "\", \"yhkh\":\"" + YHKH
				+ "\", \"je\":" + JE + ", \"isdel\":" + ISDEL + "}]";
	}
	
	public GJZXJ() {
		ID = UUID.randomUUID().toString().replaceAll("-", "");
		XYMC = "-";
		RYDM = "-";
		XM = "-";
		NJ = "-";
		ZYMC = "-";
		YHKH = "-";
		DJ = "-";
		JE = new BigDecimal("0");
		ISDEL = 2;
	}

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public String getXYMC() {
		return XYMC;
	}

	public void setXYMC(String xYMC) {
		XYMC = xYMC;
	}

	public String getRYDM() {
		return RYDM;
	}

	public void setRYDM(String rYDM) {
		RYDM = rYDM;
	}

	public String getXM() {
		return XM;
	}

	public void setXM(String xM) {
		XM = xM;
	}

	public String getNJ() {
		return NJ;
	}

	public void setNJ(String nJ) {
		NJ = nJ;
	}

	public String getZYMC() {
		return ZYMC;
	}

	public void setZYMC(String zYMC) {
		ZYMC = zYMC;
	}

	public String getYHKH() {
		return YHKH;
	}

	public void setYHKH(String yHKH) {
		YHKH = yHKH;
	}

	public BigDecimal getJE() {
		return JE;
	}

	public void setJE(BigDecimal jE) {
		JE = jE;
	}

	public String getDJ() {
		return DJ;
	}

	public void setDJ(String dJ) {
		DJ = dJ;
	}

	public int getISDEL() {
		return ISDEL;
	}

	public void setISDEL(int iSDEL) {
		ISDEL = iSDEL;
	}
}
