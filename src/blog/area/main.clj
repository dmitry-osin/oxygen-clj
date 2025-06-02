(ns blog.area.main
  (:require
    [compojure.core :refer [GET]]
    [hiccup2.core :refer [html]]
    ))

(defn index []
  (GET "/" [] (str (html [:h1 "My blog"])))
  )

(defn contact []
  (GET "/contact" [] (str (html [:h1 "Contact me"])))
  )

(defn about []
  (GET "/about" [] (str (html [:h1 "About me"])))
  )

(defn login []
  (GET "/login" [] (str (html [:h1 "Login"])))
  )