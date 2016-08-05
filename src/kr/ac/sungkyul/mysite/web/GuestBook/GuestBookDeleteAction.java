package kr.ac.sungkyul.mysite.web.GuestBook;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.ac.sungkyul.mysite.dao.GuestBookDao;
import kr.ac.sungkyul.mysite.vo.GuestBookVo;
import kr.ac.sungkyul.web.Action;
import kr.ac.sungkyul.web.WebUtil;

public class GuestBookDeleteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
    	String passWord = request.getParameter("password");
    	String no = request.getParameter("no");
    	System.out.println(no);
    	GuestBookVo vo = new GuestBookVo();
    	vo.setNo(Long.parseLong(no));
    	vo.setPassWord(passWord);
    	
    	
    	GuestBookDao dao = new GuestBookDao();
    	dao.delete(vo);
    	
    	 WebUtil.redirect("/mysite/gb?a=list", request, response);

	}

}
