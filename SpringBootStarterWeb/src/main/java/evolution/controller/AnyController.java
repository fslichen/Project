package evolution.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import evolution.service.AnyService;

@RestController
public class AnyController {
	@Autowired// In order to autowire, don't forget to define the bean inside Application.
	AnyService anyService;
	
	@RequestMapping(value = "/hello", method = RequestMethod.GET)
	public void anyControllerMethod() {
		anyService.anyServiceMethod();
	}
}
