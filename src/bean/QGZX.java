package bean;

import java.math.BigDecimal;
import java.util.UUID;

import annotation.Column;
import annotation.Table;

@Table(tableName="t_qgzx")
public class QGZX {

	@Column(field="id", type="varchar(50)", primaryKey=true, defaultNull=false)
	private String ID;
	
	@Column(field="yrdw", type="varchar(50)")
	private String YRDW;//���˵�λ
	
	@Column(field="gwmc", type="varchar(50)")
	private String GWMC;//��λ����
	
	@Column(field="rydm", type="varchar(20)")
	private String RYDM;//ѧ�š���Ա����
	
	@Column(field="xm", type="varchar(50)")
	private String XM;//����
		
	@Column(field="yhkh", type="varchar(50)")
	private String YHKH;//���п���

	@Column(field="xymc", type="varchar(50)")
	private String XYMC;//ѧԺ����
	
	@Column(field="ffny", type="varchar(50)")
	private String FFNY;//��������
	
	@Column(field="je", type="decimal(10,2)")
	private BigDecimal JE;//���
	
	@Column(field="isdel", type="int(1)")
	private int ISDEL;
	
	public String toJson() {
		return "[{\"id\":\"" + ID + "\", \"gwmc\":\"" + GWMC + "\", \"xymc\":\"" + XYMC 
				+ "\", \"rydm\":\"" + RYDM + "\", \"xm\":\"" + XM + "\", \"ffny\":\""
				+ FFNY + "\", \"yrdw\":\"" + YRDW + "\", \"yhkh\":\"" + YHKH +"\", \"je\":"
				+ JE + ", \"isdel\":" + ISDEL + "}]";
	}
	
	public QGZX() {
		ID = UUID.randomUUID().toString().replaceAll("-", "");
		XYMC = "-";
		RYDM = "-";
		XM = "-";
		YRDW = "-";
		FFNY = "-";
		YHKH = "-";
		GWMC = "-";
		JE = new BigDecimal("0");
		ISDEL = 2;
	}

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public String getYRDW() {
		return YRDW;
	}

	public void setYRDW(String yRDW) {
		YRDW = yRDW;
	}

	public String getGWMC() {
		return GWMC;
	}

	public void setGWMC(String gWMC) {
		GWMC = gWMC;
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

	public String getYHKH() {
		return YHKH;
	}

	public void setYHKH(String yHKH) {
		YHKH = yHKH;
	}

	public String getXYMC() {
		return XYMC;
	}

	public void setXYMC(String xYMC) {
		XYMC = xYMC;
	}

	public String getFFNY() {
		return FFNY;
	}

	public void setFFNY(String fFNY) {
		FFNY = fFNY;
	}

	public BigDecimal getJE() {
		return JE;
	}

	public void setJE(BigDecimal jE) {
		JE = jE;
	}

	public int getISDEL() {
		return ISDEL;
	}

	public void setISDEL(int iSDEL) {
		ISDEL = iSDEL;
	}
}
