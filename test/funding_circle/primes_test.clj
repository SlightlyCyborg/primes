(ns funding-circle.primes-test
  (:use [clojure.test])
  (:require [funding-circle.primes :as primes]))


(deftest test-divides-evenly?
  (is (primes/divides-evenly? 6 3))
  (is (not (primes/divides-evenly? 6 5))))


(deftest test-divides-evenly-by-any?
  (is (primes/divides-evenly-by-any? 3 [1 2 3]))
  (is (not (primes/divides-evenly-by-any? 3 [6 4 5]))))


(deftest test-get-next-prime
  (let [prime-cache (atom (sorted-set 2))]
   (is (= 3 (primes/get-next-prime 2 prime-cache)))
   (is (= 5 (primes/get-next-prime 3 prime-cache)))
   (is (= 7 (primes/get-next-prime 5 prime-cache)))
   ;;If all previous primes hav not been gen'd yet, an exception will be thrown
   (is (thrown? Exception (primes/get-next-prime 19 prime-cache)))))


(deftest test-gen-next-prime
  (is (= (take 10 (primes/gen-primes)) '(2 3 5 7 11 13 17 19 23 29))))
