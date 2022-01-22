# Ricart-Agrawala algorithm with token

## Descritpion

This is a simple implementation fo Ricart-Agrawala algorithm used for mutual exclusion in distributed systems. It was created as a project for course of Distributed Computing at Czech Technical University in Prague in winter semester 2021/2022.

It is programmed in Java 11 and uses Java RMI for communication between nodes. The nodes are deployed automatically to the target machines (in my case there were five machines in Virtual box) using Ansible. To change this, take a look at Ansible script and hosts file containing IPs of the target machines. It can be deployed to Docker container as well.

It only focuses on the core of the Ricart-Agrawala algorithm with token and ommits other important tasks the most important being monitoring and regenerating the token.

## Usage

Using it is straight forward as it has a menu with several options covering all the functionality of the program. When it needs input it asks for it and checks entered value.

At first, you want to start the node, set its IP and one of the neighbor's IP. It automatically connects to the network. If the started node is the first one no neighbor is entered. After starting all machines token must be generated. Then you can play with the network disconnecting and killing nodes, editing the shared variable and connecting new nodes.

## License

This program is available under GNU GPL v3 (https://www.gnu.org/licenses/gpl-3.0.en.html).

    Implementaion of Ricart-Agrawala algorithm with token
    Copyright (C) 2022  Roman Janků

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <https://www.gnu.org/licenses/>.
