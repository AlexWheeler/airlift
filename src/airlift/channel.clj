(ns airlift.channel
  (:require [airlift.sftp :as session])
  (:import
    [com.jcraft.jsch JSch]
    [java.io FileInputStream
             File]))

(defn channel [creds]
  (let [chan (.openChannel (session/session creds) "sftp")]
    (.connect chan)
    chan))

(defn upload [channel file_path]
  (let [file (File. file_path)]
    (.setWritable file true)
    (let [stream (FileInputStream. file)]
      (.put channel stream file_path)
      (.close stream))))
