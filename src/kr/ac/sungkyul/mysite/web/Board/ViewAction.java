package kr.ac.sungkyul.mysite.web.Board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.ac.sungkyul.mysite.dao.BoardDao;
import kr.ac.sungkyul.mysite.dao.UserDao;
import kr.ac.sungkyul.mysite.vo.BoardVo;
import kr.ac.sungkyul.mysite.vo.UserVo;
import kr.ac.sungkyul.web.Action;
import kr.ac.sungkyul.web.WebUtil;

public class ViewAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
    	String no = request.getParameter("no");
    	Long noLong =Long.parseLong(no);
    	BoardDao dao = new BoardDao();
    	BoardVo vo = dao.get(Long.parseLong(no));
    	dao.updateViewCount(noLong);
    	
		request.setAttribute("BoardVo", vo);
		WebUtil.forward("/WEB-INF/views/board/view.jsp", request, response);
	}

}
