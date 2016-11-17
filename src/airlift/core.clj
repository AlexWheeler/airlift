(ns airlift.core
  (:gen-class)
  (:require [airlift.db :as db]
            [airlift.channel :as ch]
            [airlift.csv :as csv]))

(defn export-tables
  []
  (let [config (read-string (slurp "config.edn"))]
    (println config)))

(def creds {:host "" :username "" :password ""})

(defn -main [& args]
  (let [conn (db/init "" "" "")
        chan (ch/channel creds)]
    (doseq [table []]
      (let [data []  res (db/query conn (str "SELECT * FROM " table))]
        (let [headers (->> (first res)
                           (keys)
                           (map name)
                           (vec)
                           (conj data))
               body (->> (rest res)
                         (map (comp vec vals))
                         (vec)
                         (apply conj headers))]
        (csv/write body (str table ".csv"))
        (ch/upload chan (str table ".csv")))))))
