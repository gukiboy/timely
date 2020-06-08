(ns timely.core-test
  (:require [clojure.test :refer :all]
            [timely.core :refer :all]
            [io.pedestal.http.route :as route]))


(deftest test-routing
  (testing
    (prn (route/try-routing-for timely.core/routes :prefix-tree "/greet" :get))
    (is (not (nil? (route/try-routing-for timely.core/routes
                                          :prefix-tree "/greet" :get))))))

(deftest test-hello-world
  (testing
    (is (= {:body "Hello World"
            :code 200}) (answer-hello {}))))


