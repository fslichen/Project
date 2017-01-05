package architect.example1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import architect.example1.service.AnyService1;

@RestController
public class AnyController1 {
	@Autowired
	AnyService1 anyService;
	
	@RequestMapping(value = "/example1", method = RequestMethod.GET) 
	public void anyMethod() {
		anyService.anyMethod();
	}
}
