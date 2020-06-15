(ns timely.test-data
  (:require [clojure.test :refer :all]))

(def user-data {:name "gustavo"
                :password "123456"
                :email "gustavo@mail.com"})
(def user-data-change-name (assoc user-data :name "gustavo2"))
(def user-data-change-email (assoc user-data :email "gustavo2@mail.com"))
