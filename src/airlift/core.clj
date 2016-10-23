(ns airlift.core
  (:gen-class))

(defn export-tables
  []
  (let [config (read-string (slurp "config.edn"))]
    (println config)))

(defn -main [& args]
  (export-tables))
