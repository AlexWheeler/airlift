(ns airlift.csv
  (:require [clojure.data.csv :as csv]
            [clojure.java.io :as io]
            [clj-ssh.ssh :as ssh]
            ))

(defn write
  [data path]
  (with-open [out-file (io/writer path)]
    (csv/write-csv out-file data)))
