package io.jayms.dictattack;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import net.lingala.zip4j.ZipFile;
import net.lingala.zip4j.exception.ZipException;

public class DictionaryAttack {
	
	public static List<String> getStringsInFile(File dictFile) throws FileNotFoundException {
		List<String> result = new ArrayList<>();
		Scanner in = new Scanner(dictFile);
		while (in.hasNext()) {
			result.add(in.nextLine());
		}
		in.close();
		return result;
	}

	private String target;
	private List<String> dictionary;
	private ZipFile zip;
	
	public DictionaryAttack(String zipFile, String target, File dictFile) throws FileNotFoundException {
		this.zip = new ZipFile(zipFile);
		this.target = target;
		this.dictionary = getStringsInFile(dictFile);
	}
	
	public void start() throws FileNotFoundException {
		System.out.println("Dictionary Size: " + dictionary.size());
		for (int i = 0; i < dictionary.size(); i++) {
			String password = dictionary.get(i);
			System.out.println("Checking " + password);
			zip.setPassword(password.toCharArray());
			try {
				zip.extractFile(target, "dest");
				System.out.println("Successful extraction, after " + i + " iterations, with password: " + password);
				break;
			} catch (ZipException e) {
			}
		}
		
		System.out.print("Target Contents: ");
		File targetFile = new File("dest/" + target);
		for (String secret : getStringsInFile(targetFile)) {
			System.out.println(secret);
		}
		
		System.out.println("Dictionary Attack Complete");
	}
	
	public ZipFile getZipFile() {
		return zip;
	}
	
	public List<String> getDictionary() {
		return dictionary;
	}
	
}
