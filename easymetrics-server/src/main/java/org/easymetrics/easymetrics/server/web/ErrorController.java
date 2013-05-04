package org.easymetrics.easymetrics.server.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ErrorController extends BaseController {

	@RequestMapping(value = "/error.do")
	public String error(HttpServletRequest _request, Model model) {
		model.addAttribute("error", _request.getAttribute("error"));
		return forward("/exception/error");
	}

}
