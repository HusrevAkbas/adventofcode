package aoc.day08;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.Iterator;

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

            Map<Pos3d, Integer> posList = new LinkedHashMap<Pos3d, Integer>();

            for (String l : lines)
            {
                String[] numbers = l.split(",");
                posList.put(new Pos3d(
                    Double.parseDouble(numbers[0]),
                    Double.parseDouble(numbers[1]),
                    Double.parseDouble(numbers[2])
                ), 0);
            }
            
            // init 3d positions
            // 1 - find first pos that doesnt have a circuit
            // 2 - find next connected positions until hit one of previous
            // 3 - increase circuit number
            // 1 - find next connected positions until hit one of previous
            Pos3d   current = null;
            Pos3d   next = null;
            Pos3d   previous = null;

            int circuit = 1;

            while (posList.containsValue(0))
            {
                // find first unassigned node and assign a circuit
                if (next == null)
                {
                    next = findPosWithValueZero(posList);
                    posList.put(next, circuit);
                }
                else
                {
                    current = next;
                    next = findNextClosePos(posList, current, previous);
                    next.printValues();
                    previous = current;
                    if (next == null || posList.get(next) > 0)
                    {
                        circuit++;
                        next = null;
                        previous = null;
                        current = null;
                        continue ;
                    }
                    posList.put(next, circuit);
                }
            }

            posList.forEach((key, val) -> System.out.println(key + " : " + val));

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

    public static Pos3d findNextClosePos(Map<Pos3d, Integer> map, Pos3d current, Pos3d previous)
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
            if (pos.isEqual(current) || (previous != null && pos.isEqual(previous)))
                continue ;
            if (target == null)
                target = pos;
            else if (current.distanceTo(pos) < current.distanceTo(target))
                target = pos;
        }
        return target;
    }
}