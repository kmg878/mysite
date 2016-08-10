package kr.ac.sungkyul.mysite.web.Board;


import kr.ac.sungkyul.mysite.web.main.MainAction;
import kr.ac.sungkyul.web.Action;
import kr.ac.sungkyul.web.ActionFactory;

public class BoardActionFactory extends ActionFactory {

	@Override
	public Action getAction(String actionName) {
		Action action=null;
		if("list".equals(actionName)){
			action=new BoardListFormAction();
		}else if("writeform".equals(actionName)){
			action=new WriterFormAction();
		}else if("write".equals(actionName)){
			action=new WriterAction();
		}else if("view".equals(actionName)){
			action=new ViewAction();
		}else if("delete".equals(actionName)){
			action=new DeleteAction();
		}else if("modifyform".equals(actionName)){
			action=new ModifyFormAction();
		}else if("modify".equals(actionName)){
			action=new ModifyAction();
		}else{
			action = new  MainAction();
		}
		return action;
	}

}
