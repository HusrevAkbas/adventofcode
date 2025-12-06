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

        System.out.println("Works");
        try {

			String input = Files.readString(Path.of(filename));

            String[] lines = input.split("\n");
            ArrayList<String[]> allOps = new ArrayList<String[]>();
            for (String line : lines)
            {
                allOps.add(line.split(" "));
            }
            for (int i = 0; i < allOps.size() - 1; i++)
            {
                for (String s : allOps.get(i))
                {
                    // s = s.trim();
                    if (s.length() > 0)
                        // System.out.println(s);
                        System.out.println(Integer.parseInt(s));
                }
                    // System.out.println(String.join(" ",allOps.get(i)));
            }

        } catch (Exception e) {
            System.out.println("Something is wrong");
        }
    }
}
