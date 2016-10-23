(ns airlift.core
  (:gen-class)
  (:require [clojure.core.async :refer [chan <! >! go]]))

(defn foo
  []
  (let [config (read-string (slurp "config.edn"))]
    (println config)))

(defn -main [& args]
  (foo))
