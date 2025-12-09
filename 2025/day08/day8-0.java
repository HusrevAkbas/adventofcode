package aoc.day08;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.Iterator;
import java.util.Collections;

class day8 {
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

            Map<Pos3d, Integer> posMap = new LinkedHashMap<Pos3d, Integer>();

            for (String l : lines)
            {
                String[] numbers = l.split(",");
                posMap.put(new Pos3d(
                    Double.parseDouble(numbers[0]),
                    Double.parseDouble(numbers[1]),
                    Double.parseDouble(numbers[2])
                ), 0);
            }
            
            
            
        } catch (Exception e) {
            System.err.println("Something is wrong, check your code!");
        }
    }

    public static Pos3d findPosWithValueZero(Map<Pos3d, Integer> map)
    {
        Set<Pos3d> keys = map.keySet();
        Pos3d   pos = null;
        
        Iterator<Pos3d> it = keys.iterator();
        while (it.hasNext())
        {
            pos = it.next();
            if (map.get(pos) == 0)
                return (pos);
        }
        return null;
    }

    public static Pos3d findNextClosePos(Map<Pos3d, Integer> map, Pos3d current)
    {
        if (current == null)
            return null;

        Set<Pos3d> keys = map.keySet();
        Iterator<Pos3d> it = keys.iterator();
        Pos3d pos = null;
        Pos3d target = null;

        while (it.hasNext())
        {
            pos = it.next();
            if (pos.isEqual(current))
                continue ;
            if (target == null)
                target = pos;
            else if (current.distanceTo(pos) < current.distanceTo(target))
                target = pos;
        }
        return target;
    }
}