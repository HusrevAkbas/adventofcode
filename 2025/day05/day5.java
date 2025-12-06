// import java.io.FileReader;
// import java.io.BufferedReader;
import java.nio.file.Files;
import java.nio.file.Path;

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
			long[]	lranges = rangesToLongs(sranges);
			// for (long r : lranges)
			// 	System.out.print(r + " ");

			// Split product ids into array
			String[] productIds = lines[1].split("\n");
			long[] lids = idsToLongs(productIds);

			int result = 0;
			for (long id : lids)
			{
				if (compare(lranges, id))
					result++;
			}

			System.out.println(result);

		} catch (Exception e) {
			System.out.println("Something is wrong");
		}
		
	}

	public static long[] rangesToLongs(String[] ranges)
	{
		long[] rangesLong = new long[ranges.length * 2];
		int i = 0;
		for (String range : ranges)
		{
			String[] edges = range.split("-");
			// System.out.println(edges[0] + " " + edges[1]);
			rangesLong[i] = Long.parseLong(edges[0]);
			rangesLong[i + 1] = Long.parseLong(edges[1]);
			i += 2;
		}
		return (rangesLong);
	}

	public static long[] idsToLongs(String[] nums)
	{
		long [] lnums = new long[nums.length];

		for (int i = 0; i < nums.length; i++)
			lnums[i] = Long.parseLong(nums[i]);
		
		return (lnums);
	}

	public static boolean compare(long[] ranges, long id)
	{
		for (int i = 0; i < ranges.length; i += 2)
		{
			if (id >= ranges[i] && id <= ranges[i+1])
				return (true);
		}
		return (false);
	}
}