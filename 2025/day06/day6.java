import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class day6
{
    public static void main(String[] args)
    {
		if (args.length == 0)
		{
			System.out.println("Provide filename as argument");
			return ;
		}
		String filename = args[0];

        try {

			String input = Files.readString(Path.of(filename));

            String[] lines = input.split("\n");
            ArrayList<String[]> allOps = new ArrayList<String[]>();
            for (String line : lines)
            {
                // System.out.println("line " + line);
                String[] splitted = line.trim().split("\\s+");
                // System.out.println(Arrays.toString(splitted));
                allOps.add(splitted);
            }

            long result = 0;

            String[] ops = allOps.get(allOps.size() - 1);
            for (int i = 0; i < ops.length; i++)
            {
                long part = ops[i].equals("*") ? 1 : 0;
                for (int a = 0; a < allOps.size() - 1; a++)
                {
                    // multiply
                    if (ops[i].equals("*"))
                    {
                        part *= Long.parseLong(allOps.get(a)[i]);
                    }
                    else // add
                    {
                        part += Long.parseLong(allOps.get(a)[i]);
                    }
                }
                result += part;
            }

            System.out.println(result);

        } catch (Exception e) {
            System.out.println("Something is wrong");
        }
    }
}
