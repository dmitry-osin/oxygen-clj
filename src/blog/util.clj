(ns blog.util)

(defn get-env [name & {:keys [default]}]
  (or (System/getenv name) default))
