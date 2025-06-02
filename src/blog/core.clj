(ns blog.core
  (:require [hiccup2.core :refer [html]]
            [ring.adapter.jetty :as ring]))

(defn- home [request]
  {:status  200
   :headers {"Content-Type" "text/html"}
   :body    (str (html [:h1 "My blog"]))
   })

(defn- about [request]
  {:status  200
   :headers {"Content-Type" "text/html"}
   :body    (str (html [:h1 "About me"]))
   })

(defn- contact [request]
  {:status  200
   :headers {"Content-Type" "text/html"}
   :body    (str (html [:h1 "Contact me"]))})

(defn dispatch [request]
  (case (:uri request)
    "/" (home request)
    "/about" (about request)
    "/contact" (contact request)
    {:status  404
     :headers {"Content-Type" "text/html"}
     :body    (str (html [:h1 "404 Not Found"]))}))

(comment
  (def server (ring/run-jetty #'dispatch {:port 3000 :join? false}))
  (.stop server)
  (.start server)
  )
