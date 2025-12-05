// import java.io.FileReader;
// import java.io.BufferedReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;

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
			Long[]	lranges = rangesToLongs(sranges);

			int result = 0;
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

			System.out.println(result);

		} catch (Exception e) {
			System.out.println("Something is wrong");
		}
		
	}

	public static Long[] rangesToLongs(String[] ranges)
	{
		Long[] rangesLong = new Long[ranges.length * 2];
		int i = 0;
		for (String range : ranges)
		{
			String[] edges = edges = range.split("-");
			// System.out.println(edges[0] + " " + edges[1]);
			rangesLong[i] = Long.parseLong(edges[0]);
			rangesLong[i + 1] = Long.parseLong(edges[1]);
			i += 2;
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