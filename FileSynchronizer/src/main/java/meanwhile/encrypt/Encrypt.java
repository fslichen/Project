package meanwhile.encrypt;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import meanwhile.file.FileInfo;
import meanwhile.file.FileUtility;

public class Encrypt extends FileUtility{
	
	private static byte alterBinaries(String binaryString) {
		if (binaryString.charAt(binaryString.length() - 1) == '1') {
			return getByte(binaryString.substring(0, binaryString.length() - 1) + "0"); 
		}
		else {
			return getByte(binaryString.substring(0, binaryString.length() - 1) + "1");
		}
	}
	public static void encryptOrDecryptFile(FileInfo fileInfo) throws IOException {
		InputStream in = new FileInputStream(fileInfo.getSource());
		OutputStream out = new FileOutputStream(fileInfo.getTarget());
		int length = -1;
		byte[] buffer = new byte[BUFFER_SIZE];
		while ((length = in.read(buffer)) != -1) {
			// Encrypt each byte within the buffer.
			for (int i = 0; i < buffer.length; i++) {
				if (buffer[i] >= 0) {
					String binaryString = String.format("%8s", Integer.toBinaryString(buffer[i] & 0xFF)).replace(' ', '0');
					System.out.println("number = " + buffer[i]);
					buffer[i] = alterBinaries(binaryString);
					System.out.println("altered number = " + buffer[i]);
				}
			}
			out.write(buffer, 0, length);
		}
		in.close();
		out.close();
	}
	public static byte getByte(String string) {
		byte number = 0;
		for (int i = string.length() - 1; i >=0; i--) {
			if (string.charAt(i) == '1') {
				number += Math.pow(2, string.length() - 1 - i);
			}
		}
		return number;
	}
}
