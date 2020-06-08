(ns timely.db.test
  (:require [clojure.test :refer :all]
            [datomic.api :as d]
            [timely.db.wrapper :as db]))


(defn- create-database
  "Creates a new database every time it is called and returns the connection"
  []
  (let [database-uri (doto (str "datomic:mem://" (d/squuid))
                       d/create-database)
        conn (d/connect database-uri)]
    (db/transact-schemas conn)
    conn))

(deftest user-creation
  "Checks if user creation database function works"
   (testing "Successful creation"
     (let [user-data {:name "gustavo"
                      :password "123456"
                      :email "gustavo@mail.com"}
           conn (create-database)]
       (db/upsert-user conn user-data)
       (is (= true (db/is-user-created? conn user-data)))))
  (testing "Email should be unique, same email creation should fail"
    (let [user1-data {:name "gustavo"
                      :password "123456"
                      :email "gustavo@mail.com"}
          user2-data {:name "gustavo2"
                      :password "123456"
                      :email "gustavo@mail.com"}
          conn (create-database)]
         (db/upsert-user conn user1-data)
         (db/upsert-user conn user2-data)
         (is (= true (db/is-user-created? conn user1-data)))
         (is (= false (db/is-user-created? conn user2-data)))))
  (testing "name should be unique. Same name creation should fail"
    (let [user1-data {:name "gustavo"
                      :password "123456"
                      :email "gustavo@mail.com"}
          user2-data {:name "gustavo"
                      :password "123456"
                      :email "gustavo2@mail.com"}
          conn (create-database)]
      (db/upsert-user conn user1-data)
      (db/upsert-user conn user2-data)
      (is (= true (db/is-user-created? conn user1-data)))
      (is (= false (db/is-user-created? conn user2-data))))))


