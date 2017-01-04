package architect.example5;

import java.io.IOException;
import java.util.UUID;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class AnyController5 {
	@RequestMapping(value = "/anyMethod5", method = RequestMethod.POST) 
	public String anyMethod5(@RequestParam("file") MultipartFile multipartFile) {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		try {
			byte[] byteArray = multipartFile.getBytes();
			for (int i = 0; i < byteArray.length; i++) {
				System.out.print(byteArray[i] + " ");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println();
		return "The file id is" + UUID.randomUUID().toString();
	}
}
