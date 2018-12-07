(ns funding-circle.primes-table-test
  (:use [clojure.test :as test])
  (:require [funding-circle.primes-table :as table]))

(deftest print-n-primes-mult-table-test
  (is (= (with-out-str (table/print-n-primes-mult-table 10))
         (slurp "expected.table"))))
