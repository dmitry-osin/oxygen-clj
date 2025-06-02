(ns blog.domain.tag
  (:require [blog.adapter.db :refer [ds]]
            [next.jdbc :as jdbc])
  (:import (java.time OffsetDateTime)))

(defn create-tag [tag]
  (jdbc/with-transaction [tx ds]
                         (jdbc/execute! ["INSERT INTO tags (title, description, url, created_at)
                                         VALUES ()"
                                         (:title tag)
                                         (:description tag)
                                         (:url tag)
                                         (OffsetDateTime/now)])))

(defn find-tag-by-id [id]
  (jdbc/execute! ds ["SELECT FROM tags WHERE id = ?" id]))
