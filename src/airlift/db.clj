(ns airlift.db
  (:require [clojure.java.jdbc :as sql])
  (:import [com.microsoft.sqlserver.jdbc.SQLServerDriver]))

(def ^:private driver (com.microsoft.sqlserver.jdbc.SQLServerDriver.))

(defn- config [username password]
  "returns java.util.Properties instance"
  (let [c (java.util.Properties.)]
    (.setProperty c "user" username)
    (.setProperty c "password" password)
    c))

(defn- extract-columns [metadata]
  "extracts table columns columns"
  (for [index (range 1 (.getColumnCount metadata))]
    (.getColumnName metadata index)))

(defn- connect [db dbname config]
  (.connect db (str "jdbc:sqlserver://127.0.0.1:1433;databaseName=" dbname) config))

(defn init [{:keys [name username password]}]
  "Returns new database connection"
  (connect driver name (config username password)))

(defn query [conn query]
  "Executes query on db connection and returns ResultSet"
  (resultset-seq (.. conn (createStatement) (executeQuery query))))

(defn columns [response]
  "Returns vector of table columns"
  (->> (.getMetaData response)
       (extract-columns)))
