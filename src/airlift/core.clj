(ns airlift.core
  (:gen-class)
  (:require [airlift.db :as db]))

(defn export-tables
  []
  (let [config (read-string (slurp "config.edn"))]
    (println config)))

(defn -main [& args]
  (let [conn (db/init "table_name" "username" "password")]
    (let [res (db/query conn "SELECT * FROM TABLE")]
      (let [columns (db/columns res)]
        (while (.next res)
          (println (map #(.getString res %) columns)))))))
