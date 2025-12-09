(ns advent-of-code-2025.day-1
  (:gen-class)
  (:require [advent-of-code-2025.utils :as utils]
            [clojure.string :as string]))

(defn parse-data
  [name]
  (->> (utils/read-input name)
       (map #(hash-map :direction (str (first %1)) :amount (Integer/parseInt (apply str (rest %1)))))))

(defn apply-instruction
  [{:keys [direction amount]} current-position]
  (let [operation (if (= direction "L") - +)]
    (mod (operation current-position amount) 100)))

(defn part-1
  [name]
  (->> (parse-data name)
       (reduce (fn [{:keys [pointed-at current-position]} instruction]
                 (let [updated-position (apply-instruction instruction current-position)
                       updated-pointed-at (if (= 0 updated-position) (inc pointed-at) pointed-at)]
                   {:pointed-at updated-pointed-at :current-position updated-position}))
               {:pointed-at 0 :current-position 50})
       (:pointed-at)))

(defn part-2
  [name]
  (->> (parse-data name)
       ))
