package tree.examples;

import java.io.File;
import java.io.IOException;

import utils.FileUtils;


public class GenerateImage {
	public static void main(String[] args) {
		String fileName = "3node_tree";
		File file = FileUtils.getFile(fileName + ".dot", TreeExamples.class);
		if(file !=null) {
			StringBuilder command = new StringBuilder();
			command.append("dot -Tpng ");
			command.append(file.getAbsolutePath());
			command.append(" -o ");
			command.append(file.getParentFile().getAbsolutePath() + File.separator + fileName + ".png");
			try {
				Process exec = Runtime.getRuntime().exec(command.toString());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
}
