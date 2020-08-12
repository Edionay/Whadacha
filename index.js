const fs = require("fs");
const modifyExif = require("modify-exif");
// const getExif = require("get-exif");

let photos = [];

fs.readdir(`${__dirname}\\Zap`, (error, filenames) => {
	filenames.forEach(async (filename) => {
		const date = filename.split("-")[1];
		const formattedDate = `${date.slice(0, 4)}:${date.slice(
			4,
			6
		)}:${date.slice(6, 8)}`;
		console.log(formattedDate);
		const imageFile = await fs.promises.readFile(
			`${__dirname}\\Zap\\${filename}`
		);
		const newFile = modifyExif(imageFile, (image) => {
			image.Exif["36867"] = formattedDate;
		});

		const newBuffer = new Buffer(newFile, "binary");

		fs.writeFile(`${__dirname}\\${date}`, newBuffer);
		console.log(newFile);
	});

	// fs.promises
	// 	.readFile(`${__dirname}\\Zap\\IMG-20170106-WA0017.jpeg`)
	// 	.then((imageBuffer) => {
	// 		console.log(imageBuffer);
	// 	})
	// 	.catch((error) => console.log(error));
	// console.log(items);
});
