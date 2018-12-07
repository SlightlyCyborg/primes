# Funding Circle Skills Test in Clojure

## API
Generating primes uses lazy-seqs
```
(take 10 (gen-primes))
```

## Using lein
To run with default 10 primes
```
lein run
```

or

```
lein run 15
```

for more or less


## Using assembled jar
I made an uberjar called `run.jar` for you to run if you do not have leiningen

```
java -jar run.jar
```

If you wish to make this jar yourself just run:

```
lein uberjar
```

It will save the standalone jar to ./target/

## Tests
To run the tests found in ./tests/: 
```
lein test
``` 

## Time complexity discussion
Assume log = natural log

The upper bound on the value of the nth prime is nlog(n) + nlog(log(n). See [Wikipedia: prime number bounds](https://en.wikipedia.org/wiki/Prime_number_theorem#Approximations_for_the_nth_prime_number)

If M is the largest prime number generated then gen-next-prime will take O(M/logM * logM) = O(M) since there will be M/logM primes to test as divisors and on average logM numbers before the next prime. See [Wikipedia: prime number theorem](https://en.wikipedia.org/wiki/Prime_number_theorem)

Replacing M with n(log(n)) + nlog(log(n)), gives `gen-next-prime` taking O(nlogn) time.

`gen-next-prime` must be ran n times, therefore the time complexity of gen-primes is O(n^2 * logn)

Multiplying is only n^2, therefore, the entire program runs in O(n^2*logn)
