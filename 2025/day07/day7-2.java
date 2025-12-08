import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.HashMap;
import java.util.Map;

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

            Map<Integer, Long> beams = new HashMap<>();
            int startPos = lines[0].indexOf("S");

            beams.put(startPos, 1L);
            Long result = 1L;

            for (String line : lines)
            {
                int i = line.indexOf("^");
                while (i >= 0)
                {
                    if (beams.containsKey(i))
                    {
                        // System.out.print("i is " + i + " "+ beams.get(i) + " | ");
                        result += beams.get(i);
                        if (i > 0)
                            beams.merge(i - 1, beams.get(i), (a, b) -> a + b);
                        if (i < line.length())
                            beams.merge(i+1, beams.get(i), (a, b) -> a + b);
                        beams.remove(i);
                    }
                    i = line.indexOf("^", i + 1);
                }
                // System.out.println(line + " " + beams + " res " + result);
            }
            System.out.println(result);
            
        } catch (Exception e) {
            System.out.println("Exception, check your code");
        }

    }
}