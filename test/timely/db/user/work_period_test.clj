(ns timely.db.user.work-period-test
  (:require [clojure.test :refer :all]
            [java-time :as jtime]
            [timely.db.wrapper :as db]
            [timely.core-test :as test]
            [timely.test-data :as data]))

(defn- now [] (jtime/instant))

(deftest work-period-start
  (testing "Tests if a work period is started after function call"
    (let [conn (test/create-database)
          user-transact-result (db/upsert-user conn data/user-data)
          user-id (db/get-user conn data/user-data)]
      (db/start-working-period! conn {:user user-id
                                      :time (now)})
      (is (= true (db/working-period-started? conn {:user user-id}))))))

(deftest work-period-finish
  (testing "Tests if a finished period is not detected by function"
    (let [conn (test/create-database)
          user-transact-result (db/upsert-user conn data/user-data)
          user-id (db/get-user conn data/user-data)]
      (db/start-working-period! conn {:user user-id
                                      :time (now)})
      (db/end-working-period! conn {:user user-id
                                    :time (now)})
      (is (= false (db/working-period-started? conn {:user user-id}))))))

