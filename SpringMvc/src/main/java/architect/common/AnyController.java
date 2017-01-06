package architect.common;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController// Combines @Controller and @ResponseBody
public class AnyController {
	// Ctrl click the annotations for more information.
	@RequestMapping(value = "/common/{anyValue}", method = RequestMethod.POST)
	public void anyMethod(@PathVariable("anyPathVariable") String anyPathVariable,
			@RequestParam("anyRequestParam") String anyRequestParam,
			@RequestBody AnyDto anyDto) {
		System.out.println(anyPathVariable);
		System.out.println(anyDto);
	}
}
