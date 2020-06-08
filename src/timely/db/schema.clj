(ns timely.db.schema)


(def user-schema [{:db/ident :user/uuid
                   :db/valueType :db.type/uuid
                   :db/cardinality :db.cardinality/one
                   :db/doc "UUID of the user"}

                  {:db/ident :user/name
                   :db/valueType :db.type/string
                   :db/unique :db.unique/value
                   :db/cardinality :db.cardinality/one
                   :db/doc "Name of the user"}

                  {:db/ident :user/password
                   :db/valueType :db.type/string
                   :db/cardinality :db.cardinality/one
                   :db/doc "Hashed user password"}

                  {:db/ident :user/email
                   :db/valueType :db.type/string
                   :db/unique :db.unique/value
                   :db/cardinality :db.cardinality/one
                   :db/doc "Email of the user"}])

(def work-period-schema [{:db/ident :period/uuid
                          :db/valueType :db.type/uuid
                          :db/cardinality :db.cardinality/one
                          :db/doc "Period UUID"}

                         {:db/ident :period/user
                          :db/valueType :db.type/ref
                          :db/cardinality :db.cardinality/many
                          :db/doc "User that owns this period"}

                         {:db/ident :period/start
                          :db/valueType :db.type/instant
                          :db/cardinality :db.cardinality/one
                          :db/doc "Period start"}

                         {:db/ident :period/end
                          :db/valueType :db.type/instant
                          :db/cardinality :db.cardinality/one
                          :db/doc "Period end"}])





