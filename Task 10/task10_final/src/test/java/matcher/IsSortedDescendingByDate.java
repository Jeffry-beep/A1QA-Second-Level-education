package matcher;

import java.util.List;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

import entity.Test;

public class IsSortedDescendingByDate extends TypeSafeMatcher<List<Test>> {

	@Override
	public void describeTo(Description description) {
		description.appendText("sorted by date (test start time) in descending order");
	}

	@Override
	protected boolean matchesSafely(List<Test> item) {
		boolean isOrdered = true;
		for (int i = 0; i < item.size() - 1; i++) {
			if (item.get(i).getStartTime().isBefore(item.get(i + 1).getStartTime())) {
				isOrdered = false;
				break;
			}
		}
		return isOrdered;
	}

	public static Matcher<List<Test>> sortedDescendingByDate() {
		return new IsSortedDescendingByDate();
	}

}
