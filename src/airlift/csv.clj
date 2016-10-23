(ns airlift.csv
  (:require [clojure.data.csv :as csv]
            [clojure.java.io :as io]
            ))

(defn write
  "given nxn matrix and path to file writes data to csv file"
  [data path]
  (with-open [out-file (io/writer path)]
    (csv/write-csv out-file data)))
