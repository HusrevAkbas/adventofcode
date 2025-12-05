#include <iostream>
#include <fstream>
#include <sstream>
#include <vector>


int main()
{
	std::ifstream	file("input2.txt");
	std::string		line;
	std::stringstream	ss;
	int		dial = 50;
	char	dir;
	int		num;
	int		res = 0;
	std::cout << "dial init: " << dial << "\n";

	while (!file.eof())
	{
		std::getline(file, line);
		dir = line[0];
		num = std::atoi(&line[1]);
		for (size_t i = 0; i < num; i++)
		{
			if (dir == 'R')
			{
				dial++;
				if (dial < 0 || dial > 99)
					dial = ((dial % 100) + 100) % 100;
			}
			else if (dir == 'L')
			{
				dial--;
				if (dial < 0 || dial > 99)
					dial = ((dial % 100) + 100) % 100;
			}
			if (dial == 0)
				res++;
		}
		std::cout << line << " dial is " << dial << " result: " << res << "\n";
		line = "";
	}
	std::cout << "RESULT IS " << res << "\n";

	return (0);
}