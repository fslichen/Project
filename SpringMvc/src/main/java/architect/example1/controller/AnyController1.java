package architect.example1.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import architect.example1.dto.AnyDto;

@RestController// Combines @Controller and @ResponseBody
public class AnyController1 {
	// Ctrl click the annotations for more information.
	@RequestMapping(value = "/example1/{anyValue}", method = RequestMethod.POST)
	public void anyMethod(@PathVariable("anyPathVariable") String anyPathVariable,
			@RequestParam("anyRequestParam") String anyRequestParam,
			@RequestBody AnyDto anyDto) {
		System.out.println(anyPathVariable);
		System.out.println(anyDto);
	}
}
