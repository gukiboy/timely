(ns timely.db.user.user_test
  (:require [clojure.test :refer :all]
            [timely.db.wrapper :as db]
            [timely.core-test :as test]
            [timely.test-data :as data]))


(deftest user-creation
  "Checks if user creation database function works"
  (testing "Successful creation"
    (let [user-data data/user-data
          conn (test/create-database)]
      (db/upsert-user conn user-data)
      (is (= true (db/is-user-created? conn user-data)))))

  (testing "Email should be unique, same email creation should fail"
    (let [user1-data data/user-data
          user2-data data/user-data-change-name
          conn (test/create-database)]
      (db/upsert-user conn user1-data)
      (db/upsert-user conn user2-data)
      (is (= true (db/is-user-created? conn user1-data)))
      (is (= false (db/is-user-created? conn user2-data)))))

  (testing "Name should be unique. Same name creation should fail"
    (let [user1-data data/user-data
          user2-data data/user-data-change-email
          conn (test/create-database)]
      (db/upsert-user conn user1-data)
      (db/upsert-user conn user2-data)
      (is (= true (db/is-user-created? conn user1-data)))
      (is (= false (db/is-user-created? conn user2-data))))))