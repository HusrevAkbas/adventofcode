import * as fs from 'fs';

let input = fs.readFileSync("input.txt", "ascii");
input = input.split("\n").join("");
let ranges = input.split(",");
let result = 0;

function  isInvalid(num: string) : boolean
{
	for (let delim = 1; delim <= num.length / 2; delim++)
	{
		if (num.length % delim == 0)
		{
			let re = new RegExp(String(".{"+delim+"}"), "g");
			let segments = num.match(re);
			if (!segments)
			{
				console.log("Error match");
				return(false);
			}
			let pat = segments[0];
			let last = segments.length - 1;
			while (last)
			{
				if (segments[last] != pat)
					break ;
				last--;
			}
			if (!last)
				return (true);
		}
	}
	return false;
}


for (let i = 0; i < ranges.length; i++)
{
	let nums = ranges[i].split("-");
	let start = Number(nums[0]);
	let end = Number(nums[1]);
	for (let n = start; start <= end ; start++)
	{
		let item = String(start);
		// if (item.length % 2 == 0)
		// {
		// 	let partlen = item.length / 2;
		// 	let p1 = item.substring(0, partlen);
		// 	let p2 = item.substring(partlen);
		// 	if (p1.length != p2.length)
		// 		console.log("YOU are doing it wrong");
		// 	if (p1 == p2)
		// 		result += start;
		// }
		if (isInvalid(item))
		{
			result += start;
			// console.log(item);
		}
	}
}
console.log(result);