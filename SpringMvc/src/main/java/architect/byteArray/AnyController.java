package architect.byteArray;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AnyController {
	@RequestMapping(value = "/byteArray", method = RequestMethod.POST)
	public void anyMethod(@RequestBody byte[] byteArray) {
		for (int i = 0; i < byteArray.length; i++) {
			System.out.print(byteArray[i] + ",");
		}
	}
}
