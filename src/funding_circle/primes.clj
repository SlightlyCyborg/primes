(ns funding-circle.primes)

(defn new-prime-cache []
  (atom (sorted-set 2)))

(defn primes-<= [last-prime primes]
  (subseq primes <= last-prime))

(defn divides-evenly?
  "returns true only if dividend is divided evenly by divisor"
  [dividend divisor]
  (= 0 (mod dividend divisor)))

(defn divides-evenly-by-any?
  "returns true if dividend is divided evenly by any of the divisors"
  [dividend divisors]
  (reduce #(or %1 (divides-evenly? dividend %2)) 
          false
          divisors))

(defn get-next-prime
  "returns next prime based on last prime and prime cache"
  [last-prime prime-cache-atom]
  (when (not (contains? @prime-cache-atom last-prime))
    (throw (Exception. "Last prime hasn't been generated yet")))

  (loop [next-prime (+ 1 last-prime)]
    (if (divides-evenly-by-any? next-prime (primes-<= last-prime
                                                      @prime-cache-atom))
        (recur (+ 1 next-prime))
        (do
          (swap! prime-cache-atom conj next-prime)
          next-prime))))

(defn gen-primes 
  "returns a lazy seq of primes"
  ([]
   (let [prime-cache (new-prime-cache)]
     (cons 2 (gen-primes 2 prime-cache))))
  ([last-prime prime-cache]
   (let [this-prime (get-next-prime last-prime prime-cache)]
     (lazy-seq (cons this-prime (gen-primes this-prime prime-cache))))))

