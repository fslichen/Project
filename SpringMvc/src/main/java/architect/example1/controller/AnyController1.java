package architect.example1.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import architect.example1.entity.AnyEntity1;

@RestController
public class AnyController1 {
	@RequestMapping(value = "/example1/{anyValue}", method = RequestMethod.POST)
	public void anyMethod(@RequestBody AnyEntity1 anyEntity1, 
			@PathVariable("anyValue") String anyValue) {
		System.out.println(anyEntity1);
		System.out.println(anyValue);
	}
}
