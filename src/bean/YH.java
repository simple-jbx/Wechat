package bean;

import annotation.Column;
import annotation.Table;
import utils.SHA1Util;

import java.util.UUID;

@Table(tableName="t_yh")
public class YH {

	@Column(field="id", type="varchar(50)", primaryKey=true, defaultNull=false)
	private String ID;//����
	
	@Column(field="xm", type="varchar(50)")
	private String XM;//����

	@Column(field="rydm", type="varchar(20)")
	private String RYDM;//��Ա����
	
	@Column(field="xymc", type="varchar(50)")
	private String XYMC;//ѧԺ����	
	
	@Column(field="zymc", type="varchar(50)")
	private String ZYMC;//רҵ����

	@Column(field="nj", type="varchar(10)")
	private String NJ;//�꼶

	@Column(field="yhkh", type="varchar(50)")
	private String YHKH;//���п���
	
	@Column(field="mm", type="varchar(50)")
	private String MM;//����
	
	@Column(field="openid", type="varchar(50)")
	private String OPENID;//΢��openid
	
	@Column(field="isdel", type="int(1)")
	private int ISDEL;//�Ƿ�ɾ��
	
	public YH() {
		ID = UUID.randomUUID().toString().replaceAll("-", "");
		OPENID = "-";
		RYDM = "-";
		XM = "-";
		MM = SHA1Util.sha1Encode("00000000");//��ʼ����
		XYMC = "-";
		ZYMC = "-";
		YHKH = "-";
		NJ = "-";
		ISDEL = 2;
	}

	public String toJson() {
		return "[{\"id\":\"" + ID + "\", \"openid\":" + OPENID + "\", \"rydm\":\"" + RYDM + "\", "
				+ "\"xm\":\"" + XM + "\", \"mm\":\"" + MM + "\", \"xymc\":" + XYMC + "\", "
				+ "\"zymc\":\"" + ZYMC + "\", \"yhkh\":" + YHKH + "\", \"nj\":\"" + NJ 
				+ "\", \"isdel\":" + ISDEL +"}]";
	}

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public String getXM() {
		return XM;
	}

	public void setXM(String xM) {
		XM = xM;
	}

	public String getRYDM() {
		return RYDM;
	}

	public void setRYDM(String rYDM) {
		RYDM = rYDM;
	}

	public String getXYMC() {
		return XYMC;
	}

	public void setXYMC(String xYMC) {
		XYMC = xYMC;
	}

	public String getZYMC() {
		return ZYMC;
	}

	public void setZYMC(String zYMC) {
		ZYMC = zYMC;
	}

	public String getNJ() {
		return NJ;
	}

	public void setNJ(String nJ) {
		NJ = nJ;
	}

	public String getYHKH() {
		return YHKH;
	}

	public void setYHKH(String yHKH) {
		YHKH = yHKH;
	}

	public String getMM() {
		return MM;
	}

	public void setMM(String mM) {
		MM = mM;
	}

	public String getOPENID() {
		return OPENID;
	}

	public void setOPENID(String oPENID) {
		OPENID = oPENID;
	}

	public int getISDEL() {
		return ISDEL;
	}

	public void setISDEL(int iSDEL) {
		ISDEL = iSDEL;
	}
}
