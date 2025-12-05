import * as fs from 'fs'

function countAdjacents(map: string[], posy: number, posx: number)
{
	if (map == undefined)
		return (0);
	let count: number = 0;
	let max_y = map.length - 1;
	let max_x = map[0].length - 1;
	// 1- check top
	if (posy > 0 && map[posy - 1][posx] == '@')
		count ++;
	// 2- check top left
	if (posy > 0 && posx > 0 && map[posy - 1][posx - 1] == '@')
		count ++;
	// 3- check left
	if (posx > 0 && map[posy][posx - 1] == '@')
		count ++;
	// 4- check bottom left
	if (posy < max_y && posx > 0 && map[posy + 1][posx - 1] == '@')
		count ++;
	// 5- check bottom
	if (posy < max_y && map[posy + 1][posx] == '@')
		count ++;
	// 6- check bottom right
	if (posy < max_y && posx < max_x && map[posy + 1][posx + 1] == '@')
		count ++;
	// 7- check right
	if (posx < max_x && map[posy][posx + 1] == '@')
		count ++;
	// 8- check top right
	if (posy > 0 && posx < max_x && map[posy - 1][posx + 1] == '@')
		count ++;
	return (count);
}

function solve(filename: string)
{
	let result: number = 0;
	let input: string = fs.readFileSync(filename, "ascii");
	let lines:string[] = input.split("\n");
	let count: number = 0;

	for(let i = 0; i < lines.length; i++)
	{
		for (let j = 0; j < lines[i].length; j++)
		{
			// console.log("("+i+","+j+") : " + lines[i][j]);
			if (lines[i][j] == '@')
			{
				count = countAdjacents(lines, i, j);
				if (count < 4)
				{
					// let chars = lines[i]?.split('');
					// chars[j] = 'x';
					// lines[i] = chars?.join('');
					result++;
				}
				// console.log(count + " " + result);
			}
		}
	}
	console.log("Result for " + filename + ": " + result);
}

// solve("example.txt");
solve("input.txt");