(ns funding-circle.primes-table
  (:use [funding-circle.primes :only [gen-primes]])
  (:gen-class))

(defn mult-table [seq1 seq2]
  (map (fn [x] (map (fn [y] (* x y)) seq2)) seq1))

(defn print-corner [max-digits]
  (print (str (apply str (repeat (- max-digits 1) " ")) "*|")))

(defn print-col-labels [col-labels max-digits]
    (doall (map #(printf (str "%" max-digits "d|") %) col-labels)))

(defn print-row [row row-label max-digits]
  (print "\n")
  (printf (str "%" max-digits "d|") row-label)
  (doall (map (fn [col] (printf (str "%" max-digits "d|") col)) row)))

(defn print-table [table row-labels col-labels]
  (let [max-val (reduce max (flatten table))
        max-digits (count (str max-val))
        row-length (* (+ 1 max-digits) (+ 1 (count col-labels)))]
    (print-corner max-digits)
    (print-col-labels row-labels max-digits)
    (doall (map (fn [row row-label] (print-row row row-label max-digits))
                table row-labels))
    (print "\n")))

(defn print-n-primes-mult-table [n]
  (let [n-primes (take n (gen-primes))]
    (print-table
     (mult-table n-primes n-primes)
     n-primes
     n-primes)))

(defn -main [& args]
  (cond 
    (= (count args) 1) (let [n (read-string (first args))]
                         (if (not (int? n))
                           (throw (Exception. "Arg must be int"))
                           (print-n-primes-mult-table n)))
  :else (print-n-primes-mult-table 10))
  (flush))
