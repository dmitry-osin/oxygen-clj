(ns blog.area.main
  (:require
   [compojure.core :refer [GET]]
   [hiccup2.core :refer [html]]))

(defn index []
  (GET "/" [] (str (html [:html
                          [:head [:title "My blog"]]
                          [:body [:h1 "My blog"]]]))))

(defn contact []
  (GET "/contact" [] (str (html [:html
                                 [:head [:title "Contact me"]]
                                 [:body [:h1 "Contact me"]]]))))

(defn about []
  (GET "/about" [] (str (html [:html
                               [:head [:title "About me"]]
                               [:body [:h1 "About me"]]]))))

(defn login []
  (GET "/login" [] (str (html [:html
                               [:head [:title "Login"]]
                               [:body [:h1 "Login"]]]))))