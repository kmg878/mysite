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

public class CommentAction implements Action {

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
	      
	      Long Userno = authUser.getNo();
		
			String no = request.getParameter("no");
			Long noLong = Long.parseLong(no);
			
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			
			//부모의 값이 들어간 vo
			BoardVo vo = new BoardVo();
			BoardDao dao = new BoardDao();
			vo = dao.get(noLong);
			Long pGroupNo = vo.getGroupNo();
			int pDepth = vo.getDepth();
		
			System.out.println(pGroupNo);
			
			BoardVo vo2 = new BoardVo();
			
			vo2.setTitle(title);
			vo2.setContent(content);
			vo2.setViewCount(0);
			vo2.setGroupNo(pGroupNo);
			
			vo2.setDepth(pDepth+1);
			vo2.setUserNo(Userno);
   	
    	
			
			dao.comment(vo2);
    	
    	 WebUtil.redirect("/mysite/board?a=list", request, response);

	}

}
