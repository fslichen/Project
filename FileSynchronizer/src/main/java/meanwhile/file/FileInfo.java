package meanwhile.file;

import java.util.List;

import lombok.Data;

@Data
public class FileInfo {
	private String method;
	private String source;
	private String target;
	private String documentType;
	private boolean overwritten;
	private boolean active;
	private List<String> files;
}
