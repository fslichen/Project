package meanwhile.entity;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FileTree {
	private File file;
	private FileTree parent;
	private List<FileTree> children;
	public FileTree createChild(File childFile) {
		FileTree child = new FileTree();
		child.setFile(childFile);
		if (this.children == null) {
			this.children = new LinkedList<FileTree>();
		}
		this.children.add(child);
		child.setParent(this);
		return child;
	}
}
