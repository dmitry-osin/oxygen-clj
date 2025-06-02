(ns blog.domain.post
  (:require
   [blog.adapter.db :refer [ds]]
   [blog.domain.user :refer [find-user-by-login]]
   [next.jdbc :as jdbc])
  (:import (java.time OffsetDateTime)))

(defn create-post [post login]
  (jdbc/with-transaction [tx ds]
                         (let [user_id (find-user-by-login login)]
                           (jdbc/execute! tx ["INSERT INTO posts (title, url, description, content, created_at, published, user_id)
                                            VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)
                                            RETURNING (id)"
                                              (:title post)
                                              (:url post)
                                              (:description post)
                                              (:content post)
                                              (OffsetDateTime/now)
                                              (:published post)
                                              user_id]))))





