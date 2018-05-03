package controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import bean.YH;
import utils.DataBaseUtil;

/**
 * Servlet implementation class loginController
 */
@WebServlet(name = "loginController", urlPatterns = { "/loginController.do" })
public class loginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public loginController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");      
        response.setCharacterEncoding("utf-8");
		String accountNo = (String)request.getParameter("accountNo");
		String password = (String)request.getParameter("password");
		//System.out.println(password + " " + accountNo); 
		PrintWriter out = response.getWriter();
		String sql = "select id, openid, rydm, xm, mm, xymc, zymc, nj, yhkh, isdel from t_yh where rydm = ? and isdel = ?";
		YH yh = DataBaseUtil.queryForBean(sql, YH.class, accountNo, 0);
		if(accountNo == null || password == null || password.isEmpty() || accountNo.isEmpty()) {
			out.print("-1");
		}else if(yh == null){
			out.println("-2");//用户不存在
		}else{
			String encodePass = "";
			try {
				 encodePass = utils.SHA1Util.sha1Encode(password);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				encodePass = "";
				e.printStackTrace();
			}
			if(encodePass.equals(yh.getMM())){
				out.print("0");
				HttpSession session = request.getSession(); 
				session.setAttribute("yh", yh);
				//System.out.println(yh.toJson());
			}else
				out.print("-3");//密码错
		}
		out.flush();
		out.close();
	}
}
