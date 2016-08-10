package kr.ac.sungkyul.mysite.web.Board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.ac.sungkyul.mysite.dao.BoardDao;
import kr.ac.sungkyul.mysite.vo.BoardVo;

import kr.ac.sungkyul.web.Action;
import kr.ac.sungkyul.web.WebUtil;

public class ModifyFormAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			String no = request.getParameter("no");
			
			BoardDao dao = new BoardDao();
			BoardVo vo = dao.get(Long.parseLong(no));
			
				request.setAttribute("boardVo", vo);
				
		WebUtil.forward("/WEB-INF/views/board/modify.jsp", request, response);
	}

}
