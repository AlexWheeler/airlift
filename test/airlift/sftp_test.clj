(ns airlift.sftp-test
  (:require [clojure.test :refer :all]
            [clojure.spec :as s]
            [airlift.sftp :as sftp]))

(s/def ::sftp-config (s/keys :req-un [::username ::password ::host]))
(s/def ::db-config (s/keys :req-un [::username ::password ::address ::port ::name ::tables]))
(s/def ::config (s/keys :req-un [::sftp ::db]))
