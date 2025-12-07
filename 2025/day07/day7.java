import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;

class day7 {
    public static void main (String[] args)
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

            HashSet<Integer> beams = new HashSet<Integer>();
            int startPos = lines[0].indexOf("S");

            beams.add(startPos);
            int result = 0;

            for (String line : lines)
            {
                int i = line.indexOf("^");
                while (i >= 0)
                {
                    if (beams.contains(i))
                    {
                        beams.remove(i);
                        beams.add(i - 1);
                        beams.add(i+1);
                        result++;
                    }
                    i = line.indexOf("^", i + 1);
                    // System.out.println("i is " + i + " "+ beams);
                }
                // System.out.println(line + " " + beams + " res " + result);
            }
            System.out.println(result);
            
        } catch (Exception e) {
            System.out.println("Exception, check your code");
        }

    }
}