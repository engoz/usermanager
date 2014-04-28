package com.eng.web;
import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.eng.domain.Member;
import com.eng.service.MemberService;


@Controller
@RequestMapping("/user")
public class MemberController {
	
	
	@Resource(name="memberService")
	private MemberService memberService;


	
	private static final Logger logger = LoggerFactory
			.getLogger(MemberController.class);


	
		@RequestMapping
		public String home(final Model model) {
		model.addAttribute("title",
				"This is Welcome page for Java Server Faces (JSF)");
		  this.memberService.findAllMembers();
		  model.addAttribute("allMembers", this.memberService); 
		  return "user";
		}
	 

		
    
		@RequestMapping(method=RequestMethod.POST)
	    public void processSubmit(final ModelMap model,
	    								@RequestParam("member:firstName") String firstName,
	    								@RequestParam("member:lastName") String lastName,
	    								@RequestParam("member:phone") String phone){
	    	
	  //  	System.out.println("post method cagrildi");
	  //  	System.out.println(firstName);    	
	    	System.out.println("post method cagirildi");
	    	this.memberService.insertFromAttributeMember(new Member(firstName,lastName,phone));
	    	 this.memberService.findAllMembers();
	    	model.addAttribute("allMembers", this.memberService); 
	    	
	    
	    }
	
	    
	    @RequestMapping(value="/edit",method = RequestMethod.POST)
	    public void editUser(final ModelMap model, @RequestParam("name:id") String id){
	    	
	    	System.out.println("userList metodu cagirildi");
	  //  	System.out.println(firstName);
	    	this.memberService.findMember(id);
	    	model.addAttribute("findMembers", this.memberService); 
	    }
	    
}
