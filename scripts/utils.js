const { spawnSync } = require('child_process');
const fs = require('fs');
const path = require('path');

const PATHS = {
	PROJECT: '/home/noman1001/Projects/PaperNet',
};

PATHS.ARTIFACTS = path.join(PATHS.PROJECT, '/artifacts');
PATHS.CONFIGS = path.join(PATHS.PROJECT, '/configs');

const runCommands = (commands, stdio = 'inherit') => {
	const children = [];
	for (let command of commands) {
		const tokens = command.split(' ');
		const child = spawnSync(tokens[0], tokens.slice(1), { stdio });
		if (child.status) {
			throw `Cannot continue execution because [${command}] failed to execute successfully`;
		}
		children.push(child);
	}

	return children;
};

const cleanFiles = (files) => {
	const commands = files
		.filter((file) => fs.statSync(file))
		.map((file) => `rm ${fs.statSync(file).isDirectory ? '-rf' : ''} ${file}`);

	runCommands(commands);
};

module.exports = { PATHS, runCommands, cleanFiles };
