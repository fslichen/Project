package architect.fileUpload;

import java.io.IOException;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class AnyController {
	@RequestMapping(value = "/fileUpload", method = RequestMethod.POST) 
	public void anyMethod(@RequestParam("file") MultipartFile multipartFile) {
		try {
			byte[] byteArray = multipartFile.getBytes();
			for (int i = 0; i < byteArray.length; i++) {
				System.out.print(byteArray[i] + " ");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
