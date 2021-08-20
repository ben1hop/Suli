#include <iostream>

#include "Directory.hpp"
#include "Entry.hpp"
#include "File.hpp"
#include "FileSystem.hpp"

int main()
{
	const FileSystem fs({
		new File(9019),
		new Directory({
			new Directory({
				new Directory({
					new File(6099),
					new File(946)
				}),
				new File(3909)
			}),
			new Directory({
				new File(2743)
			}),
		}),
		new Directory({}),
		new File(4206)
		});

	for (const Entry* entry : fs.getEntries())
	{
		std::cout << entry->getMeret() << std::endl;
	}

	fs.clear();
}