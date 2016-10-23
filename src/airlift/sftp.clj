(ns airlift.sftp
  (:require
    [clj-ssh.ssh :as ssh]))

(defn init-session [{:keys [username password host]}]
  "returns session given map with keys username, password, host"
  (ssh/session (ssh/ssh-agent {}) host {:username username :password password :strict-host-key-checking :no}))

(defn put [session in-file out-file]
  "Given session, in-file path and out-file path uploads file"
  (ssh/with-connection session
    (let [channel (ssh/ssh-sftp session)]
      (ssh/with-channel-connection channel
        (ssh/sftp channel {} :put in-file out-file)))))
