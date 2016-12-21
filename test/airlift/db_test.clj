(ns airlift.db-test
  (:require [clojure.test :refer :all]
            [airlift.db :as db]))

(deftest config-test
  (testing "returns correct config"
    (is (= {"user" "Alex" "password" "password"}
           (#'airlift.db/config "Alex" "password")))))
