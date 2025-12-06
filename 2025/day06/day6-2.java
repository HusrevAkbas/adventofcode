import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;

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

            // For second puzzle I must keep whitespaces and work char by char

            // ArrayList<String[]> allOps = new ArrayList<String[]>();
            // for (String line : lines)
            // {
            //     // System.out.println("line " + line);
            //     String[] splitted = line.trim().split("\\s+");
            //     // System.out.println(Arrays.toString(splitted));
            //     allOps.add(splitted);
            // }

            ArrayList<String> numbers = new ArrayList<>();
            long result = 0;
            
            for (int i = lines[0].length() - 1; i >= 0; i--)
            {
                String op = "";
                String num = "";
                long partial_result = 0;

                for (String l : lines)
                {
                    if (Character.isDigit(l.charAt(i)))
                        num += l.substring(i,i+1);
                    if (l.charAt(i) == ('*'))
                    {
                        op = "*";
                    }
                    if (l.charAt(i) == ('+'))
                    {
                        op = "+";
                    }

                }
                if (num.isEmpty())
                    continue;
                
                numbers.add(num);

                if (op.equals("*"))
                {
                    result += multistr(numbers);
                    numbers.clear();

                }
                if (op.equals("+"))
                {
                    result += sumstr(numbers);
                    numbers.clear();

                }
            }

            System.out.println("Solution is : " + result);

        } catch (Exception e) {
            System.out.println("Something is wrong");
        }
    }

    public static long sumstr(ArrayList<String> numbers)
    {
        long result = 0;
        for (int i = 0; i < numbers.size(); i++)
        {
            System.out.print(" + " + numbers.get(i));
            result += Long.parseLong(numbers.get(i));
        }
        System.out.println(" = " + result);
        return result;
    }

    public static long multistr(ArrayList<String> numbers)
    {
        long result = 1;
        for (int i = 0; i < numbers.size(); i++)
        {
            System.out.print(" * " + numbers.get(i));
            result *= Long.parseLong(numbers.get(i));
        }
        System.out.println(" = " + result);
        return result;
    }
}
