(ns airlift.db
  (:require [clojure.java.jdbc :as sql])
  (:import [com.microsoft.sqlserver.jdbc.SQLServerDriver]))

(def db (com.microsoft.sqlserver.jdbc.SQLServerDriver.))

(defn config [username password]
  "returns correct java.util.Properties instance"
  (let [c (java.util.Properties.)]
    (.setProperty c "user" username)
    (.setProperty c "password" password)
    c))

(defn connect [db host port dbname config]
  (.connect db (str "jdbc:sqlserver://" host ":" port ";databaseName=" dbname) config))

(defn query [query]
  (let  [connection (connect db "127.0.0.1" "1433" "db_name" (config "username" "password"))]
    (.. connection (createStatement) (executeQuery query))))

