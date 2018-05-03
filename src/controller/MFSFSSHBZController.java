package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;

import bean.MFSFSSHBZ;
import utils.List2JsonUtils;
import utils.StringUtils;
import service.MFSFSSHBZService;
import service.commonService;
/**
 * Servlet implementation class MFSFSSHBZGLController
 */
@WebServlet(name = "MFSFSSHBZController", urlPatterns = { "/MFSFSSHBZController.do" })
public class MFSFSSHBZController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MFSFSSHBZController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");      
        response.setCharacterEncoding("utf-8");
       
		PrintWriter out = response.getWriter();
		String op = (String)request.getParameter("op");
		//System.out.println("OP " + op);
		if(StringUtils.isEmpty(op)) {
			out.print("0");
		}else if(op.equals("getAll")) {
			List<Map<String, Object> > list = commonService.getAllData(MFSFSSHBZ.class);
			if(list != null && !list.isEmpty()) {
				String json = List2JsonUtils.list2Json2String(list);
				out.print(json);
			}else {
				out.print("-1");
			}
		}else if(op.equals("delete")) {
			String iD = request.getParameter("ID");
			if(!StringUtils.isEmpty(iD)) {
				commonService.deleteByID(MFSFSSHBZ.class, iD);
				out.print("1");
			}else {
				out.print("0");
			}
		}else if(op.equals("update")) {
			String row = request.getParameter("row");
			if(!StringUtils.isEmpty(row)) {
				MFSFSSHBZ mfsfsshbz = JSON.parseObject(row, MFSFSSHBZ.class);
				MFSFSSHBZService mfsfsshbzService = new MFSFSSHBZService();
				mfsfsshbzService.updateData(mfsfsshbz);
				out.print("1");
			}else {
				out.print("0");
			}
		}else if(op.equals("personal")) {
			String Rydm = request.getParameter("Rydm");
			MFSFSSHBZ mfsfsshbz = commonService.getDataByRydm(MFSFSSHBZ.class, Rydm);
			if(mfsfsshbz != null) {
				out.print(mfsfsshbz.toJson());
			}else {
				out.print("0");
			}
		}else if(op.equals("clear")) {
			commonService.deleteAll(MFSFSSHBZ.class);
			out.print("1");
		}
		out.flush();
		out.close();
	}

}
