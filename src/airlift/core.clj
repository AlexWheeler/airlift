(ns airlift.core
  (:gen-class)
  (:require [airlift.db :as db]
            [airlift.channel :as ch]
            [airlift.csv :as csv]))

(def config (clojure.edn/read-string (slurp "config.edn")))

(defn -main [& args]
  (let [conn (db/init (select-keys (:db config) [:name :username :password]))
        chan (ch/channel (:sftp config))]
    (doseq [table (get-in config [:db :tables])]
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
