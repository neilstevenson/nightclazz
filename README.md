# nightclazz

Bonsoir!

Download [Management Center](https://hazelcast.org/download/#management-center).
You don't a license to inspect clusters with 1 or 2 servers, only 3 &amp; upwards.

## "*zz-1-un*" - Clustering

Run as is, at least one JVM.

## "*zz-2-deux*" - Caching

Add `@EnableCaching` to the _Application.java_ class.

Add `@Cacheable("fib")` to the _Fibonacci.calculate()_ method. Change the name
from "_fib_" if you like. Check the Management Center to prove all is as expected.

## "*zz-3-trois*" - Computation

Run a server or two, then run a client.

## "*zz-4-quatre*" - Combinaison

A mix of all the above, plus JDBC access (assuming [MySql](https://www.mysql.com/)).

Try changing _MyPersonneLoader.loadAllKeys()_ to use `return personneRepository.findIds();`

Run a server or two, and some clients.
