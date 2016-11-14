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

;;(defn -main [& args]
;;  (let [chan (ch/channel creds)]
;;    (ch/upload chan "hello.txt")))

(defn -main [& args]
  (let [conn (db/init "" "" "")
        chan (ch/channel creds)]
    (doseq [table []]
      (let [res (db/query conn (str "SELECT * FROM " table))]
        (let [columns (db/columns res) data (atom [])]
          (while (.next res)
            (swap! data conj (map #(.getString res %) columns)))
          (csv/write @data (str table ".csv"))
          (ch/upload chan (str table ".csv")))))))
