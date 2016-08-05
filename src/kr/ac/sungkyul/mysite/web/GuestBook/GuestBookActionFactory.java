package kr.ac.sungkyul.mysite.web.GuestBook;

import kr.ac.sungkyul.mysite.web.main.MainAction;
import kr.ac.sungkyul.web.Action;
import kr.ac.sungkyul.web.ActionFactory;

public class GuestBookActionFactory extends ActionFactory  {

	@Override
	public Action getAction(String actionName) {
		Action action=null;
		if("list".equals(actionName)){
			action=new GuestBookListFormAction();
		}else if("insert".equals(actionName)){
			action=new GuestBookInsertAction();
		}else if("deleteform".equals(actionName)){
			action=new GuestBookDeleteFormAction();
		}else if("delete".equals(actionName)){
			action=new GuestBookDeleteAction();
		}else{
			action = new  MainAction();
		}
		return action;
	}
	

}
