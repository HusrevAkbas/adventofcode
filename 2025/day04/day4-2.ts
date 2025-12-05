import * as fs from 'fs'

function countAdjacents(map: any[][], posy: number, posx: number)
{
	if (map == undefined)
		return (0);
	let count: number = 0;
	let max_y = map.length - 1;
	let max_x = map[0].length - 1;
	// 1- check top
	if (posy > 0 && "@x".includes( map[posy - 1][posx]))
		count ++;
	// 2- check top left
	if (posy > 0 && posx > 0 && "@x".includes( map[posy - 1][posx - 1]))
		count ++;
	// 3- check left
	if (posx > 0 && "@x".includes( map[posy][posx - 1]))
		count ++;
	// 4- check bottom left
	if (posy < max_y && posx > 0 && "@x".includes( map[posy + 1][posx - 1]))
		count ++;
	// 5- check bottom
	if (posy < max_y && "@x".includes( map[posy + 1][posx]))
		count ++;
	// 6- check bottom right
	if (posy < max_y && posx < max_x && "@x".includes( map[posy + 1][posx + 1]))
		count ++;
	// 7- check right
	if (posx < max_x && "@x".includes( map[posy][posx + 1]))
		count ++;
	// 8- check top right
	if (posy > 0 && posx < max_x && "@x".includes( map[posy - 1][posx + 1]))
		count ++;
	return (count);
}

function count_removable_rolls(lines: any[][]): number
{
	let result: number = 0;
	let count: number = 0;

	for(let i = 0; i < lines.length; i++)
	{
		for (let j = 0; j < lines[i].length; j++)
		{
			if (lines[i][j] == '@')
			{
				count = countAdjacents(lines, i, j);
				if (count < 4)
				{
					lines[i][j] = 'x';
					result++;
				}
			}
		}
	}
	// console.log(lines.map(l => l.join('')).join('\n') + "\n");
	return (result);
}

function solve(filename: string)
{
	let input: string = fs.readFileSync(filename, "ascii");
	let lines: any[][] = input.split("\n").map(line => line.split(''));
	let result = 0;
	let count = 1;
	while (count > 0)
	{
		count = count_removable_rolls(lines);
		result += count;
		lines = lines.map(l => l.map(c => c === 'x' ? '.' : c))
		// console.log(count);
	}
	console.log("Result for " + filename + ": " + result);
}

// solve("example.txt");
solve("input.txt");