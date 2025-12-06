// import java.io.FileReader;
// import java.io.BufferedReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.Comparator;
import java.util.ArrayList;

public class day5 {
	public static void main(String[] args) {

		if (args.length == 0)
		{
			System.out.println("Provide filename as argument");
			return ;
		}
		String filename = args[0];

		try
		{
			String input = Files.readString(Path.of(filename));
			
			// Split ranges and product ids, seperated by an empty line in input file
			String[] lines = input.split("\n\n");

			// Split id ranges into array
			String[] sranges = lines[0].split("\n");
			ArrayList<long[]>	lranges = rangesToLongs(sranges);
			ArrayList<long[]>	merged = new ArrayList<long[]>();

			// sort ranges according to range min
			Collections.sort(lranges, new Comparator<long[]>()
			{
				public int compare (long[] numbers, long[] others)
				{
					return numbers[0] > others[0] ? 1 : -1;
				}
			});
			
			merged.add(lranges.get(0));

				System.out.println("lranges.get(i)[0]");
				System.out.println(lranges.get(0)[0] + " " + lranges.get(0)[1]);
			// merge overlapping ranges
			for (int i = 1; i < lranges.size(); i++)
			{
				int lasti = merged.size() - 1;
				if (lranges.get(i)[0] <= merged.get(lasti)[1]
				&& lranges.get(i)[1] >= merged.get(lasti)[1])
				{
					long[] newrange = {merged.get(lasti)[0], lranges.get(i)[1]};
					merged.set(lasti, newrange);
				}
				else
					merged.add(lranges.get(i));

				System.out.println(lranges.get(i)[0] + " " + lranges.get(i)[1]);
			}

			long result = 0L;

				System.out.println("pair[0] +  + pair[1]");
			for (int i = 0; i < merged.size(); i++)
			{
				long[] pair = merged.get(i);
				result += pair[1] - pair[0] + 1;
				System.out.println(pair[0] + " " + pair[1]);
			}

/*
	We only need id ranges for second part
 */
			// Split product ids into array
			// String[] productIds = lines[1].split("\n");
			// long[] lids = idsToLongs(productIds);

			// for (long id : lids)
			// {
			// 	if (compare(lranges, id))
			// 		result++;
			// }

			System.out.println("Result is " + result);

		} catch (Exception e) {
			System.out.println("Something is wrong");
		}
		
	}

	public static ArrayList<long[]> rangesToLongs(String[] ranges)
	{
		ArrayList<long[]> rangesLong = new ArrayList<long[]>();
		for (String range : ranges)
		{
			long[] limits = {0,0};
			String[] edges = range.split("-");
			// System.out.println(edges[0] + " " + edges[1]);
			limits[0] = Long.parseLong(edges[0]);
			limits[1] = Long.parseLong(edges[1]);
			rangesLong.add(limits);
		}
		return (rangesLong);
	}

	// public static long[] idsToLongs(String[] nums)
	// {
	// 	long [] lnums = new long[nums.length];

	// 	for (int i = 0; i < nums.length; i++)
	// 		lnums[i] = Long.parseLong(nums[i]);
		
	// 	return (lnums);
	// }

	// public static boolean compare(long[] ranges, long id)
	// {
	// 	for (int i = 0; i < ranges.length; i += 2)
	// 	{
	// 		if (id >= ranges[i] && id <= ranges[i+1])
	// 			return (true);
	// 	}
	// 	return (false);
	// }
}