package edu.ds.pubsub.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class CompilerService {

	public String compile(String code) throws IOException, InterruptedException {

		// write and create java class.
		Files.write(Paths.get("Solution.java"), wrap(code).getBytes());

		Runtime.getRuntime().exec("javac Solution.java").waitFor();
		Runtime.getRuntime().exec("java Solution").waitFor();

		List<String> bytes = Files.readAllLines(Paths.get("output.txt"));
		if (!bytes.isEmpty()) {
			return bytes.get(0);

		} else
			return "No output was received from the function.";
	}

	private String wrap(String code) {

		// wrapping the code into a java file..which writes the output to the output.txt
		return "import java.*;\n" + "import java.io.IOException;\n" + "import java.nio.file.Files;\n"
				+ "import java.nio.file.Paths;\n" + "\n" + "public class Solution {\n" + "\n"
				+ "	public static void main(String args[])  throws IOException {\n" + "\n"
				+ "		String result = compile();\n"
				+ "		Files.write(Paths.get(\"output.txt\"), result.getBytes());\n" + "	}\n"
				+ "public static String compile(){" + code + "}" + "}";

	}
}
