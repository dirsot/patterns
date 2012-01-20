package pl.dirsot.bets.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class DoString {

	String badLetters = "";

	public static String checkNull(String s) {
		if (s == null) {
			return "";
		}
		return s;
	}

	public static String checkActivationKey(String s)
			throws EmptyValidationKeyException, InvalidValidationKeyException {
		try {
			s = checkNull(s);
			if (s == "")
				return "";

			Pattern pattern = Pattern.compile(".*[;-=].*");
			Matcher matcher = pattern.matcher(s);
			if (matcher.matches())
				throw new InvalidValidationKeyException();
			return s;
		} catch (InvalidValidationKeyException ex) {
			return "";
		}
	}
}
