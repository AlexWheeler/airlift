(ns airlift.sftp
  (:require
    [clj-ssh.ssh :as ssh])
  (:import
    [com.jcraft.jsch JSch]))

(defn config []
  (doto (java.util.Properties.)
        (.put "StrictHostKeyChecking" "no")))

(defn session [{:keys [host username password]}]
  (doto (.getSession (JSch.) username host 22)
        (.setPassword password)
        (.setConfig (config))
        (.connect)))
