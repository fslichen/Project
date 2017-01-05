package architect.example2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import architect.example2.service.AnyService2;

@RestController
public class AnyController2 {
	@Autowired
	AnyService2 anyService;
	
	@RequestMapping(value = "/example2", method = RequestMethod.GET)
	public void anyMethod() { 
		anyService.anyMethod();
	}
}
