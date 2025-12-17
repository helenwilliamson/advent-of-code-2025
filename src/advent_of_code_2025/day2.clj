(ns advent-of-code-2025.day-2
  (:gen-class)
  (:require [advent-of-code-2025.utils :as utils]
            [clojure.string :as string]))

(defn parse-data
  [name]
  (->> (-> (utils/read-input name)
          (first)
          (string/split #","))
       (map (fn [line] (let [[start end] (->> (string/split line #"-")
                                   (map #(Long/parseLong %1)))]
                         (range start (inc end)))))))
(defn valid-id-multiple?
  ([id]
   (let [total-sizes (/ (count id) 2)
         sizes (range 1 (inc total-sizes))]
     (every? #(valid-id-multiple? id %1) sizes)))
  ([id size]
   (let [digits (apply str (take size id))
         number-to-take (/ (count id) size)
         potential-id (apply str (take number-to-take (repeat digits)))]
     (not= id potential-id))))

(defn valid-id?
  ([id]
   (if (odd? (count id))
     true
     (let [size (/ (count id) 2)
           digits (apply str (take size id))
           number-to-take (/ (count id) size)
           potential-id (apply str (take number-to-take (repeat digits)))]
       (not= id potential-id)))))

(defn part-1
  [name]
  (->> (parse-data name)
       (flatten)
       (map #(str %1))
       (filter #(not (valid-id? %1)))
       (map #(Long/parseLong %1))
       (reduce +)))

(defn part-2
  [name]
  (->> (parse-data name)
       (flatten)
       (map #(str %1))
       (filter #(not (valid-id-multiple? %1)))
       (map #(Long/parseLong %1))
       (reduce +)))
