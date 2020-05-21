(ns timely.db.test
  (:require [clojure.test :refer :all]
            [datomic.api :as d]))

(defn- uuid [] (str (java.util.UUID/randomUUID)))

(def db-config {:server-type :peer-server
                :access-key "myaccesskey"
                :secret "mysecret"
                :endpoint "localhost:10051"
                :validate-hostnames false
                :db-name (uuid)})

(defn db-connect []
 (let [db-uri (doto (str "datomic:mem://" (:db-name db-config)))]
   (d/create-database db-uri)
   (d/connect db-uri)))
