(ns blog.domain.db
  (:require [blog.util :refer [get-env]]
            [clojure.java.io :as io]
            [next.jdbc :as jdbc]))

(def db {:dbtype   (get-env "DB_TYPE" :default "postgresql")
         :host     (get-env "DB_HOST" :default "localhost")
         :port     (get-env "DB_PORT" :default "5432")
         :dbname   (get-env "DB_NAME" :default "oxygen")
         :user     (get-env "DB_USER" :default "oxygen")
         :password (get-env "DB_PASSWORD" :default "oxygen")})

(def ds (jdbc/get-datasource db))

(defn- execute-sql-script [name]
  (->> (io/resource name)
       (slurp)
       (vector)
       (jdbc/execute-one! ds))
  )

(defn initialize []
  (try
    (execute-sql-script "db.sql")
    (execute-sql-script "init-data.sql")
    (catch Exception e
      (println "Error initializing database:" (.getMessage e)))
    ))

(comment
  (initialize)
  )