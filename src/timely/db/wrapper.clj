(ns timely.db.wrapper
  (:require [datomic.api :as d]
            [timely.db.schema :as db-schema]))


(defn connect
  "Connects to database. Returns the connection"
  [{:keys [database]}]
  (let [uri (:db-uri database)]
    (d/create-database uri)
    (d/connect uri)))

(defn transact-schemas
  "Transacts schemas. Requires a connection"
  [conn]
  (d/transact conn db-schema/user-schema)
  (d/transact conn db-schema/work-period-schema))

(defn- user-query
  [conn {:keys [name password email]}]
  (d/q '[:find ?e
         :in $ ?name ?password ?email
         :where
         [?e :user/name ?name]
         [?e :user/password ?password]
         [?e :user/email ?email]] (d/db conn) name password email))

(defn is-user-created?
  [conn user]
  (let [result (user-query conn user)]
    (not (empty? result))))

(defn get-user
  [conn user]
  (let [result (user-query conn user)]
    (first (first result))))

(defn upsert-user
  "Inserts a single user in the database"
  [conn {:keys [name password email]}]
  (d/transact conn [{:user/name name
                     :user/password password
                     :user/email email}]))

(defn working-period-started?
  "Checks if a working period is currently started on for a given user
  and returns its uuid if so"
  [conn {:keys [user]}]
  (let [result (d/q '[:find ?e
                      :in $ ?user
                      :where
                      [?e :period/user ?user]
                      [?e :period/start]
                      (not [?e :period/end])] (d/db conn) user)]
    (not (empty? result))))

(defn start-working-period!
  "Creates a started working period for the given user"
  [conn {:keys [user time]}]
  (d/transact conn [{:period/start time
                     :period/user user}]))

(defn get-unfinished-working-periods
  [conn {:keys [user]}]
  (d/q '[:find ?e ?date
         :in $ ?user
         :where
         [?e :period/user ?user]
         [?e :period/start ?date]
         (not [?e :period/end])] (d/db conn) user))

(defn get-last-working-period
  [conn user]
  (let [result (get-unfinished-working-periods conn user)]
    (cond #(> 1 (count result))
          (throw (Exception. "There are more than one unfinished working periods!")))
    (apply max-key #(.getTime (second %)) result)))

(defn end-working-period!
  "Ends a given working period for the given user"
  [conn {:keys [user working-period time]}]
  (d/transact conn [{:period/user user
                     :period/uuid working-period
                     :period/end time}]))
