package ca.mcgill.ecse321.projectgroup09.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Random util methods.
 */
public class MiscUtil {

	/**
	 * From tutorials. Convert an iterable object into an array list.
	 * @param <T>
	 * @param iterable
	 * @return
	 */
	public static <T> List<T> toList(Iterable<T> iterable) {
		List<T> resultList = new ArrayList<T>();
		for (T t : iterable) {
			resultList.add(t);
		}
		return resultList;
	}
}
