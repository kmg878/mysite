package kr.ac.sungkyul.mysite.web.Board;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.ac.sungkyul.mysite.dao.BoardDao;
import kr.ac.sungkyul.mysite.vo.BoardVo;
import kr.ac.sungkyul.web.Action;
import kr.ac.sungkyul.web.WebUtil;

public class BoardListFormAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		BoardDao dao = new BoardDao();
		List<BoardVo> list = dao.getList();
		int beginPage;
		
		String page =request.getParameter("page");
		if( page == null || "".equals(page)){
	         page = "1";
	         
	      }
		int pageInt=Integer.parseInt(page);
	      
		List<BoardVo> list2 = dao.getListPage(pageInt);
		//request 범위(scope)에list 객체를 저장
//		request.setAttribute("list",list);
		request.setAttribute("list2",list2 );
	
		
		
		int pageLength=5;
		int currentBlock = (int)Math.ceil((double)pageInt/pageLength);
		
		int currentPage=pageInt;
		int total =(int)Math.ceil((double)list.size()/pageLength);
		
		
		 beginPage=(currentBlock-1)*5+1;
		int endPage=currentBlock*5;
		if(endPage >total){
			endPage=total;
		}
		
	
		
		request.setAttribute("beginPage", beginPage);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("total", total);
		
		
		
		WebUtil.forward("/WEB-INF/views/board/list.jsp", request, response);

	}

}
