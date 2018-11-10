package edu.ds.pubsub.type;
import java.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Solution {

	public static void main(String[] args) throws IOException {

		String result = compile();
		Files.write(Paths.get("output.txt"), result.getBytes());
	}

	public static String compile() {
		return "haha";
	}
}