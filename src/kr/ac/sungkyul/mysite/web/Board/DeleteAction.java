package kr.ac.sungkyul.mysite.web.Board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.ac.sungkyul.mysite.dao.BoardDao;
import kr.ac.sungkyul.mysite.vo.BoardVo;
import kr.ac.sungkyul.mysite.vo.UserVo;
import kr.ac.sungkyul.web.Action;
import kr.ac.sungkyul.web.WebUtil;

public class DeleteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 // 인증 여부 확인
	      HttpSession session = request.getSession();
	      if (session == null) {
	         WebUtil.redirect("/mysite/main", request, response);
	         return;
	      }

	      UserVo authUser = (UserVo) session.getAttribute("authUser");
		if (authUser == null) {
	         WebUtil.redirect("/mysite/main", request, response);
	         return;
	      }
	      
	      Long userNo = authUser.getNo();
	      String no = request.getParameter("no");
	      Long noLong = Long.parseLong(no);
	      
	      BoardVo vo = new BoardVo();
	    	vo.setNo(noLong);
	    	vo.setUserNo(userNo);
		
		BoardDao dao = new BoardDao();
    	dao.delete(vo);
		
    	 WebUtil.redirect("/mysite/board?a=list", request, response);

	}

}
