The project is written in Java, SDK: openjdk-21. Recommend to use IntelliJ IDEA.

For the original c++ code, there is an error when Printer wang to instants a Cartridge Class in its prepare method, there
is no correspond constructor (only has one perimeter). Moreover, the Printer instantiated the Cartridge Class in its
method which violate the idea of Open-closed Principle as well. For the idea of DIP: high level modules should not
depend on low level modules; both should depend on abstractions. The Printer in the original code is dependent on the
non-abstraction Cartridge Class - violate DIP.


Add two interfaces, IPrinter and ICartridge, these are abstractions for the Printer and Cartridge. With the help of
these interface, I adhere to the idea of DIP, making high-level modules and low-level modules depend on the abstractions.
At the same time, it follows the idea of OCP as well, since it can change the implement of methods easily.