package io.jayms.dictattack;

import java.io.File;
import java.io.FileNotFoundException;

public class Main {
	
	private static final String ATTACK_ZIP = "secret.zip";
	private static final String TARGET_FILE = "secret.txt";
	private static final String DICT_FILE = "dictionary.txt";
	
	public static void main(String[] args) throws FileNotFoundException {
		DictionaryAttack attack = new DictionaryAttack(ATTACK_ZIP, TARGET_FILE, new File(DICT_FILE));
		attack.start();
	}

}
