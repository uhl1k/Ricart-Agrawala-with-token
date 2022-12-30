# Ricart-Agrawala algorithm with token

**Author:** uhl1k (Roman Janku) https://uhl1k.com

This is a simple implementation fo Ricart-Agrawala algorithm used for mutual exclusion in distributed systems. It was created as a project for course of Distributed Computing at Czech Technical University in Prague in winter semester 2021/2022.

It is programmed in Java 11 and uses Java RMI for communication between nodes. The nodes are deployed automatically to the target machines (in my case there were five machines in Virtual box) using Ansible. To change this, take a look at Ansible script and hosts file containing IPs of the target machines. The Ansible script is in `deploy.yml`.

It only focuses on the core of the Ricart-Agrawala algorithm with token and ommits other important tasks the most important being monitoring and regenerating the token. It can handle only one node being dropped without notifying others.

## Compilation

It is recommended to use Maven to build the application. File `pom.xml` defines the build proccess for the application. Use

```
mvn jar
```

to compile the application and create runnable jar.

## Usage

First, distribute the `*.jar` archive to machines you want the application to run on. Each running instance should run on its own machine either virtual or physical. There should be at least two instances in network for the algorithm to work as intended.

Then you can start to launch the applications one by one. To launch the application use

```
java -jar JAR_FILE
```

After launching the application a menu will be shown. Type `0` and confirm by pressing enter to start the RMI. The application will ask for IP address of the machine it runs on. Type the IP address and confirm by enter.

Then will application ask weather it should connect to a network. If you want to connect the application to the network, write `y`. Then the application asks for IP address of one other node in the network. You can enter IP address of any node in networ, the remaining nodes will be added automatically. To not add application to network type `n`.

When you are starting first node in a network do not add it to the network. It will be in an a network by it self. When starting the second and following nodes connect it to the network. After this the application will return to the menu.

For the algorithm to work you need to generate exactly one token in the network. Use option `7` in the menu. The algorithm does not check weather the token is still present. If it dissapears with dropped node you will need to regenerate it.

By using option `1` you can gracefully disconnect the node from the network. If it has token it is passed to another node and all nodes are notified the node is disconnecting. Option `2` kills the node without passing th etoken or notifying others.

The application does not terminate after using option `1` or `2`. It disconnects from network and can be connected back again. To terminate the application use option `8`.

By using option `5` you can edit the shared variable. You can start editing on more than one node at the same time but you will be able to only edit it on a node that has a token. After this nodes finishes the edit it passes the token to another node. This node then can edit the variable and so on.

Using other menu options you can list nodes in the network, current state of this node and others.

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
