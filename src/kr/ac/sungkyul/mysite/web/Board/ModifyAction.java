package kr.ac.sungkyul.mysite.web.Board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.ac.sungkyul.mysite.dao.BoardDao;
import kr.ac.sungkyul.mysite.dao.UserDao;
import kr.ac.sungkyul.mysite.vo.BoardVo;
import kr.ac.sungkyul.mysite.vo.UserVo;
import kr.ac.sungkyul.web.Action;
import kr.ac.sungkyul.web.WebUtil;

public class ModifyAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String no = request.getParameter("no");
		
	      Long noLong = Long.parseLong(no);
	      String title = request.getParameter("title");
	      String content = request.getParameter("content");
	    

	      BoardVo vo = new BoardVo();
	      vo.setNo(noLong);
	      vo.setTitle(title);
	      vo.setContent(content);
	      BoardDao dao = new BoardDao();
	      dao.update(vo);


	      WebUtil.redirect("/mysite/board?a=list", request, response);
		
	}

}
