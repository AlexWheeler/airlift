(ns airlift.core
  (:gen-class)
  (:require [airlift.db :as db]))

(defn export-tables
  []
  (let [config (read-string (slurp "config.edn"))]
    (println config)))

(defn -main [& args]
  (println (db/query "SELECT * Table")))
